/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package latihan_uts_pbol_2020130002;

/**
 *
 * @author Yosef Adrian
 */
public class SubJualModel {
    String nofaktur, kodebrg;
    int jumlah, harga;

    public SubJualModel(String nofaktur, String kodebrg, int jumlah, int harga) {
        this.nofaktur = nofaktur;
        this.kodebrg = kodebrg;
        this.jumlah = jumlah;
        this.harga = harga;
    }

    public SubJualModel(){
        
    }
    
    public String getNofaktur() {
        return nofaktur;
    }

    public void setNofaktur(String nofaktur) {
        this.nofaktur = nofaktur;
    }

    public String getKodebrg() {
        return kodebrg;
    }

    public void setKodebrg(String kodebrg) {
        this.kodebrg = kodebrg;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
    
    public int getTotal(){
        return this.jumlah * this.harga;
    }
    
}
