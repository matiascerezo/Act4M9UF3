/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ex2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author Matias
 */
public class SecureTimeClient {

    static String HOST = "localhost";
    static int PORT = 54321;

    private static SSLSocket sclient;
    private static SSLSocketFactory sslFactory;
    private static DataOutputStream outToServer;
    private static BufferedReader inFromServer;

    private static final String PROPIETAT1 = "javax.net.ssl.trustStore";
    private static final String V_PROPIETAT1
            = "C:\\Users\\ALUMNEDAM\\Desktop\\Matias\\DAM\\M9\\Act4M9UF3\\src\\SSL\\ClienteTrustStore.jks";

    private static final String PROPIETAT2 = "javax.net.ssl.trustStorePassword";
    private static final String V_PROPIETAT2 = "123456";

    public static void main(String[] args) throws IOException {
        //Establiment del magatzem truststore
            System.setProperty(PROPIETAT1, V_PROPIETAT1);
            System.setProperty(PROPIETAT2, V_PROPIETAT2);
            sslFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            sclient = (SSLSocket) sslFactory.createSocket(HOST, PORT);
            inFromServer = new BufferedReader(new InputStreamReader(sclient.getInputStream()));
            outToServer = new DataOutputStream(sclient.getOutputStream());
            String dia = JOptionPane.showInputDialog(null, "Dia: ", "Introdueix les dades", 1);
            String mes = JOptionPane.showInputDialog(null, "Mes: ", "Introdueix les dades", 1);
            String any = JOptionPane.showInputDialog(null, "Any: ", "Introdueix les dades", 1);
            outToServer.writeBytes(dia + " " + mes + " " + any);
            inFromServer.close();
            outToServer.close();
            sclient.close();
            System.out.println("Mira el server!");
    }
}
