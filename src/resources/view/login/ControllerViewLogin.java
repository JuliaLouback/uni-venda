package resources.view.login;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.dao.DaoLogin;
import main.entity.Funcionario;
import main.util.Criptografia;
import main.util.ShowAlert;
import resources.view.pagamento.ControllerViewListaPagamento;
import resources.view.painel.ControllerViewPainel;
import resources.view.painel.ControllerViewPainelCaixa;
import resources.view.painel.ControllerViewPainelRH;

public class ControllerViewLogin {

	@FXML
	private TextField Email;

	@FXML
	private PasswordField Senha;

	@FXML
	private Button btnLogin;

	@FXML
	void btnLogar(ActionEvent event) {

		Funcionario funcionario = new DaoLogin().verificarFuncionario(Email.getText(), new Criptografia().CriptografiaSenha(Senha.getText()));
		
		if(validacaoCampos()) {
			if(funcionario.getCpf() == null) {
				new ShowAlert().erroAlert("E-mail e/ou senha incorretos!");
			} else {
				
				if(funcionario.getStatus().equals("Ativo")) {
					
					System.setProperty("Cpf", funcionario.getCpf());
					System.setProperty("Nome", funcionario.getNome());
					System.setProperty("Cargo", funcionario.getCargo());
				
					new ShowAlert().informationAlert("Login efetuado!");
					
					if(funcionario.getCargo().equals("RH")) {
						Stage stage = (Stage) btnLogin.getScene().getWindow(); 
					    ControllerViewPainelRH t = new ControllerViewPainelRH();
						t.start(stage);
					}
					else if(funcionario.getCargo().equals("Caixa")) {
						Stage stage = (Stage) btnLogin.getScene().getWindow(); 
					    ControllerViewPainelCaixa t = new ControllerViewPainelCaixa();
						t.start(stage);
					}
					else {
						Stage stage = (Stage) btnLogin.getScene().getWindow(); 
					    ControllerViewPainel t = new ControllerViewPainel();
						t.start(stage);
					}
				} else {
					new ShowAlert().erroAlert("Funcionário inativo!");
				}
			}
		}
	}
	    
	public boolean validacaoCampos() {
		if(Email.getText().isEmpty() | Senha.getText().isEmpty() ) {
			
			new ShowAlert().validacao();
			
			return false;
		}
		
		return true;
	}
	
	public void start(Stage primaryStage) {
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/view/Login/Login.fxml"));
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Login - Uni Venda");
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/resources/img/money.png"));
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
