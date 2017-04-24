package com.andy.score;

import java.awt.*;
import java.awt.event.*;
import java.util.Map;

import javax.swing.*;
import javax.swing.text.*;

@SuppressWarnings("serial")
public class ScoreQuery extends JPanel implements ActionListener {

	JLabel stuNumLabel = null;
	JTextField stuNumText = null;
	JButton queryButton = null;
	JTextPane resultPane = null;
	JScrollPane resultScrollPane = null;
	Map<String, Integer> scoreMap = null;
	String queryText = null;
	int resultScore = 0;

	public ScoreQuery(Map<String, Integer> scoreMap) {
		this.scoreMap = scoreMap;
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		stuNumLabel = new JLabel("Student Number:");
		stuNumText = new JTextField(12);
		queryButton = new JButton("Query");
		queryButton.addActionListener(this);
		resultPane = new JTextPane();
		resultPane.setEditable(false);
		resultScrollPane = new JScrollPane(resultPane);
		resultScrollPane.setPreferredSize(new Dimension(350, 160));
		add(stuNumLabel);
		add(stuNumText);
		add(queryButton);
		add(resultScrollPane);
		layout.putConstraint(SpringLayout.WEST, stuNumLabel, 100, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, stuNumLabel, 30, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, stuNumText, 10, SpringLayout.EAST, stuNumLabel);
		layout.putConstraint(SpringLayout.NORTH, stuNumText, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, queryButton, 205, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, queryButton, 20, SpringLayout.SOUTH, stuNumLabel);
		layout.putConstraint(SpringLayout.WEST, resultScrollPane, 65, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, resultScrollPane, 20, SpringLayout.SOUTH, queryButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == queryButton) {
			if (stuNumText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "empty student number!");
				return;
			}
			try {
				queryText = stuNumText.getText().trim();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "illegal student number!");
				return;
			}
			if (scoreMap.get(queryText) == null) {
				insertMsg("Can`t find the score of \"" + queryText + "\"\n", false, Color.GRAY, 15);
				stuNumText.setText(null);
			} else {
				resultScore = scoreMap.get(queryText);
				insertMsg(queryText + " - ", false, Color.BLACK, 15);
				insertMsg(resultScore + "\n", true, Color.RED, 15);
				stuNumText.setText(null);
			}

		}
	}

	public void insertMsg(String str, boolean isBold, Color color, int fontSize) {
		StyledDocument doc = resultPane.getStyledDocument();
		SimpleAttributeSet attr = new SimpleAttributeSet();
		StyleConstants.setBold(attr, isBold);
		StyleConstants.setForeground(attr, color);
		StyleConstants.setFontSize(attr, fontSize);
		try {
			doc.insertString(doc.getLength(), str, attr);
		} catch (BadLocationException ble) {
			System.out.println("BadLocationException:   " + ble);
		}
	}

}
