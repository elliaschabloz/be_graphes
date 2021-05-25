package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.AbstractInputData.Mode;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Label;
import org.insa.graphs.model.LabelStar;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    @Override
    public void Init(Label[] tab_lab, Graph graph, ShortestPathData data) {
    	if (data.getMode()== Mode.LENGTH) {
	    	for(int i=0 ; i<graph.size() ; i++) {
	    		double minCost = graph.get(i).getPoint().distanceTo(data.getDestination().getPoint());
	    		tab_lab[i] = new LabelStar(graph.get(i), false, Double.POSITIVE_INFINITY, -1, minCost);
	    	}
    	} else {
    		double maxSpeed = data.getGraph().getGraphInformation().getMaximumSpeed()/3.6;
    		for(int i=0 ; i<graph.size() ; i++) {
    			// v=d/t    			
	    		double minCost = graph.get(i).getPoint().distanceTo(data.getDestination().getPoint());
	    		tab_lab[i] = new LabelStar(graph.get(i), false, Double.POSITIVE_INFINITY, -1, minCost/maxSpeed);
	    	}
    	}
    }
}
