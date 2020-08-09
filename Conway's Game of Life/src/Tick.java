
public class Tick {
	private Main m;
	
	public Tick(Main main) {
		this.m = main;
	}

	public void tick()
	{
		
		
		if(!m.g.prepStage)
		{
			m.g.computeLife();
		}
		
		m.r.repaint();
	}
}
