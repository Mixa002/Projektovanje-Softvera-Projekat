/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverskeOperacije;

import domain.AbstractDomainObject;
import domain.Automobil;
import java.util.ArrayList;

/**
 *
 * @author mihajlodjordjevic
 */
public class DodajAutomobilSO extends OpstaSO{

    @Override
    public void proveriPreduslov(AbstractDomainObject ado) throws Exception {
        Automobil auto = (Automobil) ado;
        
        Automobil kriterijum =  new Automobil();
        kriterijum.setRegistarskiBroj(auto.getRegistarskiBroj());
        
        ArrayList<AbstractDomainObject> lista = broker.select(kriterijum);
        
        if(!lista.isEmpty()){
            throw new Exception("Automobil sa ovim registarskim brojem vec postoji!");
        }
    }

    @Override
    protected Object izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception {
        broker.insert(ado);
        return true;
    }
    
}
