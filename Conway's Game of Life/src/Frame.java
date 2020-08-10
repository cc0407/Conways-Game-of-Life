import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Frame{
	
	private Main m;
	//sets window size
	public int HEIGHT = 800, WIDTH = 710;
	private JFrame jf = new JFrame("Conway's Game of Life");
	//public JButton okayButton = new JButton("Go");
	//adds a boolean to see when the mouse has been clicked
	private boolean click = false;
	
	private JButton startButton = new JButton("Start"),
			clearButton = new JButton("Clear");
	
	private JSlider speedSlider = new JSlider(1, 5, 3);

	
	public Frame(Main m) {
		this.m = m;
		this.m.r = new Render(m);

		//sets default settings for the frame
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setAlwaysOnTop(true);
		m.r.setFocusable(true);
		m.r.setBackground(Color.BLACK);
		m.r.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		m.r.setLayout(null);
		
		startButton.setBounds(75* (WIDTH - 100) /100, 90* (HEIGHT) /100, 100, 60);
		m.r.add(startButton);
		
		clearButton.setBounds(95* (WIDTH - 100) /100, 90* (HEIGHT) /100, 100, 60);
		m.r.add(clearButton);
		
		speedSlider.setBounds(25* (WIDTH - 100) /100, 94* (HEIGHT) /100, 45* (WIDTH - 100) /100, 40);
		speedSlider.setMajorTickSpacing(1);
		speedSlider.setSnapToTicks(true);
		speedSlider.setPaintTicks(true);
		speedSlider.setPaintLabels(true);
		speedSlider.setBackground(null);
		speedSlider.setForeground(Color.WHITE);
		m.r.add(speedSlider);

		//adds renderer to the frame
		jf.add(m.r);

		//packs the frame to create the proper resolution
		jf.pack();
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		
		
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(m.g.prepStage)
				{
					m.changeTickRateFromSlider();
					m.g.prepStage = false;
					startButton.setText("Stop");
				}
				else
				{
					stopSimulation();
				}
				
					
				
			}
			
		});
		
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				m.g.clearBoard();
				stopSimulation();
				
			}
			
		});
		
		speedSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				m.changeTickRateFromSlider();
				
			}
			
			
		});
		
		m.r.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				//to avoid errors when clicking outside of the play area
				int xLocation = (e.getX()-5)/m.r.boxWid;
				int yLocation = (e.getY()-5)/m.r.boxWid;
				if(xLocation < m.g.gridSize && yLocation < m.g.gridSize)
					m.g.changePixel(xLocation,yLocation);
				
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});		
	}

	//dont use this to change speed, use changeTickRateFromSlider() inside of main class
	public int getSpeedFromSlider() {
		return this.speedSlider.getValue();
	}
	
	private void stopSimulation() {
		m.changeTickRate(60D);
		m.g.prepStage = true;
		startButton.setText("Start");
	}
}
