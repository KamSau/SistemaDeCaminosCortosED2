package cenfotec.datos;

import cenfotec.datos.jsonparser.JSONArray;
import cenfotec.datos.jsonparser.JSONObject;

public abstract class LectorJSON {
	protected String obtenerValorStringDeJSON(JSONObject objetoJSON, 	String atributo) {
		return (String) objetoJSON.get(atributo);
	}
	
	protected JSONObject obtenerObjetoJSONDeJSON(JSONObject objetoJSON, String atributo) {
		return (JSONObject) objetoJSON.get(atributo);
	}
	
	protected JSONArray obtenerArregloJSONDeJSON(JSONObject objetoJSON, String atributo) {
		return (JSONArray) objetoJSON.get(atributo);
	}
}
