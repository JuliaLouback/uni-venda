package resources.view.funcionario;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.Validator;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.dao.DaoEndereco;
import main.dao.DaoFuncionario;
import main.dao.DaoFuncionarioTelefone;
import main.dao.DaoTelefone;
import main.entity.Endereco;
import main.entity.Funcionario;
import main.entity.FuncionarioTelefone;
import main.entity.Telefone;
import main.util.MaskFieldUtil;
import main.util.ShowAlert;
import resources.view.painel.ControllerViewPainel;

public class ControllerViewFuncionario implements Initializable{

	  @FXML
	    private TextField Nome;

	    @FXML
	    private TextField Cpf;

	    @FXML
	    private TextField Email;

	    @FXML
	    private TextField Salario;

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

	    @FXML
	    private Label labelChange;

	    @FXML
	    private DatePicker Data_nascimento;

	    @FXML
	    private ComboBox<String> Cargo;

	    @FXML
	    private ComboBox<String> Status;
    
    private Funcionario Funcionarios = new Funcionario();
    
    private ArrayList<Telefone> listaTelefone = new ArrayList<Telefone>();

    private ArrayList<String> listaTelefoneTipo = new ArrayList<String>();
    
    @FXML
    void addFuncionario(ActionEvent event) {
    	
    	if(Funcionarios.getNome() != null && !Funcionarios.getNome().isEmpty()) {
    		editar();
    	} else {
    		adicionar();
    	}
    	
    }

