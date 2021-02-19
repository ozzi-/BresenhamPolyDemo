package UI;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import Forms.Line;
import Forms.Point;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1L;
	static final int winx = 800;
	static final int winy = 600;
	private static int mx;
	private static int my;
	
	public Canvas() {
		addMouseListener(mouseListener());
		addMouseMotionListener(mouseMoveListener());
		setFocusable(true);
		requestFocusInWindow();
	}

	public static int getMouseX() {
		return mx;
	}
	
	public static int getMouseY() {
		return my;
	}

	public void repaintPanel() {
		repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(winx, winy);
	}

	@Override
	public void paint(Graphics g) {
		Demo.render(g);
	}
	
	private MouseMotionListener mouseMoveListener() {
		return new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				mx = e.getX();
				my = e.getY();
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {}
		};
	}
	
	private MouseListener mouseListener() {
		return new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(!Demo.startSet) {
					int gX = mx/Demo.zoom;
					int gY = my/Demo.zoom;
					Demo.start = new Point(gX,gY);
					Demo.startSet=true;
					return;
				}
				if(Demo.startSet && !Demo.endSet) {
					int gX = mx/Demo.zoom;
					int gY = my/Demo.zoom;
					Demo.end = new Point(gX,gY);
					Demo.endSet=true;
					Demo.p = new Line(Demo.start,Demo.end);
					return;
				}
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {				
			}

		};
	}
}