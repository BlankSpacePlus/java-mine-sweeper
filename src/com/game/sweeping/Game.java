package com.game.sweeping;

import static com.game.sweeping.Constant.BLOCK_HEIGHT;
import static com.game.sweeping.Constant.BLOCK_WIDTH;
import static com.game.sweeping.Constant.PANEL_COL_NUMBER;
import static com.game.sweeping.Constant.PANEL_ROW_NUMBER;
import static com.game.sweeping.Constant.TITLE;

import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		// 创建JFrame对象作为容器
		JFrame gameFrame = new JFrame();
		// 创建mainPanel对象，初始化一个18×30的方格窗体
		GamePanel mainPanel = new GamePanel(PANEL_ROW_NUMBER, PANEL_COL_NUMBER);
		// 获取JFrame应给设置的宽度和高度
        // 因为窗体的菜单栏和边框也要占用像素，所以加上20和40修正大小
        int frameWidth = PANEL_COL_NUMBER * BLOCK_WIDTH + 20;
        int frameHeight = PANEL_ROW_NUMBER * BLOCK_HEIGHT + 40;
		// 设置JFame宽和高
		gameFrame.setSize(frameWidth, frameHeight);
		// 设置窗体标题
		gameFrame.setTitle(TITLE);
		// 设置窗体关闭则程序退出
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Panel添加到Frame里
        gameFrame.add(mainPanel);
        // 设置窗体可见
		gameFrame.setVisible(true);
	}

}
