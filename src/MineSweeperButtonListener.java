import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MineSweeperButtonListener implements ActionListener  {

    private final MineSweeperButton button;
    private final MineSweeper gameManager;

    public MineSweeperButtonListener(MineSweeperButton button, MineSweeper gameManager){
        this.button=button;
        this.gameManager=gameManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameManager.unveil(button.getRow(), button.getCol());
        gameManager.print();
    }
}