	public void start(Stage primaryStage) {
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/view/Funcionario/CadastroFuncionario.fxml"));
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Cadastro Funcionário - Uni Venda");
			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/resources/img/money.png"));
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	 @FXML
	 void BackButton(ActionEvent event) {
		 Stage stage = (Stage) btnBack.getScene().getWindow(); 
	     ControllerViewListaFuncionario t = new ControllerViewListaFuncionario();
		 t.start(stage);
		 
	 }
	 
	 public void setLabelText(Funcionario funcionario){
		 this.Funcionarios = funcionario;
		 this.labelChange.setText("Editar"); 
		 this.btnAdd.setText("Editar");
		 this.Cpf.setEditable(false);
	     this.Cpf.setText(funcionario.getCpf());
	     this.Nome.setText(funcionario.getNome());
	     this.Email.setText(funcionario.getEmail());
	     this.Cargo.setValue(funcionario.getCargo());
	     this.Data_nascimento.setValue(funcionario.getData_nascimento());	
	     this.Status.setValue(funcionario.getStatus());
	     this.Salario.setText(String.valueOf(funcionario.getSalario()));

	     Endereco endereco = new Endereco();
	     endereco = new DaoEndereco().listarEndereco(funcionario.getId_endereco());
	     
	     this.Cep.setText(endereco.getCep());
	     this.Numero.setText(String.valueOf(endereco.getNumero()));
	     this.Rua.setText(endereco.getRua());
	     this.Bairro.setText(endereco.getBairro());
	     this.Cidade.setText(endereco.getCidade());
	     this.Estado.setText(endereco.getEstado());
	     
	     ArrayList<Long> lista = new DaoFuncionarioTelefone().listarFuncionarioTelefone(funcionario.getCpf());
	  
	    
	     lista.forEach(action -> {
	    	  Telefone telefone =  new Telefone();
	    	  telefone = new DaoTelefone().listarTelefone(action);
	    	  listaTelefone.add(telefone);
	    	  listaTelefoneTipo.add(telefone.getTipo());
	     });
	     
	     if(listaTelefone.size() >= 1) {
		     for(Telefone telefone:listaTelefone) {
		    	 if(telefone.getTipo().equals("Fixo")) {
		    		 Telefone_fixo.setText(telefone.getTelefones());
		    	 } else {
		    		 Telefone_celular.setText(telefone.getTelefones());
		    	 }
		     }
	     }
	  }
	 
	 public void adicionar() {
	 
		if(validacaoCampos()) {
			Endereco endereco = new Endereco();
	    	
	    	endereco.setCep(Cep.getText());
	    	endereco.setNumero(Integer.valueOf(Numero.getText()));
	    	endereco.setRua(Rua.getText());
	    	endereco.setBairro(Bairro.getText());
	    	endereco.setCidade(Cidade.getText());
	    	endereco.setEstado(Estado.getText());
		    	
	    	long id = new DaoEndereco().inserirEndereco(endereco);
	    	
	    	Funcionario funcionario = new Funcionario();
	    	
	    	funcionario.setCpf(Cpf.getText());
	    	funcionario.setNome(Nome.getText());
	    	funcionario.setEmail(Email.getText());
	    	funcionario.setCargo(Cargo.getValue().toString());
			funcionario.setData_nascimento(Data_nascimento.getValue());
	    	funcionario.setStatus(Status.getValue().toString());
			funcionario.setSalario(Float.parseFloat(Salario.getText()));

			funcionario.setId_endereco(id);
	    	
	    	new DaoFuncionario().inserirFuncionario(funcionario);
	    	
	    	if(Telefone_fixo.getText() != null && !Telefone_fixo.getText().isEmpty()) {
	    		Telefone telefoneFixo = new Telefone();
	    		
	    		telefoneFixo.setTelefones(Telefone_fixo.getText());
	    		telefoneFixo.setTipo("Fixo");
	    		
	    		long ids = new DaoTelefone().inserirTelefone(telefoneFixo);
	    		
	    		FuncionarioTelefone FuncionarioTelefone = new FuncionarioTelefone();
	    		FuncionarioTelefone.setCpf(Cpf.getText());
	    		FuncionarioTelefone.setId_telefone(ids);
	    		
	    		new DaoFuncionarioTelefone().inserirFuncionarioTelefone(FuncionarioTelefone);
	    	}
	    	
	    	if(Telefone_celular.getText() != null && !Telefone_celular.getText().isEmpty()) {
	    		Telefone telefoneCelular = new Telefone();
	    		
	    		telefoneCelular.setTelefones(Telefone_celular.getText());
	    		telefoneCelular.setTipo("Celular");
	    		
	    		long ids = new DaoTelefone().inserirTelefone(telefoneCelular);
	    		
	    		FuncionarioTelefone FuncionarioTelefone = new FuncionarioTelefone();
	    		FuncionarioTelefone.setCpf(Cpf.getText());
	    		FuncionarioTelefone.setId_telefone(ids);
	    		
	    		new DaoFuncionarioTelefone().inserirFuncionarioTelefone(FuncionarioTelefone);
	    	}
	    	
	    	new ShowAlert().sucessoAlert("Funcionario adicionado com sucesso!"); 
	    	
	    	 Stage stage = (Stage) btnBack.getScene().getWindow(); 
		     ControllerViewListaFuncionario t = new ControllerViewListaFuncionario();
			 t.start(stage);
		}
	
	 }
	 
	 
	 private void editar() {
		if(validacaoCampos()) {
			Funcionario funcionario = new Funcionario();
		    	
			funcionario.setCpf(Cpf.getText());
	    	funcionario.setNome(Nome.getText());
	    	funcionario.setEmail(Email.getText());
	    	funcionario.setCargo(Cargo.getValue().toString());
			funcionario.setData_nascimento(Data_nascimento.getValue());
	    	funcionario.setStatus(Status.getValue().toString());
			funcionario.setSalario(Float.parseFloat(Salario.getText()));
	    	
	    	new DaoFuncionario().alterarFuncionario(funcionario);
	    	
	        Endereco endereco = new Endereco();
	    	
	    	endereco.setCep(Cep.getText());
	    	endereco.setNumero(Integer.valueOf(Numero.getText()));
	    	endereco.setRua(Rua.getText());
	    	endereco.setBairro(Bairro.getText());
	    	endereco.setCidade(Cidade.getText());
	    	endereco.setEstado(Estado.getText());
	    	endereco.setId_endereco(Funcionarios.getId_endereco());
		    	
	    	new DaoEndereco().alterarEndereco(endereco);
	    	
	    	if(listaTelefoneTipo.contains("Fixo")) {
	    		listaTelefone.forEach(action -> {
	    			
	    			if(action.getTipo().equals("Fixo")) {
		    			Telefone telefoneFixo = new Telefone();
		        		
		        		telefoneFixo.setTelefones(Telefone_fixo.getText());
		        		telefoneFixo.setId_telefone(action.getId_telefone());
		        		
		        		new DaoTelefone().alterarTelefone(telefoneFixo);
	    			}
	    		});
	    		
	    	} else if(Telefone_fixo.getText() != null && !Telefone_fixo.getText().isEmpty()) {
	    		Telefone telefoneFixo = new Telefone();
	    		
	    		telefoneFixo.setTelefones(Telefone_fixo.getText());
	    		telefoneFixo.setTipo("Fixo");
	    		
	    		long ids = new DaoTelefone().inserirTelefone(telefoneFixo);
	    		
	    		FuncionarioTelefone FuncionarioTelefone = new FuncionarioTelefone();
	    		FuncionarioTelefone.setCpf(Cpf.getText());
	    		FuncionarioTelefone.setId_telefone(ids);
	    		
	    		new DaoFuncionarioTelefone().inserirFuncionarioTelefone(FuncionarioTelefone);
	    	}
	    	
		    if(listaTelefoneTipo.contains("Celular")) {
	    		
	    		listaTelefone.forEach(action -> {
	    			
	    			if(action.getTipo().equals("Celular")) {
		    			Telefone telefoneCelular = new Telefone();
		        		
		    			telefoneCelular.setTelefones(Telefone_celular.getText());
		    			telefoneCelular.setId_telefone(action.getId_telefone());
		    			
		    			new DaoTelefone().alterarTelefone(telefoneCelular);
	    			}
	    		});
	    		
	    	} else if(Telefone_celular.getText() != null && !Telefone_celular.getText().isEmpty()) {
	    		Telefone telefoneFixo = new Telefone();
	    		
	    		telefoneFixo.setTelefones(Telefone_celular.getText());
	    		telefoneFixo.setTipo("Celular");
	    		
	    		long ids = new DaoTelefone().inserirTelefone(telefoneFixo);
	    		
	    		FuncionarioTelefone FuncionarioTelefone = new FuncionarioTelefone();
	    		FuncionarioTelefone.setCpf(Cpf.getText());
	    		FuncionarioTelefone.setId_telefone(ids);
	    		
	    		new DaoFuncionarioTelefone().inserirFuncionarioTelefone(FuncionarioTelefone);
	    	}
		    
		    if(new ShowAlert().sucessoAlert("Funcionario editado com sucesso!")) {
			    Stage stage = (Stage) btnBack.getScene().getWindow(); 
			    ControllerViewListaFuncionario t = new ControllerViewListaFuncionario();
				t.start(stage);
		    }
		}
	 }
	 
    public void buscarCep(String cep) {
	
	 String json;        

        try {
        	 Rua.setText("...");
             Bairro.setText("...");
             Cidade.setText("...");
             Estado.setText("...");
             
            URL url = new URL("http://viacep.com.br/ws/"+ Cep.getText().replace("-", "") +"/json");
            
            URLConnection urlConnection = url.openConnection();
            
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

            StringBuilder jsonSb = new StringBuilder();

            br.lines().forEach(l -> jsonSb.append(l.trim()));
         
            json = jsonSb.toString();
            
            json = json.replaceAll("[{},:]", "");
            json = json.replaceAll("\"", "\n");                       
            String array[] = new String[30];
            array = json.split("\n");
          
            String logradouro = array[7];            
            String bairro = array[15];
            String cidade = array[19]; 
            String uf = array[23];
            
            Rua.setText(logradouro);
            Bairro.setText(bairro);
            Cidade.setText(cidade);
            Estado.setText(uf);
           
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Cep.focusedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		        if(!newValue) {
		        	if(Cep.getText().length() > 8) {
		        		buscarCep(Cep.getText());
		        	}
		        }
		    }
		});	
		
