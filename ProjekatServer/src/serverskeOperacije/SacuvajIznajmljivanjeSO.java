/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverskeOperacije;

import domain.AbstractDomainObject;
import domain.Iznajmljivanje;
import domain.StavkaIznajmljivanja;
import java.util.HashMap;

/**
 *
 * @author mihajlodjordjevic
 */
public class SacuvajIznajmljivanjeSO extends OpstaSO {

    @Override
    public void proveriPreduslov(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Iznajmljivanje)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Iznajmljivanje.");
        }
        Iznajmljivanje iz = (Iznajmljivanje) ado;
        if (iz.getStavkeIznajmljivanja() == null || iz.getStavkeIznajmljivanja().isEmpty()) {
            throw new Exception("Iznajmljivanje mora imati bar jednu stavku.");
        }
    }

    @Override
    protected Object izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception {
        Iznajmljivanje iz = (Iznajmljivanje) ado;
        
        long iznajmljivanjeID = broker.insertWithGeneratedKeys(iz);
        iz.setIznajmljivanjeID(iznajmljivanjeID);
        
        for (StavkaIznajmljivanja si : iz.getStavkeIznajmljivanja() ) {
            si.setIznajmljivanje(iz);
            broker.insert(si);
        }

        return true;
    }

}
