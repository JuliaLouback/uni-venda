package resources.view.produto;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.dao.DaoCategoria;
import main.dao.DaoEndereco;
import main.dao.DaoFornecedor;
import main.dao.DaoProduto;
import main.entity.Produto;
import main.entity.Telefone;
import main.util.ShowAlert;
import resources.view.painel.ControllerViewPainel;

public class ControllerViewExcluirProduto {

	 @FXML
	    private Label Nome;

	    @FXML
	    private Label Id_produto;

	    @FXML
	    private Label Estoque_minimo;

	    @FXML
	    private Label Valor_unitario;

	    @FXML
	    private Label Estoque_maximo;

	    @FXML
	    private Label Estoque_atual;

	    @FXML
	    private Label Peso_bruto;

	    @FXML
	    private Label Peso_liquido;

	    @FXML
	    private Label Categoria;

	    @FXML
	    private Label Fornecedor;

	    @FXML
	    private Label Unidade_medida;

    
    @FXML
    private Button btnBack;

    private Produto Produtos = new Produto();

    @FXML
    void btnBack(ActionEvent event) {
    	 Stage stage = (Stage) btnBack.getScene().getWindow(); 
	     ControllerViewListaProduto t = new ControllerViewListaProduto();
		 t.start(stage);
    }

    @FXML
    void btnExcluir(ActionEvent event) {
    	
       	new DaoProduto().excluirProduto(Produtos.getId_produto());
    	
    	new ShowAlert().sucessoAlert("Produto excluido com sucesso!");
    	
		Stage stage = (Stage) btnBack.getScene().getWindow(); 
	    ControllerViewListaProduto t = new ControllerViewListaProduto();
		t.start(stage);
 
    }

    public void setLabelText(Produto Produto){
		 this.Produtos = Produto;
		 
		 this.Id_produto.setText(String.valueOf(Produto.getId_produto()));
		 this.Nome.setText(Produto.getNome());
		 this.Valor_unitario.setText(String.valueOf(Produto.getValor_unitario()));
		 this.Estoque_minimo.setText(String.valueOf(Produto.getEstoque_minimo()));
		 this.Estoque_maximo.setText(String.valueOf(Produto.getEstoque_maximo()));
		 this.Estoque_atual.setText(String.valueOf(Produto.getEstoque_atual()));
		 this.Peso_bruto.setText(String.valueOf(Produto.getPeso_bruto()));
		 this.Peso_liquido.setText(String.valueOf(Produto.getPeso_liquido()));
		 this.Unidade_medida.setText(Produto.getUnidade_medida());
		 this.Fornecedor.setText(new DaoFornecedor().listarFornecedor(Produto.getFornecedor_Cnpj()).getNome_empresa());
		 this.Categoria.setText(new DaoCategoria().listarCategoria(Produto.getId_categoria()).getNome());
    }
    
    @FXML
	 void VoltarPainel(ActionEvent event) {
		 Stage stage = (Stage) btnBack.getScene().getWindow(); 
	     ControllerViewPainel t = new ControllerViewPainel();
		 t.start(stage);
	 }
}
