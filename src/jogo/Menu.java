package jogo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel implements KeyListener {

    private JFrame frame;
    private Image backgroundImage;

    public Menu(JFrame frame) {
        this.frame = frame;
        setFocusable(true);
        setPreferredSize(new Dimension(Commons.LARGURA, 655));
        loadBackgroundImage();
        addKeyListener(this);
    }

    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(new File("src/resources/menu_background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Desenha o fundo
        if (backgroundImage != null) {
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        frame.remove(this); // Remove o painel do menu do JFrame
        ((Breakout) frame).startGame(); // Inicia o jogo
    }

    // Implementações vazias para métodos de KeyListener não utilizados
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
