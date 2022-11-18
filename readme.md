# **EN COURS DE DEVELOPPEMENT**

# **MineSweeper**

**_MineSweeper_** est un jeu de démineur développé en Java. Il a été proposé lors d'un TP à l'ISEN, lorsque j'étais à l'UQAC. Le but était de faire un démineur avec une interface graphique. J'ai rajouté plusieurs autres fonctionnalités que je trouvais manquantes comme l'enregistrement des joueurs et des scores dans une BDD.


## Description

Vous êtes un grand aventurier, vous n'avez peur de rien !
Vous vous retrouvez face à un champ de mines. Pour aider les démineurs, vous devez leur indiquer les cases contenant des mines.
Il va donc falloir trouver toutes les cases ne contenant pas de mine.
Heureusement, vous serez aidé, une voix dans votre tête vous souffle le nombre de cases contenant une mine voisine à celle que vous avez selectionnée.
Bonne chance et que la chance vous sourit !

Il y a trois modes de jeux et un mode qui permet de personnaliser sa partie :

* Le mode débutant :
    - grille 9 * 9
    - 10 mines

* Le mode normal :
    - grille 16 * 16
    - 40 mines

* Le mode expert :
    - grille 16 * 30
    - 99 mines

* Le mode personnalisé :
    - grille personnalisée
      - Le nombre de lignes L appartient à l'intervalle [9, 24]
      - La nombre de colonnes C appartient à l'intervalle [9, 30]
    - Le nombre de mines M appartient à l'intervalle [10, 0.85 * L x C]
    - Les valeurs par défaut sont : L = 9, C = 19, M = 76

Par défaut vous jouerez en niveau normal

Serez-vous capable de battre le meilleur score de chaque mode ?


## Jouer

Pour jouer au jeu, il faut :
- Executer le fichier /sql/initDB.sql
- Modifier le nom d'utilisateur et le mot de passe qui permettent d'acceder à la BDD dans le fichier /src/Main.java
- Executer le fichier /src/Main.java

## Commandes
- Pour dévoiler une case, utilisez le clic gauche de la souris sur la case correspondante.
- Pour marquer une case comme possédant une mine, effectuez un clic droit sur celle-ci.
- Pour marquer une case d'un point d'interrogation effectuez un deuxième clic droit.
- Pour effacer tout les marquages, effectuez un troisième clic droit

Si vous voulez connaitre les commandes et règles du jeu, vous pouvez consulter le menu Aide->Commandes ou Aide->Règles.

Pour connaitre les meilleurs scores de chaque mode, il faut aller dans le menu Scores. Pour voir vos scores personnels Scores->Personal high scores. Pour voir les meilleurs scores globaux : Scores->High Scores.
