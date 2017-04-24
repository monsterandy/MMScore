package com.andy.score;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.*;

@SuppressWarnings("serial")
public class ScoreInsert extends JPanel implements ActionListener {

	JLabel stuNumLabel = null;
	JLabel stuScoreLabel = null;
	JTextField stuNumText = null;
	JTextField stuScoreText = null;
	JButton confirmButton = null;
	Map<String, Integer> scoreMap = null;
	String stuNum = null;
	int stuScore = 0;

	public ScoreInsert(Map<String, Integer> scoreMap) {
		this.scoreMap = scoreMap;
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		stuNumLabel = new JLabel("Student Number:");
		stuScoreLabel = new JLabel("Student Score:");
		stuNumText = new JTextField(12);
		stuScoreText = new JTextField(12);
		confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(this);
		add(stuNumLabel);
		add(stuNumText);
		add(stuScoreLabel);
		add(stuScoreText);
		add(confirmButton);
		layout.putConstraint(SpringLayout.WEST, stuNumLabel, 100, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, stuNumLabel, 60, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, stuNumText, 10, SpringLayout.EAST, stuNumLabel);
		layout.putConstraint(SpringLayout.NORTH, stuNumText, 56, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, stuScoreLabel, 100, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, stuScoreLabel, 30, SpringLayout.SOUTH, stuNumLabel);
		layout.putConstraint(SpringLayout.WEST, stuScoreText, 26, SpringLayout.EAST, stuScoreLabel);
		layout.putConstraint(SpringLayout.NORTH, stuScoreText, 19, SpringLayout.SOUTH, stuNumText);
		layout.putConstraint(SpringLayout.WEST, confirmButton, 200, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, confirmButton, 30, SpringLayout.SOUTH, stuScoreText);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmButton) {
			try {
				stuNum = stuNumText.getText().trim();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "illegal student number!");
				return;
			}
			try {
				stuScore = Integer.parseInt(stuScoreText.getText().trim());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "illegal student score!");
				return;
			}
			scoreMap.put(stuNum, stuScore);
			JOptionPane.showMessageDialog(null, "Insert Success!");
			stuNumText.setText(null);
			stuScoreText.setText(null);
		}
	}

}
