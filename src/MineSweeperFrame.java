import javax.swing.*;
import java.awt.*;

public class MineSweeperFrame extends JFrame {
    private MineSweeperPanel panel;
    private JLabel labelNbMines;
    public MineSweeperFrame(int row, int col, MineSweeper gameManager){
        super();
        this.panel = new MineSweeperPanel(row, col,gameManager);
        this.setLayout(new BorderLayout());
        this.add(panel,BorderLayout.CENTER);
        panel.setVisible(true);
        labelNbMines = new JLabel("Remaining mines : "+gameManager.getRemainingMines(),SwingConstants.CENTER);
        this.add(labelNbMines,BorderLayout.SOUTH);
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
        this.labelNbMines.setText("Remaining mines : "+remainingMines);
    }
}
