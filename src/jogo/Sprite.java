package jogo;

import java.awt.Image;
import java.awt.Rectangle;

/**
 * A classe `Sprite` representa um objeto gráfico no jogo, como personagens, itens, etc.
 * Cada sprite possui uma posição (coordenadas x e y), dimensões da imagem e a própria
 * imagem associada.
 */
public class Sprite {

    // Coordenada x da posição do sprite
    private int positionX;

    // Coordenada y da posição do sprite
    private int positionY;

    // Largura da imagem do sprite
    private int imageWidth;

    // Altura da imagem do sprite
    private int imageHeight;

    // Objeto Image representando a imagem do sprite
    private Image imageObject;

    /**
     * Obtém a coordenada x da posição do sprite.
     *
     * @return a coordenada x da posição do sprite
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * Define a coordenada x da posição do sprite.
     *
     * @param positionX a nova coordenada x da posição do sprite
     */
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    /**
     * Obtém a coordenada y da posição do sprite.
     *
     * @return a coordenada y da posição do sprite
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * Define a coordenada y da posição do sprite.
     *
     * @param positionY a nova coordenada y da posição do sprite
     */
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    /**
     * Obtém a largura da imagem do sprite.
     *
     * @return a largura da imagem do sprite
     */
    public int getImageWidth() {
        return imageWidth;
    }

    /**
     * Define a largura da imagem do sprite.
     *
     * @param imageWidth a nova largura da imagem do sprite
     */
    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    /**
     * Obtém a altura da imagem do sprite.
     *
     * @return a altura da imagem do sprite
     */
    public int getImageHeight() {
        return imageHeight;
    }

    /**
     * Obtém o objeto Image representando a imagem do sprite.
     *
     * @return o objeto Image representando a imagem do sprite
     */
    public Image getImageObject() {
        return imageObject;
    }

    /**
     * Define o objeto Image representando a imagem do sprite.
     *
     * @param image o novo objeto Image representando a imagem do sprite
     */
    public void setImageObject(Image image) {
        this.imageObject = image;
    }

    /**
     * Obtém um retângulo que representa as dimensões e a posição do sprite.
     *
     * @return um retângulo representando as dimensões e a posição do sprite
     */
    public Rectangle getRect() {
        return new Rectangle(positionX, positionY, imageObject.getWidth(null), imageObject.getHeight(null));
    }

    /**
     * Obtém as dimensões da imagem do sprite.
     */
    public void getImageDimensions() {
        imageWidth = imageObject.getWidth(null);
        imageHeight = imageObject.getHeight(null);
    }
}
