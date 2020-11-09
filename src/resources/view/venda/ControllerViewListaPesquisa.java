package resources.view.venda;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.dao.DaoCaixa;
import main.dao.DaoVenda;
import main.dao.DaoVenda;
import main.entity.Caixa;
import main.entity.Venda;
import main.entity.Venda;
import main.util.ShowAlert;

import resources.view.login.ControllerViewLogin;
import resources.view.painel.ControllerViewPainel;
import resources.view.painel.ControllerViewPainelCaixa;

public class ControllerViewListaPesquisa implements Initializable {

    @FXML
    private Button btnAdd;
    
    @FXML
    private Button btnCaixa;
    
    @FXML
    private Button btnPesquisa;

    @FXML
    private TableView<Venda> tabela;

    @FXML
    private TableColumn<Venda, String> Cliente;

    @FXML
    private TableColumn<Venda, String> Funcionario;

    @FXML
    private TableColumn<Venda, String> Data_venda;

    @FXML
    private TableColumn<Venda,String> Valor;

    ArrayList<Venda> listas = new DaoVenda().listarVenda();

    ObservableList<Venda> lista = FXCollections.observableArrayList(listas);
    
    @FXML
    void VoltarVenda(ActionEvent event) { 	
		Stage stage = (Stage) btnAdd.getScene().getWindow(); 
	    ControllerViewListaVenda t = new ControllerViewListaVenda();
		t.start(stage);
   
    }
    

    @FXML
    void Sair(ActionEvent event) {
    	System.clearProperty("Cpf");
		 System.clearProperty("Nome");
		 System.clearProperty("Cargo");
	
		 if(new ShowAlert().sucessoAlert("Tem certeza que deseja realizar o logout?")) {
			 Stage stage = (Stage) btnAdd.getScene().getWindow(); 
		     ControllerViewLogin t = new ControllerViewLogin();
			 t.start(stage);
		 }
    }

    @FXML
    void VoltarPainel(ActionEvent event) {
    	 String cargo = System.getProperty("Cargo");
	    	
  	    if(cargo.equals("Caixa")) {
  			Stage stage = (Stage) btnAdd.getScene().getWindow(); 
  			ControllerViewPainelCaixa t = new ControllerViewPainelCaixa();
  			t.start(stage);
  		}
  		else {
  			Stage stage = (Stage) btnAdd.getScene().getWindow(); 
  			ControllerViewPainel t = new ControllerViewPainel();
  			t.start(stage);
  		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Cliente.setCellValueFactory(new PropertyValueFactory<Venda, String>("Nome_Cliente"));
		Funcionario.setCellValueFactory(new PropertyValueFactory<Venda, String>("Nome_Funcionario"));
		Valor.setCellValueFactory(new PropertyValueFactory<Venda, String>("Valor_string"));

		Data_venda.setCellValueFactory(new PropertyValueFactory<Venda, String>("Data_string"));
		
	    tabela.setItems(lista);
	    tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	public void start(Stage primaryStage) {
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/view/Venda/ListaVenda.fxml"));
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Lista Vendas - Uni Venda");
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/resources/img/money.png"));
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
