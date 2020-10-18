package resources.view.login;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.dao.DaoLogin;
import main.entity.Funcionario;
import main.util.Criptografia;
import main.util.ShowAlert;
import resources.view.funcionario.ControllerViewListaFuncionario;

public class ControllerViewAltSenha {

    @FXML
    private Label labelChange;

    @FXML
    private TextField Email;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private PasswordField Senha_atual;

    @FXML
    private PasswordField Senha_nova;

    @FXML
    private DatePicker Data_nascimento;

    @FXML
    void BackButton(ActionEvent event) {
    	Stage stage = (Stage) btnBack.getScene().getWindow(); 
	    ControllerViewLogin t = new ControllerViewLogin();
		t.start(stage);
    }

    @FXML
    void alterarSenhas(ActionEvent event) {
    	
		Funcionario funcionario = new DaoLogin().verificarFuncionarioData(Email.getText(), new Criptografia().CriptografiaSenha(Senha_atual.getText()), Data_nascimento.getValue());

		if(validacaoCampos()) {
			if(funcionario.getCpf() == null) {
				new ShowAlert().erroAlert("E-mail e/ou senha incorretos!");
			} else {
				funcionario.setSenha(new Criptografia().CriptografiaSenha(Senha_nova.getText()));
				if(new DaoLogin().alterarFuncionario(funcionario)) {
					new ShowAlert().informationAlert("Senha alterado com sucesso! Realize o login novamente.");
				} else {
					new ShowAlert().informationAlert("Senha não alterada!");
				}
			}
			
		}
    }
    
    public boolean validacaoCampos() {
		if(Email.getText().isEmpty() | Senha_atual.getText().isEmpty() | Senha_nova.getText().isEmpty() | Data_nascimento.getValue() == null) {
			
			new ShowAlert().validacao();
			
			return false;
		}
		
		return true;
	}
    
    public void start(Stage primaryStage) {
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/view/Login/AlterarSenha.fxml"));
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Alterar Senha - Uni Venda");
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/resources/img/money.png"));
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
