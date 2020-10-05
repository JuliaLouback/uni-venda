package resources.view.categoria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.dao.DaoCategoria;
import main.dao.DaoEndereco;
import main.dao.DaoFornecedor;
import main.entity.Categoria;
import main.entity.Fornecedor;
import main.util.ShowAlert;
import resources.view.fornecedor.ControllerViewListaFornecedor;
import resources.view.painel.ControllerViewPainel;

public class ControllerViewExcluirCategoria {

    @FXML
    private Label Nome;

    @FXML
    private Button btnBack;

    private Categoria Categorias = new Categoria();
    
    @FXML
    void VoltarPainel(ActionEvent event) {
    	 Stage stage = (Stage) btnBack.getScene().getWindow(); 
	     ControllerViewPainel t = new ControllerViewPainel();
		 t.start(stage);
    }

    @FXML
    void btnBack(ActionEvent event) {
    	Stage stage = (Stage) btnBack.getScene().getWindow(); 
	    ControllerViewListaCategoria t = new ControllerViewListaCategoria();
		t.start(stage);
    }

    @FXML
    void btnExcluir(ActionEvent event) {
    	
    	new DaoCategoria().excluirCategoria(Categorias.getId_categoria());
    	
    	new ShowAlert().sucessoAlert("Categoria excluido com sucesso!");
    	
		Stage stage = (Stage) btnBack.getScene().getWindow(); 
	    ControllerViewListaCategoria t = new ControllerViewListaCategoria();
		t.start(stage);
    }

    public void setLabelText(Categoria Categoria){
		 this.Categorias = Categoria;
	     this.Nome.setText(Categoria.getNome());
    }
}
