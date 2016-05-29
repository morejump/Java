import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by hungtran on 5/28/16.
 */
public class GameWindow extends Frame implements Runnable{
    Image background;
    Plane player1;
    Plane player2;
    Plane player3;

    BufferedImage bufferedImage;
    public GameWindow(){
        this.setSize(480, 600);
        this.setTitle("1945");
        this.setVisible(true);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        player1 = new Plane(100, 200);
        player3 = player1;
        System.out.println("Da khoi tao xong");
        player2 = new Plane();

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                player2.move(e.getX(), e.getY());
            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //vua an phim
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //phim duoc an va giu
                switch (e.getKeyCode()){
                    case KeyEvent.VK_W:
                        player3.speedY = -3;
                        break;
                    case KeyEvent.VK_A:
                        player3.speedX = -3;
                        break;
                    case KeyEvent.VK_S:
                        player3.speedY = 3;
                        break;
                    case KeyEvent.VK_D:
                        player3.speedX = 3;
                        break;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                //tha phim ra
                player3.speedX = 0;
                player3.speedY = 0;
            }
        });

        try {
            background = ImageIO.read(new File("Resources/Background.png"));
            player2.image = ImageIO.read(new File("Resources/PLANE2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void gameUpdate(){
        player1.update();
        player2.update();
    }


    @Override
    public void update(Graphics g) {//de? ve~//hieu la ham draw
        if(bufferedImage == null){
            bufferedImage = new BufferedImage(480, 600, 1);
        }
        Graphics bufferedGraphics = bufferedImage.getGraphics();
        bufferedGraphics.drawImage(background, 0, 0, null);
        player1.draw(bufferedGraphics);
        player2.draw(bufferedGraphics);
        g.drawImage(bufferedImage, 0, 0,null);
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(17);
                gameUpdate();
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
