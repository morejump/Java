import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Admin on 6/5/2016.
 */
public class Bullet {
    public int positionX;
    public int positionY;
    public BufferedImage image;//Sprite
    public int speedX=0;
    public int speedY=0;
    public Bullet(int positionX, int positionY, String pathImage) { // this constructor to get position and image of plane
        this.positionX = positionX;
        this.positionY = positionY;
        try {
            this.image = ImageIO.read(new File(pathImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getCurrentLocation(int x, int y) {
        positionY = y;
        positionX = x;
    }

    public void Move() {
        this.positionY -= this.speedY;
    }

    public void draw(Graphics g) {
        g.drawImage(this.image, this.positionX, this.positionY, null);
    }
}
