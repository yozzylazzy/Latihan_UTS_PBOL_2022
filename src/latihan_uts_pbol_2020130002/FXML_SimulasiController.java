/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package latihan_uts_pbol_2020130002;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Yosef Adrian
 */
public class FXML_SimulasiController implements Initializable {

    @FXML
    private ListView<String> lstpelanggan;
    @FXML
    private ListView<String> lstbarang;
    @FXML
    private TextField txtwaktu;
    @FXML
    private Button btnsimulasi;
    @FXML
    private TextArea txatransaksi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txatransaksi.setWrapText(true);
        lstpelanggan.getItems().clear();
        lstbarang.getItems().clear();
        ObservableList<PelangganModel> data = FXMLDocumentController.dtpelanggan.Load();
        if (data.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data Pelanggan kosong", ButtonType.OK);
            a.showAndWait();
            txatransaksi.getScene().getWindow().hide();;
        } else {
            for (int i = 0; i < data.size(); i++) {
                lstpelanggan.getItems().addAll(data.get(i).getKodelgn() + " - " + data.get(i).getNamalgn());
            }
            ObservableList<BarangModel> data2 = FXMLDocumentController.dtbarang.Load();
            if (data.isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data Barang kosong", ButtonType.OK);
                a.showAndWait();
                txatransaksi.getScene().getWindow().hide();;
            } else {
                for (int i = 0; i < data2.size(); i++) {
                    lstbarang.getItems().addAll(data2.get(i).getKodebrg() + " - " + data2.get(i).getNamabrg());
                }
            }
        }
    }

    @FXML
    private void simulasiklik(ActionEvent event) {
        Random acak = new Random();
        Timer t = new Timer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        int wkt = Integer.parseInt(txtwaktu.getText());
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    int p = acak.nextInt(lstpelanggan.getItems().size());
                    int b = acak.nextInt(lstbarang.getItems().size());
                    int jml = acak.nextInt(10) + 1;
                    JualModel j = new JualModel();
                    j.setNofaktur(FXMLDocumentController.dtjual.autonum(sdf.format(new java.util.Date())));
                    j.setKodelgn(lstpelanggan.getItems().get(p).substring(0, 3));
                    j.setTanggal(Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern(
                            "yyyy-MM-dd"))));
                    SubJualModel s = new SubJualModel();
                    s.setNofaktur(j.getNofaktur());
                    s.setKodebrg(lstbarang.getItems().get(b).substring(0, 5));
                    s.setJumlah(jml);
                    FXMLDocumentController.dtjual.setJualModel(j);
                    FXMLDocumentController.dtjual.setSubJualModel(s);
                    if (FXMLDocumentController.dtjual.saveall()) {
                        txatransaksi.setText(txatransaksi.getText()
                                + j.getNofaktur() + " - " + lstpelanggan.getItems().get(p) + " : "
                                + lstbarang.getItems().get(b) + " " + String.format("%d\n", jml));
                        txatransaksi.setScrollTop(Double.MAX_VALUE);
                    }
                });
            }
            //1000 artinya 1 detik
            //Timer cukup berat
            //Untuk menjalankan Perintah setiap detik atau satuan waktu yang diatur
            //Platform.runLater => Untuk mensinkronkan JavaFX dengan Perintah yang dilaksanakan
            
        }, 0, 10000 * wkt);
    }

}
