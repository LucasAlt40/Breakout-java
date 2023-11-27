package jogo;

import java.awt.Image;
import java.awt.Rectangle;

public class Sprite {

    private int positionX;
    private int positionY;
    private int imageWidth;
    private int imageHeight;
    private Image imageObject;



    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public Image getImageObject() {
        return imageObject;
    }

    public void setImageObject(Image image) {
        this.imageObject = image;
    }


    public Rectangle getRect() {
        return new Rectangle(positionX, positionY, imageObject.getWidth(null), imageObject.getHeight(null));
    }

    public void getImageDimensions() {
        imageWidth = imageObject.getWidth(null);
        imageHeight = imageObject.getHeight(null);
    }
}