		MaskFieldUtil.foneField(this.Telefone_fixo);
		MaskFieldUtil.foneField(this.Telefone_celular);
        MaskFieldUtil.cepField(this.Cep);
        MaskFieldUtil.cpfField(this.Cpf);
		Cargo.getItems().setAll("Gerente", "Caixa", "RH"); 
		Status.getItems().setAll("Ativo", "Inativo"); 

	}
	
	public boolean validacaoCampos() {
		if(Cpf.getText().isEmpty() | Nome.getText().isEmpty() | Email.getText().isEmpty() | Cargo.getSelectionModel().isEmpty() |
				Salario.getText().isEmpty() | Data_nascimento.getValue() == null| Status.getSelectionModel().isEmpty() | Cep.getText().isEmpty() | Numero.getText().isEmpty() |
		   Rua.getText().isEmpty() | Bairro.getText().isEmpty() | Cidade.getText().isEmpty() |
		   Estado.getText().isEmpty()) {
			
			new ShowAlert().validacaoAlert();
			
			return false;
		}
		
		return true;
	}
	
	 @FXML
	 void VoltarPainel(ActionEvent event) {
		 Stage stage = (Stage) btnBack.getScene().getWindow(); 
	     ControllerViewPainel t = new ControllerViewPainel();
		 t.start(stage);
	 }
}
