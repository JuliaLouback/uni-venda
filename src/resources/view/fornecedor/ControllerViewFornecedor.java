package resources.view.fornecedor;

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
import javafx.scene.control.Label;
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
import main.util.MaskFieldUtil;
import main.util.ShowAlert;
import resources.view.painel.ControllerViewPainel;

public class ControllerViewFornecedor implements Initializable{

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
    

    @FXML
    private Label labelChange;
    
    private Fornecedor fornecedores = new Fornecedor();
    
    private ArrayList<Telefone> listaTelefone = new ArrayList<Telefone>();

    private ArrayList<String> listaTelefoneTipo = new ArrayList<String>();
    
    @FXML
    void addFornecedor(ActionEvent event) {
    	
    	if(fornecedores.getNome_empresa() != null && !fornecedores.getNome_empresa().isEmpty()) {
    		editar();
    	} else {
    		adicionar();
    	}
    	
    }

	public void start(Stage primaryStage) {
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/view/fornecedor/CadastroFornecedor.fxml"));
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Cadastro Fornecedor - Uni Venda");
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
	     ControllerViewListaFornecedor t = new ControllerViewListaFornecedor();
		 t.start(stage);
		 
	 }
	 
	 public void setLabelText(Fornecedor fornecedor){
		 this.fornecedores = fornecedor;
		 this.labelChange.setText("Editar"); 
		 this.btnAdd.setText("Editar");
		 this.Cnpj.setEditable(false);
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
	     this.Cidade.setText(endereco.getCidade());
	     this.Estado.setText(endereco.getEstado());
	     
	     ArrayList<Long> lista = new DaoFornecedorTelefone().listarFornecedorTelefone(fornecedor.getCnpj());
	  
	    
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
	    		
	    		telefoneFixo.setTelefones(Telefone_fixo.getText());
	    		telefoneFixo.setTipo("Fixo");
	    		
	    		long ids = new DaoTelefone().inserirTelefone(telefoneFixo);
	    		
	    		FornecedorTelefone fornecedorTelefone = new FornecedorTelefone();
	    		fornecedorTelefone.setCnpj(Cnpj.getText());
	    		fornecedorTelefone.setId_telefone(ids);
	    		
	    		new DaoFornecedorTelefone().inserirFornecedorTelefone(fornecedorTelefone);
	    	}
	    	
	    	if(Telefone_celular.getText() != null && !Telefone_celular.getText().isEmpty()) {
	    		Telefone telefoneCelular = new Telefone();
	    		
	    		telefoneCelular.setTelefones(Telefone_celular.getText());
	    		telefoneCelular.setTipo("Celular");
	    		
	    		long ids = new DaoTelefone().inserirTelefone(telefoneCelular);
	    		
	    		FornecedorTelefone fornecedorTelefone = new FornecedorTelefone();
	    		fornecedorTelefone.setCnpj(Cnpj.getText());
	    		fornecedorTelefone.setId_telefone(ids);
	    		
	    		new DaoFornecedorTelefone().inserirFornecedorTelefone(fornecedorTelefone);
	    	}
	    	
	    	new ShowAlert().sucessoAlert("Fornecedor adicionado com sucesso!"); 
	    	
	    	 Stage stage = (Stage) btnBack.getScene().getWindow(); 
		     ControllerViewListaFornecedor t = new ControllerViewListaFornecedor();
			 t.start(stage);
		}
	
	 }
	 
	 
	 private void editar() {
		if(validacaoCampos()) {
			Fornecedor fornecedor = new Fornecedor();
		    	
	    	fornecedor.setNome_empresa(Nome_empresa.getText());
	    	fornecedor.setCnpj(Cnpj.getText());
	    	fornecedor.setEmail(Email.getText());
	    	fornecedor.setInscricao_municipal(Long.parseLong(Inscricao_municipal.getText()));
	    	fornecedor.setInscricao_estadual(Long.parseLong(Inscricao_estadual.getText()));
	    	
	    	new DaoFornecedor().alterarFornecedor(fornecedor);
	    	
	        Endereco endereco = new Endereco();
	    	
	    	endereco.setCep(Cep.getText());
	    	endereco.setNumero(Integer.valueOf(Numero.getText()));
	    	endereco.setRua(Rua.getText());
	    	endereco.setBairro(Bairro.getText());
	    	endereco.setCidade(Cidade.getText());
	    	endereco.setEstado(Estado.getText());
	    	endereco.setId_endereco(fornecedores.getId_endereco());
		    	
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
	    		
	    		FornecedorTelefone fornecedorTelefone = new FornecedorTelefone();
	    		fornecedorTelefone.setCnpj(Cnpj.getText());
	    		fornecedorTelefone.setId_telefone(ids);
	    		
	    		new DaoFornecedorTelefone().inserirFornecedorTelefone(fornecedorTelefone);
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
	    		
	    		FornecedorTelefone fornecedorTelefone = new FornecedorTelefone();
	    		fornecedorTelefone.setCnpj(Cnpj.getText());
	    		fornecedorTelefone.setId_telefone(ids);
	    		
	    		new DaoFornecedorTelefone().inserirFornecedorTelefone(fornecedorTelefone);
	    	}
		    
		    if(new ShowAlert().sucessoAlert("Fornecedor editado com sucesso!")) {
			    Stage stage = (Stage) btnBack.getScene().getWindow(); 
			    ControllerViewListaFornecedor t = new ControllerViewListaFornecedor();
				t.start(stage);
		    }
		}
	 }
	 
    public void buscarCep(String cep) {
	
	 String json;        

        try {
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
        MaskFieldUtil.cpfCnpjField(this.Cnpj);
	}
	
	public boolean validacaoCampos() {
		if(Cnpj.getText().isEmpty() | Nome_empresa.getText().isEmpty() | Email.getText().isEmpty() | Inscricao_estadual.getText().isEmpty() |
		   Inscricao_municipal.getText().isEmpty() | Cep.getText().isEmpty() | Numero.getText().isEmpty() |
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
