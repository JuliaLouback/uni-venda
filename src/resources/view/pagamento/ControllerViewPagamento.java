package resources.view.pagamento;

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
import main.dao.DaoPagamento;
import main.entity.Pagamento;
import main.util.ShowAlert;
import resources.view.login.ControllerViewLogin;
import resources.view.painel.ControllerViewPainel;

public class ControllerViewPagamento implements Initializable {

    @FXML
    private TextField Nome;

    @FXML
    private Label labelChange;
    
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    private Pagamento Pagamentos = new Pagamento();

    @FXML
    void BackButton(ActionEvent event) {
    	Stage stage = (Stage) btnBack.getScene().getWindow(); 
        ControllerViewListaPagamento t = new ControllerViewListaPagamento();
	    t.start(stage);
    }

    @FXML
    void VoltarPainel(ActionEvent event) {
		 Stage stage = (Stage) btnBack.getScene().getWindow(); 
	     ControllerViewPainel t = new ControllerViewPainel();
		 t.start(stage);
    }

    @FXML
    void adicionarPagamento(ActionEvent event) {
    	
    	if(Pagamentos.getNome() != null && !Pagamentos.getNome().isEmpty()) {
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
			
			Pagamento Pagamento = new Pagamento();
			
			Pagamento.setNome(Nome.getText());
			
	    	if(new DaoPagamento().inserirPagamento(Pagamento)) {

		    	new ShowAlert().sucessoAlert("Pagamento adicionado com sucesso!"); 
		    	
		    	Stage stage = (Stage) btnBack.getScene().getWindow(); 
	 	        ControllerViewListaPagamento t = new ControllerViewListaPagamento();
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
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/view/Pagamento/CadastroPagamento.fxml"));
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Cadastro Pagamento - Uni Venda");
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/resources/img/money.png"));
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setLabelText(Pagamento Pagamento){
		 this.Pagamentos = Pagamento;
		 this.labelChange.setText("Editar"); 
		 this.btnAdd.setText("Editar");
		
	     this.Nome.setText(Pagamento.getNome());
	}
	
	public void editar() {
		if(validacaoCampos()) {
			
			Pagamento Pagamento = new Pagamento();
			
			Pagamento.setNome(Nome.getText());
			Pagamento.setId_pagamento(Pagamentos.getId_pagamento());
			
	    	if(new DaoPagamento().alterarPagamento(Pagamento)) {

		    	if(new ShowAlert().sucessoAlert("Pagamento editado com sucesso!")) { 
		    	
			    	Stage stage = (Stage) btnBack.getScene().getWindow(); 
			        ControllerViewListaPagamento t = new ControllerViewListaPagamento();
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
