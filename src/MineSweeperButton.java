import javax.swing.*;
import java.awt.*;

public class MineSweeperButton extends JButton {
    private int row;
    private int col;
    public MineSweeperButton(int row,int col, MineSweeper gameManager) {
        this.row=row;
        this.col=col;
        this.setPreferredSize(new Dimension(50,50));
        MineSweeperButtonListener listener = new MineSweeperButtonListener(this,gameManager);
        this.addActionListener(listener);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
