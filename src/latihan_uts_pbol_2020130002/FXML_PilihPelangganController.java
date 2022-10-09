/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package latihan_uts_pbol_2020130002;

import java.net.URL;
import java.util.ResourceBundle;
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
public class FXML_PilihPelangganController implements Initializable {

    @FXML
    private TextField txtisi;
    @FXML
    private Button btncari;
    @FXML
    private Button btnpilih;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnexit;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button akhir;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnhapussiswa;
    @FXML
    private Button btneditsiswa;
    @FXML
    private Button btntambahsiswa;
    @FXML
    private TableView<PelangganModel> tbvpelanggan;

    private int hasil = 0;
    private String kodehasil = "";

    public int getHasil() {
        return (hasil);
    }

    public String getKodeHasil() {
        return (kodehasil);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showData(kodehasil);
    }

    public void showData(String a) {
        ObservableList<PelangganModel> data = FXMLDocumentController.dtpelanggan.LookUp(a);
        if (data.isEmpty()) {
            data = FXMLDocumentController.dtpelanggan.Load();
            txtisi.setText("");
        }
        if (data != null) {
            tbvpelanggan.getColumns().clear();
            tbvpelanggan.getItems().clear();

            TableColumn col = new TableColumn("Kode Pelanggan");
            col.setCellValueFactory(new PropertyValueFactory<PelangganModel, String>("Kodelgn"));
            tbvpelanggan.getColumns().addAll(col);

            col = new TableColumn("Nama Pelanggan");
            col.setCellValueFactory(new PropertyValueFactory<PelangganModel, String>("Namalgn"));
            tbvpelanggan.getColumns().addAll(col);

            col = new TableColumn("Alamat");
            col.setCellValueFactory(new PropertyValueFactory<PelangganModel, String>("Alamat"));
            tbvpelanggan.getColumns().addAll(col);

            tbvpelanggan.setItems(data);
        } else {
            Alert x = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            x.showAndWait();
            tbvpelanggan.getScene().getWindow().hide();;
        }
        btnawalklik(null);
        txtisi.requestFocus();
    }

    @FXML
    private void cariklik(ActionEvent event) {
        showData(txtisi.getText());
    }

    @FXML
    private void btnawalklik(ActionEvent event) {
        tbvpelanggan.getSelectionModel().selectFirst();
        tbvpelanggan.requestFocus();
    }

    @FXML
    private void btnakhirklik(ActionEvent event) {
        tbvpelanggan.getSelectionModel().selectLast();
        tbvpelanggan.requestFocus();
    }

    @FXML
    private void btnsebelumklik(ActionEvent event) {
        tbvpelanggan.getSelectionModel().selectAboveCell();
        tbvpelanggan.requestFocus();
    }

    @FXML
    private void btnsesudahklik(ActionEvent event) {
        tbvpelanggan.getSelectionModel().selectBelowCell();
        tbvpelanggan.requestFocus();
    }

    @FXML
    private void btnexitklik(ActionEvent event) {
        btnexit.getScene().getWindow().hide();
    }

    @FXML
    private void pilihklik(ActionEvent event) {
        hasil = 1;
        int pilihan = tbvpelanggan.getSelectionModel().getSelectedCells().get(0).getRow();
        kodehasil = tbvpelanggan.getItems().get(pilihan).getKodelgn();
        btnpilih.getScene().getWindow().hide();
    }

    @FXML
    private void batalklik(ActionEvent event) {
        hasil = 0;
        btnbatal.getScene().getWindow().hide();
    }

    @FXML
    private void btnhapusklik(ActionEvent event) {
    }

    @FXML
    private void btneditklik(ActionEvent event) {
    }

    @FXML
    private void btntambahklik(ActionEvent event) {
    }
}
