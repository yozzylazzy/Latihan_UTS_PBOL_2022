/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package latihan_uts_pbol_2020130002;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yosef Adrian
 */
public class FXML_DisplayPelangganController implements Initializable {

    @FXML
    private TableView<PelangganModel> tbvpelanggan;
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
        ObservableList<PelangganModel> data = FXMLDocumentController.dtpelanggan.Load();
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
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvpelanggan.getScene().getWindow().hide();
        }
    }

    @FXML
    private void btnexitklik(ActionEvent event) {
        btnexit.getScene().getWindow().hide();
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
    private void btntambahklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputPelanggan.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
            showData();
            btnawalklik(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
        showData();
        btnawalklik(event);
    }

    @FXML
    private void btneditklik(ActionEvent event) {
        PelangganModel s = new PelangganModel();
        s = tbvpelanggan.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputPelanggan.fxml"));
            Parent root = (Parent) loader.load();
            FXML_InputPelangganController isidt = loader.getController();
            isidt.execute(s);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showData();
        btnawalklik(event);
    }

    @FXML
    private void btnhapusklik(ActionEvent event) {
        PelangganModel s = new PelangganModel();
        s = tbvpelanggan.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Ingin Dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            // System.out.print(s);
            if (FXMLDocumentController.dtpelanggan.Delete(s.getKodelgn())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION, "Data berhasil dihapus", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Data gagal dihapus", ButtonType.OK);
                b.showAndWait();
            }
            showData();
        }
    }
}
