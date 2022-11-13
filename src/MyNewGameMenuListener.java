import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//CHANGER 4 USERID de 0 MineSweeperFrame

public class MyNewGameMenuListener implements ActionListener {
    private final int niveau;
    private MineSweeperFrame frame;

    public MyNewGameMenuListener(MineSweeperFrame frame, int niveau) {
        this.frame=frame;
        this.niveau=niveau;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(niveau==1){
            frame.dispose();
            frame = new MineSweeperFrame(9,9,10,0);
        }
        if (niveau==2){
            frame.dispose();
            frame = new MineSweeperFrame(16,16,40,0);
        }
        if(niveau==3){
            frame.dispose();
            frame = new MineSweeperFrame(16,30,99,0);
        }
        if(niveau==4){

            //Initialize components
            JFrame frameConfig = new JFrame();
            frameConfig.setLayout(new GridLayout(4,1,15,15));
            NumberEditor rows = new NumberEditor("Rows",9,24,9,5);
            NumberEditor cols = new NumberEditor("Columns",9,30,19,5);
            NumberEditor mines = new NumberEditor("Mines",10,139,76,30);
            JButton confirmButton = new JButton("Play");
            //NumberEditor mines = new NumberEditor("Mines",10,0.85*row*col,frameConfig);

            //add components to the frame
            frameConfig.add(rows);
            frameConfig.add(cols);
            frameConfig.add(mines);
            frameConfig.add(confirmButton);

            //initialise listener
            ChangeListener sliderRows = e1 -> {
                rows.getText().setText(""+rows.getSlider().getValue());
                mines.getSlider().setMaximum((int) (0.85*rows.getSlider().getValue()*cols.getSlider().getValue()));
                if(mines.getSlider().getValue()>mines.getSlider().getMaximum()){
                    mines.getSlider().setValue(mines.getSlider().getMaximum());
                    mines.getText().setText(""+mines.getSlider().getMaximum());
                }
            };
            ChangeListener sliderCols = e12 -> {
                cols.getText().setText(""+cols.getSlider().getValue());
                mines.getSlider().setMaximum((int) (0.85*rows.getSlider().getValue()*cols.getSlider().getValue()));
                if(mines.getSlider().getValue()>mines.getSlider().getMaximum()){
                    mines.getSlider().setValue(mines.getSlider().getMaximum());
                    mines.getText().setText(""+mines.getSlider().getMaximum());
                }
            };
            ChangeListener sliderMines = e13 -> mines.getText().setText(""+mines.getSlider().getValue());
            ActionListener textRows = e14 -> {
                String value = rows.getText().getText();
                try {
                    if (Integer.parseInt(value) >= 9 && Integer.parseInt(value)<=24)
                        rows.getSlider().setValue(Integer.parseInt(value));
                    else{
                        rows.getText().setText(""+rows.getSlider().getValue());
                    }
                }catch (Exception excep){
                    rows.getText().setText(""+rows.getSlider().getValue());
                    System.out.println(excep);
                }
            };
            ActionListener textCols = e15 -> {
                String value = cols.getText().getText();
                try {
                    if (Integer.parseInt(value) >= 9 && Integer.parseInt(value)<=30)
                        cols.getSlider().setValue(Integer.parseInt(value));
                    else{
                        cols.getText().setText(""+cols.getSlider().getValue());
                    }
                }catch (Exception excep){
                    cols.getText().setText(""+cols.getSlider().getValue());
                    System.out.println(excep);
                }
            };
            ActionListener textMines = e16 -> {
                String value = mines.getText().getText();
                try {
                    if (Integer.parseInt(value) >= mines.getSlider().getMinimum() && Integer.parseInt(value)<=mines.getSlider().getMaximum())
                        mines.getSlider().setValue(Integer.parseInt(value));
                    else{
                        mines.getText().setText(""+mines.getSlider().getValue());
                    }
                }catch (Exception excep){
                    mines.getText().setText(""+mines.getSlider().getValue());
                    System.out.println(excep);
                }
            };
            ActionListener buttonConfirmListener = e17 -> {
                frame.dispose();
                frameConfig.dispose();
                frame = new MineSweeperFrame(rows.getSlider().getValue(),cols.getSlider().getValue(),mines.getSlider().getValue(),0);
            };

            //add listener
            rows.getSlider().addChangeListener(sliderRows);
            cols.getSlider().addChangeListener(sliderCols);
            mines.getSlider().addChangeListener(sliderMines);
            rows.getText().addActionListener(textRows);
            cols.getText().addActionListener(textCols);
            mines.getText().addActionListener(textMines);
            confirmButton.addActionListener(buttonConfirmListener);

            //config frame
            frameConfig.setSize(new Dimension(600,800));
            frameConfig.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frameConfig.setVisible(true);
        }
    }
}
