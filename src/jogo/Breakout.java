package jogo;

import javax.swing.*;
import java.awt.*;

public class Breakout extends JFrame {

    private jogo.Menu menu;
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
        // Inicia o jogo
        board = new Board();

        SwingUtilities.invokeLater(() -> {
            remove(menu); // Remove o menu
            add(board, BorderLayout.CENTER);
            revalidate();
            board.requestFocusInWindow();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Breakout game = new Breakout();
            game.initUI(); // Inicia a interface do usuário
        });
    }
}
