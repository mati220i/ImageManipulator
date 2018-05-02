package pl.imageManipulator.algorithm;


import java.util.Random;

public class Mask {
	// Predefined mask
	private int[] averageFilter = {
			1, 1, 1, 1, 1,
			1, 1, 1, 1, 1,
			1, 1, 1, 1, 1,
			1, 1, 1, 1, 1,
			1, 1, 1, 1, 1
		};
	
	// Predefined mask
	private int[] hp3Filter = {
				-1, 0, 0, 0, 0,
				0, -1, 0, 0, 0,
				0, 0, 1, 0, 0,
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0
			};
	
	// Predefined mask
	private int[] verticalSobelFilter = {
			2, 1, 0, -1, -2,
			3, 2, 0, -2, -3,
			4, 3, 0, -3, -4,
			3, 2, 0, -2, -3,
			2, 1, 0, -1, -2
		};
	
	// User mask
	private int[] userMask = {
			1, 1, 1, 1, 1,
			1, 1, 1, 1, 1,
			1, 1, 1, 1, 1,
			1, 1, 1, 1, 1,
			1, 1, 1, 1, 1
		};
        
	private int[] getRandomMask() {
		Random rand = new Random();
		final int maskSize = 5;
		
		int[] randomMask = new int[maskSize * maskSize];
		
		for(int i=0; i<maskSize; i++) {
			randomMask[i] = rand.nextInt(30);
		}
		
		return randomMask;
	}
	
	public void setUserMask(int[] data) {
		final int maskSize = 5;
        int[] mask = new int[maskSize * maskSize];
        
        for(int i=0; i<maskSize; i++) {
			mask[i] = data[i];
		}
	}
	
	public int[] getMask(Type type) {
		if(type == Type.AVERAGING)
			return this.averageFilter;
		if(type == Type.HP3)
			return this.hp3Filter;
		if(type == Type.VERTICAL_SOBEL)
			return this.verticalSobelFilter;
		if(type == Type.RANDOM)
			return this.getRandomMask();
        if(type == Type.USER)
			return this.userMask;
		
		return null;
	}

}