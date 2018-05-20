package pl.imageManipulator.algorithm;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Algorithm for image processing
 * 
 * A simple image processing tool. There are several predefined filters to choose from, a wand with adjustable power, 
 * an algorithm for changing to shades of gray. 
 * The algorithm has user-friendly methods and several constructors to choose from.
 * 
 * Use:
 * Eg. alg.filter(img, Type.AVERAGING);
 * 
 * @author Mateusz Œliwa <mateuszsliwa7@wp.pl>
 *
 */
public class Algorithm {

	private BufferedImage image, newImage;
	private final int maskSize = 5;
	private int margin = (maskSize-1)/2;
	private int[] mask;
	private Wand wand;
	
	
	
	//--------------------- Constructors--------------------------
	
	/**
	 * Default constructor
	 */
	public Algorithm() {
		this.mask = new Mask().getMask(Type.RANDOM);
		this.wand = new Wand();
	}
	
	/**
	 * 
	 * @param image initial image
	 */
	public Algorithm(BufferedImage image) {
		this.image = image;
		this.newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		this.mask = new Mask().getMask(Type.RANDOM);
		this.wand = new Wand();
	}
	
	/**
	 * 
	 * @param image initial image
	 * @param mask initial mask
	 */
	public Algorithm(BufferedImage image, Type mask) {
		this.image = image;
		this.newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		this.mask = new Mask().getMask(mask);
		this.wand = new Wand();
	}
	
	/**
	 * 
	 * @param path image path
	 * @throws IOException
	 */
	public Algorithm(String path) throws IOException {
		File file = new File(path);
		
		this.image = ImageIO.read(file);
		this.newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		this.mask = new Mask().getMask(Type.RANDOM);
		this.wand = new Wand();
	}
	
	/**
	 * 
	 * @param path image path
	 * @param mask initial mask
	 * @throws IOException
	 */
	public Algorithm(String path, Type mask) throws IOException {
		File file = new File(path);
		
		this.image = ImageIO.read(file);
		this.newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		this.mask = new Mask().getMask(mask);
		this.wand = new Wand();
	}
	
	//-----------------------------------------------------------------------------
	
	/**
	 * Load the image into algorithm
	 * @param image
	 */
	public void chooseImage(BufferedImage image) {
		this.image = image;
	}
	
	/**
	 * Load the image into algorithm
	 * @param path image path
	 * @throws IOException
	 */
	public void chooseImage(String path) throws IOException {
		File file = new File(path);
		this.image = ImageIO.read(file);
	}
	
	/**
	 * Chooses mask
	 * @param mask
	 */
	public void chooseMask(Type mask) {
		this.mask = new Mask().getMask(mask);
	}
	
	/**
	 * Sets mask from int[] varieble
	 * @param mask
	 * @throws Exception
	 */
	public void setUserMask(int[] mask) throws Exception {
		if(mask.length != 25)
			throw new Exception("The mask is not compatible with the mask length of 5x5 (size = 25)");
		else
			this.mask = mask;
	}
	
	/**
	 * Turns on the mask
	 */
	public void turnOnWand() {
		this.wand.setActive(true);
	}
	
	/**
	 * Turns off the mask
	 */
	public void turnOffWand() {
		this.wand.setActive(false);
	}
	
	/**
	 * Sets the wand position
	 * 
	 * @param x
	 * @param y
	 */
	public void setWandPosition(int x, int y) {
		this.wand.setX(x);
		this.wand.setY(y);
	}
	
	/**
	 * Increase wand power by 5
	 */
	public void increaseWandPower() {
		this.wand.increasePower();
	}
	
	/**
	 * Decrease wand power by 5
	 */
	public void decreaseWandPower() {
		this.wand.decreasePower();
	}
	
	/**
	 * Sets wand power
	 * 
	 * @param power
	 * @throws Exception
	 */
	public void setWandPower(int power) throws Exception {
		if(power >= 0 && power <=255)
			this.wand.setPower(power);
		else
			throw new Exception("The set wand power is less than 0 or greater than 255. Range <0, 255>");
	}
		
