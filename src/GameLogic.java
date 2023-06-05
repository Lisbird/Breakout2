import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;
import javax.swing.Timer;

public class GameLogic extends JPanel {
    private List<Brick> bricks;
    private Paddle paddle;
    private Ball ball;
    private int score;
    private int ballCount;

    private Timer timer;
    private GameState state;

    public GameLogic() {
        bricks = new ArrayList<>();
        paddle = new Paddle(this, Configuration.FIELD_X_SIZE / 2, Configuration.PADDLE_Y_POSITION, Configuration.PADDLE_X_SIZE, Configuration.PADDLE_Y_SIZE, Configuration.PADDLE_COLOR);
        ball = new Ball(this, Configuration.FIELD_X_SIZE / 2, Configuration.FIELD_Y_SIZE / 2, Configuration.BALL_X_SIZE, Configuration.BALL_Y_SIZE, Configuration.BALL_COLOR);
        score = 0;
        ballCount = Configuration.BALL_COUNT_INITIAL;
        setPreferredSize(new Dimension(Configuration.FIELD_X_SIZE, Configuration.FIELD_Y_SIZE));

        bricks.add(new Brick(this, Configuration.FIELD_X_SIZE - 320, Configuration.FIELD_X_SIZE - 300, Configuration.BRICK_X_SIZE, Configuration.BRICK_Y_SIZE, Configuration.BRICK_COLOR));
        //bricks.add(new Brick(this, Configuration.FIELD_X_SIZE - 320, Configuration.FIELD_X_SIZE - 290, Configuration.BRICK_X_SIZE, Configuration.BRICK_Y_SIZE, Configuration.BRICK_COLOR));
        setFocusable(true);
        addKeyListener(new BreakoutKeyAdapter());
    }


    public void addBrick(Brick brick) {
        bricks.add(brick);
    }

    public void removeBrick(Brick brick) {
        bricks.remove(brick);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        // paint panel
        super.paintComponent(graphics);
        // configure rendering pipeline: Enable antialiasing and high render quality
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        // render bricks, paddle and ball
        for (Brick brick : bricks) {
            brick.render(graphics);
        }
        paddle.render(graphics);
        ball.render(graphics);
        // synchronize graphics state
        Toolkit.getDefaultToolkit().sync();
    }

    public void start() { //initialize timer & start game -> enum GameState RUNNING
        state = GameState.RUNNING;
        timer = new Timer(Configuration.LOOP_PERIOD, new GameLoop());
        timer.start();
    }

    private void onTick() { //react to the auslöse-ereignis of the timer
        //System.out.println("onTick");
        paddle.move();
        ball.move();
        // check physics and rules
        if (ball.getHitBox().intersects(paddle.getHitBox())) { // ball hits paddle
            ball.setVelocity(ball.getXVelocity(), -ball.getYVelocity());
        } else if (ball.getY() > Configuration.PADDLE_Y_POSITION) { // ball is lost
            // reduce number of balls
            --ballCount;
            if (ballCount <= 0) { // no balls left
                state = GameState.GAME_OVER;
                System.out.printf("Game over: You lost. Score = %d%n", score);
                System.exit(-1);
            } else { // at least one ball left, continue level
                restartWithNewBall();
            }
        }
        repaint(); //repaints the panel new, calls upon paintComponent
    }

   private void restartWithNewBall(){

        paddle.setVelocity(0);
        ball.setPosition(paddle.getX(), paddle.getY() - 10);
        ball.setVelocity(ball.getXVelocity(), -Configuration.BALL_VELOCITY_MAX);
   }

    private class GameLoop implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            onTick();
        }
    }

    private class BreakoutKeyAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent event) {
            onKeyReleased(event);
        }
        @Override
        public void keyPressed(KeyEvent event) {
            onKeyPressed(event);
        }
    }

    void onKeyPressed(KeyEvent event) {
        System.out.println("onkeypressed");
        int key = event.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            paddle.setVelocity(-Configuration.PADDLE_VELOCITY);
        }
        if (key == KeyEvent.VK_RIGHT) {
            paddle.setVelocity(Configuration.PADDLE_VELOCITY);
        }
    }
    void onKeyReleased(KeyEvent event) {
        int key = event.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            paddle.setVelocity(0);
        }
    }
}

