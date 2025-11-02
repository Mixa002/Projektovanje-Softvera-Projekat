/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domain.Automobil;
import domain.RadnikEkspo;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import org.w3c.dom.views.AbstractView;

/**
 *
 * @author mihajlodjordjevic
 */
public class ModelTabeleRadnikEkspo extends AbstractTableModel{

    ArrayList<RadnikEkspo> radnikEkspo;
    String[] kolone = {"Ime", "Prezime","Korisnicko ime","Ekspozitura","Satnica"};
    
    
    public ModelTabeleRadnikEkspo(ArrayList<RadnikEkspo> radnici){
        this.radnikEkspo = radnici;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    @Override
    public int getRowCount() {
        return radnikEkspo.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RadnikEkspo re = radnikEkspo.get(rowIndex);
        
        switch(columnIndex){
                case 0: return re.getRadnik().getIme();
                case 1: return re.getRadnik().getPrezime();
                case 2: return re.getRadnik().getKorisnickoIme();
                case 3: return re.getEkspo().getNaziv();
                case 4: return re.getSatnica();
                default: return "N/A";
        }
    }
    
    public RadnikEkspo getRadnikEkspoAt(int row){
        return radnikEkspo.get(row);
    }
}
