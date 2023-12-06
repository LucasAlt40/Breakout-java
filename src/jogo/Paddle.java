package jogo;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

/**
 * A classe `Paddle` representa a raquete controlada pelo jogador no jogo Breakout.
 * Ela herda da classe `Sprite` e adiciona funcionalidades específicas para o controle
 * da raquete, como movimento e manipulação de eventos de teclado.
 */
public class Paddle extends Sprite {

    // Variação na coordenada x durante o movimento
    private int dx;

    // Velocidade de movimento da raquete
    private int velocidadePaddle = 3;

    /**
     * Construtor padrão da classe `Paddle`. Inicializa a raquete com a imagem padrão.
     */
    public Paddle() {
        initPaddle();
    }

    /**
     * Inicializa a raquete com a imagem especificada.
     *
     * @param i um valor inteiro indicando qual imagem usar para a raquete
     */
    public void initPaddle(int i) {
        loadImage(i);
        getImageDimensions();
    }

    // Método privado para a inicialização padrão da raquete
    private void initPaddle() {
        loadImage(1);
        getImageDimensions();
        resetState();
    }

    /**
     * Define a velocidade de movimento da raquete.
     *
     * @param velocidade a nova velocidade de movimento da raquete
     */
    public void setVelocidadePaddle(int velocidade) {
        velocidadePaddle = velocidade;
    }

    /**
     * Move a raquete com base na variação na coordenada x (dx).
     * Limita a posição da raquete para que não ultrapasse os limites da tela.
     */
    public void move() {
        setPositionX(getPositionX() + dx);

        // Limita a posição da raquete para que não ultrapasse o lado esquerdo da tela
        if (getPositionX() <= 0) {
            setPositionX(0);
        }

        // Limita a posição da raquete para que não ultrapasse o lado direito da tela
        if (getPositionX() >= Commons.LARGURA - getImageWidth()) {
            setPositionX(Commons.LARGURA - getImageWidth());
        }
    }

    /**
     * Responde a uma tecla pressionada, ajustando a variação dx com base na tecla.
     *
     * @param e o evento de tecla pressionada
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // Se a tecla esquerda foi pressionada, ajusta dx para mover para a esquerda
        if (key == KeyEvent.VK_LEFT) {
            dx = -velocidadePaddle;
        }

        // Se a tecla direita foi pressionada, ajusta dx para mover para a direita
        if (key == KeyEvent.VK_RIGHT) {
            dx = velocidadePaddle;
        }
    }

    /**
     * Responde a uma tecla liberada, ajustando dx para parar o movimento.
     *
     * @param e o evento de tecla liberada
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        // Se a tecla esquerda ou direita foi liberada, ajusta dx para parar o movimento
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

      /**
     * Carrega a imagem da raquete com base no parâmetro i.
     *
     * @param i um valor inteiro que determina qual imagem da raquete carregar
     */
    private void loadImage(int i) {
        if (i == 1) {
            // Carrega a primeira imagem da raquete
            var ii = new ImageIcon("src/resources/paddle.png");
            setImageObject(ii.getImage());
        } else {
            // Carrega a segunda imagem da raquete (quando i não é igual a 1)
            var ii = new ImageIcon("src/resources/paddle2.png");
            setImageObject(ii.getImage());
        }
    }

    /**
     * Reinicia a posição inicial da raquete para as coordenadas iniciais definidas em Commons.
     */
    private void resetState() {
        // Define a posição inicial da raquete nas coordenadas iniciais especificadas
        setPositionX(Commons.INICIAR_PADDLE_X);
        setPositionY(Commons.INICIAR_PADDLE_Y);
    }

}