	/**
	 * Gets the image after filtration
	 * 
	 * @return image after filtratiin
	 */
	public BufferedImage getImageAfterFiltration() {
		return newImage;
	}
	
	/**
	 * A simple function to check if the image is colored
	 * 
	 * @param image
	 * @return true if image is colored
	 */
	boolean isColored(BufferedImage image) {
		WritableRaster raster = image.getRaster();
		int[] pixels = new int[3];
		
		raster.getPixel(0, 0, pixels);
				
		if((pixels[0] != pixels[1]) && (pixels[1] != pixels[2]))
			return true;
		else
			return false;
	}

	/**
	 * Filters the image that has been loaded into the class
	 * @return image after filtration
	 */
	public BufferedImage filter() {
		this.newImage = filtrationAlgorithm(this.image);
		return this.newImage;		
	}
	
	/**
	 * Filters the image that was given in the argument
	 * @param image
	 * @return image after filtration
	 */
	public BufferedImage filter(BufferedImage image) {
		this.newImage = filtrationAlgorithm(image);
		return this.newImage;
	}
	
	/**
	 * Filters an image that has been loaded into the class with the chosen filtering method
	 * @param mask
	 * @return image after filtration
	 */
	public BufferedImage filter(Type mask) {
		this.mask = new Mask().getMask(mask);
		
		this.newImage = filtrationAlgorithm(this.image);
		return this.newImage;		
	}
	
	/**
	 * Filters the image that was given in the argument selected by the filtering method
	 * @param image
	 * @param mask
	 * @return image after filtration
	 */
	public BufferedImage filter(BufferedImage image, Type mask) {
		this.mask = new Mask().getMask(mask);
		
		this.newImage = filtrationAlgorithm(image);
		return this.newImage;
	}
	
	/**
	 * Function that checks whether the image is in shades of gray and transmits it to the appropriate filtering function
	 * @param image
	 * @return image after filtration
	 */
	private BufferedImage filtrationAlgorithm(BufferedImage image) {
		if(this.isColored(image)){
			return this.filtrationAlgorithmColor(image);
		}
		else
			return this.filtrationAlgorithmGrayScale(image);
	}
	
	/**
	 * An algorithm that filters images in shades of gray
	 * @param image
	 * @return image after filtration
	 */
	private BufferedImage filtrationAlgorithmGrayScale(BufferedImage image) {
		BufferedImage afterEditing = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		WritableRaster raster = image.getRaster();
		WritableRaster newImageRaster = afterEditing.getRaster();
		
		int height = raster.getHeight();
		int width = raster.getWidth();
		
		
		int sumOfFilter = 0;
		for (int i=0; i<mask.length; i++)
			sumOfFilter += mask[i];
		if (sumOfFilter == 0) sumOfFilter = 1;
		
		int pixels[] = new int[3];
		
		for(int i=margin; i<width-margin; i++){
			for(int j=margin; j<height-margin; j++){
				int sum = 0;
				
				int wandPixels[] = new int[3];
				raster.getPixel(wand.getX(), wand.getY(), wandPixels);
				
				raster.getPixel(i, j, pixels);
				
				for(int k=0; k<maskSize; k++){
					for(int l=0; l<maskSize; l++){
						raster.getPixel(i+k-margin, j+l-margin, pixels);
						
						sum += mask[k*maskSize+l] * pixels[0];
					}
				}
				
				if(wand.isActive()) {
					if (pixels[0] > wandPixels[0] - wand.getPower()	&& pixels[0] < wandPixels[0] + wand.getPower()) {
						sum /= sumOfFilter;
				
						if (sum > 255) 
							sum = 255;
						else if (sum < 0) 
							sum = 0;
						
						pixels[0] = sum;
						pixels[1] = sum;
						pixels[2] = sum;
					}
				} else {
					sum /= sumOfFilter;
					
					if (sum > 255) 
						sum = 255;
					else if (sum < 0) 
						sum = 0;
					
					pixels[0] = sum;
					pixels[1] = sum;
					pixels[2] = sum;
				}
					
				newImageRaster.setPixel(i, j, pixels);
			}
		}
		afterEditing.setData(newImageRaster);
		
		return afterEditing;
	}	
	
