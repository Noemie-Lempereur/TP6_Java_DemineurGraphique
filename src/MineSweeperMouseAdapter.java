import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MineSweeperMouseAdapter extends MouseAdapter {

    private final MineSweeperButton button;
    private final MineSweeperFrame frame;
    private int nRightClick;


    MineSweeperMouseAdapter(MineSweeperButton button, MineSweeperFrame frame){
        this.button=button;
        this.frame=frame;
        nRightClick=0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!frame.getGameManager().isLose() && frame.getGameManager().getTour() < frame.getGameManager().getNbCasesWithoutMines()) {
            if (e.getButton() == 3) { // if right click
                nRightClick++;
                if (nRightClick == 1) {
                    frame.getGameManager().setRemainingMines(frame.getGameManager().getRemainingMines()-1);
                    frame.getGameManager().getGrid()[button.getRow()][button.getCol()].setThinkMine(true);
                }
                if (nRightClick == 2) {
                    frame.getGameManager().setRemainingMines(frame.getGameManager().getRemainingMines()+1);
                    frame.getGameManager().getGrid()[button.getRow()][button.getCol()].setDontKnow(true);
                }
                if (nRightClick == 3) {
                    frame.getGameManager().getGrid()[button.getRow()][button.getCol()].setDontKnow(false);
                    frame.getGameManager().getGrid()[button.getRow()][button.getCol()].setThinkMine(false);
                    button.setText("");
                    nRightClick = 0;
                }
                frame.getGameManager().getFrame().setLabelNbMines(frame.getGameManager().getRemainingMines());
                frame.getGameManager().print();
            }
            if (e.getButton() == 1) {
                frame.getGameManager().unveil(button.getRow(), button.getCol());
                button.setEnabled(false);
                frame.getGameManager().print();
                if (frame.getGameManager().isLose()) {
                    frame.getGameManager().setShowMines(true);
                    frame.getGameManager().print();
                    frame.getGameManager().getFrame().add(new JLabel("Partie finie, vous avez perdu", SwingConstants.CENTER),BorderLayout.NORTH);
                    frame.getGameManager().getFrame().pack();
                    frame.getGameManager().getFrame().setVisible(true);
                }
                if(frame.getGameManager().getTour() == frame.getGameManager().getNbCasesWithoutMines()) {
                    frame.getGameManager().setShowMines(true);
                    frame.getGameManager().print();
                    frame.getGameManager().getFrame().add(new JLabel("Partie finie, vous avez gagné", SwingConstants.CENTER),BorderLayout.NORTH);
                    frame.getGameManager().getFrame().pack();
                    frame.getGameManager().getFrame().setVisible(true);
                }
            }
        }
    }
}
