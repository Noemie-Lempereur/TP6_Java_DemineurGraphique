import javax.swing.*;
import java.awt.*;

public class MineSweeperFrame extends JFrame {
    private MineSweeperPanel panel;
    public MineSweeperFrame(int row, int col, MineSweeper gameManager){
        super();
        this.panel = new MineSweeperPanel(row, col,gameManager);
        this.add(panel);
        panel.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public MineSweeperPanel getPanel() {
        return panel;
    }
}
