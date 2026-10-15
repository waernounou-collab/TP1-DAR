package clientPackage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
public class Client3 {
    public static void main(String[] args) {
        // Adresse IP de la machine serveur
        String adresseIP = "192.168.1.17";
        int port = 1234;
        try {
            // 1. Se connecter au serveur
            Socket socket = new Socket(adresseIP, port);
            System.out.println("Connexion au serveur reussie !");
            // 2. Creer les flux
            DataOutputStream out =
                    new DataOutputStream(socket.getOutputStream());
            DataInputStream in =
                    new DataInputStream(socket.getInputStream());
            Scanner clavier = new Scanner(System.in);
            int choix;
            do {
                // 3. Afficher le menu
                System.out.println("\n=== CALCULATRICE TCP ===");
                System.out.println("1. Addition");
                System.out.println("2. Soustraction");
                System.out.println("3. Multiplication");
                System.out.println("4. Division");
                System.out.println("0. Quitter");
                System.out.print("Votre choix : ");
                choix = clavier.nextInt();
                // Envoyer le choix au serveur
                out.writeInt(choix);
                out.flush();
                // 4. Arreter si le choix est zero
                if (choix == 0) {
                    break;
                }
                // 5. Saisir les deux nombres
                System.out.print("Premier nombre : ");
                double a = clavier.nextDouble();
                System.out.print("Deuxieme nombre : ");
                double b = clavier.nextDouble();
                // 6. Envoyer les nombres
                out.writeDouble(a);
                out.writeDouble(b);
                out.flush();
                // 7. Recevoir le statut du serveur
                String statut = in.readUTF();
                if (statut.equals("OK")) {
                    // Recevoir et afficher le resultat
                    double resultat = in.readDouble();
                    System.out.println("Resultat : " + resultat);
                } else {
                    // Recevoir et afficher l'erreur
                    String message = in.readUTF();
                    System.out.println("Erreur : " + message);
                }
            } while (true);
            // 8. Fermer la communication
            clavier.close();
            in.close();
            out.close();
            socket.close();
            System.out.println("Connexion fermee.");
        } catch (IOException e) {
            System.out.println("Erreur de communication : "
                    + e.getMessage());
        }
    }
}