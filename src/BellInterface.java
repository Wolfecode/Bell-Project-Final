import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BellInterface extends JPanel {
	private JTextField input;
	private JButton on;
	private JButton off;
	public BellInterface(){
		
		on = new JButton("On");
		off = new JButton("Off");
		
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
	}
}