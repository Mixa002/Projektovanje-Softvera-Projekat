/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverskeOperacije;

import domain.AbstractDomainObject;
import domain.Iznajmljivanje;
import domain.Klijent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author mihajlodjordjevic
 */
public class ObrisiKlijentaSO extends OpstaSO {

    @Override
    public void proveriPreduslov(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Klijent)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Klijent!");
        }

        Klijent k = (Klijent) ado;
        Iznajmljivanje iz = new Iznajmljivanje();
        iz.setKlijent(k);
        ArrayList<AbstractDomainObject> lista = broker.select(iz);

        if (lista != null && !lista.isEmpty()) {
            throw new Exception("Nije moguće obrisati klijenta jer postoje iznajmljivanja vezana za njega.");
        }

    }

    @Override
    protected Object izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception {
        return broker.delete(ado);
    }

}
