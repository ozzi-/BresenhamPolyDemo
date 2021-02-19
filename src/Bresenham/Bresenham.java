package Bresenham;
import java.awt.Color;
import java.util.ArrayList;

import Forms.Point;

public class Bresenham {

	public static ArrayList<Point> calculateLine(Point from, Point to, Color color) {
		
		ArrayList<Point> linePixels = new ArrayList<Point>();
		linePixels.add(new Point(from.x, from.y, color));
		if (from.x == to.x && from.y == to.y) {
			return linePixels;
		}

		int deltaX = to.x - from.x;
		int deltaY = to.y - from.y;

		int absDeltaX = Math.abs(deltaX);
		int absDeltaY = Math.abs(deltaY);

		int growthX = deltaX > 0 ? 1 : (deltaX < 0 ? -1 : 0);
		int growthY = deltaY > 0 ? 1 : (deltaY < 0 ? -1 : 0);

		int deltaFast = absDeltaX > absDeltaY ? absDeltaX : absDeltaY;
		int deltaSlow = absDeltaX < absDeltaY ? absDeltaX : absDeltaY;
		float error = deltaFast / 2;
		int posx = from.x;
		int posy = from.y;

		int fastGrowthX = 0;
		int fastGrowthY = 0;
		if (deltaFast == absDeltaX) {
			fastGrowthX = growthX;
		} else {
			fastGrowthY = growthY;
		}

		for (int i = 0; i < deltaFast; i++) {
			error = error - deltaSlow;
			float errorP = error;
			if (error < 0) {
				// we move on both axis
				error = error + deltaFast;
				posx = posx + growthX;
				posy = posy + growthY;
			} else {
				// we only move on the faster growing axis
				posx = posx + fastGrowthX;
				posy = posy + fastGrowthY;
			}

			linePixels.add(new Point(posx, posy, color, deltaX, deltaY, absDeltaX, absDeltaY, growthX,growthY, error, errorP));
		}
		linePixels.add(new Point(to.x, to.y, color));

		return linePixels;
	}
}
