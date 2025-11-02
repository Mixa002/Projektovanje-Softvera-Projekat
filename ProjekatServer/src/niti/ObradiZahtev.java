/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domain.AbstractDomainObject;
import domain.Automobil;
import domain.Grad;
import domain.Iznajmljivanje;
import domain.Klijent;
import domain.ParametarDostupnihAutomobila;
import domain.Radnik;
import domain.RadnikEkspo;
import domain.StavkaIznajmljivanja;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import serverskeOperacije.DodajAutomobilSO;
import serverskeOperacije.DodajKlijentSO;
import serverskeOperacije.LoginSO;
import serverskeOperacije.LogoutSO;
import serverskeOperacije.ObrisiAutomobilSO;
import serverskeOperacije.ObrisiIznajmljivanjeSO;
import serverskeOperacije.ObrisiKlijentaSO;
import serverskeOperacije.SacuvajIznajmljivanjeSO;
import serverskeOperacije.UpdateAutomobilSO;
import serverskeOperacije.UpdateIznajmljivanjeSO;
import serverskeOperacije.UpdateStavkaIznajmljivanjaSO;
import serverskeOperacije.VratiDostupneAutomobileSO;
import serverskeOperacije.VratiStavkeIznajmljivanjaSO;
import serverskeOperacije.VratiSveAutomobileSO;
import serverskeOperacije.VratiSveGradSO;
import serverskeOperacije.VratiSveIznajmljivanjaSO;
import serverskeOperacije.VratiSveKlijentSO;
import serverskeOperacije.VratiSveRadnikEkspoSO;
import transfer.Request;
import transfer.Response;
import transfer.util.Operacije;
import transfer.util.ResponseStatus;

/**
 *
 * @author mihajlodjordjevic
 */
public class ObradiZahtev {

