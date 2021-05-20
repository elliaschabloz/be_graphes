package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
//import java.util.Arrays;
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
import org.insa.graphs.algorithm.AbstractInputData;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        
        // TODO:
        Graph graph = data.getGraph();
        final int nbNodes = graph.size();
        
        /* Init */
        Node origin = data.getOrigin();
        //List<Arc> shortest_path = new ArrayList<Arc>();
        BinaryHeap<Label> heap = new BinaryHeap<Label>();
        
        
        Label[] tab_label = new Label[nbNodes];
        for(Node g_node : graph.getNodes()) {
        	Label L = new Label(g_node.getId(), false, Double.POSITIVE_INFINITY, -1);
        	tab_label[g_node.getId()] = L;
        }
        tab_label[origin.getId()].setCost(0);
        heap.insert(tab_label[origin.getId()]);
    
        
        // tant que la destination n'est pas marquée
        while( !tab_label[data.getDestination().getId()].getMarque() && !heap.isEmpty() ) {
      
        	Label min;
        	
        	try {
        		min = heap.deleteMin();
        	} catch(EmptyPriorityQueueException e) {
        		break;
        	}
        	
        	min.setMarque(true);
        	
        	//liste des arcs successeurs=y de min=x
        	List<Arc> successors = graph.getNodes().get(min.getSommet()).getSuccessors();
        	
        	// pour chaque y de x
        	for(Arc succ : successors) {
        		int id_succ = succ.getDestination().getId();
        		
        		if(!(tab_label[id_succ].getMarque()) ) {
        			
        			double current_cost = (tab_label[id_succ]).getCost();
        			double w = data.getCost(succ);
        			double new_cost = min.getCost() + w;
        			
        			if( current_cost > new_cost ) {
        				tab_label[id_succ].setCost(new_cost);
        				tab_label[id_succ].setPere(min.getSommet());
        				
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
        ShortestPathSolution solution = null;
        
     // Destination has no predecessor, the solution is infeasible...
        if (!tab_label[data.getDestination().getId()].getMarque()) {
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);
            
        }
        else {
        	
	        //The destination has been found, notify the observers.
	        notifyDestinationReached(data.getDestination());
	        
	        // Initialize array of fathers id
	        ArrayList<Node> nodes = new ArrayList<>();
	        Node pere = data.getDestination();
	        nodes.add(pere);
	        
	        while (!(pere.equals(origin)) ) {
	            pere = graph.getNodes().get(tab_label[pere.getId()].getFather());
	            nodes.add(pere);
	        }
	        // Reverse the path...
	        Collections.reverse(nodes);
	        
	        Path path_of_nodes;
	        
	        //selon le mode
	        if(data.getMode().equals(AbstractInputData.Mode.LENGTH)) {
	        	path_of_nodes = Path.createShortestPathFromNodes(graph, nodes);
	        }else {
	        	path_of_nodes = Path.createFastestPathFromNodes(graph, nodes);
	        }
	        
	        solution = new ShortestPathSolution(data, Status.OPTIMAL, path_of_nodes);
        }
        return solution;
    }

}
