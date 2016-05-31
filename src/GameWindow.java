import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by luuthao on 5/28/16.
 */
public class GameWindow extends Frame implements Runnable{
    Image background;
    Plane player1;
    Plane player2;
    Bullet dan1,dan2;
    BufferedImage bufferedImage;
    public GameWindow(){ //constructor
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
        }); // this event is just used to close the game
        player1 = new Plane(100, 200,"Resources/PLANE2.png");
        player2 = new Plane(200,300,"Resources/PLANE3.png");
        dan1 = new Bullet(-200,-200,"Resources/DAN.png");
        dan2 = new Bullet(-200,-200,"Resources/DAN.png");

        // this event is used to catch motion of mouse
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                player2.move(e.getX(), e.getY());
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (dan2.positionY<=0){
                    dan2 = new Bullet(player2.positionX+35, player2.positionY,"Resources/DAN.png");
                    dan2.speedY=3;}
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }
        });
        // this event is used to catch key from keyboard
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                //phim duoc an va giu
                switch (e.getKeyCode()){
                    case KeyEvent.VK_W:
                        player1.speedY = -3;
                        break;
                    case KeyEvent.VK_A:
                        player1.speedX = -3;
                        break;
                    case KeyEvent.VK_S:
                        player1.speedY = 3;
                        break;
                    case KeyEvent.VK_D:
                        player1.speedX = 3;
                        break;
                    case KeyEvent.VK_SPACE:
                        if (dan1.positionY<=0){
                        dan1 = new Bullet(player1.positionX+30, player1.positionY,"Resources/DAN.png");
                        dan1.speedY=3;}
                        break;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                //tha phim ra
                player1.speedX = 0;
                player1.speedY = 0;
            }
        });

        try {
            background = ImageIO.read(new File("Resources/Background.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }

    }


    public void gameUpdate(){
        player1.update();
        player2.update();
        dan1.update();
        dan2.update();

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
        dan1.draw(bufferedGraphics);
        dan2.draw(bufferedGraphics);
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
