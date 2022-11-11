import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MineSweeperButton extends JButton {
    private int row;
    private int col;
    public MineSweeperButton(int row,int col, MineSweeperFrame frame) {
        this.row=row;
        this.col=col;
        this.setPreferredSize(new Dimension(40,40));
        MineSweeperMouseAdapter mouseAdapter = new MineSweeperMouseAdapter(this,frame);
        this.addMouseListener (mouseAdapter);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
