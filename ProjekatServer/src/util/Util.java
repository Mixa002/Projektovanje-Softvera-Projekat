/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mihajlodjordjevic
 */
public class Util {
    private static Util instance;
    private Properties properties;

    public Util() {
        try {
            properties = new Properties();
            InputStream is = getClass().getClassLoader().getResourceAsStream("dbconfig.properties");
            if(is == null){
                throw new RuntimeException("Ovaj fajl ne postoji");
            }
            properties.load(is);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Greska prilikom ucitavanja properties fajla!");
        }
    }
    
    public static Util getInstance(){
        if(instance == null){
            instance = new Util();
        }
        return instance;
    }
    
    public String getDriver(){
        return properties.getProperty("driver");
    }
    public String getUrl(){
        return properties.getProperty("url");
    }
    public String getUsername(){
        return properties.getProperty("username");
    }
    public String getPassword(){
        return properties.getProperty("password");
    }
    
    
}
