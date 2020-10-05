package resources.view.painel;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import resources.view.categoria.ControllerViewListaCategoria;
import resources.view.cliente.ControllerViewCliente;
import resources.view.cliente.ControllerViewListaCliente;
import resources.view.fornecedor.ControllerViewListaFornecedor;
import resources.view.funcionario.ControllerViewListaFuncionario;
import resources.view.produto.ControllerViewListaProduto;

public class ControllerViewPainel {

    @FXML
    private Button btnFornecedor;

    @FXML
    private Button btnFuncionario;

    @FXML
    private Button btnCliente;
    
    @FXML
    private Button btnProduto;

    @FXML
    private Button btnCategoria;

    
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
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/resources/img/money.png"));
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
    void btnIrCliente(ActionEvent event) {
		Stage stage = (Stage) btnCliente.getScene().getWindow(); 
	    ControllerViewListaCliente t = new ControllerViewListaCliente();
		t.start(stage);
    }
	
	@FXML
    void btnIrFuncionario(ActionEvent event) {
		Stage stage = (Stage) btnFuncionario.getScene().getWindow(); 
	    ControllerViewListaFuncionario t = new ControllerViewListaFuncionario();
		t.start(stage);
    }
	
	@FXML
    void btnIrProduto(ActionEvent event) {
		Stage stage = (Stage) btnProduto.getScene().getWindow(); 
	    ControllerViewListaProduto t = new ControllerViewListaProduto();
		t.start(stage);
    }
	
	@FXML
    void btnIrCategoria(ActionEvent event) {
		Stage stage = (Stage) btnCategoria.getScene().getWindow(); 
	    ControllerViewListaCategoria t = new ControllerViewListaCategoria();
		t.start(stage);
    }
}
