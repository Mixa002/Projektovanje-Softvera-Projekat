/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domain.StavkaIznajmljivanja;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mihajlodjordjevic
 */
public class ModelTabeleStavkeIznajmljivanja extends AbstractTableModel{
    private ArrayList<StavkaIznajmljivanja> stavkeIznajmljivanja;
    String[] kolone = {"RB","Automobil","Datum od","Datum do","Cena","Napomena"};
    
    @Override
    public int getRowCount() {
        return stavkeIznajmljivanja.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaIznajmljivanja si = stavkeIznajmljivanja.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return si.getRb();
            case 1:
                return si.getAutomobi();
            case 2:
                return si.getDatumOd();
            case 3:
                return si.getDatumDo();
            case 4:
                return si.getCena();
            case 5:
                return si.getNapomena();
            default:
                return "N/A";
        }
    }
    
}
