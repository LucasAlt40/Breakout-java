package jogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Menu extends JPanel implements KeyListener {

    private boolean gameStarted = false;
    private JFrame frame;

    public Menu(JFrame frame) {
        this.frame = frame;
        setFocusable(true);
        setPreferredSize(new Dimension(Commons.LARGURA, Commons.ALTURA));
        setBackground(Color.BLACK);
        addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Verdana", Font.BOLD, 24));
        String message = "Pressione qualquer tecla para iniciar";
        FontMetrics fontMetrics = g.getFontMetrics();
        int stringWidth = fontMetrics.stringWidth(message);
        g2d.drawString(message, (Commons.LARGURA - stringWidth) / 2, Commons.ALTURA / 2);
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gameStarted = true;
        frame.remove(this); // Remove o painel do menu do JFrame
        ((Breakout)frame).startGame(); // Inicia o jogo
    }

    // Implementações vazias para métodos de KeyListener não utilizados
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
