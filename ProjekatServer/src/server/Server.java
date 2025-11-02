/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import niti.HandleClient;

/**
 *
 * @author mihajlodjordjevic
 */
public class Server extends Thread{
    private ServerSocket serverSocket;
    private Socket socket;
    private ArrayList<Socket> korisnici;
    private final JTextArea txtPoruka;

    public Server(JTextArea txtPoruka) {
        korisnici = new ArrayList<>();
        this.txtPoruka = txtPoruka;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(7868);
            log("Server je pokrenut na portu 7868");
            while(!serverSocket.isClosed()){
                socket = serverSocket.accept();
                korisnici.add(socket);
                log("Klijent se povezao! " + socket.getInetAddress());
                HandleClient hc = new HandleClient(socket);
                hc.start();
            }
        } catch (SocketException ex) {
            log("Server je ugasen!");
        } catch (Exception ex) {
            ex.printStackTrace();
            log("Greska: " + ex.getMessage());
        }
    }
    
    public void stopServer() throws Exception{
        for (Socket korisnik : korisnici) {
            korisnik.close();
        }
        serverSocket.close();
    }

    private void log(String poruka) {
        txtPoruka.append(poruka + "\n");
    }
    
    
    
}
