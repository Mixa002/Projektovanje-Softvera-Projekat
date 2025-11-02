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
public class Automobil extends AbstractDomainObject {

    private long automobilID;
    private String registarskiBroj;
    private String marka;
    private String model;
    private double cenaPoDanu;

    public Automobil() {
    }

    public Automobil(long automobilID, String registarskiBroj, String marka, String model, double cenaPoDanu) {
        this.automobilID = automobilID;
        this.registarskiBroj = registarskiBroj;
        this.marka = marka;
        this.model = model;
        this.cenaPoDanu = cenaPoDanu;
    }

    public double getCenaPoDanu() {
        return cenaPoDanu;
    }

    public void setCenaPoDanu(double cenaPoDanu) {
        this.cenaPoDanu = cenaPoDanu;
    }

    public long getAutomobilID() {
        return automobilID;
    }

    public void setAutomobilID(long automobilID) {
        this.automobilID = automobilID;
    }

    public String getRegistarskiBroj() {
        return registarskiBroj;
    }

    public void setRegistarskiBroj(String registarskiBroj) {
        this.registarskiBroj = registarskiBroj;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return marka + " " + model + " (Cena po danu " + cenaPoDanu + ") ";
    }

    @Override
    public String nazivTabele() {
        return " Automobil ";
    }

    @Override
    public String alijas() {
        return " aut ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Automobil aut = new Automobil(rs.getLong("AutomobilID"), rs.getString("RegistarskiBroj"), rs.getString("Marka"),
                    rs.getString("Model"), rs.getDouble("CenaPoDanu"));
            lista.add(aut);
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (RegistarskiBroj,Marka,Model,CenaPoDanu) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + registarskiBroj + "', '" + marka + "' ,'" + model + "' , " + cenaPoDanu + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "registarskiBroj = '" + registarskiBroj + "', cenaPoDanu = " + cenaPoDanu + " ";
    }

    @Override
    public String uslov() {
        return "AutomobilID = " + automobilID;
    }

    @Override
    public String uslovZaSelect() {
        if (registarskiBroj != null) {
            return " RegistarskiBroj = '" + registarskiBroj + "' ";
        }
        return "";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Automobil a = (Automobil) obj;
        return this.automobilID == a.automobilID;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(automobilID);
    }

}
