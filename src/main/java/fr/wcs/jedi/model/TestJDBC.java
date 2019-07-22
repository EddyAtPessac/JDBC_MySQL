package fr.wcs.jedi.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TestJDBC {
    /* La liste qui contiendra tous les résultats de nos essais */
    private List<String> messages = new ArrayList<String>();
    private final Logger slf4j = LoggerFactory.getLogger(TestJDBC.class);

    public TestJDBC() {
        /* Chargement du driver JDBC pour MySQL */
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
            /* Gérer les éventuelles erreurs ici. */
            slf4j.error(e.getMessage());
        }


    }

    public List<Jedi> execRequete( HttpServletRequest request ) {
        /* Connexion à la base de données */
        String url = "jdbc:mysql://localhost:3306/starwars"
                +"?serverTimezone=UTC";     // Alors, ca, il le faut, un point c'est tout...
        String utilisateur = "root";
        String motDePasse = "Eddy2222";
        Connection connexion = null;
        List<Jedi> jedilist = new ArrayList<>();
        slf4j.info("Entering in JDBC");

        try {
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            slf4j.info("Connexion established");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }



        try {
            Statement statement = connexion.createStatement();
            /* Exécution d'une requête de lecture */
            ResultSet resultat = statement.executeQuery( "SELECT * FROM jedi_masters;" );
            slf4j.info("Statement passed");

            /* Exécution d'une requête d'écriture */
            // int statut = statement.executeUpdate( "INSERT INTO Utilisateur (email, mot_de_passe, nom, date_inscription) VALUES ('jmarc@mail.fr', MD5('lavieestbelle78'), 'jean-marc', NOW());" );
            /* Récupération des données du résultat de la requête de lecture */

            while ( resultat.next() ) {
                Jedi aJedi=new Jedi();
                slf4j.info("Get a tuple result ");
                aJedi.id=resultat.getInt( "id" );
                aJedi.name=resultat.getString("name");
                aJedi.surname=resultat.getString("surname");
                jedilist.add(aJedi);
            }
            slf4j.info("Get the tuples:");
            for ( Jedi aj : jedilist) {
                slf4j.info(aj.id+" "+aj.name+" "+aj.surname);
            }

        } catch (SQLException e ) {
            /* Gérer les éventuelles erreurs ici */
            slf4j.error(e.getMessage());
        } finally {
            if ( connexion != null )
                try {
                    /* Fermeture de la connexion */
                    connexion.close();
                } catch ( SQLException ignore ) {
                    /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
                }
        }

        return jedilist;
    }
}