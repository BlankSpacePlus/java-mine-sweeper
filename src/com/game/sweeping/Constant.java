package com.game.sweeping;

/**
 * 常量类
 */
public interface Constant {

    /**
     * 游戏标题
     */
    String TITLE = "扫雷";

    /**
     * 每个方格宽度
     */
    int BLOCK_WIDTH = 50;

    /**
     * 每个方格长度
     */
    int BLOCK_HEIGHT = 50;

    /**
     * Panel行数（一列的方块数）
     */
    int PANEL_ROW_NUMBER = 18;

    /**
     * Panel列数（一行的方块数）
     */
    int PANEL_COL_NUMBER = 30;

    /**
     * 炸弹emoji 💣
     */
    String BOMB_EMOJI = "\uD83D\uDCA3";

    /**
     * 棋子emoji 🚩
     */
    String FLAG_EMOJI = "\uD83D\uDEA9";

    /**
     * 问号emoji ❓
     */
    String QUESTION_MARK = "❓";

    /**
     * 空字符串文本
     */
    String BLANK_SPACE = "";

    /**
     * 偏移量数组
     */
    int[][] OFFSETS = {{-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}};

}
