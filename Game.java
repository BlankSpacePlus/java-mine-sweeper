package com.games.mineSweeping;

import java.awt.Container;
import javax.swing.JFrame;

public class Game {
	public static void main(String[] args) {
		// ����JFrame������Ϊ����
		JFrame w = new JFrame();
		// ����mainPanel����,��ʼ��һ��20*30�ķ�����
		GamePanel mainPanel = new GamePanel(20, 30);
		// ��ȡJFrameӦ�����õĿ�Ⱥ͸߶�
		int[] a = mainPanel.returnSize();
		// ����JFame��͸�
		w.setSize(a[0], a[1]);
		//����
		w.setTitle("ɨ��");
		//����ر�������˳�
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = w.getContentPane();
		c.add(mainPanel);
		w.setVisible(true);
	}
}
