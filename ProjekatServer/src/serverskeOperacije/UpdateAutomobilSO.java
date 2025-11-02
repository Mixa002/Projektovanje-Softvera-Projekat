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
public class UpdateAutomobilSO extends OpstaSO{

    @Override
    public void proveriPreduslov(AbstractDomainObject ado) throws Exception {
        Automobil a = (Automobil) ado;
         if (!a.getRegistarskiBroj().matches("[A-Z]{2}-\\d{3}-[A-Z]{2}")) {
            throw new Exception("Neispravan format registarskog broja!");
        }
    }

    @Override
    protected Object izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception {
        return broker.update(ado);
    }
    
}
