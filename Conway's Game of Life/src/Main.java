
public class Main {
	Render r;
	Frame f = new Frame(this);
	Game g = new Game(this);
	Tick t = new Tick(this);
	
	
	public double amountOfTicks;
	private double ns;
	
	public Main() {
		run();
	}
	//boolean to determine if the program is running or not
	public boolean running = true;

	//main method
	public static void main(String[] args) {
		new Main();
	}

	//run method to run the program
	public void run() {
		
		//equations to calculate tick rate, does 60 ticks per second
		long lastTime = System.nanoTime();
		changeTickRate(60D);
		double delta = 0;

		//loop while the game is running
		while (running) {

			//calculating and running the tick method in the tick class
			long now = System.nanoTime();
			delta += (now - lastTime) / this.ns;
			lastTime = now;

			if (delta >= 1) {
				delta--;
				t.tick();
			}
		}
	}
	
	public void changeTickRateFromSlider() {
		this.changeTickRate(f.getSpeedFromSlider());
	
	}
	
	public void changeTickRate (double amt) {
		this.amountOfTicks = amt;
		this.ns = 1000000000 / amountOfTicks;
	}
}
