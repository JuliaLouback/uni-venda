package resources.view.fornecedor;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.dao.DaoEndereco;
import main.dao.DaoFornecedor;
import main.dao.DaoFornecedorTelefone;
import main.dao.DaoTelefone;
import main.entity.Endereco;
import main.entity.Fornecedor;
import main.entity.FornecedorTelefone;
import main.entity.Telefone;

public class ControllerViewFornecedor {

    @FXML
    private TextField Cnpj;

    @FXML
    private TextField Nome_empresa;

    @FXML
    private TextField Email;

    @FXML
    private TextField Inscricao_estadual;

    @FXML
    private TextField Inscricao_municipal;

    @FXML
    private TextField Telefone_celular;

    @FXML
    private TextField Telefone_fixo;

    @FXML
    private TextField Cep;

    @FXML
    private TextField Rua;

    @FXML
    private TextField Numero;

    @FXML
    private TextField Bairro;

    @FXML
    private TextField Cidade;

    @FXML
    private TextField Estado;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    private Fornecedor forn = new Fornecedor();
    
    @FXML
    void addFornecedor(ActionEvent event) {
    	
    	Endereco endereco = new Endereco();
    	
    	endereco.setCep(Cep.getText());
    	endereco.setNumero(Integer.valueOf(Numero.getText()));
    	endereco.setRua(Rua.getText());
    	endereco.setBairro(Bairro.getText());
    	endereco.setCidade(Cidade.getText());
    	endereco.setEstado(Estado.getText());
    	
    	long id = new DaoEndereco().inserirEndereco(endereco);
    	
    	Fornecedor fornecedor = new Fornecedor();
    	
    	fornecedor.setNome_empresa(Nome_empresa.getText());
    	fornecedor.setCnpj(Cnpj.getText());
    	fornecedor.setEmail(Email.getText());
    	fornecedor.setInscricao_municipal(Long.parseLong(Inscricao_municipal.getText()));
    	fornecedor.setInscricao_estadual(Long.parseLong(Inscricao_estadual.getText()));
    	fornecedor.setId_endereco(id);
    	
    	new DaoFornecedor().inserirFornecedor(fornecedor);
    	
    	if(Telefone_fixo.getText() != null && !Telefone_fixo.getText().isEmpty()) {
    		Telefone telefoneFixo = new Telefone();
    		
    		telefoneFixo.setTelefones(Long.parseLong(Telefone_fixo.getText()));
    		
    		long ids = new DaoTelefone().inserirTelefone(telefoneFixo);
    		
    		FornecedorTelefone fornecedorTelefone = new FornecedorTelefone();
    		fornecedorTelefone.setCnpj(Cnpj.getText());
    		fornecedorTelefone.setId_telefone(ids);
    		
    		new DaoFornecedorTelefone().inserirFornecedorTelefone(fornecedorTelefone);
    	}
    	
    	if(Telefone_celular.getText() != null && !Telefone_celular.getText().isEmpty()) {
    		Telefone telefoneCelular = new Telefone();
    		
    		telefoneCelular.setTelefones(Long.parseLong(Telefone_celular.getText()));
    		
    		long ids = new DaoTelefone().inserirTelefone(telefoneCelular);
    		
    		FornecedorTelefone fornecedorTelefone = new FornecedorTelefone();
    		fornecedorTelefone.setCnpj(Cnpj.getText());
    		fornecedorTelefone.setId_telefone(ids);
    		
    		new DaoFornecedorTelefone().inserirFornecedorTelefone(fornecedorTelefone);
    	}
    	
    	
    }

	public void start(Stage primaryStage) {
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/view/fornecedor/CadastroFornecedor.fxml"));
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Cadastro Fornecedor - Uni Venda");
			forn = (Fornecedor) primaryStage.getUserData();
			primaryStage.show();
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	 @FXML
	 void BackButton(ActionEvent event) {
		 Stage stage = (Stage) btnBack.getScene().getWindow(); 
	     ControllerViewListaFornecedor t = new ControllerViewListaFornecedor();
		 t.start(stage);
	 }
	 

}
