import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Break Out");
        // exit application on close
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // show window in the center of the screen
        frame.setLocationRelativeTo(null);
        // window is not resizable
        frame.setResizable(false);

        // create game logic object
        GameLogic gameLogic = new GameLogic();
        // add panel to window
        frame.add(gameLogic);

        // arrange components
        frame.pack();
        // show window
        frame.setVisible(true);

        gameLogic.start();
    }
}