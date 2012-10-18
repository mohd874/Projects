package ch12;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SimpleGui implements ActionListener{
	JButton btn;
	
	public static void main(String[] args) {
		SimpleGui gui = new SimpleGui();
		gui.goPanel();
	}

	private void goButton() {
		JFrame frame = createJFrame();		
		
		btn = new JButton("Click Me!");
		btn.addActionListener(this);
		
		frame.getContentPane().add(btn);
	}

	private void goPanel() {
		JFrame frame = createJFrame();
		frame.getContentPane().add(new MyDrawPanel());
		frame.repaint();
	}

	private JFrame createJFrame(){
		JFrame frame = new JFrame("Simple GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(255, 255);
		frame.setVisible(true);
		frame.requestFocus();
		
		return frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		btn.setText("I've been clicked");
	}

}
