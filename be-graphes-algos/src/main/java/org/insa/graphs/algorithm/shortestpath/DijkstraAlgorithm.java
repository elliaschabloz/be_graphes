package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Label;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.utils.ElementNotFoundException;
import org.insa.graphs.algorithm.utils.EmptyPriorityQueueException;

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
        List<Arc> shortest_path = new ArrayList<Arc>();
        BinaryHeap<Label> heap = new BinaryHeap<Label>();
        
        
        Label[] tab_label = new Label[nbNodes];
        for(Node g_node : graph.getNodes()) {
        	Label L = new Label(g_node.getId(), false, Double.POSITIVE_INFINITY, null);
        	tab_label[g_node.getId()] = L;
        }
        tab_label[origin.getId()].setCost(0);
        heap.insert(tab_label[origin.getId()]);
        
        //ArrayList<boolean> mark = new ArrayList<boolean>(nbNodes); 
        /*boolean[] mark = new boolean[nbNodes];
        Arrays.fill(mark, false);*/
        List<Boolean> mark = new ArrayList<Boolean>(Arrays.asList(new Boolean[nbNodes]));
        Collections.fill(mark, Boolean.FALSE);
       
        /*
        Double[] cost = new Double[nbNodes];
        Arrays.fill(cost, Double.POSITIVE_INFINITY);
        
        Arc[] fatherArcs = new Arc[nbNodes];
        Arrays.fill(fatherArcs, null);
        */
        
        Label min;

        // tant qu'il existe des sommets non marqués
        while(mark.contains(Boolean.FALSE)) {
        	
        	try {
        		min = heap.deleteMin();
        	} catch(EmptyPriorityQueueException e) {
        		break;
        	}
        	//min = heap.findMin();
        	min.setMarque(true);
        	//mark[min.getSommet()] = true;
        	mark.set(min.getSommet(), true);
        	
        	//liste des arcs successeurs=y de min=x
        	List<Arc> successors = graph.getNodes().get(min.getSommet()).getSuccessors();
        	
        	// pour chaque y de x
        	for(Arc succ : successors) {
        		int id_succ = succ.getDestination().getId();
        		if(!(mark.get(id_succ))) {
        			double current_cost = (tab_label[id_succ]).getCost();
        			if( current_cost > min.getCost()+succ.getMinimumTravelTime()) {
        				
        				current_cost = min.getCost()+succ.getMinimumTravelTime();
        				tab_label[id_succ].setCost(current_cost);
        				tab_label[id_succ].setPere(succ);
        				
        				try { // on met a jour le label dans le tas
        					heap.remove(tab_label[id_succ]);
        					heap.insert(tab_label[id_succ]);
        				}catch(ElementNotFoundException e) { // on l'ajoute s'il n'y était pas
        					heap.insert(tab_label[id_succ]);
        				}
        			}	
        		}
        		
        	}
        }
        
        // The destination has been found, notify the observers.
        notifyDestinationReached(data.getDestination());
        
        // Initialize array of predecessors.
        Arc[] predecessorArcs = new Arc[nbNodes];
        
        // Create the path from the array of predecessors...
        ArrayList<Arc> arcs = new ArrayList<>();
        Arc arc = predecessorArcs[data.getDestination().getId()];
        while (arc != null) {
            arcs.add(arc);
            arc = predecessorArcs[arc.getOrigin().getId()];
        }
        // Reverse the path...
        Collections.reverse(shortest_path);

        // Create the final solution.
        solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, shortest_path));
        
        return solution;
    }

}
