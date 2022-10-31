/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package latihan_uts_pbol_2020130002;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Yosef Adrian
 */
public class FXML_PilihBarangController implements Initializable {

    @FXML
    private TableView<BarangModel> tbvbarang;
    @FXML
    private Button btntambahsiswa;
    @FXML
    private Button btneditsiswa;
    @FXML
    private Button btnhapussiswa;
    @FXML
    private Button btnawal;
    @FXML
    private Button akhir;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnexit;
    @FXML
    private ComboBox<String> cmbfield;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnpilih;
    @FXML
    private Button btncari;
    @FXML
    private TextField txtisi;

    private int hasil = 0;
    private String kodehasil = "";
    private int harga;
    private String nama = "";

    public int getHasil() {
        return (hasil);
    }

    public String getKodeBarang() {
        return (kodehasil);
    }

    public Integer getHargaBarang() {
        return (harga);
    }

    public String getNamaBarang() {
        return (nama);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cmbfield.setItems(FXCollections.observableArrayList(
                "KodeBrg", "Harga"));
        cmbfield.getSelectionModel().select(0);
        showData("KodeBrg", "");
    }

    public void showData(String a, String b) {
        ObservableList<BarangModel> data = FXMLDocumentController.dtbarang.LookUp(a, b);
        if (data.isEmpty()) {
            data = FXMLDocumentController.dtbarang.Load();
            txtisi.setText("");
        }
        if (data != null) {
            tbvbarang.getColumns().clear();
            tbvbarang.getItems().clear();

            TableColumn col = new TableColumn("Kode Barang");
            col.setCellValueFactory(new PropertyValueFactory<BarangModel, String>("Kodebrg"));
            tbvbarang.getColumns().addAll(col);

            col = new TableColumn("Nama Barang");
            col.setCellValueFactory(new PropertyValueFactory<BarangModel, String>("Namabrg"));
            tbvbarang.getColumns().addAll(col);

            col = new TableColumn("Harga");
            col.setCellValueFactory(new PropertyValueFactory<BarangModel, Integer>("Harga"));
            tbvbarang.getColumns().addAll(col);

            tbvbarang.setItems(data);
        } else {
            Alert x = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            x.showAndWait();
            tbvbarang.getScene().getWindow().hide();;
        }
        btnawalklik(null);
        txtisi.requestFocus();
    }

    @FXML
    private void cariklik(ActionEvent event) {
        showData(cmbfield.getSelectionModel().getSelectedItem(), txtisi.getText());
    }

    @FXML
    private void btnawalklik(ActionEvent event) {
        tbvbarang.getSelectionModel().selectFirst();
        tbvbarang.requestFocus();
    }

    @FXML
    private void btnakhirklik(ActionEvent event) {
        tbvbarang.getSelectionModel().selectLast();
        tbvbarang.requestFocus();
    }

    @FXML
    private void btnsebelumklik(ActionEvent event) {
        tbvbarang.getSelectionModel().selectAboveCell();
        tbvbarang.requestFocus();
    }

    @FXML
    private void btnsesudahklik(ActionEvent event) {
        tbvbarang.getSelectionModel().selectBelowCell();
        tbvbarang.requestFocus();
    }

    @FXML
    private void btnexitklik(ActionEvent event) {
        btnexit.getScene().getWindow().hide();
    }

    @FXML
    private void pilihklik(ActionEvent event) {
        hasil = 1;
        int pilihan = tbvbarang.getSelectionModel().getSelectedCells().get(0).getRow();
        kodehasil = tbvbarang.getItems().get(pilihan).getKodebrg();
        harga = tbvbarang.getItems().get(pilihan).getHarga();
        nama = tbvbarang.getItems().get(pilihan).getNamabrg();
        btnpilih.getScene().getWindow().hide();
    }

    @FXML
    private void batalklik(ActionEvent event) {
        hasil = 0;
        btnbatal.getScene().getWindow().hide();
    }

    @FXML
    private void btntambahklik(ActionEvent event) {
    }

    @FXML
    private void btneditklik(ActionEvent event) {
    }

    @FXML
    private void btnhapusklik(ActionEvent event) {
    }

}
