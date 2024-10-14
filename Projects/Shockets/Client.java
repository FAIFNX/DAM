package Projectos.Shockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author pco9330
 */
public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String missatge;

        try (Socket socket = new Socket("localhost", 4);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connexió establerta al servidor.");

            // Captura de missatges de teclat
            while (true) {

                System.out.print("Escriu el missatge (o 'QUIT' per sortir del xat): ");
                missatge = scanner.nextLine();
                out.println(missatge);

                if (missatge.equalsIgnoreCase("QUIT")) {
                    break;
                }
                // Esperar resposta del servidor
                String resposta = in.readLine();
                System.out.println("Servidor: " + resposta);
            }
        } catch (IOException e) {
            System.out.println("No s'ha trobat el servidor.");
        } finally {
            System.out.println("Connexió finalitzada.");
            scanner.close();
        }
    }
}
