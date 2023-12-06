package jogo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

/**
 * A classe `Menu` representa o painel de menu do jogo e estende a classe `JPanel`
 * para fornecer uma interface gráfica. Implementa a interface `KeyListener` para
 * lidar com eventos de teclado.
 */
public class Menu extends JPanel implements KeyListener {

    // Referência para o JFrame que contém este painel de menu
    private JFrame frame;

    // Imagem de fundo do menu
    private Image backgroundImage;

    /**
     * Construtor da classe Menu.
     *
     * @param frame o JFrame que contém o painel de menu
     */
    public Menu(JFrame frame) {
        this.frame = frame;
        setFocusable(true);
        setPreferredSize(new Dimension(Commons.LARGURA, 655));
        loadBackgroundImage();
        addKeyListener(this);
    }

    /**
     * Carrega a imagem de fundo do menu a partir de um arquivo.
     */
    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(new File("src/resources/menu_background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sobrescreve o método paintComponent para desenhar o fundo do menu.
     *
     * @param g o contexto gráfico para desenhar
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Desenha o fundo
        if (backgroundImage != null) {
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * Manipula o evento de tecla pressionada. Remove o painel do menu do JFrame
     * e inicia o jogo.
     *
     * @param e o evento de tecla
     */
    @Override
    public void keyPressed(KeyEvent e) {
        frame.remove(this); // Remove o painel do menu do JFrame
        ((Breakout) frame).startGame(); // Inicia o jogo
    }

    /**
     * Implementação vazia para o método keyTyped da interface KeyListener.
     *
     * @param e o evento de tecla
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Implementação vazia
    }

    /**
     * Implementação vazia para o método keyReleased da interface KeyListener.
     *
     * @param e o evento de tecla
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // Implementação vazia
    }
}
