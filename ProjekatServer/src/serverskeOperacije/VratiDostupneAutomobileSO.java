/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverskeOperacije;

import domain.AbstractDomainObject;
import domain.Automobil;
import domain.ParametarDostupnihAutomobila;
import domain.StavkaIznajmljivanja;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author mihajlodjordjevic
 */
public class VratiDostupneAutomobileSO extends OpstaSO {
    

    @Override
    public void proveriPreduslov(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof ParametarDostupnihAutomobila)) {
            throw new Exception("Pogrešan parametar za dostupne automobile!");
        }
    }

    @Override
    protected Object izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception {
        ParametarDostupnihAutomobila param = (ParametarDostupnihAutomobila) ado;
        Date datumOd = param.getDatumOd();
        Date datumDo = param.getDatumDo();
        
        ArrayList<AbstractDomainObject> listaAdoAutomobil = broker.select(new Automobil());
        ArrayList<Automobil> sviAutomobili = new ArrayList<>();
        for (AbstractDomainObject adoAuto : listaAdoAutomobil) {
            sviAutomobili.add((Automobil) adoAuto);
        }

        ArrayList<AbstractDomainObject> listaAdoStavke = broker.select(new StavkaIznajmljivanja());
        ArrayList<StavkaIznajmljivanja> sveStavke = new ArrayList<>();
        for (AbstractDomainObject adoStavka : listaAdoStavke) {
            sveStavke.add((StavkaIznajmljivanja) adoStavka);
        }

        ArrayList<Automobil> dostupni = new ArrayList<>();

        for (Automobil a : sviAutomobili) {
            boolean zauzet = false;

            for (StavkaIznajmljivanja stavka : sveStavke) {
                if (stavka.getAutomobi().getAutomobilID() == a.getAutomobilID()) {
                    // Provera preklapanja termina
                    if (!(datumDo.before(stavka.getDatumOd()) || datumOd.after(stavka.getDatumDo()))) {
                        zauzet = true;
                        break;
                    }
                }
            }
            if (!zauzet) {
                dostupni.add(a);
            }
        }
        return dostupni;

    }

}
