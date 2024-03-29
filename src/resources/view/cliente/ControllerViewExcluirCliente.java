package resources.view.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.dao.DaoCliente;
import main.dao.DaoEndereco;
import main.dao.DaoFornecedor;
import main.entity.Cliente;
import main.entity.Fornecedor;
import main.util.ShowAlert;
import resources.view.fornecedor.ControllerViewListaFornecedor;
import resources.view.login.ControllerViewLogin;
import resources.view.painel.ControllerViewPainel;
import resources.view.painel.ControllerViewPainelCaixa;

public class ControllerViewExcluirCliente {

    @FXML
    private Label Nome;

    @FXML
    private Label Email;

    @FXML
    private Label CPF;

    @FXML
    private Button btnBack;

    private Cliente clientes = new Cliente();
    
    @FXML
    void VoltarPainel(ActionEvent event) {
    	String cargo = System.getProperty("Cargo");
    	
    	if(cargo.equals("Caixa")) {
			Stage stage = (Stage) btnBack.getScene().getWindow(); 
		    ControllerViewPainelCaixa t = new ControllerViewPainelCaixa();
			t.start(stage);
		}
		else {
			Stage stage = (Stage) btnBack.getScene().getWindow(); 
		    ControllerViewPainel t = new ControllerViewPainel();
			t.start(stage);
		}
    }

    @FXML
    void btnBack(ActionEvent event) {
    	Stage stage = (Stage) btnBack.getScene().getWindow(); 
	    ControllerViewListaCliente t = new ControllerViewListaCliente();
		t.start(stage);
    }

    @FXML
    void btnExcluir(ActionEvent event) {
    	
    	if(new DaoCliente().excluirCliente(clientes.getCpf())) {
    	
	    	new ShowAlert().sucessoAlert("Cliente excluido com sucesso!");
	    	
			Stage stage = (Stage) btnBack.getScene().getWindow(); 
		    ControllerViewListaCliente t = new ControllerViewListaCliente();
			t.start(stage);
    	}
    }

    public void setLabelText(Cliente cliente){
		 this.clientes = cliente;
	     this.Nome.setText(cliente.getNome());
	     this.Email.setText(cliente.getEmail());
	     this.CPF.setText(cliente.getCpf());
    }
    
    @FXML
    void Sair(ActionEvent event) {
		 System.clearProperty("Cpf");
		 System.clearProperty("Nome");
		 System.clearProperty("Cargo");
	
		 if(new ShowAlert().sucessoAlert("Tem certeza que deseja realizar o logout?")) {
			 Stage stage = (Stage) btnBack.getScene().getWindow(); 
		     ControllerViewLogin t = new ControllerViewLogin();
			 t.start(stage);
		 }
    }
}
