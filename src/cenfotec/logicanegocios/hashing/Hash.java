package cenfotec.logicanegocios.hashing;
import cenfotec.logicanegocios.modelos.Vertice;

public class Hash {
	Vertice vertice;
	int estado; // 0 = Vac�o, 1 = Eliminado, 2 = Ocupado

	public Hash(Vertice vertice, int estado) {
		this.vertice = vertice;
		this.estado = estado;
	}

	public Hash(int estado) {
		this.vertice = null;
		this.estado = estado;
	}

	public Hash() {
		this.vertice = null;
		this.estado = 0;
	}

	public Vertice getVertice() {
		return vertice;
	}

	public void setVertice(Vertice vertice) {
		this.vertice = vertice;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}