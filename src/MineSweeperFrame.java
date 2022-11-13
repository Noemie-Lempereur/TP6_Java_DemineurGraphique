import javax.swing.*;
import java.awt.*;

public class MineSweeperFrame extends JFrame {
    private final MineSweeperPanel panel;
    private final JLabel labelNbMines;
    private final MineSweeper gameManager;
    private int id;

    public MineSweeperFrame(int row, int col, int nbMines, int idUser){
        super();

        this.id=idUser;

        //set menu
        JMenuBar gameBar = new JMenuBar(); //create JMenu Bar
        //create Menu
        JMenu game = new JMenu("Game");
        JMenu score = new JMenu("Scores");
        //create Menu items for Game
        JMenu newGame = new JMenu("New");
        JMenuItem quitGame = new JMenuItem("Quit");
        //create Menu items for Game
        JMenuItem scoreIndiv = new JMenuItem("Personal high scores");
        JMenuItem scoreGeneral = new JMenuItem("High scores");
        //create Menu items for newGame
        JMenuItem beginner = new JMenuItem("Beginner");
        JMenuItem intermediate = new JMenuItem("Intermediate");
        JMenuItem expert = new JMenuItem("Expert");
        JMenuItem custom = new JMenuItem("Custom");
        newGame.add(beginner);
        newGame.add(intermediate);
        newGame.add(expert);
        newGame.add(custom);
        game.add(newGame);
        game.add(quitGame);
        score.add(scoreIndiv);
        score.add(scoreGeneral);
        gameBar.add(game);
        gameBar.add(score);
        this.setJMenuBar(gameBar);
        //Add actions listeners
        quitGame.addActionListener(new MyQuitMenuListener(this));
        MyNewGameMenuListener beginnerListener = new MyNewGameMenuListener(this,1);
        beginner.addActionListener(beginnerListener);
        MyNewGameMenuListener intermediateListener = new MyNewGameMenuListener(this,2);
        intermediate.addActionListener(intermediateListener);
        MyNewGameMenuListener expertListener = new MyNewGameMenuListener(this,3);
        expert.addActionListener(expertListener);
        MyNewGameMenuListener customListener = new MyNewGameMenuListener(this,4);
        custom.addActionListener(customListener);

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
        this.setTitle("MineSweeper");
        this.setIconImage(new ImageIcon("ressources/bomb.png").getImage());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public MineSweeperPanel getPanel() {
        return panel;
    }

    public void setLabelNbMines(int remainingMines) {
        if(remainingMines<0){
            remainingMines=0;
        }
        this.labelNbMines.setText("Remaining mines : "+remainingMines);
    }

    public MineSweeper getGameManager() {
        return gameManager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
