package jogo;

import javax.swing.*;
import java.awt.*;

public class Breakout extends JFrame {

    private Menu menu;
    private Board board;

    public Breakout() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        menu = new Menu(this); // Passa a referência do JFrame para o Menu
        add(menu);

        setTitle("Breakout");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
        setVisible(true);
    }

    public void startGame() {
        board = new Board(this);

        SwingUtilities.invokeLater(() -> {
            remove(menu);
            add(board, BorderLayout.CENTER);
            revalidate();
            board.requestFocusInWindow();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Breakout game = new Breakout();
            game.initUI();
        });
    }
}
