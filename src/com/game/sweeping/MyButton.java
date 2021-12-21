package com.game.sweeping;

import javax.swing.JButton;

/**
 * 自定义Button类继承自JButton，放大row和col的访问权限
 */
public class MyButton extends JButton {

    private static final long serialVersionUID = 1L;

    /**
     * 扩大访问权限，便于直接访问row的值
     */
    protected int row;

    /**
     * 扩大访问权限，便于直接访问col的值
     */
    protected int col;

}
