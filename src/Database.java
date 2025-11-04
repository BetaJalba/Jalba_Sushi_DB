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

        //Controlla connessione al database
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

    public boolean insert(String nomePiatto, float prezzo, int quantita) {

        //Controlla connessione al database
        try {
            if(connection == null || !connection.isValid(5)){
                System.err.println("Errore di connessione al database");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database");
            return false;
        }

        String query = "INSERT INTO menu(nome_piatto, prezzo, quantita) VALUES (?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, nomePiatto);
            statement.setFloat(2, prezzo);
            statement.setInt(3, quantita);

            statement.executeUpdate();


        } catch (SQLException e) {
            System.err.println("Errore di query: " + e.getMessage());
            return false;
        }


        return true;
    }

    public boolean create(String tableName, ArrayList<String> params, ArrayList<String> primaryKeys, ArrayList<String[]> foreignKeys) {
        //Controlla connessione al database
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
            Statement statement = connection.createStatement();

            String query = "CREATE TABLE " + tableName + " (";

            for (int i = 0; i < params.size(); i++) {
                query += params.get(i);
                query += ",";
            }

            query += "PRIMARY KEY (";

            for (int i = 0; i < primaryKeys.size(); i++) {
                query += primaryKeys.get(i);

                if (i != primaryKeys.size() - 1)
                    query += ",";
            }

            query += "),";

            for (int i = 0; i < foreignKeys.size(); i++) {
                query += "FOREIGN KEY (" + foreignKeys.get(i)[0] + ") REFERENCES " + foreignKeys.get(i)[1] + "(" + foreignKeys.get(i)[2] + ")";
                if (i != foreignKeys.size() - 1)
                    query += ",";
            }

            query += ")";

        } catch (SQLException e) {
            System.err.println("Errore di query: " + e.getMessage());
            return false;
        }


        return true;
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

    public int update(String sql, Object... params) {
        try {
            if(connection == null || !connection.isValid(5)){
                System.err.println("Errore di connessione al database");
                return -1;
            }
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database");
            return -1;
        }

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Errore di query: " + e.getMessage());
            return -1;
        }
    }

    public int delete(String sql, Object... params) {
        try {
            if(connection == null || !connection.isValid(5)){
                System.err.println("Errore di connessione al database");
                return -1;
            }
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database");
            return -1;
        }

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Errore di query: " + e.getMessage());
            return -1;
        }
    }
}