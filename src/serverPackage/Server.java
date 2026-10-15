
package serverPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        try {
            // Première étape : créer le serveur
            ServerSocket serverSocket = new ServerSocket(1234);

            System.out.println(
                    "Je suis un serveur en attente la connexion d'un client "
            );

            // Attendre la connexion d'un client
            Socket socket = serverSocket.accept();

            // Deuxième étape : le client est connecté
            System.out.println("un client est connecté");

            // Dernière étape : fermer les sockets
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
