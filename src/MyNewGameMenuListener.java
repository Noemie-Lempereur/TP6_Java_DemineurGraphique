import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyNewGameMenuListener implements ActionListener {
    private int niveau;
    private MineSweeperFrame frame;

    public MyNewGameMenuListener(MineSweeperFrame frame, int niveau) {
        this.frame=frame;
        this.niveau=niveau;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(niveau==1){
            frame.dispose();
            frame = new MineSweeperFrame(9,9,10);
        }
        if (niveau==2){
            frame.dispose();
            frame = new MineSweeperFrame(16,16,40);
        }
        if(niveau==3){
            frame.dispose();
            frame = new MineSweeperFrame(16,30,99);
        }
    }
}
