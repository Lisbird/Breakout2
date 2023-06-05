import java.awt.*;

public class Brick extends GameObject{
    private boolean destroyed;

    public Brick(GameLogic gameLogic, int xPosition, int yPosition, int xSize, int ySize, Color color) {
        super(gameLogic, xPosition, yPosition, xSize, ySize, color);
        this.destroyed = false;
    }

    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(xPosition - xSize / 2, yPosition - ySize / 2, xSize, ySize);
    }

    public boolean isDestroyed() { //returns if brick is destroyed or not
        return destroyed;
    }

    public void setDestroyed() {
        destroyed = true;
    }

    public void setIntact() {
        destroyed = false;
    }
}
