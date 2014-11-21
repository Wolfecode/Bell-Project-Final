import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BellInterface extends JPanel {
	private JButton on;
	private JButton off;
	private JTextArea textArea;
	public BellInterface(){
		
		on = new JButton("On");
		off = new JButton("Off");
		JButton testButton = new JButton("Test Alarm");
		textArea = new JTextArea(400,200);
		testButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PlaySound s = new PlaySound();
				Thread t = new Thread(s);
				t.start();
			}
		});
		
		off.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BellProjectMain.setOn(false);
				
				off.setEnabled(false);
				on.setEnabled(true);
				
			}
			
		});
		
		on.addActionListener(new ActionListener(){
		
			@Override
			public void actionPerformed(ActionEvent e) {
				BellProjectMain.setOn(true);
				on.setEnabled(false);
				off.setEnabled(true);
				
			}
			
			
		});


		
		this.add(on);
		this.add(off);
		this.add(testButton);
		this.add(textArea);
	}
}