
//Classe representant une case du démineur partie logique
public class Cell {
    private final int row;  //la ligne de la case
    private final int col;  //la colonne de la case
    private boolean mine;   //la case contient une mine ?
    private boolean visible;    //est ce que la case a été dévoilée?
    private boolean thinkMine;  //est ce que l'utilisateur pense qu'il y a une mine sur la case
    private boolean dontKnow;   //est ce que l'utilisateur veut mettre un point d'interrogation sur la case
    private int nbTouchingMines;    //combien de mines se trouvent autour

    public Cell(int row, int col) {     //constructeur : permet de créerune nouvelle case
        //initialisation des parametres
        this.row = row;
        this.col = col;
        mine=false;                     //par defaut la case ne possède pas de mines (rajouté ulterieurement)
        nbTouchingMines=0;              //par defaut la case n'a pas de voisine contenant une mine (rajouté ulterieurement)
        visible=false;                  //par defaut une cellule est cachée car non dévoilée
        thinkMine=false;                //par defaut l'utilisateur n'a pas encore donné son avis
        dontKnow=false;                 // par defaut l'utilisateur n'a pas encore donné son avis
    }

    public int getRow() {
        return row;
    }   //getter retourne la ligne de la cellule

    public int getCol() {
        return col;
    }   //getter retourne la colonne de la cellule

    public boolean isMine() {
        return mine;
    }   //getter retourne si la cellule contient une mine

    public boolean isVisible() {
        return visible;
    }   //getter retourne si la cellule a été dévoilée

    public int getNbTouchingMines() {   //getter retourne le nombre de cellules voisines contenant une mine
        return nbTouchingMines;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }   //setter permet de mettre une mine dans la cellule

    public void setVisible(boolean visible) {
        this.visible = visible;
    }   //setter permet de dévoiler la cellule

    public void setNbTouchingMines(int nbTouchingMines) {   //permet de modifier le nombre de cellules voisines contenant une mine
        this.nbTouchingMines = nbTouchingMines;
    }

    public boolean isThinkMine() {   //getter retourne si l'utilisateur pense que la cellule contient une mine
        return thinkMine;
    }

    public boolean isDontKnow() {   //getter retourne si l'utilisateur ne sait pas quoi penser de la cellule
        return dontKnow;
    }

    public void setThinkMine(boolean thinkMine) {   //setter permet de modifier la cellule car l'utilisateur pense que s'est une mine (permet d'ajouter un drapeau lors de l'affichage)
        this.thinkMine = thinkMine;
        if(isThinkMine()) { //si l'utilisateur pense qu'il y a une mine alors il ne peut pas aussi penser qu'il ne sait pas
            this.dontKnow = false;
        }
    }

    public void setDontKnow(boolean dontKnow) { //setter permet de modifier la cellule car l'utilisateur ne sait pas si la cellule contient une mine (permet d'ajouter un point d'interrogation lors de l'affichage)
        this.dontKnow = dontKnow;
        if(isDontKnow()) { //si l'utilisateur ne sait pas quoi penser de la cellule alors il ne peut pas aussi penser qu'il y a une mine
            this.thinkMine = false;
        }
    }
}
