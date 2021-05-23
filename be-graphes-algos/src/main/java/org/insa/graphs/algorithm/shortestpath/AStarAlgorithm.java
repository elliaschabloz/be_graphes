package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.AbstractInputData.Mode;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.LabelStar;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    
    public void Init(LabelStar[] tab_lab, Graph graph, ShortestPathData data) {
    	if (data.getMode()== Mode.LENGTH) {
	    	for(int i=0 ; i<graph.size() ; i++) {
	    		tab_lab[i] = new LabelStar(graph.get(i), data.getDestination(), false, Double.POSITIVE_INFINITY, -1);
	    	}
    	}
    }
}
