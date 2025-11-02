/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverskeOperacije;

import domain.AbstractDomainObject;
import domain.Iznajmljivanje;
import domain.StavkaIznajmljivanja;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mihajlodjordjevic
 */
public class ObrisiIznajmljivanjeSO extends OpstaSO {

    @Override
    public void proveriPreduslov(AbstractDomainObject ado) throws Exception {

    }

    @Override
    protected Object izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception {
        Iznajmljivanje izn = (Iznajmljivanje) ado;
        
        StavkaIznajmljivanja si = new StavkaIznajmljivanja();
        si.setIznajmljivanje(izn);
        izn.setStavkeIznajmljivanja((ArrayList <StavkaIznajmljivanja>)(List<?>)broker.select(si));
        

        for (StavkaIznajmljivanja stavka : izn.getStavkeIznajmljivanja()) {
           broker.delete(si);
        }
        return broker.delete(ado);
    }

}
