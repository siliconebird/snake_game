package com.snake.frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

import com.snake.snake.SnakePanel;

public class SnakeFrame extends JFrame implements ActionListener {
	//主游戏区域
	SnakePanel p = new SnakePanel(this);
	//菜单栏
	JMenuBar menubar = new JMenuBar();
	//文件菜单
	JMenu fileMenu = new JMenu("文件");
	JMenuItem startItem = new JMenuItem("开始");
	JMenuItem pauseItem = new JMenuItem("暂停");
	JMenuItem runItem = new JMenuItem("继续");
	JMenuItem exitItem = new JMenuItem("退出");
	//设置菜单
	JMenu optionMenu = new JMenu("设置");
		//等级菜单
		JMenu degreeMenu = new JMenu("等级");
		JRadioButtonMenuItem lowItem = new JRadioButtonMenuItem("初级");
		JRadioButtonMenuItem midItem = new JRadioButtonMenuItem("中级");
		JRadioButtonMenuItem hightItem = new JRadioButtonMenuItem("高级");
		ButtonGroup degreeGroup = new ButtonGroup();
		//show grid菜单
		JCheckBoxMenuItem showGridItem = new JCheckBoxMenuItem("显示网格");
	//帮助菜单
		JMenu helpMenu = new JMenu("帮助");
		JMenuItem helpItem = new JMenuItem("操作指南");
	//游戏速度
		private long speed = 0;
	//帮助文档	
		String helpStr = "you know that~";
		public JTextField scoreField;
	
	//构造器
	public SnakeFrame(){
		setJMenuBar(menubar);
		fileMenu.add(startItem);
		fileMenu.add(pauseItem);
		fileMenu.add(runItem);
		fileMenu.add(exitItem);
		menubar.add(fileMenu);
		
		lowItem.setSelected(true);
		degreeGroup.add(lowItem);
		degreeGroup.add(midItem);
		degreeGroup.add(hightItem);
		degreeMenu.add(lowItem);
		degreeMenu.add(midItem);
		degreeMenu.add(hightItem);
		optionMenu.add(degreeMenu);
		showGridItem.setSelected(true);
		optionMenu.add(showGridItem);
		helpMenu.add(helpItem);
		menubar.add(optionMenu);
		menubar.add(helpMenu);
		Container contentPanel = getContentPane();
		contentPanel.setLayout(new FlowLayout());
		contentPanel.add(p);
		JPanel scorePanel = new JPanel();
		JLabel scoreLabel = new JLabel("得  分：");
		scoreField = new JTextField("0",15);
		scoreField.setEnabled(false);
		scoreField.setHorizontalAlignment(0);
		scorePanel.add(scoreLabel);
		scorePanel.add(scoreField);
		contentPanel.add(scorePanel);
		
		startItem.addActionListener(this);
		pauseItem.addActionListener(this);
		runItem.addActionListener(this);
		exitItem.addActionListener(this);
		lowItem.addActionListener(this);
		midItem.addActionListener(this);
		hightItem.addActionListener(this);
		helpItem.addActionListener(this);
		showGridItem.addActionListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==startItem){
			p.startGame(speed);
		}else if(e.getSource()==pauseItem){
			p.stopGame();
		}else if(e.getSource()==runItem){
			p.returnGame();
		}else if(e.getSource()==exitItem){
			System.exit(0);
		}else if(e.getSource()==lowItem){
			speed = 200;
		}else if(e.getSource()==midItem){
			speed = 100;
		}else if(e.getSource()==hightItem){
			speed = 50;
		}else if(e.getSource()==showGridItem){
			if(!showGridItem.isSelected()){
				p.setBackground(Color.lightGray);
			}else{
				p.setBackground(Color.darkGray);
			}
		}
		else if(e.getSource()==helpItem){
			JOptionPane.showConfirmDialog(p, helpStr, "指南", JOptionPane.PLAIN_MESSAGE);
		}
	}

}
