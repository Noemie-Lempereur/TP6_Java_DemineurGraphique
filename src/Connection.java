import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Connection {
    private final int row;
    private final int col;
    private final int nbMines;
    public Connection (int row, int col, int nbMines) {
        this.row=row;
        this.col=col;
        this.nbMines=nbMines;

        JFrame connect = new JFrame();
        connect.setLayout(new GridLayout(4,1,5,5));

        //create components
        JLabel userLabel = new JLabel("Username : ");
        JLabel passwordLabel = new JLabel("Password : ");
        JTextField userText = new JTextField();
        userText.setPreferredSize(new Dimension(200, 30));
        JTextField passwordText = new JTextField();
        passwordText.setPreferredSize(new Dimension(200, 30));
        JButton connection = new JButton("Sign in");
        JButton inscription = new JButton("Sign up");
        JButton invite = new JButton("Play as a guest");

        //create Panels
        JPanel userPanel = new JPanel(new FlowLayout());
        JPanel passwordPanel = new JPanel(new FlowLayout());
        JPanel connectionPanel = new JPanel(new FlowLayout());
        JPanel notConnectionPanel = new JPanel(new FlowLayout());

        //Add components to panels
        userPanel.add(userLabel);
        userPanel.add(userText);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordText);
        connectionPanel.add(connection);
        notConnectionPanel.add(inscription);
        notConnectionPanel.add(invite);

        connect.add(userPanel);
        connect.add(passwordPanel);
        connect.add(connectionPanel);
        connect.add(notConnectionPanel);

        //Add listener
        ActionListener buttonConfirmListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idUser = 0;
                //Connexion BDD
                java.sql.Connection connection = null;
                Statement statement = null;
                ResultSet resultSet = null;
                try{
                    connection = DriverManager.getConnection(Main.url, Main.user, Main.password);
                    statement = connection.createStatement();
                    String query = "SELECT id_player FROM player WHERE nom='"+userText.getText()+"' AND password='"+passwordText.getText()+"';";
                    resultSet = statement.executeQuery(query);
                    resultSet.next();
                    idUser = resultSet.getInt("id_player");
                    connect.dispose();
                    MineSweeperFrame frame = new MineSweeperFrame(getRow(), getCol(), getNbMines(), idUser);
                }
                catch(SQLException except){
                    except.printStackTrace();
                }
            }
        };
        connection.addActionListener(buttonConfirmListener);

        ActionListener buttonInscriptionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idUser = 0;
                //Connexion BDD
                java.sql.Connection connection = null;
                Statement statement = null;
                ResultSet resultSet = null;
                try{
                    connect.setVisible(false);
                    JFrame inscript = new JFrame();
                    inscript.setLayout(new GridLayout(4,1,5,5));

                    //create components
                    JLabel userLabel = new JLabel("Username : ");
                    JLabel passwordLabel = new JLabel("Password : ");
                    JTextField userText = new JTextField();
                    userText.setPreferredSize(new Dimension(200, 30));
                    JTextField passwordText = new JTextField();
                    passwordText.setPreferredSize(new Dimension(200, 30));
                    JButton confirmInscript = new JButton("Sign up");
                    JButton connectionButton = new JButton("Sign in");
                    JButton inviteButton = new JButton("Play as a guest");
                    JPanel userPanel = new JPanel(new FlowLayout());
                    JPanel passwordPanel = new JPanel(new FlowLayout());
                    JPanel signUpPanel = new JPanel(new FlowLayout());
                    JPanel buttonPannel = new JPanel(new FlowLayout());

                    userPanel.add(userLabel);
                    userPanel.add(userText);
                    passwordPanel.add(passwordLabel);
                    passwordPanel.add(passwordText);
                    signUpPanel.add(confirmInscript);
                    buttonPannel.add(connectionButton);
                    buttonPannel.add(inviteButton);
                    inscript.add(userPanel);
                    inscript.add(passwordPanel);
                    inscript.add(signUpPanel);
                    inscript.add(buttonPannel);
                    ActionListener buttonInscriptListener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int idUser = 0;
                            //Connexion BDD
                            java.sql.Connection connection = null;
                            Statement statement = null;
                            ResultSet resultSet = null;
                            try{
                                if(!userText.getText().equals("")&&!passwordText.getText().equals("")) {
                                    connection = DriverManager.getConnection(Main.url, Main.user, Main.password);
                                    statement = connection.createStatement();
                                    String query = "SELECT COUNT(id_player) FROM player WHERE nom='" + userText.getText() + "' AND password='" + passwordText.getText() + "';";
                                    resultSet = statement.executeQuery(query);
                                    resultSet.next();
                                    if (resultSet.getInt(1) == 0) {
                                        //create new player
                                        query = "INSERT INTO `minesweeper`.`player` (`nom`,`password`) VALUES (\"" + userText.getText() + "\", \"" + passwordText.getText() + "\");";
                                        statement.execute(query);
                                        query = "SELECT id_player FROM player WHERE nom='"+userText.getText()+"' AND password='"+passwordText.getText()+"';";
                                        resultSet = statement.executeQuery(query);
                                        resultSet.next();
                                        idUser = resultSet.getInt("id_player");
                                        if (idUser != 0) {
                                            inscript.dispose();
                                            connect.dispose();
                                            MineSweeperFrame frame = new MineSweeperFrame(getRow(), getCol(), getNbMines(), idUser);
                                        }
                                    }
                                }
                            }
                            catch(SQLException except){
                                except.printStackTrace();
                            }
                        }
                    };
                    confirmInscript.addActionListener(buttonInscriptListener);

                    ActionListener buttonInvitListener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int idUser = 1;
                            connect.dispose();
                            inscript.dispose();
                            MineSweeperFrame frame = new MineSweeperFrame(getRow(),getCol(),getNbMines(),idUser);
                        }
                    };
                    inviteButton.addActionListener(buttonInvitListener);

                    ActionListener buttonConnectListener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            inscript.dispose();
                            connect.setVisible(true);
                        }
                    };
                    connectionButton.addActionListener(buttonConnectListener);

                    inscript.pack();
                    inscript.setTitle("Sign up");
                    inscript.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    inscript.setVisible(true);
                }
                catch(Exception except){
                    except.printStackTrace();
                }
            }
        };
        inscription.addActionListener(buttonInscriptionListener);

        ActionListener buttonInviteListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idUser = 1;
                connect.dispose();
                MineSweeperFrame frame = new MineSweeperFrame(getRow(),getCol(),getNbMines(),idUser);
            }
        };
        invite.addActionListener(buttonInviteListener);


        connect.pack();
        connect.setTitle("Sign in");
        connect.setDefaultCloseOperation(EXIT_ON_CLOSE);
        connect.setVisible(true);
    }


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getNbMines() {
        return nbMines;
    }

}
