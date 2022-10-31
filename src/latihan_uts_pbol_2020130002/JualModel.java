/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package latihan_uts_pbol_2020130002;

import java.sql.Date;

/**
 *
 * @author Yosef Adrian
 */
public class JualModel {
    String nofaktur, kodelgn, namalgn, alamat;
    Date tanggal;

    public JualModel(){
        
    }
    
    public JualModel(String nofaktur, String kodelgn, Date tanggal) {
        this.nofaktur = nofaktur;
        this.kodelgn = kodelgn;
        this.tanggal = tanggal;
    }

    public String getNamalgn() {
        return namalgn;
    }

    public void setNamalgn(String namalgn) {
        this.namalgn = namalgn;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    
    
    public String getNofaktur() {
        return nofaktur;
    }

    public void setNofaktur(String nofaktur) {
        this.nofaktur = nofaktur;
    }

    public String getKodelgn() {
        return kodelgn;
    }

    public void setKodelgn(String kodelgn) {
        this.kodelgn = kodelgn;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
    
}
