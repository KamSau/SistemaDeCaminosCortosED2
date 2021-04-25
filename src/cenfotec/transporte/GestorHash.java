package cenfotec.transporte;

import java.util.List;

import cenfotec.logicanegocios.hashing.HashTable;
import cenfotec.logicanegocios.modelos.Vertice;

public class GestorHash {

	private HashTable hashTable;

	public GestorHash(int tamanno) {
		hashTable = new HashTable(tamanno);
	}
	
	public HashTable getHash() {
		return this.hashTable;
	}

	public int funcionHash(String n) {
		return hashTable.funcionHash(n);
	}

	public String insertaHash(Vertice vert) {
		return hashTable.insertaHash(vert);
	}

	public Vertice buscaHash(String label) {
		return hashTable.buscaHash(label);
	}

	public boolean eliminaHash(String label) {
		return hashTable.eliminaHash(label);
	}

	public List<Vertice> retornarLista() {
		return hashTable.retornarLista();
	}
}