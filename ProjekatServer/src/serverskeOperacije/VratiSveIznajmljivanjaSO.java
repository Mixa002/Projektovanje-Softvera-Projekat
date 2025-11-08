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
public class VratiSveIznajmljivanjaSO extends OpstaSO {

    @Override
    public void proveriPreduslov(AbstractDomainObject ado) throws Exception {

    }

    @Override
    protected Object izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> listaADO = broker.select(ado);
        ArrayList<Iznajmljivanje> iznajmljivanja = new ArrayList<>();
        for (AbstractDomainObject obj : listaADO) {
            iznajmljivanja.add((Iznajmljivanje) obj);
        }

        for (Iznajmljivanje iz : iznajmljivanja) {
            StavkaIznajmljivanja si = new StavkaIznajmljivanja();
            si.setIznajmljivanje(iz);

            ArrayList<AbstractDomainObject> listaStavkiADO = broker.select(si);
            ArrayList<StavkaIznajmljivanja> stavke = new ArrayList<>();

            for (AbstractDomainObject obj : listaStavkiADO) {
                stavke.add((StavkaIznajmljivanja) obj);
            }

            iz.setStavkeIznajmljivanja(stavke);
        }

        return iznajmljivanja;
    }

}
