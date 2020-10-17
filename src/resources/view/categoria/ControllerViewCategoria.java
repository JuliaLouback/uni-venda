package resources.view.categoria;

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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.dao.DaoCategoria;
import main.dao.DaoFornecedor;
import main.entity.Categoria;
import main.entity.Fornecedor;
import main.util.MaskFieldUtil;
import main.util.ShowAlert;
import resources.view.fornecedor.ControllerViewListaFornecedor;
import resources.view.login.ControllerViewLogin;
import resources.view.painel.ControllerViewPainel;

public class ControllerViewCategoria implements Initializable {

    @FXML
    private TextField Nome;

    @FXML
    private Label labelChange;
    
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    private Categoria Categorias = new Categoria();

    @FXML
    void BackButton(ActionEvent event) {
    	Stage stage = (Stage) btnBack.getScene().getWindow(); 
        ControllerViewListaCategoria t = new ControllerViewListaCategoria();
	    t.start(stage);
    }

    @FXML
    void VoltarPainel(ActionEvent event) {
		 Stage stage = (Stage) btnBack.getScene().getWindow(); 
	     ControllerViewPainel t = new ControllerViewPainel();
		 t.start(stage);
    }

    @FXML
    void adicionarCategoria(ActionEvent event) {
    	
    	if(Categorias.getNome() != null && !Categorias.getNome().isEmpty()) {
    		editar();
    	} else {
    		adicionar();
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		  
	}
	
	public void adicionar() {
		if(validacaoCampos()) {
			
			Categoria Categoria = new Categoria();
			
			Categoria.setNome(Nome.getText());
			
	    	if(new DaoCategoria().inserirCategoria(Categoria)) {

		    	new ShowAlert().sucessoAlert("Categoria adicionado com sucesso!"); 
		    	
		    	Stage stage = (Stage) btnBack.getScene().getWindow(); 
	 	        ControllerViewListaCategoria t = new ControllerViewListaCategoria();
			    t.start(stage);
	    	}
		}
	}

	public boolean validacaoCampos() {
		if(Nome.getText().isEmpty()) {
			
			new ShowAlert().validacaoAlert();
			
			return false;
		}
		
		return true;
	}

	public void start(Stage primaryStage) {
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/view/Categoria/CadastroCategoria.fxml"));
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Cadastro Categoria - Uni Venda");
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/resources/img/money.png"));
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setLabelText(Categoria Categoria){
		 this.Categorias = Categoria;
		 this.labelChange.setText("Editar"); 
		 this.btnAdd.setText("Editar");
		
	     this.Nome.setText(Categoria.getNome());
	}
	
	public void editar() {
		if(validacaoCampos()) {
			
			Categoria Categoria = new Categoria();
			
			Categoria.setNome(Nome.getText());
			Categoria.setId_categoria(Categorias.getId_categoria());
			
	    	if(new DaoCategoria().alterarCategoria(Categoria)) {

		    	if(new ShowAlert().sucessoAlert("Categoria editada com sucesso!")) { 
		    	
			    	Stage stage = (Stage) btnBack.getScene().getWindow(); 
			        ControllerViewListaCategoria t = new ControllerViewListaCategoria();
				    t.start(stage);
		    	}
	    	}
		}
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
}
