import java.awt.*;

/**
 * Created by Admin on 6/4/2016.
 */
public class PlaneFighter extends Plane implements IFighter, IStrategy {// shooting and droping bomb
    Bomb bomb;
    Bullet bullet;
    public PlaneFighter(int positionX, int positionY, String pathImage) {
        super(positionX, positionY, pathImage);
    }

    @Override
    public void dropBomb() {
        bomb = new Bomb(this.positionX, this.positionY, "Resources/Bomb.png");
        bomb.Move();
        System.out.println("drop bomb");
    }

    @Override
    public void Shot() {
        bullet = new Bullet(this.positionX, this.positionY,"Resources/DAN.png");
        bullet.Move();
        System.out.println("shot");
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        bomb.draw(g);
    }

    @Override
    public void Move() {
        super.Move();
        bomb.Move();
    }
}
