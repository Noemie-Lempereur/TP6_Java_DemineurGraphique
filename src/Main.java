public class Main {
    static String url = "jdbc:mysql://localhost/minesweeper";   //URL de la BDD
    static String user = "guest";   //user permettant de se connecter à la BDD
    static String password = "guest";   //password du user permettant de se connecter à la BDD
    public static void main(String[] args) {
        Connection connection = new Connection(16,16,40);
    }
}