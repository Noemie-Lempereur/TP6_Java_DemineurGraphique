import javax.swing.*;
import java.awt.*;

public class MineSweeperFrame extends JFrame {
    private MineSweeperPanel panel;
    private JLabel labelNbMines;
    private MineSweeper gameManager;

    public MineSweeperFrame(int row, int col, int nbMines){
        super();

        //set menu
        JMenuBar gameBar = new JMenuBar(); //create JMenu Bar
        //create Menu
        JMenu game = new JMenu("Game");
        //create Menu items for Game
        JMenu newGame = new JMenu("New");
        JMenuItem quitGame = new JMenuItem("Quit");
        //create Menu items for newGame
        JMenuItem beginner = new JMenuItem("Beginner");
        JMenuItem intermediate = new JMenuItem("Intermediate");
        JMenuItem expert = new JMenuItem("Expert");
        newGame.add(beginner);
        newGame.add(intermediate);
        newGame.add(expert);
        game.add(newGame);
        game.add(quitGame);
        gameBar.add(game);
        this.setJMenuBar(gameBar);
        //Add actions listeners
        quitGame.addActionListener(new MyQuitMenuListener(this));
        MyNewGameMenuListener beginnerListener = new MyNewGameMenuListener(this,1);
        beginner.addActionListener(beginnerListener);
        MyNewGameMenuListener intermediateListener = new MyNewGameMenuListener(this,2);
        intermediate.addActionListener(intermediateListener);
        MyNewGameMenuListener expertListener = new MyNewGameMenuListener(this,3);
        expert.addActionListener(expertListener);

        //set panel game
        this.panel = new MineSweeperPanel(row, col,this);
        this.setLayout(new BorderLayout());
        this.add(panel,BorderLayout.CENTER);
        panel.setVisible(true);

        gameManager = new MineSweeper(row,col,nbMines,this);

        //set remining mines
        labelNbMines = new JLabel("Remaining mines : "+gameManager.getRemainingMines(),SwingConstants.CENTER);
        this.add(labelNbMines,BorderLayout.SOUTH);

        //set frame
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public MineSweeperPanel getPanel() {
        return panel;
    }

    public JLabel getLabelNbMines() {
        return labelNbMines;
    }

    public void setLabelNbMines(int remainingMines) {
        if(remainingMines<0){
            remainingMines=0;
        }
        this.labelNbMines.setText("Remaining mines : "+remainingMines);
    }

    public void setGameManager(MineSweeper gameManager) {
        this.gameManager = gameManager;
    }

    public MineSweeper getGameManager() {
        return gameManager;
    }
}
