package cenfotec.interfazgrafica;

import cenfotec.logicanegocios.modelos.Arco;
import cenfotec.logicanegocios.modelos.Pais;
import cenfotec.logicanegocios.modelos.Vertice;
import cenfotec.transporte.Gestor;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;



public class BuscarPais {


    private List<Pais> paises = new ArrayList<>();
    private Gestor g;
    @FXML
    private TextField paisTextBox;
    @FXML
    private Text codigoText;
    @FXML
    private Text nombreText;
    @FXML
    private TableView<Pais> tabla;
    @FXML
    private TableColumn<Pais, String> nombreCol;
    @FXML
    private TableColumn<Pais, String> codigoCol;
    @FXML
    private TableColumn<Pais, Double> distanciaCol;


    @FXML
    void buscar(){
        Pais pais = buscarCodigoOPais(paisTextBox.getText());
        if(pais != null){
            codigoText.setText("Código: " + pais.getCodigo());
            nombreText.setText("Nombre: " + pais.getNombre());

            Vertice vertice = g.getGestorHash().buscaHash(pais.getCodigo());
            ObservableList<Pais> fxCollections = FXCollections.observableArrayList();
            for(Arco arco : vertice.getArcos()){
                Pais p = (Pais) arco.getDestino().getContenido();
                fxCollections.add(p);
            }

            tabla.setItems(fxCollections);
        }else{
            codigoText.setText("Código: N/A");
            nombreText.setText("Nombre: N/A");
            ObservableList<Pais> fxCollections = FXCollections.observableArrayList();
            tabla.setItems(fxCollections);
        }




    }

    Pais buscarCodigoOPais(String str){
        for(Pais p : paises){
            if(p.getNombre().equals(str) || p.getCodigo().equals(str)) return p;
        }
        return null;
    }


    @FXML
    public void cambiar(){
        try {
            Parent root =  FXMLLoader.load(getClass().getResource("CaminoMasCorto.fxml"));
            Scene sce = new Scene(root);
            Stage stg = (Stage) (tabla).getScene().getWindow();
            stg.setScene(sce);
            stg.setTitle("GeoLocator");
            stg.getIcons().add(new Image("https://i.imgur.com/PFsgpos.png"));
            stg.setScene(sce);
            stg.setAlwaysOnTop(true);
            stg.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        g = Gestor.getGestor();
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        codigoCol.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        for(Vertice v : g.getGestorJSON().obtenerValoresPaises()){
            paises.add((Pais) v.getContenido());
        }
    }
}
