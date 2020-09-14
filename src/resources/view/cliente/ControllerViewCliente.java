package resources.view.cliente;

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
import main.dao.DaoCliente;
import main.dao.DaoFornecedor;
import main.entity.Cliente;
import main.entity.Fornecedor;
import main.util.MaskFieldUtil;
import main.util.ShowAlert;
import resources.view.fornecedor.ControllerViewListaFornecedor;
import resources.view.painel.ControllerViewPainel;

public class ControllerViewCliente implements Initializable {

    @FXML
    private TextField Nome;

    @FXML
    private TextField Email;

    @FXML
    private TextField Cpf;

    @FXML
    private Label labelChange;
    
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    private Cliente clientes = new Cliente();

    @FXML
    void BackButton(ActionEvent event) {
    	Stage stage = (Stage) btnBack.getScene().getWindow(); 
        ControllerViewListaCliente t = new ControllerViewListaCliente();
	    t.start(stage);
    }

    @FXML
    void VoltarPainel(ActionEvent event) {
		 Stage stage = (Stage) btnBack.getScene().getWindow(); 
	     ControllerViewPainel t = new ControllerViewPainel();
		 t.start(stage);
    }

    @FXML
    void adicionarCliente(ActionEvent event) {
    	
    	if(clientes.getNome() != null && !clientes.getNome().isEmpty()) {
    		editar();
    	} else {
    		adicionar();
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		  MaskFieldUtil.cpfField(this.Cpf);
	}
	
	public void adicionar() {
		if(validacaoCampos()) {
			
			Cliente cliente = new Cliente();
			cliente.setCpf(Cpf.getText());
			cliente.setEmail(Email.getText());
			cliente.setNome(Nome.getText());
			
	    	new DaoCliente().inserirCliente(cliente);

	    	new ShowAlert().sucessoAlert("Cliente adicionado com sucesso!"); 
	    	
	    	Stage stage = (Stage) btnBack.getScene().getWindow(); 
	        ControllerViewListaCliente t = new ControllerViewListaCliente();
		    t.start(stage);
		}
	}

	public boolean validacaoCampos() {
		if(Cpf.getText().isEmpty() | Nome.getText().isEmpty()) {
			
			new ShowAlert().validacaoAlert();
			
			return false;
		}
		
		return true;
	}

	public void start(Stage primaryStage) {
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/view/cliente/CadastroCliente.fxml"));
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Cadastro Fornecedor - Uni Venda");
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/resources/img/money.png"));
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setLabelText(Cliente cliente){
		 this.clientes = cliente;
		 this.labelChange.setText("Editar"); 
		 this.btnAdd.setText("Editar");
		 this.Cpf.setEditable(false);
	     this.Cpf.setText(cliente.getCpf());
	     this.Nome.setText(cliente.getNome());
	     this.Email.setText(cliente.getEmail());
	}
	
	public void editar() {
		if(validacaoCampos()) {
			
			Cliente cliente = new Cliente();
			cliente.setCpf(Cpf.getText());
			cliente.setEmail(Email.getText());
			cliente.setNome(Nome.getText());
			
	    	new DaoCliente().alterarCliente(cliente);

	    	new ShowAlert().sucessoAlert("Cliente adicionado com sucesso!"); 
	    	
	    	Stage stage = (Stage) btnBack.getScene().getWindow(); 
	        ControllerViewListaCliente t = new ControllerViewListaCliente();
		    t.start(stage);
		}
	}
}
