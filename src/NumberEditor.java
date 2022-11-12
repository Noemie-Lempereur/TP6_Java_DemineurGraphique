import javax.swing.*;
import java.awt.*;

public class NumberEditor extends JPanel {
    private final JSlider slider;
    private final JTextField text;

    public NumberEditor(String name, int min, int max,int defaut, int spacement){
        //Set label
        JLabel label = new JLabel(name, SwingConstants.CENTER);
        //Set slider
        this.slider = new JSlider(min, max);
        this.slider.setValue(defaut);
        // paint the ticks and tracks
        this.slider.setPaintTrack(true);
        this.slider.setPaintTicks(true);
        this.slider.setPaintLabels(true);
        // set spacing
        this.slider.setMajorTickSpacing(spacement);
        this.slider.setMinorTickSpacing(1);
        //set text
        this.text = new JTextField(""+defaut);
        //set layout
        this.setLayout(new GridLayout(1,3,5 ,5));
        //add to panel
        this.add(label);
        this.add(slider);
        this.add(text);
    }

    public JSlider getSlider() {
        return slider;
    }

    public JTextField getText() {
        return text;
    }
}
