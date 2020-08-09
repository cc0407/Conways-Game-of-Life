import java.util.Arrays;

public class Game {
	private Main m;
	public int gridSize = 50;
	public boolean pixels[][] = new boolean[gridSize][gridSize];
	private int amountAdjacent[][] = new int[gridSize][gridSize];
	public boolean prepStage = true;
	
	public Game(Main main)
	{
		this.m = main;
		clearBoard();
		
		
	}
	
	public void clearBoard() {
		for (boolean[] row: pixels)
			Arrays.fill(row, false);
	}
	
	public void computeLife() {
		for(int x = 0; x < gridSize; x++) {
			for(int y = 0; y < gridSize; y++)
			{
				amountAdjacent[x][y] = checkAmtAdj(x,y);
			}
		}
				
		for(int x = 0; x < gridSize; x++) {
			for(int y = 0; y < gridSize; y++)
			{
				int amtAdj = amountAdjacent[x][y];
				if(amtAdj >= 4)
					pixels[x][y] = false;
				else if(amtAdj == 3)
					pixels[x][y] = true;
				else if(amtAdj >= 2 && pixels[x][y])
					pixels[x][y] = true;
				else if(amtAdj <= 1)
					pixels[x][y] = false;

			}
		}
	}
	
	public void changePixel(int x, int y) {
		if(pixels[x][y])
			pixels[x][y] = false;
		else
			pixels[x][y] = true;
	}

	public int checkAmtAdj(int x, int y) {
		int amt = 0;
		
		for(int i = -1; i <=1; i++)
			for(int j = -1; j <=1; j++) {
				try {
					if(i==0 && j==0)
						continue;
					
					if(pixels[x+i][y+j])
						amt++;
				}
				catch(IndexOutOfBoundsException e) {
					
				}
			}		
		return amt;
	}
}
