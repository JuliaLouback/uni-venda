package resources.view.funcionario;

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
import main.dao.DaoFuncionario;
import main.entity.Funcionario;
import main.util.ShowAlert;
import resources.view.login.ControllerViewLogin;
import resources.view.painel.ControllerViewPainel;
import resources.view.painel.ControllerViewPainelCaixa;
import resources.view.painel.ControllerViewPainelRH;

public class ControllerViewListaFuncionario implements Initializable{

    @FXML
    private TableView<Funcionario> tabela;

    @FXML
    private TableColumn<Funcionario, String> Nome;

    @FXML
    private TableColumn<Funcionario, String> Cpf;

    @FXML
    private TableColumn<Funcionario, String> Email;

    @FXML
    private Button btnAdd;
   
	ArrayList<Funcionario> listas = new DaoFuncionario().listarFuncionarios();

    ObservableList<Funcionario> lista = FXCollections.observableArrayList(listas);
    		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Nome.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("Nome"));
		Cpf.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("Cpf"));
		Email.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("Email"));
		
	    tabela.setItems(lista);
	    tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	    addButtonToTable();
	    addButtonExcluir();
		
	}
	
	 @FXML
	 void AddFuncionario(ActionEvent event) {
		 try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Funcionario/CadastroFuncionario.fxml"));
             Parent root = loader.load();

             ControllerViewFuncionario controllerView = loader.getController();
             controllerView.mostrarCampo();;
     
             Stage stage = new Stage();
             stage.setScene(new Scene(root));
             stage.setTitle("Adicionar Funcionário - Uni Venda");
             stage.centerOnScreen();
 			stage.getIcons().add(new Image("/resources/img/money.png"));
 			stage.setResizable(false);
             stage.show();
             
             Stage stages = (Stage) btnAdd.getScene().getWindow();
             stages.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
	 }

	public void start(Stage primaryStage) {
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/view/Funcionario/ListaFuncionario.fxml"));
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Lista Funcionários - Uni Venda");
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/resources/img/money.png"));
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	private void addButtonToTable() {
        TableColumn<Funcionario, Void> colBtn = new TableColumn("Editar");

        Callback<TableColumn<Funcionario, Void>, TableCell<Funcionario, Void>> cellFactory = new Callback<TableColumn<Funcionario, Void>, TableCell<Funcionario, Void>>() {
            @Override
            public TableCell<Funcionario, Void> call(final TableColumn<Funcionario, Void> param) {
                final TableCell<Funcionario, Void> cell = new TableCell<Funcionario, Void>() {

                    private final Button btn = new Button("Editar");{
                        btn.setOnAction((ActionEvent event) -> {
                        	
                            Funcionario funcionario = getTableView().getItems().get(getIndex());
                            
                            try {
	                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Funcionario/CadastroFuncionario.fxml"));
	                            Parent root = loader.load();
	
	                            ControllerViewFuncionario controllerView = loader.getController();
	                            controllerView.setLabelText(funcionario);
	                    
	                            Stage stage = new Stage();
	                            stage.setScene(new Scene(root));
	                            stage.setTitle("Editar Funcionário - Uni Venda");
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
        TableColumn<Funcionario, Void> colBtn = new TableColumn("Excluir");

        Callback<TableColumn<Funcionario, Void>, TableCell<Funcionario, Void>> cellFactory = new Callback<TableColumn<Funcionario, Void>, TableCell<Funcionario, Void>>() {
            @Override
            public TableCell<Funcionario, Void> call(final TableColumn<Funcionario, Void> param) {
                final TableCell<Funcionario, Void> cell = new TableCell<Funcionario, Void>() {

                    private final Button btn = new Button("Excluir");{
                        btn.setOnAction((ActionEvent event) -> {
                        	
                            Funcionario funcionario = getTableView().getItems().get(getIndex());
                            
                            try {
	                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Funcionario/ExcluirFuncionario.fxml"));
	                            Parent root = loader.load();
	
	                            ControllerViewExcluirFuncionario controllerView = loader.getController();
	                            controllerView.setLabelText(funcionario);
	                    
	                            Stage stage = new Stage();
	                            stage.setScene(new Scene(root));
	                            stage.setTitle("Excluir Funcionario - Uni Venda");
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
		 String cargo = System.getProperty("Cargo");
	    	
		 if(cargo.equals("RH")) {
			Stage stage = (Stage) btnAdd.getScene().getWindow(); 
			ControllerViewPainelRH t = new ControllerViewPainelRH();
			t.start(stage);
		 }
		 else {
			Stage stage = (Stage) btnAdd.getScene().getWindow(); 
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
			 Stage stage = (Stage) btnAdd.getScene().getWindow(); 
		     ControllerViewLogin t = new ControllerViewLogin();
			 t.start(stage);
		 }
    }

}

