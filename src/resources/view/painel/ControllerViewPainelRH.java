package resources.view.painel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.util.ShowAlert;
import resources.view.categoria.ControllerViewListaCategoria;
import resources.view.cliente.ControllerViewCliente;
import resources.view.cliente.ControllerViewListaCliente;
import resources.view.fornecedor.ControllerViewListaFornecedor;
import resources.view.funcionario.ControllerViewListaFuncionario;
import resources.view.login.ControllerViewLogin;
import resources.view.pagamento.ControllerViewListaPagamento;
import resources.view.produto.ControllerViewListaProduto;

public class ControllerViewPainelRH implements Initializable{
 
    @FXML
    private Button btnFuncionario;

    @FXML
    private Label NomeFunc;
    
	public void start(Stage primaryStage) {
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/view/painel/PainelRH.fxml"));
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
    void btnIrFuncionario(ActionEvent event) {
		Stage stage = (Stage) btnFuncionario.getScene().getWindow(); 
	    ControllerViewListaFuncionario t = new ControllerViewListaFuncionario();
		t.start(stage);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		NomeFunc.setText(System.getProperty("Nome"));
	}
	
	@FXML
    void Sair(ActionEvent event) {
		 System.clearProperty("Cpf");
		 System.clearProperty("Nome");
		 System.clearProperty("Cargo");
	
		 if(new ShowAlert().sucessoAlert("Tem certeza que deseja realizar o logout?")) {
			 Stage stage = (Stage) NomeFunc.getScene().getWindow(); 
		     ControllerViewLogin t = new ControllerViewLogin();
			 t.start(stage);
		 }
   }
}
