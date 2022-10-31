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
    String nofaktur, kodebrg, namabrg;
    int jumlah, harga, bayar;
    
    public SubJualModel(){
        
    }
    
    public SubJualModel(String nofaktur, String kodebrg, String namabrg, int jumlah, int harga) {
        this.nofaktur = nofaktur;
        this.kodebrg = kodebrg;
        this.namabrg = namabrg;
        this.jumlah = jumlah;
        this.harga = harga;
    }

    public String getNamabrg() {
        return namabrg;
    }

    public void setNamabrg(String namabrg) {
        this.namabrg = namabrg;
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

    public int getBayar() {
        return bayar;
    }

    public void setBayar(int bayar) {
        this.bayar = bayar;
    }
    
}
