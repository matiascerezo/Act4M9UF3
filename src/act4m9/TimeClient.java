/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package act4m9;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author SICMA21
 */
public class TimeClient {

    private static final String[] DIES_SETMANA = {"Diumenge", "Dilluns", "Dimarts",
        "Dimecres", "Dijous", "Divendres", "Dissabte"};
    
    private static Socket socket;

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(10000);
            while (true) {
                socket = serverSocket.accept();
                Scanner in = new Scanner(socket.getInputStream());
                int[] data = new int[3];
                boolean correcte = true;
                for (int i = 0; i < data.length; i++) {
                    if (in.hasNextInt()) {
                        data[i] = in.nextInt();
                    } else {
                        correcte = false;
                    }
                }
                PrintStream out = new PrintStream(socket.getOutputStream());
                if (correcte) {
                    data[1] -= 1;
                    GregorianCalendar cal = new GregorianCalendar(data[2],
                            data[1], data[0]);
                    int dia = cal.get(Calendar.DAY_OF_WEEK) - 1;
                    System.out.println("Aquest dia era " + DIES_SETMANA[dia] + ".");
                } else {
                    System.out.println("Format de les dades incorrecte.");
                }
                socket.close();
            }
        } catch (Exception ex) {
            System.out.println("Error en les comunicacions: " + ex);
        }
    }
}