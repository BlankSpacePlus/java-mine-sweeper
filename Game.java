package com.games.mineSweeping;

import java.awt.Container;
import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		// 创建JFrame对象作为容器
		JFrame w = new JFrame();
		// 创建mainPanel对象,初始化一个20*30的方格窗体
		GamePanel mainPanel = new GamePanel(20, 30);
		// 获取JFrame应给设置的宽度和高度
		int[] a = mainPanel.returnSize();
		// 设置JFame宽和高
		w.setSize(a[0], a[1]);
		// 标题
		w.setTitle("扫雷");
		// 窗体关闭则程序退出
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = w.getContentPane();
		c.add(mainPanel);
		w.setVisible(true);
	}

}
