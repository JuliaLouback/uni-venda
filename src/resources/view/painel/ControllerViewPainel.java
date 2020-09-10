package resources.view.painel;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import resources.view.fornecedor.ControllerViewListaFornecedor;

public class ControllerViewPainel {

    @FXML
    private Button btnFornecedor;

    @FXML
    void btnIrFornecedor(ActionEvent event) {
    	 Stage stage = (Stage) btnFornecedor.getScene().getWindow(); 
	     ControllerViewListaFornecedor t = new ControllerViewListaFornecedor();
		 t.start(stage);
    }

	public void start(Stage primaryStage) {
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/view/painel/Painel.fxml"));
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Painel - Uni Venda");
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
