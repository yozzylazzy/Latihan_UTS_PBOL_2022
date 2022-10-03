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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Yosef Adrian
 */
public class FXML_DisplayBarangController implements Initializable {

    @FXML
    private TableView<BarangModel> tbvbarang;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showData();
    }

    public void showData() {
        ObservableList<BarangModel> data = FXMLDocumentController.dtbarang.Load();
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
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvbarang.getScene().getWindow().hide();;
        }
    }

    @FXML
    private void btnexitklik(ActionEvent event) {
        btnexit.getScene().getWindow().hide();
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
    private void btnhapusklik(ActionEvent event) {
    }

    @FXML
    private void btneditklik(ActionEvent event) {
    }

    @FXML
    private void btntambahklik(ActionEvent event) {
    }

}
