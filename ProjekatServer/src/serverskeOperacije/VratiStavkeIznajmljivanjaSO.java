/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverskeOperacije;

import domain.AbstractDomainObject;
import domain.StavkaIznajmljivanja;

/**
 *
 * @author mihajlodjordjevic
 */
public class VratiStavkeIznajmljivanjaSO extends OpstaSO {

    @Override
    public void proveriPreduslov(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof StavkaIznajmljivanja)) {
            throw new Exception("Nije prosleđena stavka iznajmljivanja!");
        }
    }

    @Override
    protected Object izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception {
        return broker.select(ado);
    }

}
