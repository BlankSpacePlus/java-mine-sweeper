package com.game.sweeping;

import static com.game.sweeping.Constant.BLOCK_HEIGHT;
import static com.game.sweeping.Constant.BLOCK_WIDTH;
import static com.game.sweeping.Constant.PANEL_COL_NUMBER;
import static com.game.sweeping.Constant.PANEL_ROW_NUMBER;
import static com.game.sweeping.Constant.TITLE;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameFrame extends JFrame {

    /**
     * 确保序列化版本一致
     */
    private static final long serialVersionUID = 1L;

    public GameFrame() {
        // 设置图标
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("../../../logo.jpg")));
        this.setIconImage(imageIcon.getImage());
        // 创建mainPanel对象，初始化一个18×30的方格窗体
        GamePanel mainPanel = new GamePanel(PANEL_ROW_NUMBER, PANEL_COL_NUMBER);
        // 获取JFrame应给设置的宽度和高度
        // 因为窗体的菜单栏和边框也要占用像素，所以加上20和40修正大小
        int frameWidth = PANEL_COL_NUMBER * BLOCK_WIDTH + 20;
        int frameHeight = PANEL_ROW_NUMBER * BLOCK_HEIGHT + 40;
        // 设置JFame宽和高
        this.setSize(frameWidth, frameHeight);
        // 设置窗体标题
        this.setTitle(TITLE);
        // 设置窗体关闭无反应
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // 设置窗体居中
        this.setLocationRelativeTo(null);
        // 设置关闭事件监听器
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(GameFrame.this, "确定退出游戏？", "退出游戏", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION && e.getWindow() == GameFrame.this) {
                    GameFrame.this.dispose();
                    System.exit(0);
                }
            }
        });
        // Panel添加到Frame里
        this.add(mainPanel);
        // 设置窗体可见
        this.setVisible(true);
    }

}
