package Forms;
import java.awt.Color;

// Yes this is bloated, but used to show the internal values at each bresenham iteration
public class Point {
	public int x;
	public int y;
	public Color color;
	public int deltaX;
	public int deltaY;
	public int absDeltaX;
	public int absDeltaY;
	public int growthX;
	public int growthY;
	public float error;
	public float errorP;
    
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		this.color = Color.black;
	}

	public Point(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public Point(int x, int y, Color color, int deltaX, int deltaY, int absDeltaX, int absDeltaY, int growthX, int growthY, float error, float errorP) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
		this.absDeltaX = absDeltaX;
		this.absDeltaY = absDeltaY;
		this.growthX = growthX;
		this.growthY = growthY;
		this.error = error;
		this.errorP = errorP;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Point point = (Point) obj;
		return point.x == x && point.y == y;
	}

	// bijective - https://www.cs.upc.edu/~alvarez/calculabilitat/enumerabilitat.pdf
	public int hashCode() {
		int tmp = (y + ((x + 1) / 2));
		return x + (tmp * tmp);
	}
}
