/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverskeOperacije;

import db.DbBroker;
import domain.AbstractDomainObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mihajlodjordjevic
 */
public abstract class OpstaSO {

    DbBroker broker;

    public OpstaSO() {
        try {
            this.broker = DbBroker.getInstance();
        } catch (Exception ex) {
            Logger.getLogger(OpstaSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public final Object izvrsi(AbstractDomainObject ado) throws Exception {
        try {
            proveriPreduslov(ado);
            Object rezultat = izvrsiKonkretnuOperaciju(ado); // true/false
            try {
                potvrdi(); // commit
            } catch (Exception e) {
                e.printStackTrace();
                return false; // commit nije uspeo
            }
            return rezultat;
        } catch (Exception ex) {
            try {
                ponisti(); // rollback
            } catch (Exception e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
            return false; // vraća false klijentu
        }
    }

    public abstract void proveriPreduslov(AbstractDomainObject ado) throws Exception;

    /**
     *
     * @param ado
     * @return
     * @throws Exception
     */
    protected abstract Object izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception;

    private void potvrdi() throws Exception {
        broker.commit();
    }

    private void ponisti() throws Exception {
        broker.rollback();
    }
}
