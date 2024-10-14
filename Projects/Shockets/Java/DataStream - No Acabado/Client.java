package Java.DataStream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client que es connecta al servidor per a enviar i rebre dades.
 */
public class Client {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String missatge;

        try (Socket socket = new Socket("localhost", 4);
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                DataInputStream in = new DataInputStream(socket.getInputStream())) {

            System.out.println("Connexió establerta al servidor.");
            while (true) {
                missatge = in.readUTF(); // Leer el mensaje del servidor
                System.out.println("Servidor: " + missatge);

                // Dependiendo del tipo de mensaje recibido, envía el tipo de dato apropiado
                switch (missatge) {
                    case "Demana un enter":
                        System.out.print("Introdueix un enter: ");
                        int enteredInt = scanner.nextInt();
                        out.writeInt(enteredInt); // Enviar el entero al servidor
                        break;
                    case "Demana un double":
                        System.out.print("Introdueix un double: ");
                        double enteredDouble = scanner.nextDouble();
                        out.writeDouble(enteredDouble); // Enviar el double al servidor
                        break;
                    case "Demana un caràcter":
                        System.out.print("Introdueix un caràcter: ");
                        char enteredChar = scanner.next().charAt(0);
                        out.writeChar(enteredChar); // Enviar el carácter al servidor
                        break;
                    case "Sortir":
                        System.out.println("Saliendo del client.");
                        return; // Salir del bucle y terminar el cliente
                }
            }
        } catch (IOException e) {
            System.out.println("No s'ha trobat el servidor.");
        } finally {
            System.out.println("Connexió finalitzada.");
            scanner.close();
        }
    }
}

/*
 * while (true) {
 * 
 * System.out.print("Escriu el missatge (o 'QUIT' per sortir del xat): ");
 * missatge = scanner.nextLine();
 * out.println(missatge);
 * 
 * if (missatge.equalsIgnoreCase("QUIT")) {
 * break;
 * }
 * // Esperar resposta del servidor
 * String resposta = in.readLine();
 * System.out.println("Servidor: " + resposta);
 * }
 */
