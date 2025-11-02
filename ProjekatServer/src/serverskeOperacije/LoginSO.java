/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverskeOperacije;

import domain.AbstractDomainObject;
import domain.Radnik;
import java.util.List;
import server.SessionManager;

/**
 *
 * @author mihajlodjordjevic
 */
public class LoginSO extends OpstaSO {

    @Override
    public void proveriPreduslov(AbstractDomainObject ado) throws Exception {
        Radnik r = (Radnik) ado;
        if (r.getKorisnickoIme() == null || r.getKorisnickoIme().isEmpty()) {
            throw new Exception("Korisničko ime je obavezno.");
        }
        if (r.getLozinka() == null || r.getLozinka().isEmpty()) {
            throw new Exception("Lozinka je obavezna.");
        }
    }

    @Override
    protected Object izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception {
        List<AbstractDomainObject> rezultatPretrage = broker.pronadji((Radnik) ado);
        if (rezultatPretrage.isEmpty()) {
            throw new Exception("Pogrešno korisničko ime ili lozinka.");
        }
        Radnik radnik = (Radnik) rezultatPretrage.get(0);
        if (!SessionManager.prijavi(radnik)) {
            throw new Exception("Radnik je već prijavljen na sistem.");
        }
        return radnik;
    }

}
