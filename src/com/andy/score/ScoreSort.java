package com.andy.score;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.List;

import javax.swing.*;
import javax.swing.text.*;

@SuppressWarnings("serial")
public class ScoreSort extends JPanel implements ActionListener {

	JButton sortButton = null;
	JTextPane resultPane = null;
	JScrollPane resultScrollPane = null;
	Map<String, Integer> scoreMap = null;
	String resultText = null;

	public ScoreSort(Map<String, Integer> scoreMap) {
		this.scoreMap = scoreMap;
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		sortButton = new JButton("Sort");
		sortButton.addActionListener(this);
		resultPane = new JTextPane();
		resultPane.setEditable(false);
		resultScrollPane = new JScrollPane(resultPane);
		resultScrollPane.setPreferredSize(new Dimension(350, 250));
		add(sortButton);
		add(resultScrollPane);
		layout.putConstraint(SpringLayout.WEST, sortButton, 207, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, sortButton, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, resultScrollPane, 65, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, resultScrollPane, 10, SpringLayout.SOUTH, sortButton);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sortButton) {
			resultPane.setText(null);
			if (scoreMap.isEmpty()) {
				insertMsg("No Student Score Added!\n", true, Color.GRAY, 15);
				return;
			}
			List<Map.Entry<String, Integer>> sortList = new ArrayList<Map.Entry<String, Integer>>(scoreMap.entrySet());
			Collections.sort(sortList, new Comparator<Map.Entry<String, Integer>>() {
				public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
					return (o2.getValue() - o1.getValue());
				}
			});
			for (int i = 0; i < sortList.size(); i++) {
				resultText = sortList.get(i).getKey();
				insertMsg(resultText + " - ", false, Color.BLACK, 15);
				resultText = sortList.get(i).getValue().toString() + "\n";
				insertMsg(resultText, true, Color.RED, 15);

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
