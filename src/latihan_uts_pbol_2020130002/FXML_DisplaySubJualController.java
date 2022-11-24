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
public class FXML_DisplaySubJualController implements Initializable {

    @FXML
    private TableView<SubJualModel> tbvsubjual;
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
        ObservableList<SubJualModel> data = FXMLDocumentController.dtsubjual.Load();
        if (data != null) {
            tbvsubjual.getColumns().clear();
            tbvsubjual.getItems().clear();

            TableColumn col = new TableColumn("No Faktur");
            col.setCellValueFactory(new PropertyValueFactory<SubJualModel, String>("Nofaktur"));
            tbvsubjual.getColumns().addAll(col);

            col = new TableColumn("Kode Barang");
            col.setCellValueFactory(new PropertyValueFactory<SubJualModel, String>("Kodebrg"));
            tbvsubjual.getColumns().addAll(col);

            col = new TableColumn("Jumlah");
            col.setCellValueFactory(new PropertyValueFactory<SubJualModel, String>("Jumlah"));
            tbvsubjual.getColumns().addAll(col);

            col = new TableColumn("Harga Barang");
            col.setCellValueFactory(new PropertyValueFactory<SubJualModel, String>("Harga"));
            tbvsubjual.getColumns().addAll(col);

            col = new TableColumn("Total Bayar");
            col.setCellValueFactory(new PropertyValueFactory<SubJualModel, String>("Total"));
            tbvsubjual.getColumns().addAll(col);

            tbvsubjual.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvsubjual.getScene().getWindow().hide();
        }
    }

    @FXML
    private void btnexitklik(ActionEvent event) {
        btnexit.getScene().getWindow().hide();
    }

    @FXML
    private void btnawalklik(ActionEvent event) {
        tbvsubjual.getSelectionModel().selectFirst();
        tbvsubjual.requestFocus();
    }

    @FXML
    private void btnakhirklik(ActionEvent event) {
        tbvsubjual.getSelectionModel().selectLast();
        tbvsubjual.requestFocus();
    }

    @FXML
    private void btnsebelumklik(ActionEvent event) {
        tbvsubjual.getSelectionModel().selectAboveCell();
        tbvsubjual.requestFocus();
    }

    @FXML
    private void btnsesudahklik(ActionEvent event) {
        tbvsubjual.getSelectionModel().selectBelowCell();
        tbvsubjual.requestFocus();
    }

    @FXML
    private void btntambahklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputSubJual.fxml"));
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
        SubJualModel s = new SubJualModel();
        s = tbvsubjual.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputSubJual.fxml"));
            Parent root = (Parent) loader.load();
            FXML_InputSubJualController isidt = loader.getController();
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
        SubJualModel s = new SubJualModel();
        s = tbvsubjual.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Ingin Dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            // System.out.print(s);
            if (FXMLDocumentController.dtsubjual.Delete(s.getNofaktur(), s.getKodebrg())) {
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