	/**
	 * Algorithm for filtering colored images
	 * @param image
	 * @return image after filtration
	 */
	private BufferedImage filtrationAlgorithmColor(BufferedImage image) {
		BufferedImage afterEditing = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		WritableRaster raster = image.getRaster();
		WritableRaster newImageRaster = afterEditing.getRaster();
		
		int height = raster.getHeight();
		int width = raster.getWidth();
		
		
		int sumOfFilter = 0;
		for (int i=0; i<mask.length; i++)
			sumOfFilter += mask[i];
		if (sumOfFilter == 0) sumOfFilter = 1;
		
		int pixels[] = new int[3];
		
		for(int i=margin; i<width-margin; i++){
			for(int j=margin; j<height-margin; j++){
				int sumR = 0;
				int sumG = 0;
				int sumB = 0;
				
				int wandPixels[] = new int[3];
				raster.getPixel(wand.getX(), wand.getY(), wandPixels);
				
				raster.getPixel(i, j, pixels);
				
				
				for(int k=0; k<maskSize; k++){
					for(int l=0; l<maskSize; l++){
						raster.getPixel(i+k-margin, j+l-margin, pixels);
						
						sumR += mask[k*maskSize+l] * pixels[0];
						sumG += mask[k*maskSize+l] * pixels[1];
						sumB += mask[k*maskSize+l] * pixels[2];
					}
				}
				
				if(wand.isActive()) {
					if (pixels[0] > wandPixels[0] - wand.getPower()	&& pixels[0] < wandPixels[0] + wand.getPower()) {
						sumR /= sumOfFilter;
						sumG /= sumOfFilter;
						sumB /= sumOfFilter;
						
						if (sumR > 255) 
							sumR = 255;
						else if (sumR < 0) 
							sumR = 0;
						if (sumG > 255) 
							sumG = 255;
						else if (sumG < 0) 
							sumG = 0;
						if (sumB > 255) 
							sumB = 255;
						else if (sumB < 0) 
							sumB = 0;
						
						pixels[0] = sumR;
						pixels[1] = sumG;
						pixels[2] = sumB;
						
					}
				} else {
					sumR /= sumOfFilter;
					sumG /= sumOfFilter;
					sumB /= sumOfFilter;
					
					if (sumR > 255) 
						sumR = 255;
					else if (sumR < 0) 
						sumR = 0;
					if (sumG > 255) 
						sumG = 255;
					else if (sumG < 0) 
						sumG = 0;
					if (sumB > 255) 
						sumB = 255;
					else if (sumB < 0) 
						sumB = 0;
					
					pixels[0] = sumR;
					pixels[1] = sumG;
					pixels[2] = sumB;
					
				}
				
				
				newImageRaster.setPixel(i, j, pixels);
			}
		}
		afterEditing.setData(newImageRaster);
		
		return afterEditing;
	}
	
	/**
	 * Converts a color image into an image in shades of gray
	 * @param image
	 * @return image after filtration
	 */
	public BufferedImage shadesOfGray(BufferedImage image){
		WritableRaster raster = image.getRaster();
		
		int pixels[] = new int[3];
		
		double shades[] = new double[3];
		int h = raster.getHeight();
		int w = raster.getWidth();
		
		for(int i=0; i<w; i++){
			for(int j=0; j<h; j++){
	            raster.getPixel(i, j, pixels);
	            
	            shades[0] = 0.299 * pixels[0] + 0.587 * pixels[1] + 0.114 * pixels[2];
	            shades[1] = 0.299 * pixels[0] + 0.587 * pixels[1] + 0.114 * pixels[2];
	            shades[2] = 0.299 * pixels[0] + 0.587 * pixels[1] + 0.114 * pixels[2];
	            
	            raster.setPixel(i, j, shades);
			}
		}
		image.setData(raster);
        
		return image;
	}
	
	public int getWandPower() {
		return this.wand.getPower();
	}
	
}
