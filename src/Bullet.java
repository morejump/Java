import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Admin on 5/30/2016.
 */
public class Bullet {
    public int positionX, positionY;
    public int speed = 3;
    public BufferedImage image;

    // Constructor
    public Bullet(int positionX, int positionY, String pathImage) {
        this.positionX = positionX;
        this.positionY = positionY;
        try {
            this.image = ImageIO.read(new File(pathImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move(int x, int y) {
        positionY = y;
        positionX = x;
    }

    public void update() {
        this.positionY -= this.speed;
    }

    public void draw(Graphics g) {
        g.drawImage(this.image, this.positionX, this.positionY, null);
    }
}