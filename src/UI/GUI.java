package UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {
	private static JFrame frame;
	private static Canvas panel;
	private static boolean initialized = false;

	public static void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setTitle("Bresenham - Line Stepper Demo");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setSize(Canvas.winx,Canvas.winy);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		panel = new Canvas();
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel creaturePanel = new JPanel();
		creaturePanel.setLayout(new BoxLayout(creaturePanel, BoxLayout.PAGE_AXIS));       

		JPanel controlPanel = new JPanel();
		frame.getContentPane().add(controlPanel, BorderLayout.SOUTH);

	
		JButton btn_next = new JButton("Next");
		btn_next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Demo.step++;
			}
		});
		controlPanel.add(btn_next);
		
		JButton btn_back = new JButton("Back");
		btn_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(Demo.step>0) {
					Demo.step--;					
				}
			}
		});
		controlPanel.add(btn_back);
		
		JButton btn_reset = new JButton("Reset");
		btn_reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Demo.reset();
			}
		});
		controlPanel.add(btn_reset);
		

		frame.pack();
		
		initialized=true;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void paint() {
		if (initialized) {
			panel.repaint();
		}
	}

}
