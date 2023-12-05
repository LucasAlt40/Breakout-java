package jogo;

import javax.swing.ImageIcon;

/**
 * A classe `Ball` representa a bola no jogo Breakout, que estende a classe
 * `Sprite`.
 * Inclui métodos para inicialização da bola, atualização da velocidade,
 * movimento e reinicialização do estado.
 */
public class Ball extends Sprite {

    // Direção horizontal da bola
    private int xdir;

    // Direção vertical da bola
    private int ydir;

    // Velocidade inicial da bola
    private int velocidadeBolinha = 1;

    /**
     * Construtor da classe `Ball`. Inicializa a bola com base no número da bola.
     *
     * @param N_DA_BOLA um valor inteiro que identifica a bola
     */
    public Ball(int N_DA_BOLA) {
        initBall(N_DA_BOLA);
    }

    /**
     * Define a direção horizontal da bola.
     *
     * @param x a nova direção horizontal da bola
     */
    public void setXDir(int x) {
        xdir = x;
    }

    /**
     * Define a direção vertical da bola.
     *
     * @param y a nova direção vertical da bola
     */
    public void setYDir(int y) {
        ydir = y;
    }

    /**
     * Obtém a direção vertical atual da bola.
     *
     * @return a direção vertical da bola
     */
    public int getYDir() {
        return ydir;
    }

    /**
     * Atualiza a velocidade da bola com base no valor fornecido.
     *
     * @param velocidadeBolinha a nova velocidade da bola
     */
    public void updateVelocidadeBolinha(int velocidadeBolinha) {
        // Atualiza a velocidade da bola, limitando-a à velocidade máxima definida em
        // Commons
        if (this.velocidadeBolinha < Commons.VELOCIDADEMAXBOLA) {
            this.velocidadeBolinha = velocidadeBolinha;
        }
    }

    /**
     * Move a bola de acordo com suas direções atuais e verifica as bordas da tela.
     */
    public void move() {
        // Atualiza as coordenadas da bola de acordo com suas direções
        this.setPositionX(this.getPositionX() + xdir);
        this.setPositionY(this.getPositionY() + ydir);

        // Verifica e ajusta as direções da bola nas bordas da tela
        if (this.getPositionX() <= 0) {
            setXDir(velocidadeBolinha);
        }

        if (this.getPositionX() >= Commons.LARGURA - this.getImageWidth()) {
            setXDir(-velocidadeBolinha);
        }

        if (this.getPositionY() <= 0) {
            setYDir(velocidadeBolinha);
        }
    }

    // Método privado para carregar a imagem da bola
    private void loadImage(int N_DA_BOLA) {
        // Carrega a imagem da bola com base no número da bola
        if (N_DA_BOLA == 1) {
            var ii = new ImageIcon("src/resources/ball.png");
            this.setImageObject(ii.getImage());
        } else {
            var ii = new ImageIcon("src/resources/ball 2.png");
            this.setImageObject(ii.getImage());
        }
    }

    // Método privado para inicializar a bola
    private void initBall(int N_DA_BOLA) {
        // Define as direções iniciais da bola
        xdir = 1;
        ydir = -1;

        // Carrega a imagem associada à bola
        loadImage(N_DA_BOLA);

        // Obtém as dimensões da imagem da bola
        getImageDimensions();

        // Reinicializa o estado da bola
        resetState();
    }

    // Método privado para reiniciar o estado da bola
    private void resetState() {
        // Define as coordenadas iniciais da bola
        this.setPositionX(Commons.INICIAR_BOLA_X);
        this.setPositionY(Commons.INICIAR_BOLA_Y);
    }

}
