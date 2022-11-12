import javax.swing.*;
import java.awt.*;

public class MineSweeperPanel extends JPanel {
    private final MineSweeperButton[][] buttonsArray;
    public MineSweeperPanel(int row, int col, MineSweeperFrame frame) {
        buttonsArray = new MineSweeperButton[row][col];
        this.setLayout(new GridLayout(row,col,2,2));
        for (int i = 0; i < row ; i++){
            for (int j = 0; j < col ; j++){
                MineSweeperButton button = new MineSweeperButton(i,j,frame);
                buttonsArray[i][j]=button;
                this.add(button);
            }
        }
    }

    public MineSweeperButton[][] getButtonsArray() {
        return buttonsArray;
    }

    public MineSweeperButton getButton(int row,int col){
        return buttonsArray[row][col];
    }
}
