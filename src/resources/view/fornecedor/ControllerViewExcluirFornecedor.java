package resources.view.fornecedor;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.dao.DaoEndereco;
import main.dao.DaoFornecedor;
import main.dao.DaoFornecedorTelefone;
import main.dao.DaoTelefone;
import main.entity.Endereco;
import main.entity.Fornecedor;
import main.entity.Telefone;
import main.util.ShowAlert;
import resources.view.painel.ControllerViewPainel;

public class ControllerViewExcluirFornecedor {

    @FXML
    private Label Nome_empresa;

    @FXML
    private Label Email;

    @FXML
    private Label Cnpj;

    @FXML
    private Label Inscricao_estadual;

    @FXML
    private Label Inscricao_municipal;

    @FXML
    private Label Telefone_fixo;

    @FXML
    private Label Cep;

    @FXML
    private Label Numero;

    @FXML
    private Label Rua;

    @FXML
    private Label Bairro;

    @FXML
    private Label Cidade;

    @FXML
    private Label Estado;
    
    @FXML
    private Label Telefone_celular;
    
    @FXML
    private Button btnBack;

    private Fornecedor fornecedores = new Fornecedor();
    
    private ArrayList<Long> lista; 


    @FXML
    void btnBack(ActionEvent event) {
    	 Stage stage = (Stage) btnBack.getScene().getWindow(); 
	     ControllerViewListaFornecedor t = new ControllerViewListaFornecedor();
		 t.start(stage);
    }

    @FXML
    void btnExcluir(ActionEvent event) {
    	lista.forEach(action -> {
	    	new DaoFornecedorTelefone().excluirFornecedorTelefone(action);
	    	new DaoTelefone().excluiTelefone(action);
	    });
    	
       	new DaoFornecedor().excluirFornecedor(fornecedores.getCnpj());
    	new DaoEndereco().excluirEndereco(fornecedores.getId_endereco());
    	
    	new ShowAlert().sucessoAlert("Fornecedor excluido com sucesso!");
    	
		Stage stage = (Stage) btnBack.getScene().getWindow(); 
	    ControllerViewListaFornecedor t = new ControllerViewListaFornecedor();
		t.start(stage);
 
    }

    public void setLabelText(Fornecedor fornecedor){
		 this.fornecedores = fornecedor;
	     this.Nome_empresa.setText(fornecedor.getNome_empresa());
	     this.Email.setText(fornecedor.getEmail());
	     this.Inscricao_estadual.setText(String.valueOf(fornecedor.getInscricao_estadual()));
	     this.Inscricao_municipal.setText(String.valueOf(fornecedor.getInscricao_municipal()));
	     this.Cnpj.setText(fornecedor.getCnpj());
	     
	     Endereco endereco = new Endereco();
	     endereco = new DaoEndereco().listarEndereco(fornecedor.getId_endereco());
	     
	     this.Cep.setText(endereco.getCep());
	     this.Numero.setText(String.valueOf(endereco.getNumero()));
	     this.Rua.setText(endereco.getRua());
	     this.Bairro.setText(endereco.getBairro());
	     this.Cidade.setText(endereco.getEstado());
	     this.Estado.setText(endereco.getEstado());
	     
	     lista = new DaoFornecedorTelefone().listarFornecedorTelefone(fornecedor.getCnpj());
		  
	     lista.forEach(action -> {
	    	  Telefone telefone =  new Telefone();
	    	  telefone = new DaoTelefone().listarTelefone(action);
	    	  if(telefone.getTipo().equals("Fixo")) {
	    		  Telefone_fixo.setText(telefone.getTelefones());
	    		
	    	  } else {
	    		  Telefone_celular.setText(telefone.getTelefones());
	    	  }
	     });
	     
	  }
    
    @FXML
	 void VoltarPainel(ActionEvent event) {
		 Stage stage = (Stage) btnBack.getScene().getWindow(); 
	     ControllerViewPainel t = new ControllerViewPainel();
		 t.start(stage);
	 }
}
