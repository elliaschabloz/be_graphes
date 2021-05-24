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
    
    public void Init(Label[] tab_lab, Graph graph, ShortestPathData data) {
    	for(int i=0 ; i<graph.size() ; i++) {
    		tab_lab[i] = new Label(graph.get(i), false, Double.POSITIVE_INFINITY, -1);
    	}
    }
    
    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        
        // TODO:
        Graph graph = data.getGraph();
        final int nbNodes = graph.size();
        
        /* Init */
        Node origin = data.getOrigin();
        BinaryHeap<Label> heap = new BinaryHeap<Label>();
        
        
        Label[] tab_label = new Label[nbNodes];
        Init(tab_label, graph, data);
        /*
        for(Node g_node : graph.getNodes()) {
        	Label L = new Label(g_node, false, Double.POSITIVE_INFINITY, -1);
        	tab_label[g_node.getId()] = L;
        }
        */
        
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
        	//notifyNodeMarked(min.getNode());
        	
        	//liste des arcs successeurs=y de min=x
        	List<Arc> successors = min.getNode().getSuccessors();
        	
        	// pour chaque y de x
        	for(Arc succ : successors) {
        		
        		if(data.isAllowed(succ)) {
	        		int id_succ = succ.getDestination().getId();
	        		
	        		if(!(tab_label[id_succ].getMarque()) ) {
	        			
	        			double current_cost = (tab_label[id_succ]).getTotalCost();
	        			double w = data.getCost(succ) + tab_label[id_succ].getMinCost();
	        			double new_cost = min.getCost() + w;
	        			
	        			if ( Double.isFinite(new_cost) && Double.isInfinite(current_cost) ) {
	        				notifyNodeReached(succ.getDestination());
	        			}
	        			if( current_cost > new_cost ) {
	        				tab_label[id_succ].setCost(new_cost - tab_label[id_succ].getMinCost());
	        				tab_label[id_succ].setPere(min.getNode().getId());
	        				
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
	        	//System.out.print("id node = " + pere.getId() + "\n");
	            pere = graph.getNodes().get(tab_label[pere.getId()].getFather());
	            nodes.add(pere);
	        }
	        /* affichage pour debug
	        for(Node n : nodes) {
	        	System.out.print("node " + n.getId() + " ");
	        }
	        */
	        // Reverse the path...
	        Collections.reverse(nodes);
	        
	        /* affichage pour debug
	        for(Node n : nodes) {
	        	System.out.print("node " + n.getId() + " ");
	        }
	      	*/
	        
	        Path path_of_nodes;
	        
	        //selon le mode
	        if(data.getMode().equals(AbstractInputData.Mode.LENGTH)) {
	        	path_of_nodes = Path.createShortestPathFromNodes(graph, nodes);
	        }else {
	        	path_of_nodes = Path.createFastestPathFromNodes(graph, nodes);
	        }
	        
	        /* affichage pour debug
	        for(Arc morceau : path_of_nodes.getArcs()) {
	        	System.out.println("from " + morceau.getOrigin().getId() +
	        			"to " + morceau.getDestination().getId() + "\n");
	        }
	        */ 
	        
	        solution = new ShortestPathSolution(data, Status.OPTIMAL, path_of_nodes);
	        
        }
        return solution;
    }

}
