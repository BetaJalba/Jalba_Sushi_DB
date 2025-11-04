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

        String query;
        int mod;
        do{
            System.out.println("Premere 1 per vedere il menu" +
                    "\nPremere 2  per aggiungere un piatto al menu" +
                    "\nPremere 3 per moficare un piatto del menu" +
                    "\nPremere 4 per cancellare un piatto dal menu" +
                    "\nPremere 5 per uscire dal menu");
            mod = scanner.nextInt();

            switch (mod) {
                case 1:
                    System.out.printf(db.selectAll());
                    break;
                case 2:
                    query = "INSERT INTO menu(nome_piatto, prezzo, quantita) VALUES (?, ?, ?)";

                    System.out.println("Inserisci un nome del nuovo piatto");
                    String nome = scanner.next();

                    System.out.println("Inserisci il prezzo del nuovo piatto");
                    float prezzo = scanner.nextFloat();

                    System.out.println("Inserisci la quantità del nuovo piatto");
                    int quant = scanner.nextInt();

                    db.insert(query, nome, prezzo, quant);
                    break;
                case 3:
                    query = "UPDATE menu SET nome_piatto = ?, prezzo = ?, quantita = ? WHERE id_piatto = ?";
                    System.out.printf(db.selectAll());

                    System.out.println("inserire l'id del piatto che si vuole modificare");
                    int id = scanner.nextInt();

                    System.out.println("nuovo nome");
                    String newNome = scanner.next();

                    System.out.println("nuovo prezzo");
                    float newPrezzo = scanner.nextFloat();

                    System.out.println("nuovo nome");
                    int newQuantita = scanner.nextInt();

                    db.update(query, id, newNome, newPrezzo, newQuantita);
                    break;
                case 4:
                    query = "DELETE FROM menu WHERE id_piatto = ?";
                    System.out.printf(db.selectAll());

                    System.out.println("selezionare l'id del piatto che si vuole cancellare");
                    int deletePiatto = scanner.nextInt();

                    db.delete(query, deletePiatto);
                    break;
            }
        } while(mod != 5);
    }
}