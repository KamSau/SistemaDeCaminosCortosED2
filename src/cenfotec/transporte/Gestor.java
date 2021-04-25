package cenfotec.transporte;
import java.util.List;

import cenfotec.logicanegocios.modelos.Vertice;

public class Gestor {
	private static Gestor singleton = null;
	private GestorHash gestorHash;
	private GestorJSON gestorJSON;

	public Gestor() {
		if (singleton == null) {
			singleton = this;
		}
		gestorHash = new GestorHash(250);
		gestorJSON = new GestorJSON();
		this.agregarVertices(this.gestorJSON.obtenerValoresPaises());
	}

	public static Gestor getGestor() {
		if (singleton != null) {
			singleton = new Gestor();
		}
		return singleton;
	}

	public void agregarVertice(Vertice... verts) {
		for (Vertice n : verts)
			gestorHash.insertaHash(n);
	}
	
	public void agregarVertices(List<Vertice> verts) {
		for (Vertice n : verts)
			gestorHash.insertaHash(n);
	}

	public GestorHash getGestorHash() {
		return gestorHash;
	}

	public GestorJSON getGestorJSON() {
		return gestorJSON;
	}

	@Override
	public String toString() {
		return "Gestor [vertices=" + gestorHash.retornarLista() + "]";
	}

}