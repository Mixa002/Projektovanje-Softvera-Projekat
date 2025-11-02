/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverskeOperacije;

import domain.AbstractDomainObject;
import domain.Grad;
import domain.Klijent;

/**
 *
 * @author mihajlodjordjevic
 */
public class DodajKlijentSO extends OpstaSO {

    @Override
    public void proveriPreduslov(AbstractDomainObject ado) throws Exception {
        Klijent klijent = (Klijent) ado;
        Grad unetiGrad = klijent.getGrad();

        //  Proveravamo da li grad postoji u bazi
        Grad gradZaProveru = new Grad();
        gradZaProveru.setNaziv(unetiGrad.getNaziv());

        Grad gradIzBaze = (Grad) broker.vratiJednog(gradZaProveru);

        //  Ako grad ne postoji, dodaj ga
        if (gradIzBaze == null) {
            System.out.println("[INFO] Grad '" + unetiGrad.getNaziv() + "' ne postoji. Dodajem ga u bazu...");
            broker.insert(unetiGrad);

            gradIzBaze = (Grad) broker.vratiJednog(gradZaProveru);
            if (gradIzBaze == null) {
                throw new Exception("Greška prilikom dodavanja grada. Grad i dalje nije pronađen.");
            }
        } else {
            System.out.println("[INFO] Grad '" + gradIzBaze.getNaziv() + "' već postoji u bazi.");
        }

        // 3. Vežemo klijentu grad iz baze (sa ID-jem)
        klijent.setGrad(gradIzBaze);
    }

    @Override
    protected Object izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception {
        
        Klijent k = (Klijent) ado;

        

        broker.insert(k);

        return true;
    }

}
