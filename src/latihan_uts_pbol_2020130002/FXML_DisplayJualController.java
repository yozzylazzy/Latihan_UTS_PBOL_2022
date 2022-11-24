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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yosef Adrian
 */
public class FXML_DisplayJualController implements Initializable {

    @FXML
    private TableView<JualModel> tbvjual;
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
    private TextField txtjumlah;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showData();
        getTotalJumlah();
    }

    public void showData() {
        ObservableList<JualModel> data = FXMLDocumentController.dtjual.Load();
        if (data != null) {
            tbvjual.getColumns().clear();
            tbvjual.getItems().clear();
            
            TableColumn col = new TableColumn("No Faktur");
            col.setCellValueFactory(new PropertyValueFactory<JualModel, String>("Nofaktur"));
            tbvjual.getColumns().addAll(col);

            col = new TableColumn("Tanggal");
            col.setCellValueFactory(new PropertyValueFactory<JualModel, String>("Tanggal"));
            tbvjual.getColumns().addAll(col);

            col = new TableColumn("Kode Pelanggan");
            col.setCellValueFactory(new PropertyValueFactory<JualModel, String>("Kodelgn"));
            tbvjual.getColumns().addAll(col);

            tbvjual.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvjual.getScene().getWindow().hide();
        }
    }

    public void getTotalJumlah() {
        ObservableList<JualModel> data = FXMLDocumentController.dtjual.Load();
        int x = 0;
        for (int i = 0; i < data.size(); i++) {
            x++;
        }
        txtjumlah.setText("Jumlah : " + x);
    }

    @FXML
    private void btnexitklik(ActionEvent event) {
        btnexit.getScene().getWindow().hide();
    }

    @FXML
    private void btnawalklik(ActionEvent event) {
        tbvjual.getSelectionModel().selectFirst();
        tbvjual.requestFocus();
    }

    @FXML
    private void btnakhirklik(ActionEvent event) {
        tbvjual.getSelectionModel().selectLast();
        tbvjual.requestFocus();
    }

    @FXML
    private void btnsebelumklik(ActionEvent event) {
        tbvjual.getSelectionModel().selectAboveCell();
        tbvjual.requestFocus();
    }

    @FXML
    private void btnsesudahklik(ActionEvent event) {
        tbvjual.getSelectionModel().selectBelowCell();
        tbvjual.requestFocus();
    }

    @FXML
    private void btntambahklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputJual.fxml"));
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
        JualModel s = new JualModel();
        s = tbvjual.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputJual.fxml"));
            Parent root = (Parent) loader.load();
            FXML_InputJualController isidt = loader.getController();
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
        JualModel s = new JualModel();
        s = tbvjual.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Ingin Dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            // System.out.print(s);
            if (FXMLDocumentController.dtjual.Delete(s.getNofaktur())) {
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
