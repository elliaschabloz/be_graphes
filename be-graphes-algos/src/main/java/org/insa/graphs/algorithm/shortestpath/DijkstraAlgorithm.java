package org.insa.graphs.algorithm.shortestpath;

import java.util.Arrays;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        // TODO:
        Graph graph = data.getGraph();
        final int nbNodes = graph.size();
        for (int i = 0; i < nbNodes; ++i) {
        	
        }
        /* Init */
        boolean[] mark = new boolean[nbNodes];
        Arrays.fill(mark, false);
        
        Double[] cost = new Double[nbNodes];
        Arrays.fill(cost, Double.POSITIVE_INFINITY);
        
        Arc[] fatherArcs = new Arc[nbNodes];
        
        return solution;
    }

}
