/**
 * Created by Admin on 6/4/2016.
 */
public class PlaneFighter extends Plane implements IFighter, IStrategy {// shooting and droping bomb

    public PlaneFighter(int positionX, int positionY, String pathImage) {
        super(positionX, positionY, pathImage);
    }
    @Override
    public void dropBomb() {

    }
    @Override
    public void Shot(Bullet bl) {
       bl.speedY=20;
    }
}
