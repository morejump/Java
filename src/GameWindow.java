import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by luuthao on 5/28/16.
 */
public class GameWindow extends Frame implements Runnable {
    PlaneFighter pf01;
    PlaneFighter pf02;
    PlaneSupport ps;
    Bullet bl01;
    Bullet bl02;
    HP hp01;
    HP hp02;
    Bomb bomb;
    int x, y,count=0, them;
    Image background;
    BufferedImage bufferedImage;

    public GameWindow() { //constructor
        this.setSize(480, 600);
        this.setTitle("1945");
        this.setVisible(true);
        pf01 = new PlaneFighter(100, 200, "Resources/PLANE2.png");
        pf02 = new PlaneFighter(200, 300, "Resources/PLANE3.png");
        ps = new PlaneSupport(300,300,"Resources/PlaneSupporter.png");
        bl01 = new Bullet(-200, -300, "Resources/DAN.png");
        bl02 = new Bullet(-200, -300, "Resources/DAN.png");
        hp01 = new HP(pf01.positionX, pf01.positionY, "Resources/HP01.png");
        hp02 = new HP(pf02.positionX, pf02.positionY, "Resources/HP01.png");
        bomb = new Bomb(200, 300, "Resources/transparent.png");
        // closing game
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        // this event is used to catch motion of mouse and move plane 2
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                pf02.getCurrentLocation(e.getX(), e.getY());
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) { // shooting when left mouse button is clicked
                    if (bl02.positionY <= 0) {
                        bl02 = new Bullet(pf02.positionX+35, pf02.positionY, "Resources/DAN.png");
                        pf01.Shot(bl02);
                    }
                }
                if (SwingUtilities.isRightMouseButton(e)) {// Drop bomb when player click right mousse button
                    if (bomb.positionY >= (pf02.positionY + 200) || bomb.positionY < 0) {
                        y = pf02.positionY + 201;
                        x = pf02.positionX;
                        bomb = new Bomb(pf02.positionX+35, pf02.positionY, "Resources/Bomb.png");
                    }
                }
            }
        });
        // this event is used to catch key from keyboard and move plan 1, shot
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        pf01.speedY = -10;
                        break;
                    case KeyEvent.VK_A:
                        pf01.speedX = -10;
                        break;
                    case KeyEvent.VK_S:
                        pf01.speedY = 10;
                        break;
                    case KeyEvent.VK_D:
                        pf01.speedX = 10;
                        break;
                    case KeyEvent.VK_SPACE:
                        if (bl01.positionY <= 0) {
                            bl01 = new Bullet(pf01.positionX+35, pf01.positionY, "Resources/DAN.png");
                            pf01.Shot(bl01);
                        }
                        break;
                    case KeyEvent.VK_UP:
                        ps.speedY=-10;
                        break;
                    case KeyEvent.VK_DOWN:
                        ps.speedY=10;
                        break;
                    case KeyEvent.VK_LEFT:
                        ps.speedX=-10;
                        break;
                    case  KeyEvent.VK_RIGHT:
                        ps.speedX=10;
                        break;

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                pf01.speedX = 0;
                pf01.speedY = 0;
                ps.speedX=0;
                ps.speedY=0;
            }
        });
        try {
            background = ImageIO.read(new File("Resources/Background.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public void gameUpdate() {
//        count=0;
//        Graphics g;

        pf01.Move();
        pf02.Move();
        ps.Move();
        bl01.Move();
        bl02.Move();
        bomb.Move();
//        if (kc(pf01.positionX,pf01.positionY,ps.positionX,ps.positionY)<100.0f){
//            count++;
//            if (count==600){
//                g.drawImage(bufferedImage,pf01.positionX,pf01.positionY,pf01.positionX+90,pf01.positionY+3,pf01.positionX,pf01.positionY,pf01.positionX+70,pf01.positionY+3,null);
//            }
//
//        }
        if (bomb.positionY == y) {
            bomb = new Bomb(x, y, "Resources/fire.png");
        }
        if (bomb.positionY == y+6) {
            bomb = new Bomb(x, y, "Resources/transparent.png");
            x=0;
            y=0;
        }
        hp01.getCurrentLocation(pf01.positionX, pf01.positionY);
        hp02.getCurrentLocation(pf02.positionX, pf02.positionY);
    }
    private double kc(int x1, int y1, int x2, int y2){
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
    @Override
    public void update(Graphics g) {// để vẽ hiểu là hàm draw--> da overide de tranh nhap nhay
        if (bufferedImage == null) {
            bufferedImage = new BufferedImage(480, 600, 1);
        }
        Graphics bufferedGraphics = bufferedImage.getGraphics();
        bufferedGraphics.drawImage(background, 0, 0, null);
        pf01.draw(bufferedGraphics);
        pf02.draw(bufferedGraphics);
        ps.draw(bufferedGraphics);
        bl01.draw(bufferedGraphics);
        bl02.draw(bufferedGraphics);
        hp01.draw(bufferedGraphics);
        hp02.draw(bufferedGraphics);
        bomb.draw(bufferedGraphics);
        g.drawImage(bufferedImage, 0, 0, null);
        if (kc(pf01.positionX,pf01.positionY,ps.positionX,ps.positionY)<100.0f){
            count++;
            if (count==100){
                count=0;
                them+=20;
                g.drawImage(bufferedImage,pf01.positionX,pf01.positionY,(pf01.positionX+70)+them,pf01.positionY+3,pf01.positionX,pf01.positionY,pf01.positionX+70,pf01.positionY+3,null);
            }

        }
//        g.drawImage(bufferedImage,pf01.positionX,pf01.positionY,pf01.positionX+90,pf01.positionY+3,pf01.positionX,pf01.positionY,pf01.positionX+70,pf01.positionY+3,null);
    }
    double getDistance(int x1, int x2, int y1, int y2){
        return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(20);
                gameUpdate();
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


