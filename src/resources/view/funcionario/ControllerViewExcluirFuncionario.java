package resources.view.funcionario;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.dao.DaoEndereco;
import main.dao.DaoFuncionario;
import main.dao.DaoFuncionarioTelefone;
import main.dao.DaoTelefone;
import main.entity.Endereco;
import main.entity.Funcionario;
import main.entity.Telefone;
import main.util.ShowAlert;
import resources.view.login.ControllerViewLogin;
import resources.view.painel.ControllerViewPainel;
import resources.view.painel.ControllerViewPainelCaixa;
import resources.view.painel.ControllerViewPainelRH;

public class ControllerViewExcluirFuncionario {


    @FXML
    private Label Nome;

    @FXML
    private Label Data_nascimento;

    @FXML
    private Label Email;

    @FXML
    private Label Cpf;

    @FXML
    private Label Cargo;

    @FXML
    private Label Inscricao_municipal;

    @FXML
    private Label Telefone_fixo;

    @FXML
    private Label Telefone_celular;

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
    private Button btnBack;

    @FXML
    private Label Salário;

    @FXML
    private Label Status;

    @FXML
    private Label Salario;

    private Funcionario Funcionarios = new Funcionario();
    
    private ArrayList<Long> lista; 


    @FXML
    void btnBack(ActionEvent event) {
    	 Stage stage = (Stage) btnBack.getScene().getWindow(); 
	     ControllerViewListaFuncionario t = new ControllerViewListaFuncionario();
		 t.start(stage);
    }

    @FXML
    void btnExcluir(ActionEvent event) {
    	lista.forEach(action -> {
	    	new DaoFuncionarioTelefone().excluirFuncionarioTelefone(action);
	    	new DaoTelefone().excluiTelefone(action);
	    });
    	
       	if(new DaoFuncionario().excluirFuncionario(Funcionarios.getCpf())) {
	    	new DaoEndereco().excluirEndereco(Funcionarios.getId_endereco());
	    	
	    	new ShowAlert().sucessoAlert("Funcionário excluído com sucesso!");
	    	
			Stage stage = (Stage) btnBack.getScene().getWindow(); 
		    ControllerViewListaFuncionario t = new ControllerViewListaFuncionario();
			t.start(stage);
       	}
 
    }

    public void setLabelText(Funcionario funcionario){
    	 this.Funcionarios = funcionario;
		
	     this.Cpf.setText(funcionario.getCpf());
	     this.Nome.setText(funcionario.getNome());
	     this.Email.setText(funcionario.getEmail());
	     this.Cargo.setText(funcionario.getCargo());
	     
	     LocalDate data = funcionario.getData_nascimento();

	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	     String dataFormatada = data.format(formatter);
	     
	     this.Data_nascimento.setText(dataFormatada);	
	     this.Status.setText(funcionario.getStatus());
	     this.Salario.setText(String.valueOf(funcionario.getSalario()));

	     Endereco endereco = new Endereco();
	     endereco = new DaoEndereco().listarEndereco(funcionario.getId_endereco());
	     
	     this.Cep.setText(endereco.getCep());
	     this.Numero.setText(String.valueOf(endereco.getNumero()));
	     this.Rua.setText(endereco.getRua());
	     this.Bairro.setText(endereco.getBairro());
	     this.Cidade.setText(endereco.getCidade());
	     this.Estado.setText(endereco.getEstado());
	     this.Telefone_celular.setText("");
	     this.Telefone_fixo.setText("");
	     
	     
	     lista = new DaoFuncionarioTelefone().listarFuncionarioTelefone(funcionario.getCpf());
		  
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
    	
    	String cargo = System.getProperty("Cargo");
    	
    	if(cargo.equals("RH")) {
			Stage stage = (Stage) btnBack.getScene().getWindow(); 
		    ControllerViewPainelRH t = new ControllerViewPainelRH();
			t.start(stage);
		}
		else {
			Stage stage = (Stage) btnBack.getScene().getWindow(); 
		    ControllerViewPainel t = new ControllerViewPainel();
			t.start(stage);
		}
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
