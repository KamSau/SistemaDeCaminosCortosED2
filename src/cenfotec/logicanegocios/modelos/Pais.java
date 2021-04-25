package cenfotec.logicanegocios.modelos;

public class Pais {
	private String codigo;
	private String nombre;
	// La posicion 0 siempre llevara la latitud y la posicion 1 la longitud
	private double[] coordenadas;
	
	public Pais(String codigo, String nombre, double[] coordenadas) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.coordenadas = coordenadas;
	}
	
	public Pais(String codigo) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.coordenadas = coordenadas;
	}
	
	public Pais() {
		this.codigo = null;
		this.nombre = null;
		this.coordenadas = new double[2];
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getNombre() {
		return nombre;
	}

	public double[] getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(double[] coordenadas) {
		this.coordenadas = coordenadas;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return getNombre();
	}
}
