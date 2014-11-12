import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.swing.JFrame;

public class BellProjectMain {
	Timer timer;
	int nextHour;
	int nextMinute;
	static String urlString = "http://apps.xrds.org/roomschedulefinder/bellproj_bellschedule.aspx";
	static boolean isOn;
	static char dayType;
	static boolean assemblyDay;
	static boolean doubleBlock;
	static int[] schedule;

	public static void main(String args[]) throws IOException {

		// added code for GUI
		JFrame frame = new JFrame("BellInterface");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new BellInterface());
		frame.pack();
		frame.setSize(400, 120);
		frame.setVisible(true);

		try {
			String output = readUrl(urlString);
			output = output.substring(1, output.length() - 1);
			String[] splitOutput = output.split(",");

			// daytype
			String dayString = splitOutput[0];
			String[] dayparts = dayString.split("\"");
			dayType = dayparts[3].charAt(0);

			// assembly
			String assemblyString = splitOutput[1];
			String[] assparts = assemblyString.split("\"");
			if (assparts[3] == "1")
				assemblyDay = true;
			else
				assemblyDay = false;

			// doubleblock
			String doublebString = splitOutput[2];
			String[] doublebparts = doublebString.split("\"");
			if (doublebparts[3] == "1")
				doubleBlock = true;
			else
				doubleBlock = false;

			// schedule
			int pos = output.indexOf("schedule");
			String scheduleString = output.substring(pos + 15,
					output.length() - 2);

			String[] answerSplit = scheduleString.split(",");
			int[] times = new int[answerSplit.length];
			for (int i = 0; i < answerSplit.length; i++) {
				times[i] = Integer.parseInt(answerSplit[i]
						.replaceAll("\\s", ""));
			}

			System.out.println(Arrays.toString(times));

			for (int i = 0; i < times.length; i++) {
				int hour = (int) (times[i] / 100.0);
				int minute = (int) (times[i] % 100);

				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.HOUR_OF_DAY, hour);
				calendar.set(Calendar.MINUTE, minute);
				calendar.set(Calendar.SECOND, 0);
				Date time = calendar.getTime();
				Date currentDate = new Date();
				if (currentDate.compareTo(time) < 0) {
					// new Reminder(5);
					Timer myTimer = new Timer();
					RemindTask rt = new RemindTask();
					myTimer.schedule(rt, time);
					System.out.println("Task scheduled." + time.toString());
				} else {
					System.out.println("Time has already passed " + time.toString());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);
			
			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

	public BellProjectMain(int seconds) {
		timer = new Timer();
		isOn = true;
	}

	public static boolean isOn() {
		return isOn;
	}

	public static void setOn(boolean isOn) {
		isOn = isOn;
	}

}