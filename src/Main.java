import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database db = null;
        try {
            db = new Database();
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database: " + e.getMessage());
            System.exit(-1);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci il nome del piatto da inserire: ");
        String nomePiatto = scanner.nextLine();
        System.out.println("Inserisci il prezzo del piatto da inserire: ");
        float prezzo = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Inserisci la quantità del piatto da inserire: ");
        int quantita = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Creiamo la nostra bellissima tabella");
        System.out.println("Inserire nome della tabella: ");
        String tableName = scanner.nextLine();
        System.out.println("Inserire numero di parametri: ");
        int numeroParam = scanner.nextInt();
        scanner.nextLine();
        ArrayList<String> params = new ArrayList<>();
        for (int i = 0; i < numeroParam; i++) {
            System.out.println("Inserire il parametro e i relativi dati (non primary key): ");
            params.add(scanner.nextLine());
        }
        System.out.println("Inserire quantità primary key: ");
        int numeroPrimary = scanner.nextInt();
        scanner.nextLine();
        ArrayList<String> primaryKeys = new ArrayList<>();
        for (int i = 0; i < numeroPrimary; i++) {
            System.out.println("Inserire il primary key: ");
            primaryKeys.add(scanner.nextLine());
        }
        System.out.println("Inserire numero di foreign key: ");
        int numeroForeign = scanner.nextInt();
        scanner.nextLine();
        ArrayList<String[]> foreignKeys = new ArrayList<String[]>();
        for (int i = 0; i < numeroPrimary; i++) {
            String[] temp = new String[3];
            System.out.println("Inserire nome foreign key key: ");
            temp[0] = scanner.nextLine();
            System.out.println("Inserire tabella della foreign key: ");
            temp[1] = scanner.nextLine();
            System.out.println("Inserire campo altra tabella della foreign key: ");
            temp[2] = scanner.nextLine();
        }

        System.out.println("Inserire la nostra bellissima query");
        String query = "SELECT ?, ? FROM Menu WHERE prezzo > ?";


        if(db.insert(nomePiatto, prezzo, quantita))
            System.out.println("Piatto inserito con successo");

        if(db.create(tableName, params, primaryKeys, foreignKeys))
            System.out.println("Tabella creata con successo");

        System.out.println(db.select(query, "nome_piatto", "prezzo", 3.5));
        System.out.println(db.selectAll());
    }
}