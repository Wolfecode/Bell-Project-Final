import java.util.Timer;
import java.util.TimerTask;

public class RemindTask extends TimerTask {
	public boolean Reminded = false;
	Timer timer;

	RemindTask() {
		super();
	}

	RemindTask(Timer t, BellProjectMain remind) {
		super();
		timer = t;

	}

	public void run() {

		if (BellProjectMain.isOn()) {

			System.out.println("Time's up!%n");

			System.out.println("fired off an event!");
			Reminded = true;
			// Create a new Thread to play the sound, so you can still interact with the remaining program
			PlaySound s = new PlaySound();
			Thread t = new Thread(s);
			t.start();

			try {
				timer.cancel(); // Terminate the timer thread
			} catch (Exception e) {
			}

		}
	}
}