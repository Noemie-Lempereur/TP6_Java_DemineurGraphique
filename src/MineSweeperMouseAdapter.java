import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MineSweeperMouseAdapter extends MouseAdapter {

    private final MineSweeperButton button;
    private final MineSweeper gameManager;
    private int nRightClick;


    MineSweeperMouseAdapter(MineSweeperButton button, MineSweeper gameManager){
        this.button=button;
        this.gameManager=gameManager;
        nRightClick=0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!gameManager.isLose() && gameManager.getTour() < gameManager.getNbCasesWithoutMines()) {
            if (e.getButton() == 3) { // if right click
                nRightClick++;
                if (nRightClick == 1) {
                    gameManager.getGrid()[button.getRow()][button.getCol()].setThinkMine(true);
                }
                if (nRightClick == 2) {
                    gameManager.getGrid()[button.getRow()][button.getCol()].setDontKnow(true);
                }
                if (nRightClick == 3) {
                    gameManager.getGrid()[button.getRow()][button.getCol()].setDontKnow(false);
                    gameManager.getGrid()[button.getRow()][button.getCol()].setThinkMine(false);
                    button.setText("");
                    nRightClick = 0;
                }
                gameManager.print();
            }
            if (e.getButton() == 1) {
                gameManager.unveil(button.getRow(), button.getCol());
                button.setEnabled(false);
                gameManager.print();
                if (gameManager.isLose()) {
                    gameManager.setShowMines(true);
                    gameManager.print();
                    gameManager.getFrame().add(new JLabel("Partie finie, vous avez perdu", SwingConstants.CENTER),BorderLayout.NORTH);
                    gameManager.getFrame().pack();
                    gameManager.getFrame().setVisible(true);
                }
                if(gameManager.getTour() == gameManager.getNbCasesWithoutMines()) {
                    gameManager.setShowMines(true);
                    gameManager.print();
                    gameManager.getFrame().add(new JLabel("Partie finie, vous avez gagné", SwingConstants.CENTER),BorderLayout.NORTH);
                    gameManager.getFrame().pack();
                    gameManager.getFrame().setVisible(true);
                }
            }
        }
    }
}
