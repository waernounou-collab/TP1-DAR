package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
public class Server3 {
    public static void main(String[] args) {
        int port = 1234;
        // Remplacer par l'adresse IPv4 reelle du serveur
        String adresseIP = "192.168.1.17";
        try {
            // 1. Recuperer l'adresse IP du serveur
            InetAddress adresse = InetAddress.getByName(adresseIP);
            // 2. Creer une adresse IP avec un port
            InetSocketAddress adresseServeur =
                    new InetSocketAddress(adresse, port);
            // 3. Creer le socket serveur et ecouter sur cette adresse
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(adresseServeur);
            System.out.println("Serveur en attente sur : "
                    + adresseServeur);
            // 4. Attendre la connexion du client
            Socket socket = serverSocket.accept();
            System.out.println("Client connecte !");
            // 5. Creer les flux de communication
            DataInputStream in =
                    new DataInputStream(socket.getInputStream());
            DataOutputStream out =
                    new DataOutputStream(socket.getOutputStream());
            boolean continuer = true;
            while (continuer) {
                // Recevoir le choix du client
                int choix = in.readInt();
                // Le choix 0 termine la communication
                if (choix == 0) {
                    continuer = false;
                    break;
                }
                // Recevoir les deux nombres
                double a = in.readDouble();
                double b = in.readDouble();
                double resultat = 0;
                boolean calculValide = true;
                // 6. Effectuer l'operation demandee
                switch (choix) {
                    case 1:
                        resultat = a + b;
                        break;
                    case 2:
                        resultat = a - b;
                        break;
                    case 3:
                        resultat = a * b;
                        break;
                    case 4:
                        if (b == 0) {
                            calculValide = false;
                        } else {
                            resultat = a / b;
                        }
                        break;
                    default:
                        calculValide = false;
                }
                // 7. Envoyer le resultat ou un message d'erreur
                if (calculValide) {
                    out.writeUTF("OK");
                    out.writeDouble(resultat);
                    System.out.println("Calcul effectue : "
                            + resultat);
                } else {
                    out.writeUTF("ERREUR");
                    if (choix == 4 && b == 0) {
                        out.writeUTF(
                                "Division impossible par zero.");
                    } else {
                        out.writeUTF("Choix invalide.");
                    }
                }
                out.flush();
            }
            // 8. Fermer les ressources
            in.close();
            out.close();
            socket.close();
            serverSocket.close();
            System.out.println("Connexion fermee.");
            System.out.println("Serveur arrete.");

        } catch (IOException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}