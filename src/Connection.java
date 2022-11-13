import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

//Cette classe permet d'afficher les fenetres de connexion et d'inscription
//Suite à la connexion ou à l'inscription, la classe MineSweeperFrame va être appelée

public class Connection {
    private final int row;  //le nombre de lignes que contient la partie qui sera lancée automatiquement
    private final int col;  //le nombre de colonnes que contient la partie qui sera lancée automatiquement
    private final int nbMines;  //le nombre de mines que contient la partie qui sera lancée automatiquement
    public Connection (int row, int col, int nbMines) {
        //initialisation des paramètres
        this.row=row;
        this.col=col;
        this.nbMines=nbMines;

        //création de la fenêtre de connexion
        JFrame connect = new JFrame();
        connect.setLayout(new GridLayout(4,1,5,5)); //la fenêtre de connexion se décompose en un tableau de 4lignes et 1 colonne pour mettre les différents composants

        //Création des composants
        //Création des composants de type texte en lecture simple
        JLabel userLabel = new JLabel("Username : ");   //pour indiquer à l'utilisateur de rentrer son pseudo dans la zone de texte
        JLabel passwordLabel = new JLabel("Password : ");   //pour indiquer à l'utilisateur de rentrer son mot de passe dans la zone de texte
        //Création des composants de type zone de texte où l'utilisateur peut écrire
        JTextField userText = new JTextField();
        userText.setPreferredSize(new Dimension(200, 30));  //initialisation de la taille de la zone de texte
        JTextField passwordText = new JTextField();
        passwordText.setPreferredSize(new Dimension(200, 30));  //initialisation de la taille de la zone de texte
        //Création des composants de type bouton
        JButton connection = new JButton("Sign in");    //bouton de connexion
        JButton inscription = new JButton("Sign up");   //bouton d'inscription
        JButton invite = new JButton("Play as a guest");    //bouton pour jouer en tant qu'invité

        //creation de panels pour organiser les composants
        //Les panels sont ensuite ajouté à la fenêtre
        JPanel userPanel = new JPanel(new FlowLayout());
        JPanel passwordPanel = new JPanel(new FlowLayout());
        JPanel connectionPanel = new JPanel(new FlowLayout());
        JPanel notConnectionPanel = new JPanel(new FlowLayout());

        //Ajout des composants au panel correspondant
        //UserPanel
        userPanel.add(userLabel);
        userPanel.add(userText);
        //PasswordPanel
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordText);
        //ConnectiionPanel
        connectionPanel.add(connection);
        //NotConnectionPanel
        notConnectionPanel.add(inscription);
        notConnectionPanel.add(invite);

        //Ajouts des panels à la fenêtre
        connect.add(userPanel);
        connect.add(passwordPanel);
        connect.add(connectionPanel);
        connect.add(notConnectionPanel);

        //Ajout des listeners pour écouter les bouttons et réagir au click
        ActionListener buttonConfirmListener = new ActionListener() {       //action listener pour le bouton de confirmation
            @Override
            public void actionPerformed(ActionEvent e) {
                int idUser = 0;
                //Connexion BDD
                java.sql.Connection connection = null;
                Statement statement = null;
                ResultSet resultSet = null;
                try{
                    connection = DriverManager.getConnection(Main.url, Main.user, Main.password);   //connexion à la BDD avec les parametres generaux de connexion
                    statement = connection.createStatement();
                    //Nous voulons recuperer l'id du joueur
                    String query = "SELECT id_player FROM player WHERE nom='"+userText.getText()+"' AND password='"+passwordText.getText()+"';";
                    resultSet = statement.executeQuery(query);
                    resultSet.next();
                    idUser = resultSet.getInt("id_player");
                    connect.dispose();  //La connexion est réussie, on ferme la fenetre de connexion
                    MineSweeperFrame frame = new MineSweeperFrame(getRow(), getCol(), getNbMines(), idUser);    //on lance la partie par defaut
                }
                catch(SQLException except){
                    except.printStackTrace();
                }
            }
        };
        connection.addActionListener(buttonConfirmListener);    //on ajoute le listener au bouton de connexion

        ActionListener buttonInscriptionListener = new ActionListener() {       //action listener pourle bouton de confirmation
            @Override
            public void actionPerformed(ActionEvent e) {
                int idUser = 0;
                //Connexion BDD
                java.sql.Connection connection = null;
                Statement statement = null;
                ResultSet resultSet = null;
                try{
                    connect.setVisible(false);  //on cache la fenetre de connexion
                    JFrame inscript = new JFrame(); //n crée une nouvelle fenetre d'inscription
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
                    inscript.setIconImage(new ImageIcon("ressources/bomb.png").getImage());
                    inscript.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    inscript.setVisible(true);
                }
                catch(Exception except){
                    except.printStackTrace();
                }
            }
        };
        inscription.addActionListener(buttonInscriptionListener);

        ActionListener buttonInviteListener = new ActionListener() {       //action listener pour le bouton d'invite
            @Override
            public void actionPerformed(ActionEvent e) {
                int idUser = 1;
                connect.dispose();
                MineSweeperFrame frame = new MineSweeperFrame(getRow(),getCol(),getNbMines(),idUser);
            }
        };
        invite.addActionListener(buttonInviteListener);    //on ajoute le listener au bouton d'invite


        connect.pack();     //ajuste la taille de la fenetre par rapport aux panels et composants
        connect.setTitle("Sign in");    //titre de la fenetre
        connect.setIconImage(new ImageIcon("ressources/bomb.png").getImage());  //permet d'ajouter l'icone de la fenetre
        connect.setDefaultCloseOperation(EXIT_ON_CLOSE);        //permet la fermeture des fenetres lors du click sur la croix
        connect.setVisible(true);       //affiche la fenetre de connexion
    }


    public int getRow() {
        return row;
    }   //getter retourne le nombre de lignes que contient la partie par defaut

    public int getCol() {
        return col;
    }   //getter retourne le nombre de colonnes que contient la partie par defaut

    public int getNbMines() {
        return nbMines;
    }   //getter retourne le nombre de mines que contient la partie par defaut

}
