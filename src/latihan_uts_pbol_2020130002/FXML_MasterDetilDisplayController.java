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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yosef Adrian
 */
public class FXML_MasterDetilDisplayController implements Initializable {

    @FXML
    private TableView<JualModel> tbvjual;
    @FXML
    private TableView<SubJualModel> tbvdetil;
    @FXML
    private Button btnawal;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnubah;
    @FXML
    private Button btnakhir;
    @FXML
    private Button btnkeluar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tbvjual.getColumns().clear();
        tbvjual.getItems().clear();

        TableColumn col = new TableColumn("No Faktur");
        col.setCellValueFactory(new PropertyValueFactory<JualModel, String>("Nofaktur"));
        tbvjual.getColumns().addAll(col);
        col = new TableColumn("Tanggal");
        col.setCellValueFactory(new PropertyValueFactory<JualModel, String>("Tanggal"));
        tbvjual.getColumns().addAll(col);
        col = new TableColumn("Kode lgn");
        col.setCellValueFactory(new PropertyValueFactory<JualModel, String>("Kodelgn"));
        tbvjual.getColumns().addAll(col);
        col = new TableColumn("Nama lgn");
        col.setCellValueFactory(new PropertyValueFactory<JualModel, String>("Namalgn"));
        tbvjual.getColumns().addAll(col);
        col = new TableColumn("Alamat");
        col.setCellValueFactory(new PropertyValueFactory<JualModel, String>("Alamat"));
        tbvjual.getColumns().addAll(col);

        tbvdetil.getColumns().clear();
        tbvdetil.getItems().clear();
        col = new TableColumn("No Faktur");
        col.setCellValueFactory(new PropertyValueFactory<SubJualModel, String>("Nofaktur"));
        tbvdetil.getColumns().addAll(col);
        col = new TableColumn("Kode Brg");
        col.setCellValueFactory(new PropertyValueFactory<SubJualModel, String>("Kodebrg"));
        tbvdetil.getColumns().addAll(col);
        col = new TableColumn("Nama Brg");
        col.setCellValueFactory(new PropertyValueFactory<SubJualModel, String>("Namabrg"));
        tbvdetil.getColumns().addAll(col);
        col = new TableColumn("Harga");
        col.setCellValueFactory(new PropertyValueFactory<SubJualModel, String>("Harga"));
        tbvdetil.getColumns().addAll(col);
        col = new TableColumn("Jumlah");
        col.setCellValueFactory(new PropertyValueFactory<SubJualModel, String>("Jumlah"));
        tbvdetil.getColumns().addAll(col);
        col = new TableColumn("Bayar");
        col.setCellValueFactory(new PropertyValueFactory<SubJualModel, String>("Bayar"));
        tbvdetil.getColumns().addAll(col);
        showData();
    }

    private void showData() {
        ObservableList<JualModel> data = FXML_InputMasterDetilController.data.Load();
        if (data != null) {
            tbvjual.setItems(data);
            awalklik(null);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvjual.getScene().getWindow().hide();
        }
    }

    private void showdetil() {
        FXML_InputMasterDetilController.data.getJualModel().setNofaktur(
                tbvjual.getSelectionModel().getSelectedItem().getNofaktur());
        ObservableList<SubJualModel> data = FXML_InputMasterDetilController.data.LoadDetil();
        if (data != null) {
            tbvdetil.setItems(data);
            tbvdetil.getSelectionModel().selectFirst();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvjual.getScene().getWindow().hide();;
        }
    }

    @FXML
    private void tbvjualklik(MouseEvent event) {
        showdetil();
    }

    @FXML
    private void tbvdetilklik(MouseEvent event) {

    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvjual.getSelectionModel().selectFirst();
        showdetil();
        tbvjual.requestFocus();
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputMasterDetil.fxml"));
            Parent root = (Parent) loader.load();
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
        awalklik(event);
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvjual.getSelectionModel().selectAboveCell();
        showdetil();
        tbvjual.requestFocus();
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvjual.getSelectionModel().selectBelowCell();
        showdetil();
        tbvjual.requestFocus();
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        JualModel s = new JualModel();
        s = tbvjual.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?",
                ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXMLDocumentController.dtjual.delete(s.getNofaktur())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION,
                        "Data berhasil dihapus", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR,
                        "Data gagal dihapus", ButtonType.OK);
                b.showAndWait();
            }
            showData();
            awalklik(event);
        }
    }

    @FXML
    private void ubahklik(ActionEvent event) {
        JualModel s = new JualModel();
        s = tbvjual.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputMasterDetil.fxml"));
            Parent root = (Parent) loader.load();
            FXML_InputMasterDetilController isidt = (FXML_InputMasterDetilController) loader.getController();
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
        awalklik(event);
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvjual.getSelectionModel().selectLast();
        showdetil();
        tbvjual.requestFocus();
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

}
