/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package latihan_uts_pbol_2020130002;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Yosef Adrian
 */
public class DBJual {

    private JualModel data = new JualModel();
    private HashMap<String, SubJualModel> data2 = new HashMap<String, SubJualModel>();

    //String pada HashMap di atas mengarah pada KodeBrg dari tabel Jual
    public JualModel getJualModel() {
        return (data);
    }

    public void setJualModel(JualModel s) {
        data = s;
    }

    public HashMap<String, SubJualModel> getSubJualModel() {
        return (data2);
    }

    public void setSubJualModel(SubJualModel d) {
        data2.put(d.getKodebrg(), d);
    }

    public ObservableList<SubJualModel> LoadDetil() {
        try {
            ObservableList<SubJualModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            data2.clear();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select * from subjual s join barang b on (s.Kodebrg=b.Kodebrg) where nofaktur = '" + getJualModel().getNofaktur() + "'");
            int i = 1;
            while (rs.next()) {
                SubJualModel d = new SubJualModel();
                d.setNofaktur(rs.getString("NoFaktur"));
                d.setKodebrg(rs.getString("KodeBrg"));
                d.setNamabrg(rs.getString("NamaBrg"));
                d.setJumlah(rs.getInt("Jumlah"));
                d.setHarga(rs.getInt("Harga"));
                tableData.add(d);
                setSubJualModel(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
//    public ObservableList<JualModel> Load() {
//        try {
//            ObservableList<JualModel> TableData = FXCollections.observableArrayList();
//            Koneksi con = new Koneksi();
//            con.bukaKoneksi();
//            con.statement = con.dbKoneksi.createStatement();
//            ResultSet rs
//                    = con.statement.executeQuery("Select * from jual");
//            int i = 1;
//            while (rs.next()) {
//                JualModel d = new JualModel();
//                d.setNofaktur(rs.getString("NoFaktur"));
//                d.setKodelgn(rs.getString("KodeLgn"));
//                d.setTanggal(rs.getDate("Tanggal"));
//                TableData.add(d);
//                i++;
//            }
//            con.tutupKoneksi();
//            return TableData;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public int validasi(String nomor) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "select count(*) as jml from jual where Nofaktur = '"
                    + nomor + "'");
            while (rs.next()) {
                val = rs.getInt("jml");
            }
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }

    public boolean Delete(String a) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            //System.out.println(a);
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                    "delete from jual where NoFaktur = ?");
            con.preparedStatement.setString(1, a);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean insert() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into jual(NoFaktur,Tanggal,KodeLgn) values (?,?,?)");
            con.preparedStatement.setString(1, getJualModel().getNofaktur());
            con.preparedStatement.setDate(2, getJualModel().getTanggal());
            con.preparedStatement.setString(3, getJualModel().getKodelgn());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean update() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update jual set Tanggal = ?, KodeLgn = ? where NoFaktur = ?");
            con.preparedStatement.setDate(1, getJualModel().getTanggal());
            con.preparedStatement.setString(2, getJualModel().getKodelgn());
            con.preparedStatement.setString(3, getJualModel().getNofaktur());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean saveall() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.dbKoneksi.setAutoCommit(false); // membuat semua perintah menjadi 1 transaksi
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                    "delete from jual where NoFaktur=?");
            con.preparedStatement.setString(1, getJualModel().getNofaktur());
            con.preparedStatement.executeUpdate();
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                    "insert into jual (NoFaktur,tanggal, kodelgn) values (?,?,?)");
            con.preparedStatement.setString(1, getJualModel().getNofaktur());
            con.preparedStatement.setDate(2, getJualModel().getTanggal());
            con.preparedStatement.setString(3, getJualModel().getKodelgn());
            con.preparedStatement.executeUpdate();
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                    "delete from subjual where NoFaktur=?");
            con.preparedStatement.setString(1, getJualModel().getNofaktur());
            con.preparedStatement.executeUpdate();
            for (SubJualModel sm : data2.values()) {
                con.preparedStatement = con.dbKoneksi.prepareStatement("insert into subjual (NoFaktur,kodebrg, jumlah) values (?,?,?)");
                con.preparedStatement.setString(1, sm.getNofaktur());
                con.preparedStatement.setString(2, sm.getKodebrg());
                con.preparedStatement.setInt(3, sm.getJumlah());
                con.preparedStatement.executeUpdate();
            }
            con.dbKoneksi.commit(); //semua perintah di transaksi dikerjakan
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public JualModel getdata(String nomor) {
        JualModel tmp = new JualModel();
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "select * from jual j join pelanggan p using (Kodelgn) where Nofaktur = '"
                    + nomor + "'");
            while (rs.next()) {
                tmp.setNofaktur(rs.getString("nofaktur"));
                tmp.setTanggal(rs.getDate("tanggal"));
                tmp.setKodelgn(rs.getString("Kodelgn"));
                tmp.setNamalgn(rs.getString("Namalgn"));
                tmp.setAlamat(rs.getString("Alamat"));
            }
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public ObservableList<JualModel> Load() {
        try {
            ObservableList<JualModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "select * from jual j join pelanggan p on (j.kodelgn=p.kodelgn) ");
            int i = 1;
            while (rs.next()) {
                JualModel d = new JualModel();
                d.setNofaktur(rs.getString("NoFaktur"));
                d.setTanggal(rs.getDate("tanggal"));
                d.setKodelgn(rs.getString("Kodelgn"));
                d.setNamalgn(rs.getString("Namalgn"));
                d.setAlamat(rs.getString("Alamat"));
                tableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(String nomor) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();;
            con.dbKoneksi.setAutoCommit(false);
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from subjual where Nofaktur = ?");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from jual where Nofaktur = ?");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();
            con.dbKoneksi.commit();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
    
    public void cetaklaporanMD(String pilih){
        Koneksi con = new Koneksi();        
        String is = "./ src/ nama project/nama tampilan laporan.jrxml";   
        Map<String,Object> map = new HashMap<String,Object>(); 
        map.put("Prm_NoFakturMaster",pilih);       
        con.bukaKoneksi();        
        try{
           JasperReport jasperReport = JasperCompileManager.compileReport(is);
           JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,map,con.dbKoneksi);
           JasperViewer.viewReport(jasperPrint,false);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
