package jogo;

import javax.swing.ImageIcon;

/**
 * A classe `Brick` representa um bloco no jogo Breakout.
 * Cada bloco herda da classe `Sprite` e possui um estado indicando se foi destruído ou não.
 */
public class Brick extends Sprite {

    // Indica se o bloco foi destruído
    private boolean destroyed;

    /**
     * Construtor da classe `Brick`.
     *
     * @param x a coordenada x inicial do bloco
     * @param y a coordenada y inicial do bloco
     */
    public Brick(int x, int y) {
        initBrick(x, y);
    }

       /**
     * Inicializa as propriedades do bloco.
     *
     * @param x a coordenada x inicial do bloco
     * @param y a coordenada y inicial do bloco
     */
    private void initBrick(int x, int y) {
        // Define as coordenadas iniciais do bloco
        this.setPositionX(x);
        this.setPositionY(y);

        // Inicializa o estado do bloco como não destruído
        destroyed = false;

        // Carrega a imagem associada ao bloco
        loadImage();

        // Obtém as dimensões da imagem do bloco
        getImageDimensions();
    }

    /**
     * Carrega a imagem associada ao bloco a partir do arquivo de recursos.
     */
    private void loadImage() {
        // Carrega a imagem do bloco a partir do arquivo de recursos
        var ii = new ImageIcon("src/resources/brick.png");

        // Define a imagem do bloco com base na imagem carregada
        this.setImageObject(ii.getImage());
    }


    /**
     * Verifica se o bloco foi destruído.
     *
     * @return true se o bloco foi destruído, false caso contrário
     */
    public boolean isDestroyed() {
        return destroyed;
    }

    /**
     * Define o estado destruído do bloco.
     *
     * @param val o novo valor para o estado destruído do bloco
     */
    public void setDestroyed(boolean val) {
        destroyed = val;
    }
}
