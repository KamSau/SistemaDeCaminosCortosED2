package cenfotec.datos;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cenfotec.datos.jsonparser.JSONArray;
import cenfotec.datos.jsonparser.JSONObject;
import cenfotec.datos.jsonparser.parser.JSONParser;
import cenfotec.datos.jsonparser.parser.ParseException;
import cenfotec.logicanegocios.modelos.Pais;
import cenfotec.logicanegocios.modelos.Vertice;

public class LectorJSONPaises extends LectorJSON {
	private final String ATRIBUTO_OBJETO_NOMBRE = "name";
	private final String ATRIBUTO_NOMBRE_CORTO = "common";
	private final String ATRIBUTO_CODIGO = "cca3";
	private final String ATRIBUTO_COORDENADAS = "latlng";
	private final String ATRIBUTO_COLINDANTES = "borders";

	public LectorJSONPaises() {
	}

	private String readFromInputStream(InputStream inputStream)
			throws IOException {
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br
					 = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line).append("\n");
			}
		}
		return resultStringBuilder.toString();
	}

	public List<Vertice> obtenerValores(String nombreArchivo) {
		JSONParser jsonParser = new JSONParser();
		List<Vertice> valores = new LinkedList<>();
		Map<String, Vertice> mapaVertices = new HashMap<>();

		try (InputStream inputStream = LectorJSONPaises.class.getResourceAsStream(nombreArchivo)) {

			Object obj = jsonParser.parse(readFromInputStream(inputStream));

			JSONArray listaPaises = (JSONArray) obj;

			for (Object pais : listaPaises) {
				Vertice verticePais = crearVerticePais((JSONObject) pais);
				mapaVertices.put(verticePais.getLabel(), verticePais);
			}

			for (Object pais : listaPaises) {
				Vertice verticeOrigen = mapaVertices.get(obtenerValorStringDeJSON((JSONObject) pais, ATRIBUTO_CODIGO));
				JSONArray colindantes = obtenerArregloJSONDeJSON((JSONObject) pais, ATRIBUTO_COLINDANTES);
				if (colindantes.size() == 0)
					continue;
				for (Object colindante : colindantes) {
					Vertice verticeDestino = mapaVertices.get((String) colindante);
					Pais paisOrigen = (Pais) verticeOrigen.getContenido();
					Pais paisDestino = (Pais) verticeDestino.getContenido();
					double distancia = distanciaDesdeCoordenadas(paisOrigen.getCoordenadas(),
							paisDestino.getCoordenadas());
					verticeOrigen.agregarArco(verticeDestino, distancia);
				}
				valores.add(verticeOrigen);
			}

		} catch (FileNotFoundException fne) {
			fne.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ParseException pe) {
			pe.printStackTrace();
		}

		return valores;
	}

	private Vertice crearVerticePais(JSONObject pais) {
		double latitud = 0, longitud = 0;
		JSONObject objetoNombrePais = obtenerObjetoJSONDeJSON(pais, ATRIBUTO_OBJETO_NOMBRE);
		JSONArray objetoCoordenadas = obtenerArregloJSONDeJSON(pais, ATRIBUTO_COORDENADAS);

		String nombre = obtenerValorStringDeJSON(objetoNombrePais, ATRIBUTO_NOMBRE_CORTO);
		String codigo = obtenerValorStringDeJSON(pais, ATRIBUTO_CODIGO);

		// La posicion de la latitud y longitud esta definida por la posicion de estos
		// valores en el archivo JSON
		if (objetoCoordenadas.get(0) instanceof Number || objetoCoordenadas.get(1) instanceof Number) {
			latitud = ((Number) objetoCoordenadas.get(0)).doubleValue();
			longitud = ((Number) objetoCoordenadas.get(1)).doubleValue();
		} else {
			latitud = (double) objetoCoordenadas.get(0);
			longitud = (double) objetoCoordenadas.get(1);
		}

		double[] arregloCoordenadas = { latitud, longitud };

		Pais objetoPais = new Pais(codigo, nombre, arregloCoordenadas);
		Vertice vertice = new Vertice(codigo, objetoPais);

		return vertice;
	}

	private Vertice crearVerticePaisCodigo(JSONObject pais) {
		String codigo = obtenerValorStringDeJSON(pais, ATRIBUTO_CODIGO);
		Pais objetoPais = new Pais(codigo);
		Vertice vertice = new Vertice(codigo, objetoPais);
		return vertice;
	}

	private double distanciaDesdeCoordenadas(double[] coordenadasOrigen, double[] coordenadasDestino) {

		// Implementaci�n de la formula de Haversine para calcular la distancia en KM
		// con base a latitudes y longitudes
		// Siempre la primera posicion de los arreglos de coordenadas tienen la latitud
		// en la posicion 0 y la longitud en la posicion 1
		double radioTierra = 6371; // km

		double latitudOrigen = coordenadasOrigen[0];
		double longitudOrigen = coordenadasOrigen[1];
		double latitudDestino = coordenadasDestino[0];
		double longitudDestino = coordenadasDestino[1];

		double diferenciaLatitud = Math.toRadians((latitudDestino - latitudOrigen));
		double diferenciaLongitud = Math.toRadians((longitudDestino - longitudOrigen));

		double a = Math.sin(diferenciaLatitud / 2) * Math.sin(diferenciaLatitud / 2)
				+ Math.cos(Math.toRadians(latitudOrigen)) * Math.cos(Math.toRadians(latitudDestino))
						* Math.sin(diferenciaLongitud / 2) * Math.sin(diferenciaLongitud / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distancia = radioTierra * c;

		return (double) distancia;

	}

}
