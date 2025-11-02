/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package transfer.util;

/**
 *
 * @author mihajlodjordjevic
 */
public interface Operacije {
    public static final int LOGIN = 0;
    public static final int LOGOUT = 1;

    public static final int ADD_AUTOMOBIL = 2;
    public static final int DELETE_AUTOMOBIL = 3;
    public static final int UPDATE_AUTOMOBIL = 4;
    public static final int GET_ALL_AUTOMOBIL = 5;
    public static final int GET_ALL_DOSTUPNE_AUTOMOBILE = 18;

    public static final int ADD_KLIJENT = 6;
    public static final int DELETE_KLIJENT = 7;
    public static final int UPDATE_KLIJENT = 8;
    public static final int GET_ALL_KLIJENT = 9;
    
    public static final int ADD_IZNAJMLJIVANJE = 10;
    public static final int DELETE_IZNAJMLJIVANJE = 11;
    public static final int UPDATE_IZNAJMLJIVANJE = 12;
    public static final int GET_ALL_IZNAJMLJIVANJE = 13;
    
    public static final int GET_ALL_STAVKA_IZNAJMLJIVANJA = 14;

    public static final int GET_ALL_GRAD = 15;
    public static final int ADD_GRAD = 16;
    
    public static final int GET_ZAUZETE_AUTMOBILE = 17;
    public static final int UPDATE_STAVKA_IZNAJMLJIVANJA = 19;
    
    public static final int GET_ALL_RADNIK_EKSPO = 20;
}
