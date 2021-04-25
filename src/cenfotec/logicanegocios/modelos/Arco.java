package cenfotec.logicanegocios.modelos;

public class Arco {
	private Vertice origen;
	private Vertice destino;
	private double distancia;

	public Arco(Vertice origen, Vertice destino, double distancia) {
		this.origen = origen;
		this.destino = destino;
		this.distancia = distancia;
	}

	public Arco() {
	}

	public Vertice getOrigen() {
		return origen;
	}

	public void setOrigen(Vertice origen) {
		this.origen = origen;
	}

	public Vertice getDestino() {
		return destino;
	}

	public void setDestino(Vertice destino) {
		this.destino = destino;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	@Override
	public String toString() {
		return "\n Arco [origen=" + origen.getLabel() + ", destino=" + destino.getLabel() + ", distancia=" + distancia
				+ "]";
	}
}