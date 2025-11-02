/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Request;
import transfer.Response;
import niti.ObradiZahtev;
import static niti.ObradiZahtev.ObradiZahtev;

/**
 *
 * @author mihajlodjordjevic
 */
public class HandleClient extends Thread {

    private Socket klijentSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public HandleClient(Socket socket) {
        this.klijentSocket = socket;
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(klijentSocket.getOutputStream());
            in = new ObjectInputStream(klijentSocket.getInputStream());
            while (true) {
                Request zahtev = (Request) in.readObject();
                Response odgovor = ObradiZahtev(zahtev);
                out.writeObject(odgovor);
                out.flush();
            }
        } catch (Exception ex) {
            Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
            try {
                if (out != null) {
                    Response errorResponse = new Response();
                    errorResponse.setStatus(transfer.util.ResponseStatus.Error);
                    errorResponse.setException(ex);
                    out.writeObject(errorResponse);
                    out.flush();
                }
            } catch (Exception sendEx) {
                sendEx.printStackTrace();
            }
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                if (klijentSocket != null && !klijentSocket.isClosed()) {
                    klijentSocket.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
