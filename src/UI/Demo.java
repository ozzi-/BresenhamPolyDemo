package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Bresenham.Bresenham;
import Forms.Line;
import Forms.Point;

public class Demo {
	
	static int zoom = 10;
	private static int renderedPixels;
	public static int step = 0;
	public static Line p;
	public static boolean startSet= false;
	public static boolean endSet = false;
	public static Point start;
	public static Point end;
	
	public static void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		cls(g2d);

		long startTime=0;
		long startCTime=0;
		
		startCTime= System.nanoTime();
		ArrayList<Point> linePixels = null;
		if(startSet && endSet) {
			linePixels = Bresenham.calculateLine(p.from, p.to, Color.black);			
		}
		startTime= System.nanoTime();
		renderGrid(g2d);
		renderStartAndEnd(g2d);
		renderBresenhamLine(g2d, linePixels);
		
		long elapsedTime = System.nanoTime() - startTime;
		long elapsedCTime = System.nanoTime() - startCTime;
		
		g2d.setColor(Color.black);
		g2d.drawString("Calculation time = "+elapsedCTime/1000+" μs - Render time = " + elapsedTime/1000 + " μs - drawn " + renderedPixels + " pixels", 10, 450);		
	}

	private static void cls(Graphics2D g2d) {
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, Canvas.winx, Canvas.winy);
	}
	
	public static void reset() {
		startSet=false;
		endSet=false;
		step = 0;
	}
	
	private static void renderBresenhamLine(Graphics2D g2d, ArrayList<Point> linePixels) {
		if(startSet && endSet) {
	        java.util.Iterator<Point> iterator = linePixels.iterator(); 
			g2d.setColor(Color.red);
			g2d.fillRect(p.to.x* zoom, p.to.y*zoom,zoom,zoom);
			
	        Point p = new Point(0,0);
			for (int i = 0; i < step && i < linePixels.size() && i > -1; i++) {
				p = iterator.next();
				g2d.setColor(p.color);
				g2d.fillRect(p.x*zoom, p.y*zoom,zoom,zoom);
				
			}
			g2d.setColor(Color.black);
			renderStepVars(g2d, p);
			
			renderedPixels = linePixels.size();
		}
	}

	private static void renderStepVars(Graphics2D g2d, Point p) {
		g2d.drawString("Growth X       = "+p.growthX, 450,10);
		g2d.drawString("Growth Y       = "+p.growthY, 450,25);
		g2d.drawString("Abs Delta X  = "+p.absDeltaX, 450,40);
		g2d.drawString("Abs Delta Y  = "+p.absDeltaY, 450,55);
		
		g2d.drawString("Error before iteration  = "+p.errorP, 450,80);
		g2d.drawString("Error after iteration     = "+p.error, 450,95);
	}


	private static void renderStartAndEnd(Graphics2D g2d) {
		if(!startSet) {
			g2d.setColor(Color.blue);
			int gX = Canvas.getMouseX()/zoom*zoom;
			int gY = Canvas.getMouseY()/zoom*zoom;
			g2d.fillRect(gX,gY,zoom,zoom);
		}else {
			g2d.setColor(Color.green);
			g2d.fillRect(start.x*zoom,start.y*zoom,zoom,zoom);			
		}
		if(startSet && !endSet) {
			g2d.setColor(Color.blue);
			int gX = Canvas.getMouseX()/zoom*zoom;
			int gY = Canvas.getMouseY()/zoom*zoom;
			g2d.fillRect(gX,gY,zoom,zoom);
			step = 0;
		}
		g2d.setColor(Color.black);
	}

	private static void renderGrid(Graphics2D g2d) {
		g2d.setColor(Color.LIGHT_GRAY);

        for (int rx = 0; rx < 400/zoom; rx++) {
        	g2d.drawLine(rx*zoom,0, rx*zoom,400);
        }
        for (int ry = 0; ry < 400/zoom; ry++) {
        	g2d.drawLine(0, ry*zoom, 400, ry*zoom);
        }
	}
	
}
