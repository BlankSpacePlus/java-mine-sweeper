package com.game.sweeping;

import static com.game.sweeping.Constant.BLANK_SPACE;
import static com.game.sweeping.Constant.BLOCK_HEIGHT;
import static com.game.sweeping.Constant.BLOCK_WIDTH;
import static com.game.sweeping.Constant.BOMB_EMOJI;
import static com.game.sweeping.Constant.FLAG_EMOJI;
import static com.game.sweeping.Constant.OFFSETS;
import static com.game.sweeping.Constant.QUESTION_MARK;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 游戏主面板
 */
public class GamePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    // 界面行数
    private final int rows;

    // 界面列数
    private final int cols;

    // 炸弹数
    private final int bombCount;

    // 存储界面中每一个方格Label的绘制信息
    private final JLabel[][] labels;

    // 存储界面中每一个方块按钮的绘制信息
    private final MyButton[][] buttons;

    public GamePanel(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.bombCount = rows * cols / 10;
        this.labels = new JLabel[rows][cols];
        this.buttons = new MyButton[rows][cols];
        this.setLayout(null);
        this.initButtons();
        this.initLabels();
        this.initRandomBomb();
        this.initNumber();
    }

    /**
     * 初始化按钮数组
     */
    private void initButtons() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 创建按钮实例
                MyButton button = new MyButton();
                // 根据Label大小设置按钮的大小边界
                button.setBounds(j * BLOCK_WIDTH, i * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
                // 将Button逐一添加到Panel里
                this.add(button);
                // 将按钮引用存一下
                buttons[i][j] = button;
                // 设置按钮坐标属性
                button.row = i;
                button.col = j;
                // 给按钮添加鼠标单击事件监听器
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            // 左键单击
                            leftClick((MyButton) e.getSource());
                        } else if (e.getButton() == MouseEvent.BUTTON3) {
                            // 右键单击
                            rightClick((MyButton) e.getSource());
                        }
                    }
                });
                // 按钮文字设置红色
                button.setForeground(Color.RED);
            }
        }
    }

    /**
     * 初始化绘制扫雷的边框
     */
    private void initLabels(){
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                JLabel label = new JLabel(BLANK_SPACE, JLabel.CENTER);
                // 设置每个小方格的边界
                label.setBounds(j * BLOCK_WIDTH, i * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
                // 绘制方格边框
                label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                // 设置方格为透明，便于填充颜色
                label.setOpaque(true);
                // 背景填充为灰色
                label.setBackground(Color.LIGHT_GRAY);
                // 将方格加入到Panel中
                this.add(label);
                // 将方格引用存一下
                labels[i][j] = label;
            }
        }
    }

    /**
     * 产生bombCount个炸弹并在labels中用💣标注出来
     */
    private void initRandomBomb() {
        for (int i = 0; i < this.bombCount; i++) {
            // 生成一个随机数表示行坐标
            int randomRow = (int) (Math.random() * this.rows);
            // 生成一个随机数表示列坐标
            int randomCol = (int) (Math.random() * this.cols);
            // 根据坐标确定Label的位置并显示💣
            this.labels[randomRow][randomCol].setText(BOMB_EMOJI);
            // 设置背景颜色
            this.labels[randomRow][randomCol].setBackground(Color.LIGHT_GRAY);
            // 设置💣的颜色
            this.labels[randomRow][randomCol].setForeground(Color.RED);
        }
    }

    /**
     * 围绕💣的周围标注上数字
     */
    private void initNumber() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                String text = labels[i][j].getText();
                // 如果是炸弹则不标注任何数字
                if (!BOMB_EMOJI.equals(text)) {
                    // 如果不是炸弹，遍历它周围的8个方块，将炸弹的总个数标注在这个方格上
                    // 方块周围的8个方块中炸弹个数
                    int bombCount = 0;
                    // 通过偏移量数组循环遍历8个方块
                    for (int[] offset : OFFSETS) {
                        int row = i + offset[0];
                        int col = j + offset[1];
                        // 判断是否越界
                        if (checkOutOfBound(row, col) && BOMB_EMOJI.equals(labels[row][col].getText())) {
                            bombCount++;
                        }
                    }
                    // 如果炸弹的个数不为0则标注出来
                    if (bombCount > 0) {
                        labels[i][j].setText(String.valueOf(bombCount));
                    }
                }
            }
        }
    }

    /**
     * 左键鼠标单击按钮事件方法
     * @param actionButton 被左键单击的Button
     */
    private void leftClick(MyButton actionButton) {
        String buttonText = buttons[actionButton.row][actionButton.col].getText();
        // 如果按钮是空白才可以进行操作，不是空白就是递归的终止条件
        if (BLANK_SPACE.equals(buttonText)) {
            String labelText = labels[actionButton.row][actionButton.col].getText();
            // 将当前按钮设置为不可见
            actionButton.setVisible(false);
            // 判断Label中的内容：数字/炸弹/空白
            if (BLANK_SPACE.equals(labelText)) {
                // 如果是空的则将他周围空的按钮都打开进入递归
                for (int[] offset: OFFSETS) {
                    int newRow = actionButton.row + offset[0];
                    int newCol = actionButton.col + offset[1];
                    if (checkOutOfBound(newRow, newCol)) {
                        MyButton button = buttons[newRow][newCol];
                        if (button.isVisible()) {
                            // 进入递归
                            leftClick(button);
                        }
                    }
                }
            } else if (BOMB_EMOJI.equals(labelText)) {
                // 如果是炸弹则将全部按钮都打开游戏结束
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        buttons[i][j].setVisible(false);
                    }
                }
            }
        }
    }

    /**
     * 右键鼠标单击按钮事件方法
     * @param actionButton 被右键单击的Button
     */
    private void rightClick(MyButton actionButton) {
        String buttonText = buttons[actionButton.row][actionButton.col].getText();
        // 判断Button中的内容：红旗/问号/空白
        if (BLANK_SPACE.equals(buttonText)) {           // 内容为空白
            // 变成🚩
            actionButton.setText(FLAG_EMOJI);
        } else if (FLAG_EMOJI.equals(buttonText)) {     // 内容为🚩
            // 变成❓
            actionButton.setText(QUESTION_MARK);
        } else if (QUESTION_MARK.equals(buttonText)) {  // 内容为❓
            // 变成空白
            actionButton.setText(BLANK_SPACE);
        }
    }

    /**
     * 越界检查
     * @param row 行号
     * @param col 列号
     * @return 没有越界
     */
    private boolean checkOutOfBound(int row, int col) {
        return row >= 0 && row < this.rows && col >= 0 && col < this.cols;
    }

}
