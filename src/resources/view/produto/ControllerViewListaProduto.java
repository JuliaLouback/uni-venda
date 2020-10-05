package resources.view.produto;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.javafx.fxml.LoadListener;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.dao.DaoProduto;
import main.entity.Produto;
import resources.view.painel.ControllerViewPainel;

public class ControllerViewListaProduto implements Initializable{

    @FXML
    private TableView<Produto> tabela;

    @FXML
    private TableColumn<Produto, Integer> Id_produto;

    @FXML
    private TableColumn<Produto, String> Nome;

    @FXML
    private TableColumn<Produto, String> Unidade_medida;

    @FXML
    private Button btnAdd;
   
	ArrayList<Produto> listas = new DaoProduto().listarProduto();

    ObservableList<Produto> lista = FXCollections.observableArrayList(listas);
    		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Nome.setCellValueFactory(new PropertyValueFactory<Produto, String>("Nome"));
		Id_produto.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("Id_produto"));
		Unidade_medida.setCellValueFactory(new PropertyValueFactory<Produto, String>("Unidade_medida"));
		
	    tabela.setItems(lista);
	    tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	    addButtonToTable();
	    addButtonExcluir();
		
	}
	
	 @FXML
	 void AddProduto(ActionEvent event) {
		 Stage stage = (Stage) btnAdd.getScene().getWindow(); 
	     ControllerViewProduto t = new ControllerViewProduto();
		 t.start(stage);
	 }

	public void start(Stage primaryStage) {
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/view/Produto/ListaProduto.fxml"));
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Lista Produtos - Uni Venda");
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/resources/img/money.png"));
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	private void addButtonToTable() {
        TableColumn<Produto, Void> colBtn = new TableColumn("Editar");

        Callback<TableColumn<Produto, Void>, TableCell<Produto, Void>> cellFactory = new Callback<TableColumn<Produto, Void>, TableCell<Produto, Void>>() {
            @Override
            public TableCell<Produto, Void> call(final TableColumn<Produto, Void> param) {
                final TableCell<Produto, Void> cell = new TableCell<Produto, Void>() {

                    private final Button btn = new Button("Editar");{
                        btn.setOnAction((ActionEvent event) -> {
                        	
                            Produto Produto = getTableView().getItems().get(getIndex());
                            
                            try {
	                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Produto/CadastroProduto.fxml"));
	                            Parent root = loader.load();
	
	                            ControllerViewProduto controllerView = loader.getController();
	                            controllerView.setLabelText(Produto);
	                    
	                            Stage stage = new Stage();
	                            stage.setScene(new Scene(root));
	                            stage.setTitle("Editar Produto - Uni Venda");
	                            stage.centerOnScreen();
	                			stage.getIcons().add(new Image("/resources/img/money.png"));
	                			stage.setResizable(false);
	                            stage.show();
	                            
	                            Stage stages = (Stage) btn.getScene().getWindow();
	                            stages.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            
                        });
                       
                        btn.setPrefWidth(280);
                        btn.setStyle("-fx-background-color: #28A745;-fx-text-fill:#ffffff; "); 
                       
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
              
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        colBtn.setMinWidth(100);

        tabela.getColumns().add(colBtn);

    }
	
	private void addButtonExcluir() {
        TableColumn<Produto, Void> colBtn = new TableColumn("Excluir");

        Callback<TableColumn<Produto, Void>, TableCell<Produto, Void>> cellFactory = new Callback<TableColumn<Produto, Void>, TableCell<Produto, Void>>() {
            @Override
            public TableCell<Produto, Void> call(final TableColumn<Produto, Void> param) {
                final TableCell<Produto, Void> cell = new TableCell<Produto, Void>() {

                    private final Button btn = new Button("Excluir");{
                        btn.setOnAction((ActionEvent event) -> {
                        	
                            Produto Produto = getTableView().getItems().get(getIndex());
                            
                            try {
	                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Produto/ExcluirProduto.fxml"));
	                            Parent root = loader.load();
	
	                            ControllerViewExcluirProduto controllerView = loader.getController();
	                            controllerView.setLabelText(Produto);
	                    
	                            Stage stage = new Stage();
	                            stage.setScene(new Scene(root));
	                            stage.setTitle("Excluir Produto - Uni Venda");
	                            stage.centerOnScreen();
	                			stage.getIcons().add(new Image("/resources/img/money.png"));
	                			stage.setResizable(false);

	                            stage.show();
	                            
	                            Stage stages = (Stage) btn.getScene().getWindow();
	                            stages.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            
                        });
                       
                        btn.setPrefWidth(280);
                        btn.setStyle("-fx-background-color:#e04b59;-fx-text-fill:#ffffff;"); 
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        colBtn.setMinWidth(90);

        tabela.getColumns().add(colBtn);

    }
	
	 @FXML
	 void VoltarPainel(ActionEvent event) {
		 Stage stage = (Stage) btnAdd.getScene().getWindow(); 
	     ControllerViewPainel t = new ControllerViewPainel();
		 t.start(stage);
	 }

}

