package cenfotec.interfazgrafica;

import cenfotec.logicanegocios.Dijkstra;
import cenfotec.logicanegocios.modelos.Arco;
import cenfotec.logicanegocios.modelos.Pais;
import cenfotec.logicanegocios.modelos.Vertice;
import cenfotec.transporte.Gestor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class CaminoMasCorto {


    private List<Pais> paises = new ArrayList<>();
    private Gestor g;
    @FXML
    private Text caminoText;
    @FXML
    private Text distanciaText;
    @FXML
    private ChoiceBox<Pais> origenBox;
    @FXML
    private ChoiceBox<Pais> destinoBox;


    @FXML
    void buscar() {
        if (origenBox.getValue() == null && destinoBox.getValue() == null) return;
        Pais pais1 = buscarCodigoOPais(origenBox.getValue().getCodigo());
        Pais pais2 = buscarCodigoOPais(destinoBox.getValue().getCodigo());
        if (pais1 != null && pais2 != null) {
            Dijkstra dijkstra = new Dijkstra();

            Vertice origen = g.getGestorHash().buscaHash(pais1.getCodigo());
            Vertice destino = g.getGestorHash().buscaHash(pais2.getCodigo());
            dijkstra.calcularCaminoMasCorto(origen);
            System.out.println(dijkstra.obtenerCaminoMasCorto(destino));
            String camino = "";
            List<Vertice> caminoMasCorto = dijkstra.obtenerCaminoMasCorto(destino);
            if (caminoMasCorto.size() == 1) {
                camino = "No es posible este camino.";
                destino.setDistancia(0);
            } else {
                for (Vertice vert : dijkstra.obtenerCaminoMasCorto(destino)) {
                    Pais p = (Pais) vert.getContenido();
                    camino += p.getNombre() + ", ";
                }
                camino = camino.substring(0, camino.length() - 2);
            }
            distanciaText.setText("Distancia: " + String.format("%.2f",destino.getDistancia()) + " km");
            caminoText.setText(camino);
            limpiarDijkstra();
        } else {
            caminoText.setText("");
        }


    }

    private void limpiarDijkstra() {
        g.getGestorHash().retornarLista().forEach(v -> {
            v.setVisitado(false);
            v.setDistancia(Double.MAX_VALUE);
            v.setPredecesor(null);
        });
    }

    private Pais buscarCodigoOPais(String str) {
        for (Pais p : paises) {
            if (p.getNombre().equals(str) || p.getCodigo().equals(str)) return p;
        }
        return null;
    }

    @FXML
    public void cambiar() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("BuscarPais.fxml"));
            Scene sce = new Scene(root);
            Stage stg = (Stage) (origenBox).getScene().getWindow();
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
        for (Vertice v : g.getGestorJSON().obtenerValoresPaises()) {
            if (v.getArcos().size() > 0)
                paises.add((Pais) v.getContenido());
        }
        Collections.sort(paises, new Comparator<Pais>() {
            public int compare(Pais p1, Pais p2) {
                return p1.getNombre().compareTo(p2.getNombre());
            }
        });
        destinoBox.setItems(FXCollections.observableArrayList(paises));
        origenBox.setItems(FXCollections.observableArrayList(paises));
    }
}
