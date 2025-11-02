/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domain.Klijent;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mihajlodjordjevic
 */
public class ModelTabeleKlijenti extends AbstractTableModel{
    
    private ArrayList<Klijent> klijenti;
    String[] kolone = {"Ime","Prezime","E-mail","Telefon","Grad"};
    
    public ModelTabeleKlijenti(ArrayList<Klijent> lista){
        klijenti = lista;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    @Override
    public int getRowCount() {
        return klijenti.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Klijent k = klijenti.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return k.getIme();
            case 1:
                return k.getPrezime();
            case 2:
                return k.getEmail();
            case 3:
                return k.getTelefon();
            case 4:
                return k.getGrad();
            default:
                return "N/A";
        }
    }

    public Klijent getKlijent(int selectedRow) {
        return klijenti.get(selectedRow);
    }
    
}
