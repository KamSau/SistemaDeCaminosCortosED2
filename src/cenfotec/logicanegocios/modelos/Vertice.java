package cenfotec.logicanegocios.modelos;

import java.util.ArrayList;
import java.util.List;

public class Vertice implements Comparable<Vertice>{
	private String label;
	private Object contenido;
	private List<Arco> arcos;
	private boolean visitado;
	private Vertice predecesor;
	private double distancia = Double.MAX_VALUE;

	public Vertice(String label) {
		this.label = label;
	}
	
	public Vertice(String label, Object contenido) {
		this.label = label;
		this.contenido = contenido;
	}

	public Vertice() {
	}

	public void agregarArco(Vertice destination, double valor) {
		if (arcos == null) {
			arcos = new ArrayList<>();
		}
		arcos.add(new Arco(this, destination, valor));
	}

	public String getLabel() {
		return label;
	}

	public Object getContenido() {
		return contenido;
	}

	public void setContenido(Object contenido) {
		this.contenido = contenido;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Arco> getArcos() {
		return arcos;
	}

	public void setArcos(List<Arco> arcos) {
		this.arcos = arcos;
	}
	
	public boolean isVisitado() {
		return visitado;
	}
	
	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public Vertice getPredecesor() {
		return predecesor;
	}

	public void setPredecesor(Vertice predecesor) {
		this.predecesor = predecesor;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	@Override
	public String toString() {
		// return "\n\n \tVertice [label=" + label + ", arcos=" + arcos + "] ";
		return "\n\n \tVertice [label=" + label + "] ";
	}

	@Override
	public int compareTo(Vertice vertice) {
		return Double.compare(this.getDistancia(), vertice.getDistancia());
	}
}