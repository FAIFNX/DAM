/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ftp;

import java.io.IOException;

public class Main {
     public static void main(String[] args) {
        MiClienteFTP clienteFTP = new MiClienteFTP();
        try {
            clienteFTP.conectar("localhost", 21, "pol", "123");

            /*
            // Verificar la conexión antes de proceder
            if (clienteFTP.isConnected()) {
                // Listado de archivos en el directorio raíz del usuario FTP
                clienteFTP.llistarDirectori("/");

                // Descarga de un archivo específico (modifica las rutas según lo necesites)
                clienteFTP.descarregarFitxer("/ruta_remota/fichero.txt", "ficheroLocal.txt");

                // Descarga de archivos que contienen una palabra específica en su nombre
                clienteFTP.descarregaCoincidents("/", "palabra");
            } else {
                System.out.println("No se pudo conectar al servidor FTP");
            }

            // Desconectar del servidor FTP
            clienteFTP.desconnectar();
            */

        } 
        catch (IOException e) 
        {
            System.out.println("Error durante la operación FTP: " + e.getMessage());
            e.printStackTrace();
        }
        
    }
     
}

