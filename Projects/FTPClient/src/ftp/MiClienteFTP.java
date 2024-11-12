/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ftp;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MiClienteFTP {
    private FTPClient ftp;

    public MiClienteFTP() {
        ftp = new FTPClient();
    }

    // Método para conectar al servidor FTP
    public void conectar(String server, int port, String user, String pass) throws IOException {
        ftp.connect(server, port);  // Conecta al servidor
        if (ftp.login(user, pass)) {  // Inicia sesión
            ftp.enterLocalPassiveMode();  // Modo pasivo
            ftp.setFileType(FTP.BINARY_FILE_TYPE);  // Tipo de archivo binario para transferencia
            System.out.println("Conectado al servidor FTP");
        } else {
            System.out.println("No se pudo conectar al servidor FTP");
            desconnectar();
        }
    }

    // Método para listar archivos en un directorio
    public void llistarDirectori(String directorio) throws IOException {
        FTPFile[] archivos = ftp.listFiles(directorio);
        System.out.println("Archivos en el directorio " + directorio + ":");
        for (FTPFile archivo : archivos) {
            System.out.println(archivo.getName());
        }
    }

    // Método para descargar un archivo específico
    public void descarregarFitxer(String fitxerRemot, String fitxerLocal) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(fitxerLocal)) {
            if (ftp.retrieveFile(fitxerRemot, outputStream)) {
                System.out.println("Archivo descargado: " + fitxerRemot);
            } else {
                System.out.println("No se pudo descargar el archivo: " + fitxerRemot);
            }
        }
    }

    // Método para descargar archivos que contienen una palabra específica en su nombre
    public void descarregaCoincidents(String directorio, String paraulaCoincident) throws IOException {
        FTPFile[] archivos = ftp.listFiles(directorio);
        System.out.println("Descargando archivos que contienen '" + paraulaCoincident + "' en el directorio " + directorio + ":");
        for (FTPFile archivo : archivos) {
            if (archivo.getName().contains(paraulaCoincident)) {
                descarregarFitxer(directorio + "/" + archivo.getName(), archivo.getName());
            }
        }
    }

    // Método para desconectar del servidor FTP
    public void desconnectar() throws IOException {
        if (ftp.isConnected()) {
            ftp.logout();
            ftp.disconnect();
            System.out.println("Desconectado del servidor FTP");
        }
    }
    public boolean isConnected() 
    {
    return ftp.isConnected();
    }
}
