/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverskeOperacije;

import domain.AbstractDomainObject;
import domain.Radnik;
import server.SessionManager;

/**
 *
 * @author mihajlodjordjevic
 */
public class LogoutSO extends OpstaSO{

    @Override
    public void proveriPreduslov(AbstractDomainObject ado) throws Exception {

    }

    @Override
    protected Object izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception {
        
        Radnik radnik = (Radnik) ado;
        SessionManager.odjavi(radnik);
        return true;
    }
    
}
