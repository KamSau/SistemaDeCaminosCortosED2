package cenfotec.transporte;

import java.util.List;
import cenfotec.datos.LectorJSONPaises;
import cenfotec.logicanegocios.modelos.Pais;
import cenfotec.logicanegocios.modelos.Vertice;

public class GestorJSON {
	private LectorJSONPaises lectorJSON;
	private final String jsonPaises = "/paises.json";

	public GestorJSON() {
		lectorJSON = new LectorJSONPaises();
	}

	public LectorJSONPaises getLectorJSON() {
		return lectorJSON;
	}

	public void setLectorJSON(LectorJSONPaises lectorJSON) {
		this.lectorJSON = lectorJSON;
	}
	
	public List<Vertice> obtenerValoresPaises(){
		return lectorJSON.obtenerValores(jsonPaises);
	}
}
