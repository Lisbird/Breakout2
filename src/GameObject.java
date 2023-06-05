import java.awt.Color;
import java.awt.Rectangle;
public class GameObject {
    protected  GameLogic gameLogic;
    protected Color color;
    protected int xPosition;
    protected int yPosition;
    protected int xSize;
    protected int ySize;

    public GameObject (GameLogic gameLogic, int xPosition, int yPosition, int xSize, int ySize, Color color) {
        this.gameLogic = gameLogic;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.xSize = xSize;
        this.ySize = ySize;
        this.color = color;
    }

    public void setPosition(int xPosition, int yPosition){
        this.xPosition = xPosition;
        this.yPosition  = yPosition;
    }

    public int getX() {
        return xPosition;
    }

    public int getY() {
        return yPosition;
    }

    public int getXSize() {
        return xSize;
    }

    public int getYSize() {
        return xSize;
    }

    public Rectangle getHitBox() {
        return new Rectangle(xPosition, yPosition, xSize, ySize);
    }
}
