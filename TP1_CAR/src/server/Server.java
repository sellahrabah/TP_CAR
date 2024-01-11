package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        Socket s;

        try {
            ServerSocket serverSocket = new ServerSocket(2158);

            while (true) {
                s = serverSocket.accept();
                System.out.println("Serveur prêt à accepter des connexions sur le port 2121\r\n");

                // Obtention du flux d'entrée du client
                InputStream inStream = s.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(inStream));

                // Obtention du flux de sortie vers le client
                OutputStream out = s.getOutputStream();

                // Demande du nom au client
                out.write("220 Service ready \r\n".getBytes());
                String nom = in.readLine();
                System.out.println("Client a envoyé le nom : " + nom);

                // Demande du mot de passe au client
                out.write("331 User name OK\r\n".getBytes());
                String motDePasse = in.readLine();
                out.write("331 User login \r\n".getBytes());
                System.out.println("Client a envoyé le mot de passe : " + motDePasse);
                String nom1 = in.readLine();
                if (nom1.equalsIgnoreCase("quit")) {
                    System.out.println("Le client a demandé la fermeture de la connexion.");
                    s.close();
                }

                
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
