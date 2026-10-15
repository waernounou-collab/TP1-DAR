package serverPackage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Server2 {
    public static void main(String[] args) {
        try {
            // 1. Creer le serveur sur le port 1234
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Serveur en attente d'un client...");
            // 2. Attendre la connexion du client
            Socket socket = serverSocket.accept();
            System.out.println("Client connecte !");
            // 3. Creer les flux de communication
            DataInputStream in =
                    new DataInputStream(socket.getInputStream());
            DataOutputStream out =
                    new DataOutputStream(socket.getOutputStream());
            // 4. Recevoir et traiter plusieurs entiers
            int x;
            do {
                // Recevoir un entier envoye par le client
                x = in.readInt();
                // Si x vaut zero, arreter la communication
                if (x == 0) {
                    System.out.println("Fin de la communication.");
                    break;
                }
                // Effectuer le calcul cote serveur
                int resultat = x * 5;
                System.out.println("Entier recu : " + x);
                System.out.println("Resultat : " + resultat);
                // Envoyer le resultat au client
                out.writeInt(resultat);
                out.flush();
            } while (true);
            // 5. Fermer les ressources
            in.close();
            out.close();
            socket.close();
            serverSocket.close();
            System.out.println("Serveur arrete.");
        } catch (IOException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}