package cenfotec.logicanegocios.hashing;

import java.util.ArrayList;
import java.util.List;

import cenfotec.logicanegocios.modelos.Vertice;

public class HashTable {

	private int tamanno;
	private Hash[] h;

	public HashTable(int tamanno) {
		this.tamanno = tamanno;
		this.h = new Hash[tamanno];
		for (int i = 0; i < tamanno; i++) {
			this.h[i] = new Hash();
		}
	}
	
	public Hash[] getHashArreglo() {
		return this.h;
	}

	private int _funcionHash(String n) {
		int suma = 0;
		for (int i = 0; i < n.length(); i++) {
			if (i % 2 != 0) {
				suma += n.charAt(i);
			}
		}
		return ((suma + 1) % tamanno);
	}
	
	public int funcionHash(String n) {
		return this._funcionHash(n);
	}

	private String _insertaHash(Vertice vert) {
		boolean isInsertado = false;
		int resultadoHash = funcionHash(vert.getLabel());
		do {
			// 0 = Vac�o, 1 = Eliminado, 2 = Ocupado
			if (h[resultadoHash].estado == 0 || h[resultadoHash].estado == 1) {
				h[resultadoHash].vertice = vert;
				h[resultadoHash].estado = 2;
				isInsertado = true;
			} else {
				resultadoHash++;
			}
		} while (resultadoHash < tamanno && !isInsertado);
		return isInsertado ? "�V�rtice insertado con �xito!" : "�Tabla hash llena!";
	}
	
	public String insertaHash(Vertice vert) {
		return this._insertaHash(vert);
	}

	private Vertice _buscaHash(String label) {
		int resultadoHash = funcionHash(label);
		while (resultadoHash < tamanno) {
			if (h[resultadoHash].estado == 0) {
				return null;
			} else if (h[resultadoHash].vertice.getLabel().equals(label)) {
				if (h[resultadoHash].estado == 1) {
					return null;
				} else {
					return h[resultadoHash].vertice;
				}
			} else {
				resultadoHash++;
			}
		}
		return null;
	}
	
	public Vertice buscaHash(String label) {
		return this._buscaHash(label);
	}

	private boolean _eliminaHash(String label) {
		Vertice i = buscaHash(label);
		if (i == null) {
			return false;
		} else {
			int resultadoHash = funcionHash(label);
			h[resultadoHash].estado = 1;
			return true;
		}
	}
	
	public boolean eliminaHash(String label) {
		return this._eliminaHash(label);
	}

	private List<Vertice> _retornarLista() {
		List<Vertice> lista = new ArrayList<>();
		for (int i = 0; i < tamanno; i++) {
			if (h[i].estado == 2) {
				lista.add(h[i].vertice);
			}
		}
		return lista;

	}
	
	public List<Vertice> retornarLista() {
		return this._retornarLista();
		
	}
}