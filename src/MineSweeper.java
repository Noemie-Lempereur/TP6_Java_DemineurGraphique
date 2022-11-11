import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {

    // debug flags
    private boolean showMines = false;
    private boolean showNbTouchingMines = false;

    // grid properties
    private int nbRows;

    private int nbCols;
    private Cell[][] grid;  //premier crochet correspond à la ligne et le second à la colonne

    private int nbCasesWithoutMines;
    private int tour;
    private boolean lose;
    private MineSweeperFrame frame;


    public MineSweeper(int nbRows, int nbCols, int nbMines) {
        this.frame = new MineSweeperFrame(nbRows,nbCols,this);
        this.nbRows = nbRows;
        this.nbCols = nbCols;
        this.grid = new Cell[nbRows][nbCols];
        for(int i = 0; i<nbRows;i++){
            for(int j=0; j<nbCols;j++){
                grid[i][j]=new Cell(i,j);
            }
        }
        this.nbCasesWithoutMines = this.nbCols * this.nbRows - nbMines;
        this.tour=0;
        this.lose=false;
        putMines(nbMines);
        print();
    }

    // returns the String representation of a Cell, depending on its attributes and the debug flags
    public String getCellSymbol(Cell cell){

        String symbol = "#";        // default symbol = hidden cell

        if(cell.isThinkMine()){
            symbol="!";
        }

        if(cell.isDontKnow()){
            symbol="?";
        }

        // shows the mine in the cell if the cell is visible or if the showMines flag is on
        if((cell.isVisible() || this.showMines) && cell.isMine()){
            symbol = "*";
        }
        // shows the number of touching mines if the cell is visible or if the showNbTouchingMines flag is on
        else if( cell.isVisible() || this.showNbTouchingMines ){

            // special case of a visible cell : " " is displayed instead of "0"
            if( cell.isVisible() && cell.getNbTouchingMines() == 0){
                symbol = " ";
            }
            else{
                symbol = Integer.toString(cell.getNbTouchingMines());
            }

        }

        return symbol;
    }

    // prints the game grid
    public void print(){

        MineSweeperButton[][] buttonArray = frame.getPanel().getButtonsArray();

        for(int i = 0; i < this.nbRows; i++){

            for(int j= 0; j < this.nbCols; j++){

                Cell cell = this.grid[i][j];
                String cellSymbol = getCellSymbol(cell);
                if(!cellSymbol.equals("#")) {
                    if (cellSymbol.equals("*")) {
                        Icon icon = new ImageIcon("ressources/bomb.png");
                        buttonArray[i][j].setText("");
                        buttonArray[i][j].setIcon(icon);
                    }else {
                        if (cellSymbol.equals("!")) {
                            Icon icon = new ImageIcon("ressources/flag.png");
                            buttonArray[i][j].setText("");
                            buttonArray[i][j].setIcon(icon);
                        }else{
                            if (cellSymbol.equals("?")) {
                                Icon icon = new ImageIcon("ressources/dontKnow.png");
                                buttonArray[i][j].setText("");
                                buttonArray[i][j].setIcon(icon);
                                buttonArray[i][j].setDisabledIcon(icon);
                            }else {
                                Icon icon = new ImageIcon("ressources/"+cellSymbol+".png");
                                buttonArray[i][j].setIcon(icon);
                                buttonArray[i][j].setDisabledIcon(icon);
                                buttonArray[i][j].setText("");
                            }
                        }
                    }
                }else{
                    buttonArray[i][j].setIcon(null);
                    buttonArray[i][j].setText("");
                }
            }
        }
    }

    // returns the neighbors of a Cell at the specified row and col in the grid
    public LinkedList<Cell> getNeighbors(Cell cell){

        LinkedList<Cell> neighbors = new LinkedList<>();
        int row = cell.getRow();
        int col = cell.getCol();

        if(row - 1 >= 0){
            neighbors.add( this.grid[row - 1][col] );

            if(col + 1 < this.nbCols){
                neighbors.add( this.grid[row - 1][col + 1] );
            }

            if(col - 1 >= 0){
                neighbors.add( this.grid[row - 1][col - 1] );
            }
        }

        if(col + 1 < this.nbCols){
            neighbors.add(  this.grid[row][col + 1] );
        }

        if(col - 1 >= 0){
            neighbors.add(  this.grid[row][col - 1] );
        }

        if(row + 1 < this.nbRows){
            neighbors.add( this.grid[row + 1][col] );

            if(col + 1 < this.nbCols){
                neighbors.add(  this.grid[row + 1][col + 1] );
            }

            if(col - 1 >= 0){
                neighbors.add( this.grid[row + 1][col - 1] );
            }
        }

        return neighbors;
    }

    public void putMines(int nbMines) {
        Random random = new Random();
        if(nbRows*nbCols>nbMines){
            int nbMinesPosees = 0;
            while (nbMinesPosees!=nbMines) {
                int row = random.nextInt(nbRows); // ligne aléatoire entre 0 et nbRows
                int col = random.nextInt(nbCols); // colonne aléatoire entre 0 et nbCols
                if (!grid[row][col].isMine()) {
                    grid[row][col].setMine(true);
                    nbMinesPosees++;
                    LinkedList<Cell> neighbors = new LinkedList<>();
                    neighbors = getNeighbors(grid[row][col]);
                    for (Cell neighbor : neighbors) {
                        neighbor.setNbTouchingMines(neighbor.getNbTouchingMines() + 1);
                    }
                }
            }
        }
    }

    public void unveil(int row, int col){
        if(grid[row][col].isMine()){
            grid[row][col].setVisible(true);
            showMines=true;
            print();
            lose=true;
            MineSweeperButton[][] buttonArray = frame.getPanel().getButtonsArray();
            for (int i =0;i<nbRows;i++){
                for(int j=0;j<nbCols;j++){
                    buttonArray[i][j].setEnabled(false);
                }
            }
            print();
        }else{
            if(grid[row][col].getNbTouchingMines()==0){
                LinkedList <Cell> cells = new LinkedList<>();
                cells.add(grid[row][col]);
                while (cells.size()!=0){
                    Cell test = cells.poll();
                    if(getCellSymbol(test).equals("#")||getCellSymbol(test).equals("?")||getCellSymbol(test).equals("!")) {
                        test.setVisible(true);
                        frame.getPanel().getButton(test.getRow(),test.getCol()).setEnabled(false);
                        tour++;
                        if (test.getNbTouchingMines() == 0) {
                            LinkedList<Cell> neighbors = getNeighbors(test);
                            for (Cell neighbor : neighbors) {
                                cells.add(neighbor);
                            }
                        }
                    }
                }
            }else{
                grid[row][col].setVisible(true);
                tour++;
            }
        }
    }


    public Cell[][] getGrid() {
        return grid;
    }

    public boolean isLose() {
        return lose;
    }

    public int getTour() {
        return tour;
    }

    public int getNbCasesWithoutMines() {
        return nbCasesWithoutMines;
    }

    public MineSweeperFrame getFrame() {
        return frame;
    }

    public void setShowMines(boolean showMines) {
        this.showMines = showMines;
    }
}
