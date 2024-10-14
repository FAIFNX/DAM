package Java.DataStream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
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
            // Crear el mapa para almacenar las opciones
            Map<Integer, String> opcions = new HashMap<>();

            // Añadir las opciones al mapa
            opcions.put(1, "Demana un enter");
            opcions.put(2, "Demana un double");
            opcions.put(3, "Demana un caràcter");
            opcions.put(4, "Sortir");

            while (true) {
                try (Socket socket = serverSocket.accept();
                        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                        DataInputStream in = new DataInputStream(socket.getInputStream())) {

                    System.out.println(
                            "Usuari connectat des de la IP: " + socket.getInetAddress() + " port " + socket.getPort());

                    int opcion = 0;
                    while (opcion < 1 || opcion > 4) {
                        System.out.println("Quin tipus de dada demanem?");
                        for (Map.Entry<Integer, String> entry : opcions.entrySet()) {
                            System.out.println("\t" + entry.getKey() + " - " + entry.getValue());
                        }
                        opcion = scanner.nextInt();
                        out.writeInt(opcion); // Enviar la opción al cliente

                        switch (opcion) {
                            case 1:
                                out.writeUTF(opcions.get(opcion));
                                int enteredInt = in.readInt(); // Leer un entero del cliente
                                System.out.println("Client: " + enteredInt);
                                break;
                            case 2:
                                out.writeUTF(opcions.get(opcion));
                                double enteredDouble = in.readDouble(); // Leer un double del cliente
                                System.out.println("Client: " + enteredDouble);
                                break;
                            case 3:
                                out.writeUTF(opcions.get(opcion));
                                char enteredChar = in.readChar(); // Leer un carácter del cliente
                                System.out.println("Client: " + enteredChar);
                                break;
                            case 4:
                                System.out.println("Saliendo del programa");
                                break;
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

/*
 * String missatge;
 * while ((missatge = in.readLine()) != null) {
 * if (missatge.equalsIgnoreCase("QUIT")) {
 * System.out.println("Usuari desconnectat");
 * break;
 * }
 * System.out.println("Missatge rebut: " + missatge);
 * while (true) {
 * System.out.print("Escriu el missatge (o 'QUIT' per sortir del Xat): ");
 * missatge = scanner.nextLine();
 * out.println(missatge);
 * 
 * if (missatge.equalsIgnoreCase("QUIT")) {
 * break;
 * }
 * 
 * // Esperar resposta del Client
 * String resposta = in.readLine();
 * System.out.println("Client: " + resposta);
 * }
 * }
 */
