/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ex2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author SICMA21
 */
public class SecureTimeServer {

    private static final String[] DIES_SETMANA = {"Diumenge", "Dilluns", "Dimarts",
        "Dimecres", "Dijous", "Divendres", "Dissabte"};

    static int PORT = 54321;

    private static SSLServerSocketFactory sslFactory;
    private static SSLServerSocket sslserversocket;
    private static SSLSocket sserver;
    private static BufferedReader entrada;
    private static PrintStream sortida;

    private static final String PROPIETAT1 = "javax.net.ssl.keyStore";
    private static final String V_PROPIETAT1
            = "C:\\Users\\ALUMNEDAM\\Desktop\\Matias\\DAM\\M9\\Act4M9UF3\\src\\SSL\\servidorKey.jks";

    private static final String PROPIETAT2 = "javax.net.ssl.keyStorePassword";
    private static final String V_PROPIETAT2 = "123456";

    public static void main(String[] args) throws IOException {
        try {
            System.setProperty(PROPIETAT1, V_PROPIETAT1);
            System.setProperty(PROPIETAT2, V_PROPIETAT2);
            sslFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            sslserversocket = (SSLServerSocket) sslFactory.createServerSocket(PORT);

            while (true) {
                sserver = (SSLSocket) sslserversocket.accept();
                Scanner in = new Scanner(sserver.getInputStream());
                int[] data = new int[3];
                boolean correcte = true;
                for (int i = 0; i < data.length; i++) {
                    if (in.hasNextInt()) {
                        data[i] = in.nextInt();
                    } else {
                        correcte = false;
                    }
                }
                sortida = new PrintStream(sserver.getOutputStream());
                if (correcte) {
                    data[1] -= 1;
                    GregorianCalendar cal = new GregorianCalendar(data[2],
                            data[1], data[0]);
                    int dia = cal.get(Calendar.DAY_OF_WEEK) - 1;
                    System.out.println("Aquest dia era " + DIES_SETMANA[dia] + ".");
                } else {
                    System.out.println("Format de les dades incorrecte.");
                }
                sserver.close();
            }
        } catch (Exception ex) {
            System.out.println("Error en les comunicacions: " + ex);
        }
    }
}
