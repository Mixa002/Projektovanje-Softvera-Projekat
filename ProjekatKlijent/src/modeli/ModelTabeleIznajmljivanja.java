/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domain.Iznajmljivanje;
import domain.Klijent;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mihajlodjordjevic
 */
public class ModelTabeleIznajmljivanja extends AbstractTableModel{
    
    private ArrayList<Iznajmljivanje> iznajmljivanja;
    String[] kolone = {"Klijent","Opis","Ukupan iznos", "Radnik"};
    
    public ModelTabeleIznajmljivanja(ArrayList<Iznajmljivanje> lista){
        this.iznajmljivanja = lista;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    @Override
    public int getRowCount() {
        return iznajmljivanja.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Iznajmljivanje iz = iznajmljivanja.get(rowIndex);
        switch(columnIndex){
            case 0:
                return iz.getKlijent();
            case 1:
                return iz.getOpis();
            case 2:
                return iz.getUkupanIznos();
            case 3:
                return iz.getRadnik();
            default:
                return "N/A";
        }
                
    }

    public Iznajmljivanje getIznajmljivanje(int selectedRow) {
        return iznajmljivanja.get(selectedRow);
    }
    
}
