package cenfotec.interfazgrafica;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cenfotec.logicanegocios.Dijkstra;
import cenfotec.logicanegocios.hashing.Hash;
import cenfotec.logicanegocios.hashing.HashTable;
import cenfotec.logicanegocios.modelos.Pais;
import cenfotec.logicanegocios.modelos.Vertice;
import cenfotec.transporte.Gestor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
        Gestor g = new Gestor();

		Parent root = FXMLLoader.load(getClass().getResource("BuscarPais.fxml"));
		primaryStage.setTitle("GeoLocator");
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("https://i.imgur.com/PFsgpos.png"));
		primaryStage.show();
	}
}
