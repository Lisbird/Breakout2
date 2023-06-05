import java.awt.*;

public class Paddle extends GameObject{
    private int velocity;

    public Paddle(GameLogic gameLogic, int xPosition, int yPosition, int xSize, int ySize, Color color) {
        super(gameLogic, xPosition, yPosition, xSize, ySize, color);
        this.velocity = 0;
    }

    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(xPosition - xSize / 2, yPosition - ySize / 2, xSize, ySize);
    }

    public void move() {
        //System.out.println("Paddle move() called");
        xPosition += velocity;
        int xHalf = xSize / 2;
        if (xPosition < xHalf) {
            xPosition = xHalf;
        } else if (xPosition >= gameLogic.getWidth() - xHalf) {
            xPosition = gameLogic.getWidth() - xHalf;
        }
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void moveRight() {
        velocity = Configuration.PADDLE_VELOCITY;
    }

    public void moveLeft() {
        velocity = -Configuration.PADDLE_VELOCITY;
    }

    public void stopMoving() {
        velocity = 0;
    }

}
