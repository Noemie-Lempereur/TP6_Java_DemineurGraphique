import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MineSweeperButton extends JButton {
    private int row;
    private int col;
    public MineSweeperButton(int row,int col, MineSweeper gameManager) {
        this.row=row;
        this.col=col;
        this.setPreferredSize(new Dimension(50,50));
        MineSweeperMouseAdapter mouseAdapter = new MineSweeperMouseAdapter(this,gameManager);
        this.addMouseListener (mouseAdapter);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
