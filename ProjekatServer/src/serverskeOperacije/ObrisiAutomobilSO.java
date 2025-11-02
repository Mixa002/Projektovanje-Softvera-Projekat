/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverskeOperacije;

import domain.AbstractDomainObject;
import domain.Automobil;

/**
 *
 * @author mihajlodjordjevic
 */
public class ObrisiAutomobilSO extends OpstaSO{

    @Override
    public void proveriPreduslov(AbstractDomainObject ado) throws Exception {
        broker.proveriDostupnost(ado);
    }

    @Override
    protected Object izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception {
        return broker.delete(ado);
    }
    
}
