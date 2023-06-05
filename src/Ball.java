import java.awt.*;

public class Ball extends GameObject {
    private int xVelocity;
    private int yVelocity;

    public Ball(GameLogic gameLogic, int xPosition, int yPosition, int xSize, int ySize, Color color) {
        super(gameLogic, xPosition, yPosition, xSize, ySize, color);
        this.xVelocity = Configuration.BALL_VELOCITY_MAX;
        this.yVelocity = Configuration.BALL_VELOCITY_MAX;
    }

    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillOval(xPosition - xSize / 2, yPosition - ySize / 2, xSize, ySize);
    }

    public void move() {
        xPosition += xVelocity;
        if (xPosition < 0) {
            xVelocity = -xVelocity;
        } else if (xPosition >= gameLogic.getWidth()) {
            xVelocity = -xVelocity;
        }
        yPosition += yVelocity;
        if (yPosition < 0) {
            yVelocity = -yVelocity;
        } else if (yPosition >= gameLogic.getHeight()) {
            yVelocity = -yVelocity;
        }
    }

    public int getXVelocity() {
        return xVelocity;
    }

    public void setVelocity(int xVelocity, int yVelocity) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public int getYVelocity() {
        return yVelocity;
    }
}
