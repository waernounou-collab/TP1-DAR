package clientPackage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
public class Client2 {
    public static void main(String[] args) {
        try {
            // 1. Se connecter au serveur
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Connexion au serveur reussie !");
            // 2. Creer les flux de communication
            DataOutputStream out =
                    new DataOutputStream(socket.getOutputStream());
            DataInputStream in =
                    new DataInputStream(socket.getInputStream());
            // 3. Permettre a l'utilisateur de saisir des entiers
            Scanner clavier = new Scanner(System.in);
            int x;
            do {
                System.out.print(
                        "Saisir un entier (0 pour quitter) : ");
                x = clavier.nextInt();
                // 4. Envoyer l'entier au serveur
                out.writeInt(x);
                out.flush();
                // 5. Si x vaut zero, terminer
                if (x == 0) {
                    System.out.println("Arret du client.");
                    break;
                }
                // 6. Recevoir le resultat du serveur
                int resultat = in.readInt();
                // 7. Afficher le resultat
                System.out.println("Resultat recu : " + resultat);
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