package Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Servidor que gestiona les connexions d'usuari i mostra els missatges rebuts.
 * Quan l'usuari es desconecta, espera una nova connexió.
 */
public class Servidor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (ServerSocket serverSocket = new ServerSocket(4)) {
            System.out.println("Servidor en marxa. Esperant connexions...");

            while (true) {
                try (Socket socket = serverSocket.accept();
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                    System.out.println(
                            "Usuari connectat des de la IP: " + socket.getInetAddress() + " port " + socket.getPort());

                    String missatge;
                    while ((missatge = in.readLine()) != null) {
                        if (missatge.equalsIgnoreCase("QUIT")) {
                            System.out.println("Usuari desconnectat");
                            break;
                        }
                        System.out.println("Missatge rebut: " + missatge);
                        while (true) {
                            System.out.print("Escriu el missatge (o 'QUIT' per sortir del Xat): ");
                            missatge = scanner.nextLine();
                            out.println(missatge);

                            if (missatge.equalsIgnoreCase("QUIT")) {
                                break;
                            }

                            // Esperar resposta del Client
                            String resposta = in.readLine();
                            System.out.println("Client: " + resposta);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error de comunicació amb l'usuari. Esperant nova connexió...");
                }
            }
        } catch (IOException e) {
            System.out.println("Error en iniciar el servidor.");
        }
    }
}
