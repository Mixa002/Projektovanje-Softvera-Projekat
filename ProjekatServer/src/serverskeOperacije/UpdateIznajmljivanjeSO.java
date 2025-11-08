/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverskeOperacije;

import domain.AbstractDomainObject;
import domain.Iznajmljivanje;
import domain.StavkaIznajmljivanja;
import java.util.ArrayList;

/**
 *
 * @author mihajlodjordjevic
 */
public class UpdateIznajmljivanjeSO extends OpstaSO {

    @Override
    public void proveriPreduslov(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Iznajmljivanje)) {
            throw new Exception("Prosleđeni objekat nije instanca klase Iznajmljivanje!");
        }

        Iznajmljivanje iz = (Iznajmljivanje) ado;
        if (iz.getIznajmljivanjeID() <= 0) {
            throw new Exception("Iznajmljivanje mora imati ID (nije moguće ažurirati nepostojeće iznajmljivanje)!");
        }

        if (iz.getStavkeIznajmljivanja() == null) {
            throw new Exception("Lista stavki ne sme biti null!");
        }
    }

    @Override
    protected Object izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception {
        Iznajmljivanje iz = (Iznajmljivanje) ado;
        boolean uspesno = broker.update(iz);
        if (!uspesno) {
            throw new Exception("Ažuriranje iznajmljivanja nije uspelo!");
        }
        StavkaIznajmljivanja si = new StavkaIznajmljivanja();
        si.setIznajmljivanje(iz);
        boolean obrisano = broker.delete(si);
        if (!obrisano) {
            System.out.println("Upozorenje: nije bilo stavki za brisanje (možda novo iznajmljivanje).");
        }
        
        ArrayList<StavkaIznajmljivanja> stavke =  iz.getStavkeIznajmljivanja();
        for (StavkaIznajmljivanja s : stavke) {
            s.setIznajmljivanje(iz);
            broker.insert(s);
        }
        return true;
    }

}
