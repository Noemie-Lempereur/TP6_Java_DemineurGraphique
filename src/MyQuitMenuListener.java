import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyQuitMenuListener implements ActionListener {
    private final MineSweeperFrame frame;
    public MyQuitMenuListener(MineSweeperFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
    }
}
