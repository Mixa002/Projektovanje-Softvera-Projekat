/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mihajlodjordjevic
 */
public class RadnikEkspo extends AbstractDomainObject {

    private Radnik radnik;
    private Ekspozitura ekspo;
    private double satnica;

    public RadnikEkspo() {
    }

    public RadnikEkspo(Radnik radnik, Ekspozitura ekspo, double satnica) {
        this.radnik = radnik;
        this.ekspo = ekspo;
        this.satnica = satnica;
    }

    public double getSatnica() {
        return satnica;
    }

    public void setSatnica(double satnica) {
        this.satnica = satnica;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public Ekspozitura getEkspo() {
        return ekspo;
    }

    public void setEkspo(Ekspozitura ekspo) {
        this.ekspo = ekspo;
    }

    @Override
    public String nazivTabele() {
        return " RadnikEkspo ";
    }

    @Override
    public String alijas() {
        return " re ";
    }

    @Override
    public String join() {
        return """
               JOIN Radnik r ON(re.RadnikID = r.RadnikID)
               JOIN Ekspozitura ekspo ON(ekspo.EkspozituraID = re.EkspozituraID)
               """;
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while(rs.next()){
            Radnik r = new Radnik(rs.getLong("RadnikID"), rs.getString("r.Ime"), rs.getString("r.Prezime"),
                    rs.getString("KorisnickoIme"), rs.getString("Lozinka"));
            Ekspozitura ekspo = new Ekspozitura(rs.getLong("EkspozituraID"), rs.getString("Naziv"));
            RadnikEkspo re = new RadnikEkspo(r, ekspo, rs.getDouble("Satnica"));
            lista.add(re);
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (RadnikID, EkspozituraID, Satnica) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + radnik.getRadnikID() + ", " + ekspo.getEkspozituraID() + ", " + satnica + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " satnica = " + satnica + " ";
    }

    @Override
    public String uslov() {
        return " radnikID = " + radnik.getRadnikID();
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

}
