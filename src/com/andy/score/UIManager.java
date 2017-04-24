package com.andy.score;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

@SuppressWarnings("serial")
public class UIManager extends JFrame {

	JPanel title = null;
	JTabbedPane funcPane = null;
	ScoreInsert scoreInsert = null;
	ScoreQuery scoreQuery = null;
	ScoreSort scoreSort = null;
	Map<String, Integer> scoreMap = null;

	public UIManager() {
		super("Score System");
		scoreMap = new HashMap<String, Integer>();
		funcPane = new JTabbedPane(JTabbedPane.TOP);
		title = new JPanel();
		title.add(new JLabel("Student Score Manager"));
		scoreInsert = new ScoreInsert(scoreMap);
		scoreQuery = new ScoreQuery(scoreMap);
		scoreSort = new ScoreSort(scoreMap);
		funcPane.add("Insert Score", scoreInsert);
		funcPane.add("Query Score", scoreQuery);
		funcPane.add("Sort Score", scoreSort);
		setLayout(new BorderLayout());
		add(title, BorderLayout.NORTH);
		add(funcPane, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setBounds(0, 0, 500, 400);
		validate();
	}

}
