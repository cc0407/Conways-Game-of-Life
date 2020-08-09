import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Render extends JPanel{
	private Main m;
	private FontMetrics fontMetrics;
	public int gridLen, gridWid, boxWid, boxLen;
	public Render(Main main) {
		this.m = main;
	}
	
	protected void paintComponent(Graphics g) 
	{
		gridLen = m.f.WIDTH - 10;
		gridWid = gridLen;
		boxLen = gridLen/m.g.gridSize;
		boxWid = gridWid/m.g.gridSize;
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//		g.setFont(new Font("Monospaced", Font.BOLD, 30));
//		fontMetrics = g.getFontMetrics();
		g.setFont(new Font("Monospaced", Font.PLAIN, 24));
		fontMetrics = g.getFontMetrics();
		g.setColor(Color.WHITE);
		
//		g.drawString("Conway's Game of Life!", justifyX("Conway's Game of Life!", 10), justifyY(92));
		
		g.drawString("Mode : ", justifyX("Mode:", 5), justifyY(92));
		g.drawString("Speed:", justifyX("Speed:", 5), justifyY(97));
		
		if(m.g.prepStage) {
			g.setColor(Color.YELLOW);
			g.drawString("Editing ", justifyX("Editing", 25), justifyY(92));
		}
		else {
			g.setColor(Color.GREEN);
			g.drawString("Playing ", justifyX("Playing", 25), justifyY(92));
		}
		


		
		
		//GRID DRAWING
		g2d.fillRect(5, 5, gridLen, gridWid);
		
		
		int xCount = 0, yCount = 0;

		for(int i = 5; i < gridLen + 5; i+= boxLen) {
			for(int j = 5; j < gridWid + 5; j += boxWid) {
				
				g.setColor(Color.white);
				if(m.g.pixels[xCount][yCount] == true)
					g.setColor(Color.black);
				
				g2d.fillRect(i, j, boxLen, boxWid);
				
				g.setColor(Color.black);
				g2d.drawRect(i, j, boxLen, boxWid);
				
				yCount++;
			}
		xCount++;
		yCount = 0;
		}
		
				
	}
	
	private int justifyX(String input, int percentage)
	{
		return percentage * (m.f.WIDTH - fontMetrics.stringWidth(input)) /100;
	}
	
	private int justifyY(int percentage)
	{
		return percentage * m.f.HEIGHT/100;
	}

}
