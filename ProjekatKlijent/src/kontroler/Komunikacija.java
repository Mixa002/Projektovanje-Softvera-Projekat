    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package kontroler;

    import domain.Radnik;
    import java.io.IOException;
    import java.io.ObjectInputStream;
    import java.io.ObjectOutputStream;
    import java.net.Socket;
    import java.util.logging.Level;
    import java.util.logging.Logger;
    import transfer.Request;
    import transfer.Response;

    /**
     *
     * @author mihajlodjordjevic
     */
    public class Komunikacija {

        private static Komunikacija instance;
        private Socket s;
        private Radnik zaposleni;
        private ObjectInputStream in;
        private ObjectOutputStream out;

        public Komunikacija() {
            try {
                s = new Socket("localhost", 7868);
                out = new ObjectOutputStream(s.getOutputStream());
                out.flush();
                in = new ObjectInputStream(s.getInputStream());
            } catch (IOException ex) {
                throw new RuntimeException("Proveri da li je server pokrenut!");
            }
        }

        public static Komunikacija getInstance() {
            if (instance == null) {
                instance = new Komunikacija();
            }
            return instance;
        }

        public Response primiOdgovor() {
            try {
                return (Response) in.readObject();
            } catch (IOException  | ClassNotFoundException ex) {
                Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

        public void posaljiZahtev(Request zahtev) {
            try {
                out.writeObject(zahtev);
                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        public Radnik getZaposleni() {
            return zaposleni;
        }

        public void setZaposleni(Radnik zaposleni) {
            this.zaposleni = zaposleni;
        }


    }
