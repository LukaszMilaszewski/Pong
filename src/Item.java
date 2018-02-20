

import java.util.Random;

public class Item {
	static int x;
	static int y;
	static int length = 40;
	static boolean isVisible = false;
	
	static Random random = new Random();
	
	static void generateItem(int width, int height) {
	//	int randomNumber = random.nextInt(max + 1 - min) + min;
		x = random.nextInt(width - 240 + 1 - 200) + 200;
		y = random.nextInt(height - 41 + 1 - 1) + 1;
		isVisible = true;
	}
}