    public static Response ObradiZahtev(Request zahtev) {
        Response odgovor = new Response();
        try {
            switch (zahtev.getOperation()) {
                case Operacije.LOGIN:
                    try {
                        HashMap<String, String> mapa = (HashMap<String, String>) zahtev.getData();
                        String korisnickoIme = mapa.get("username");
                        String password = mapa.get("password");

                        Radnik r = new Radnik();
                        r.setKorisnickoIme(korisnickoIme);
                        r.setLozinka(password);

                        LoginSO loginSO = new LoginSO();
                        Radnik radnik = (Radnik) loginSO.izvrsi(r);
                        if (radnik != null) {
                            odgovor.setResult(radnik);
                            odgovor.setStatus(ResponseStatus.Success);
                        } else {
                            odgovor.setStatus(ResponseStatus.Error);
                            odgovor.setException(new Exception("Korisničko ime ili lozinka nisu tačni."));
                        }

                    } catch (Exception ex) {
                        String message = ex.getMessage();
                        if (message != null && message.toLowerCase().contains("communications link failure")) {
                            odgovor.setStatus(ResponseStatus.Error);
                            odgovor.setException(new Exception("Server nije pokrenut. Pokušajte ponovo kasnije."));
                        } else {
                            odgovor.setStatus(ResponseStatus.Error);
                            odgovor.setException(new Exception("Greška prilikom logovanja. Pokušajte ponovo."));
                        }
                    }
                    break;

                case Operacije.LOGOUT:
                    try {
                        Radnik rad = (Radnik) zahtev.getData();
                        LogoutSO logout = new LogoutSO();
                        boolean rezultat1 = (boolean) logout.izvrsi(rad);
                        odgovor.setResult(rezultat1);
                    } catch (Exception ex) {
                        odgovor.setResult(false);
                        odgovor.setException(ex);
                    }
                    break;
                case Operacije.ADD_AUTOMOBIL:
                    try {
                        Automobil a = (Automobil) zahtev.getData();
                        DodajAutomobilSO dodaj = new DodajAutomobilSO();
                        boolean uspesno = (boolean) dodaj.izvrsi(a);
                        odgovor.setResult(uspesno);
                        odgovor.setStatus(ResponseStatus.Success);
                    } catch (Exception ex) {
                        odgovor.setResult(false);
                        odgovor.setException(ex);
                        odgovor.setStatus(ResponseStatus.Error);
                    }
                    break;
                case Operacije.GET_ALL_AUTOMOBIL:
                    try {
                        VratiSveAutomobileSO vrati = new VratiSveAutomobileSO();
                        ArrayList<Automobil> lista = (ArrayList<Automobil>) vrati.izvrsi(new Automobil());
                        odgovor.setResult(lista);
                        odgovor.setStatus(ResponseStatus.Success);
                    } catch (Exception e) {
                        odgovor.setStatus(ResponseStatus.Error);
                        odgovor.setException(e);
                    }
                    break;
                case Operacije.DELETE_AUTOMOBIL:
                    try {
                        Automobil a = (Automobil) zahtev.getData();
                        ObrisiAutomobilSO obrisi = new ObrisiAutomobilSO();
                        boolean obrisano = (boolean) obrisi.izvrsi(a);
                        odgovor.setResult(obrisano);
                        odgovor.setStatus(ResponseStatus.Success);

                    } catch (Exception e) {
                        odgovor.setStatus(ResponseStatus.Error);
                        odgovor.setException(e);
                        odgovor.setResult(false);
                    }
                    break;
                case Operacije.UPDATE_AUTOMOBIL:
                    try {
                        Automobil a = (Automobil) zahtev.getData();
                        UpdateAutomobilSO update = new UpdateAutomobilSO();
                        boolean uspesno = (boolean) update.izvrsi(a);
                        odgovor.setResult(uspesno);
                        odgovor.setStatus(ResponseStatus.Success);
                    } catch (Exception ex) {
                        odgovor.setStatus(ResponseStatus.Error);
                        odgovor.setException(ex);
                        odgovor.setResult(false);
                    }
                    break;

                case Operacije.ADD_KLIJENT:
                    try {
                        Klijent k = (Klijent) zahtev.getData();
                        DodajKlijentSO dodaj = new DodajKlijentSO();
                        boolean uspesno = (boolean) dodaj.izvrsi(k);
                        odgovor.setResult(uspesno);
                        odgovor.setStatus(ResponseStatus.Success);
                    } catch (Exception ex) {
                        odgovor.setResult(false);
                        odgovor.setException(ex);
                        odgovor.setStatus(ResponseStatus.Error);
                    }
                    break;
                case Operacije.GET_ALL_KLIJENT:
                    try {
                        VratiSveKlijentSO vrati = new VratiSveKlijentSO();
                        ArrayList<Klijent> lista = (ArrayList<Klijent>) vrati.izvrsi(new Klijent());
                        odgovor.setResult(lista);
                        odgovor.setStatus(ResponseStatus.Success);
                    } catch (Exception ex) {
                        odgovor.setStatus(ResponseStatus.Error);
                        odgovor.setException(ex);
                    }
                    break;
                case Operacije.GET_ALL_DOSTUPNE_AUTOMOBILE:
                    try {
                        ParametarDostupnihAutomobila param = (ParametarDostupnihAutomobila) zahtev.getData();
                        VratiDostupneAutomobileSO vrati = new VratiDostupneAutomobileSO();
                        ArrayList<Automobil> dostupno = (ArrayList<Automobil>) vrati.izvrsi(param);
                        odgovor.setResult(dostupno);

                    } catch (Exception ex) {
                        odgovor.setStatus(ResponseStatus.Error);
                        odgovor.setException(ex);
                    }
                    break;
                case Operacije.ADD_IZNAJMLJIVANJE:
                    try {
                        Iznajmljivanje iznajmljivanje = (Iznajmljivanje) zahtev.getData();
                        SacuvajIznajmljivanjeSO soAdd = new SacuvajIznajmljivanjeSO();
                        boolean uspesno = (boolean) soAdd.izvrsi(iznajmljivanje);
                        odgovor.setResult(uspesno);

                    } catch (Exception ex) {
                        odgovor.setStatus(ResponseStatus.Error);
                        odgovor.setException(ex);
                    }
                    break;
                case Operacije.DELETE_KLIJENT:
                    try {
                        Klijent k = (Klijent) zahtev.getData();
                        ObrisiKlijentaSO obrisi = new ObrisiKlijentaSO();
                        boolean obrisano = (boolean) obrisi.izvrsi(k);
                        odgovor.setResult(obrisano);
                        odgovor.setStatus(ResponseStatus.Success);

                    } catch (Exception e) {
                        odgovor.setStatus(ResponseStatus.Error);
                        odgovor.setException(e);
                        odgovor.setResult(false);
                    }
                    break;
                case Operacije.GET_ALL_IZNAJMLJIVANJE:
                    try {
                        VratiSveIznajmljivanjaSO vrati = new VratiSveIznajmljivanjaSO();
                        ArrayList<Iznajmljivanje> lista = (ArrayList<Iznajmljivanje>) vrati.izvrsi(new Iznajmljivanje());
                        odgovor.setResult(lista);
                        odgovor.setStatus(ResponseStatus.Success);
                    } catch (Exception ex) {
                        odgovor.setStatus(ResponseStatus.Error);
                        odgovor.setException(ex);
                    }
                    break;
                case Operacije.DELETE_IZNAJMLJIVANJE:

                    try {
                        Iznajmljivanje izn = (Iznajmljivanje) zahtev.getData();
                        ObrisiIznajmljivanjeSO obrisi = new ObrisiIznajmljivanjeSO();
                        boolean obrisano = (boolean) obrisi.izvrsi(izn);
                        System.out.println("TIP: " + obrisano);
                        odgovor.setResult(obrisano);
                        odgovor.setStatus(ResponseStatus.Success);
                        System.out.println("????: " + odgovor.getResult().getClass().getName());
                    } catch (Exception e) {
                        odgovor.setStatus(ResponseStatus.Error);
                        odgovor.setException(e);
                        odgovor.setResult(false);
                    }
                    break;

                case Operacije.GET_ALL_STAVKA_IZNAJMLJIVANJA:
                    try {
                        StavkaIznajmljivanja kriterijum = (StavkaIznajmljivanja) zahtev.getData();
                        VratiStavkeIznajmljivanjaSO vrati = new VratiStavkeIznajmljivanjaSO();
                        ArrayList<StavkaIznajmljivanja> lista = (ArrayList<StavkaIznajmljivanja>) vrati.izvrsi(kriterijum);
                        odgovor.setResult(lista);
                        odgovor.setStatus(ResponseStatus.Success);
                    } catch (Exception ex) {
                        odgovor.setStatus(ResponseStatus.Error);
                        odgovor.setException(ex);
                    }
                    break;
                case Operacije.UPDATE_STAVKA_IZNAJMLJIVANJA:
                    try {
                        StavkaIznajmljivanja si = (StavkaIznajmljivanja) zahtev.getData();
                        UpdateStavkaIznajmljivanjaSO updateSO = new UpdateStavkaIznajmljivanjaSO();
                        boolean uspesno = (boolean) updateSO.izvrsi(si); // izvršava update i vraća rezultat
                        odgovor.setResult(uspesno);
                        odgovor.setStatus(ResponseStatus.Success);
//                        StavkaIznajmljivanja si = (StavkaIznajmljivanja) zahtev.getData();
//                        UpdateStavkaIznajmljivanjaSO update = (UpdateStavkaIznajmljivanjaSO) new UpdateStavkaIznajmljivanjaSO().izvrsi(si);
//                        odgovor.setStatus(ResponseStatus.Success);
                    } catch (Exception ex) {
                        odgovor.setStatus(ResponseStatus.Error);
                        odgovor.setException(ex);
                    }
                    break;
                case Operacije.UPDATE_IZNAJMLJIVANJE:
                    try {
                        Iznajmljivanje iz = (Iznajmljivanje) zahtev.getData();
                        System.out.println("OVDE JE UKUPNO KOLIKO PRE UPDATE " + iz.getUkupanIznos());

                        UpdateIznajmljivanjeSO updateSO = new UpdateIznajmljivanjeSO();
                        boolean uspesno = (boolean) updateSO.izvrsi(iz);
                        System.out.println("OVDE JE UKUPNO KOLIKO????? " + iz.getUkupanIznos());
                        odgovor.setResult(uspesno);
                        odgovor.setStatus(ResponseStatus.Success);
//                        Iznajmljivanje iz = (Iznajmljivanje) zahtev.getData();
//                        UpdateIznajmljivanjeSO update = (UpdateIznajmljivanjeSO) new UpdateIznajmljivanjeSO().izvrsi(iz);
//                        odgovor.setStatus(ResponseStatus.Success);
                    } catch (Exception ex) {
                        odgovor.setStatus(ResponseStatus.Error);
                        odgovor.setException(ex);
                    }
                    break;
                case Operacije.GET_ALL_RADNIK_EKSPO:
                    try {
                        VratiSveRadnikEkspoSO vrati = new VratiSveRadnikEkspoSO();
                        ArrayList<RadnikEkspo> lista = (ArrayList<RadnikEkspo>) vrati.izvrsi(new RadnikEkspo());
                        odgovor.setResult(lista);
                        odgovor.setStatus(ResponseStatus.Success);
                    } catch (Exception ex) {
                        odgovor.setStatus(ResponseStatus.Error);
                        odgovor.setException(ex);
                    }
                    break;
                default:
                    odgovor.setStatus(ResponseStatus.Error);
                    odgovor.setException(new UnsupportedOperationException("Nepoznata operacija"));
                    break;
            }
        } catch (Exception e) {
            odgovor.setStatus(ResponseStatus.Error);
            odgovor.setException(e);
        }
        return odgovor;
    }

}
