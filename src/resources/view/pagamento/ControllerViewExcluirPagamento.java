package resources.view.pagamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.dao.DaoPagamento;
import main.entity.Pagamento;
import main.util.ShowAlert;
import resources.view.fornecedor.ControllerViewListaFornecedor;
import resources.view.login.ControllerViewLogin;
import resources.view.painel.ControllerViewPainel;

public class ControllerViewExcluirPagamento {

    @FXML
    private Label Nome;

    @FXML
    private Button btnBack;

    private Pagamento Pagamentos = new Pagamento();
    
    @FXML
    void VoltarPainel(ActionEvent event) {
    	 Stage stage = (Stage) btnBack.getScene().getWindow(); 
	     ControllerViewPainel t = new ControllerViewPainel();
		 t.start(stage);
    }

    @FXML
    void btnBack(ActionEvent event) {
    	Stage stage = (Stage) btnBack.getScene().getWindow(); 
	    ControllerViewListaPagamento t = new ControllerViewListaPagamento();
		t.start(stage);
    }

    @FXML
    void btnExcluir(ActionEvent event) {
    	
    	if(new DaoPagamento().excluirPagamento(Pagamentos.getId_pagamento())) {
    	
	    	new ShowAlert().sucessoAlert("Pagamento excluido com sucesso!");
	    	
			Stage stage = (Stage) btnBack.getScene().getWindow(); 
		    ControllerViewListaPagamento t = new ControllerViewListaPagamento();
			t.start(stage);
    	}
    }

    public void setLabelText(Pagamento Pagamento){
		 this.Pagamentos = Pagamento;
	     this.Nome.setText(Pagamento.getNome());
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
