package com.games.mineSweeping;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	// ��������
	private int rows;
	// ��������
	private int cols;
	// ը����
	private int bombCount;
	// ÿ��������
	private final int BLOCKWIDTH = 20;
	// ÿ�����񳤶�
	private final int BLOCKHEIGHT = 20;
	// �洢������ÿһ������Ļ�����Ϣ
	private JLabel[][] labels;
	private  MyButton[][] buttons;
	private final int[][] offset = {{-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}};

	// ���췽��,��ʼ������
	public GamePanel(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.bombCount = rows * cols / 10;
		this.labels = new JLabel[rows][cols];
		this.buttons = new MyButton[rows][cols];
		this.setLayout(null);
		this.initButtons();
		this.initLabels();
	}

	// �����ʼ��,����ɨ�׵ı߿�
	private void initLabels(){
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				JLabel l = new JLabel("", JLabel.CENTER);
				// ����ÿ��С����ı߽�
				l.setBounds(j * BLOCKWIDTH, i * BLOCKHEIGHT, BLOCKWIDTH, BLOCKHEIGHT);
				// ���Ʒ���߿�
				l.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				// ���÷���Ϊ͸��,�������������ɫ
				l.setOpaque(true);
				// �������Ϊ��ɫ
				l.setBackground(Color.YELLOW);
				// ��������뵽������(�����JPanel)
				this.add(l);
				// ������浽�������,���㹫��
				labels[i][j] = l;
			}
		}
		randomBomb();
		writeNumber();
	}


	// ����bombCount��ը��,����labels����"*"��ע����
	private void randomBomb() {
		for (int i = 0; i < this.bombCount; i++) {
			// ����һ���������ʾ������
			int rRow = (int) (Math.random() * this.rows);
			// ����һ���������ʾ������
			int rCol = (int) (Math.random() * this.cols);
			// ��������ȷ��JLabel��λ��,����ʾ*
			this.labels[rRow][rCol].setText("*");
			// ���ñ�����ɫ
			this.labels[rRow][rCol].setBackground(Color.DARK_GRAY);
			// ����*����ɫ
			this.labels[rRow][rCol].setForeground(Color.RED);
		}
	}

	// ��ը������Χ��ע������
	private void writeNumber() {
		for (int  i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				// �����ը��,����ע�κ�����
				if (labels[i][j].getText().equals("*")) {
					continue;
				}
				// �������ը��,��������Χ�İ˸�����,��ը�����ܸ�����ע�����������
				// ������Χ��8��������ը������
				int bombCount = 0;
				// ͨ��ƫ��������ѭ������8������
				for (int[] off: offset) {
					int row = i + off[1];
					int col = j + off[0];
					// �ж��Ƿ�Խ��,�Ƿ�Ϊը��
					if (verify(row, col) && labels[row][col].getText().equals("*")) {
						bombCount++;
					}
				}
				// ���ը���ĸ�����Ϊ0,��ע����
				if (bombCount > 0) {
					labels[i][j].setText(String.valueOf(bombCount));
				}
			}
		}
	}

	// �ж�λ���Ƿ�Խ��
	private boolean verify(int row, int col) {
		return row >= 0 && row < this.rows && col >= 0 && col < this.cols;
	}

	// ��ʼ����ť,��JLabel����ס
	private void initButtons() {
		// ѭ�����ɰ�ť
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				MyButton btn = new MyButton();
				// ����JLabel��С���ð�ť�Ĵ�С�߽�
				btn.setBounds(j * BLOCKWIDTH, i * BLOCKHEIGHT, BLOCKWIDTH, BLOCKHEIGHT);
				this.add(btn);
				// ����ť�����������(��Ȼ,ʵ���ϴ���Ƕ�������õ�ַ)
				buttons[i][j] = btn;
				btn.row = i;
				btn.col = j;
				// ����ť��Ӽ�����,ע�����¼�
				// (������ťʱ,��ִ���ڲ���ActionListener()�е�actionPerformed(ActionEvent e)����)
				btn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						open((MyButton) e.getSource());
					}});
			}
		}
	}

	// ������ťʱ�򿪻��Ƭ��
	private void open(MyButton btn) {
		// �Ƚ����ڰ�ť����Ϊ���ɼ�,�����˰�ť
		btn.setVisible(false);
		// �жϰ�ť�� �Ƿ�Ϊ���ֻ��ǿ�
		switch (labels[btn.row][btn.col].getText()) {
			// �����ը����ȫ����ť����,��Ϸ����
			case "*" :
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						buttons[i][j].setVisible(false);
					}
				}
				break;
			// ����ǿյ�������Χ�յİ�ť����,����ݹ�
			case "" :
				for (int[] off: offset) {
					int newRow = btn.row + off[0];
					int newCol = btn.col + off[1];
					if (verify(newRow, newCol)) {
						MyButton sButton = buttons[newRow][newCol];
						if (sButton.isVisible()) {
							open(sButton);
						}
					}
				}
			default:
		}
	}

	// �����͸�,����������
	public int[] returnSize() {
		// ��Ϊ����Ĳ˵���,�߿�ҲҪռ������,���Լ���20��40������С
		int[] a = {this.cols * BLOCKWIDTH + 20, this.rows * BLOCKHEIGHT + 40};
		return a;
	}
}
