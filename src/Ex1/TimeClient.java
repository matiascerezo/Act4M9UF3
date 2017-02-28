package Ex1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Matias
 */
public class TimeClient {

    static String host = "localhost";
    static int port = 5432;
    DataOutputStream outToServer;
    BufferedReader inFromServer;
    Socket socket;
    
    public TimeClient(String host, int port) throws IOException {
        socket = new Socket(host, port);
        outToServer = new DataOutputStream(socket.getOutputStream());
        inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String dia = JOptionPane.showInputDialog(null, "Dia: ", "Introdueix les dades", 1);
        String mes = JOptionPane.showInputDialog(null, "Mes: ", "Introdueix les dades", 1);
        String any = JOptionPane.showInputDialog(null, "Any: ", "Introdueix les dades", 1);
        outToServer.writeBytes(Integer.parseInt(dia) + " " + Integer.parseInt(mes) + " " + Integer.parseInt(any));
        inFromServer.close();
        outToServer.close();
        socket.close();
        System.out.println("Mira el server!");
    }
    
    public static void main(String[] args) throws IOException {
        new TimeClient(host, port);
    }
}
