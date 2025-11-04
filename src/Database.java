import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    private final Connection connection;

    public Database() throws SQLException {
        String url = "jdbc:sqlite:database/sushi.db";
        connection = DriverManager.getConnection(url);
        System.out.println("Connected to database");
    }

    public String selectAll() {
        String result = "";

        try {
            if(connection == null || !connection.isValid(5)){
                System.err.println("Errore di connessione al database");
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database");
            return null;
        }

        String query = "SELECT * FROM menu";


        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet piatti = statement.executeQuery();
            while (piatti.next()) {
                result += piatti.getString("id_piatto") + "\t";
                result += piatti.getString("nome_piatto") + "\t";
                result += piatti.getString("prezzo") + "\t";
                result += piatti.getString("quantita") + "\n";
            }


        } catch (SQLException e) {
            System.err.println("Errore di query: " + e.getMessage());
            return null;
        }


        return result;
    }

    public boolean insert(String sql, Object... params) {
        try {
            if(connection == null || !connection.isValid(5)){
                System.err.println("Errore di connessione al database");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database");
            return false;
        }

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Errore di query: " + e.getMessage());
            return false;
        }
    }

    public boolean create(String sql, Object... params) {
        try {
            if(connection == null || !connection.isValid(5)){
                System.err.println("Errore di connessione al database");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database");
            return false;
        }

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Errore di query: " + e.getMessage());
            return false;
        }
    }

    public ResultSet select(String sql, Object... params) {
        try {
            if(connection == null || !connection.isValid(5)){
                System.err.println("Errore di connessione al database");
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database");
            return null;
        }

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            return stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Errore di query: " + e.getMessage());
            return null;
        }
    }

    public boolean update(String sql, Object... params) {
        try {
            if(connection == null || !connection.isValid(5)){
                System.err.println("Errore di connessione al database");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database");
            return false;
        }

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Errore di query: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(String sql, Object... params) {
        try {
            if(connection == null || !connection.isValid(5)){
                System.err.println("Errore di connessione al database");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database");
            return false;
        }

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Errore di query: " + e.getMessage());
            return false;
        }
    }

    // si sono tutti identici
}