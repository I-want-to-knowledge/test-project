package com.geo.source.head_first.design_mode.observer.swing_observer;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 按钮监听器
 *
 * @author YanZhen 2018-10-27 15:52:38 SwingObserverExample
 */
public class SwingObserverExample {
	JFrame frame;
	public static void main(String[] args) {
		SwingObserverExample soe = new SwingObserverExample();
		soe.go();
	}

	private void go() {
		frame = new JFrame();

		JButton button = new JButton("Should i do it?");
		button.addActionListener(event -> System.out.println("Don't do it, you might regret it!"));
		button.addActionListener(event -> System.out.println("come on, do it!"));
		button.setVisible(true);
		frame.getContentPane().add(BorderLayout.CENTER, button);
		
		// set frame properties
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 300);
		frame.setVisible(true);
	}

	/*class AngelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Don't do it, you might regret it!");
		}
	}

	class DevilListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("come on, do it!");
		}
	}*/
}
