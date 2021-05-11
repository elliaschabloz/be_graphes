package org.insa.graphs.algorithm.shortestpath;

import java.util.Arrays;
import java.util.List;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Label;
import org.insa.graphs.model.Node;
import org.insa.graphs.algorithm.utils.BinaryHeap;;

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
        
        /* Init */
        Node origin = data.getOrigin();
        BinaryHeap<Label> heap = new BinaryHeap<Label>();
        
        
        Label[] tab_label = new Label[nbNodes];
        for(Node g_node : graph.getNodes()) {
        	Label L = new Label(g_node.getId(), false, Double.POSITIVE_INFINITY, null);
        	tab_label[g_node.getId()] = L;
        }
        
        boolean[] mark = new boolean[nbNodes];
        Arrays.fill(mark, false);
       
        /*
        Double[] cost = new Double[nbNodes];
        Arrays.fill(cost, Double.POSITIVE_INFINITY);
        
        Arc[] fatherArcs = new Arc[nbNodes];
        Arrays.fill(fatherArcs, null);
        */
        
        Label min;
        Label courant = new Label(origin.getId(), false, 0, null);

        heap.insert(courant);
        
        while(Arrays.asList(mark).contains(false)) {
        	min = heap.findMin();
        	min.setMarque(true);
        	mark[min.getSommet()] = true;
        	
        	List<Arc> successors = graph.getNodes().get(min.getSommet()).getSuccessors();
        	
        	for(Arc succ : successors) {
        		int id_succ = succ.getDestination().getId();
        		if(!(mark[id_succ])) {
        			double current_cost = (tab_label[id_succ]).getCost();
        			if( current_cost > min.getCost()+succ.getMinimumTravelTime()) {
        				current_cost = min.getCost()+succ.getMinimumTravelTime();
        				tab_label[id_succ].setCost(current_cost);
        				tab_label[id_succ].setPere(succ);	
        			}	
        		}
        	}
        	heap.remove(min);
        }
        
        
        return solution;
    }

}
