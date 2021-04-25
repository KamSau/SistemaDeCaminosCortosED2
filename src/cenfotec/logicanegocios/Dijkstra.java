package cenfotec.logicanegocios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import cenfotec.logicanegocios.modelos.Arco;
import cenfotec.logicanegocios.modelos.Vertice;

public class Dijkstra {

    private void _calcularCaminoMasCorto(Vertice verticeOrigen) {
        verticeOrigen.setDistancia(0);
        PriorityQueue<Vertice> colaPrioridad = new PriorityQueue<>();
        colaPrioridad.add(verticeOrigen);
        verticeOrigen.setVisitado(true);

        while (!colaPrioridad.isEmpty()) {
            Vertice verticeActual = colaPrioridad.poll();

            for (Arco arco : verticeActual.getArcos()) {
                Vertice auxiliar = arco.getDestino();

                if (!auxiliar.isVisitado()) {
                    double nuevaDistancia = verticeActual.getDistancia() + arco.getDistancia();

                    if (nuevaDistancia < auxiliar.getDistancia()) {
                        colaPrioridad.remove(auxiliar);
                        auxiliar.setDistancia(nuevaDistancia);
                        auxiliar.setPredecesor(verticeActual);
                        colaPrioridad.add(auxiliar);
                    }
                }
            }
            verticeActual.setVisitado(true);
        }
    }

    public void calcularCaminoMasCorto(Vertice verticeOrigen) {
        this._calcularCaminoMasCorto(verticeOrigen);
    }


    private List<Vertice> _obtenerCaminoMasCorto(Vertice verticeDestino) {
        List<Vertice> camino = new ArrayList<>();
        for (Vertice vertice = verticeDestino; vertice != null; vertice = vertice.getPredecesor()) {
            camino.add(vertice);
        }
        Collections.reverse(camino);
        return camino;
    }

    public List<Vertice> obtenerCaminoMasCorto(Vertice verticeDestino) {
        return this._obtenerCaminoMasCorto(verticeDestino);
    }
}
