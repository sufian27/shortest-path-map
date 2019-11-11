import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
/**
 * @author Sufian Mushtaq
 * NetID: smushtaq
 * Email: smushtaq@u.rochester.edu
 */
public class Graph {
	//hashmap that stores all vertices which can be looked up from their id
	public static Map<String, Node> nodes = new HashMap<String, Node>(); 
	
	/**
	 * Algorithm that finds the shortest path to all nodes from a single source
	 * Worst case time complexity: O(nlogn)
	 * 
	 * @param start	the intersection to start from
	 * @param end	the intersection to end at 
	 */
	static void dijkstra(Node start, Node end) {
		PriorityQueue<Node> nextNode = new PriorityQueue<Node>();
		start.setDistance(0);
		nextNode.add(start);
		while(nextNode.isEmpty() == false){ //while queue is not empty
			Node current = nextNode.poll(); //get the minimum node
			current.visited = true;	//mark it as visited
			List<Edge> neighbors = current.getNeighborIds(); //list of neighbors of the current vertex
			for (Edge e : neighbors) { 
				if (nodes.get(e.endVertexId).visited == false) {
					if (nodes.get(e.endVertexId).getDistance() > //this condition relaxes the vertices
					current.getDistance() + e.weight) {
						nodes.get(e.endVertexId).setDistance(
								current.getDistance() +	e.weight);
						nodes.get(e.endVertexId).setParent(current);
						nextNode.add(nodes.get(e.endVertexId));
						nodes.get(e.endVertexId).visited = true;
					}
				}
			}
			if(end.visited == true) { //if end has been visited, break from the loop
				StreetMap.found = true;
				System.out.println("Found");
				break;
			}
		}
	}
}
