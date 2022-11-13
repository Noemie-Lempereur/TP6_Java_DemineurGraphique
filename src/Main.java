public class Main {
    static String url = "jdbc:mysql://localhost/minesweeper";
    static String user = "guest";
    static String password = "guest";
    public static void main(String[] args) {
        Connection connection = new Connection(16,16,40);
    }
}