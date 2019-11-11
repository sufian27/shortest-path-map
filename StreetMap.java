
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
/**
 * @author Sufian Mushtaq
 * NetID: smushtaq
 * Email: smushtaq@u.rochester.edu
 */
public class StreetMap extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6136394851913623525L;

	static boolean found = false;
	static double maxLat = 0;
	static double maxLong = 0;
	static double minLat = 400;
	static double minLong = 400;
	static List<Node> pathList = new ArrayList<Node>(); //list that stores the path
	public StreetMap() {
		setSize(1000, 750);
		setTitle("Sufian Maps");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new MapCanvas());
	}

	public static void main(String[] args) {
		Scanner scanner = null;
		String start = "";
		String end = "";
		File inputFile = new File(args[0]);
		try {
			scanner = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while(scanner.hasNext()) { //loop to get input from the file
			String line = scanner.nextLine();
			String[] lineData = line.split("\t");
			if(lineData[0].equals("i")) { //stores all intersections 
				Graph.nodes.put(lineData[1], new Node(Double.parseDouble(lineData[3]), Double.parseDouble(lineData[2]),
						lineData[1]));
				if (Math.abs(Double.parseDouble(lineData[2])) > Math.abs(maxLat)) {
					maxLat = Double.parseDouble(lineData[2]);
				}
				if (Math.abs(Double.parseDouble(lineData[3])) > Math.abs(maxLong)) {
					maxLong = Double.parseDouble(lineData[3]);
				}
				if (Math.abs(Double.parseDouble(lineData[2])) < Math.abs(minLat)) {
					minLat = Double.parseDouble(lineData[2]);
				}
				if (Math.abs(Double.parseDouble(lineData[3])) < Math.abs(minLong)) {
					minLong = Double.parseDouble(lineData[3]);
				}
			} else if (lineData[0].equals("r")) {//stores all roads
				double weight = Edge.calculateWeight(Graph.nodes.get(lineData[2]).getLatitude(), 
						Graph.nodes.get(lineData[3]).getLatitude(), Graph.nodes.get(lineData[2]).getLongitude(),
						Graph.nodes.get(lineData[3]).getLongitude());
				Graph.nodes.get(lineData[2]).getNeighborIds().add(new Edge(lineData[3], weight));
				Graph.nodes.get(lineData[3]).getNeighborIds().add(new Edge(lineData[2], weight));
			}
		}

		if(args[1].equals("--show")) { //conditions to handle the command prompt input
			try {
				if (args[2].equals("--directions")) {
					start = args[3];
					end = args[4];
					Graph.dijkstra(Graph.nodes.get(start), Graph.nodes.get(end));
					try {
						found = true;
						getPath(Graph.nodes.get(start), Graph.nodes.get(end));
					} catch (NullPointerException ex) {
						found = false;
						System.out.println("The nodes are not connected");
					}
				}
			} catch (ArrayIndexOutOfBoundsException ex) {

			}
			new StreetMap().setVisible(true);
		} else {
			try {
				if (args[2].equals("--show")) {
					start = args[3];
					end = args[4];
					new StreetMap().setVisible(true);
				} else {
					start = args[2];
					end = args[3];
				}
			} catch (ArrayIndexOutOfBoundsException ex) {

			}
			Graph.dijkstra(Graph.nodes.get(start), Graph.nodes.get(end));
			try {
				found = true;
				getPath(Graph.nodes.get(start), Graph.nodes.get(end));
			} catch (NullPointerException ex) {
				found = false;
				System.out.println("The nodes are not connected");
			}
		}
		if (found == true) { //if a path is found, then the path is printed
			System.out.println("PATH: ");
			for (int i = pathList.size() - 1; i >= 0; i--) {
				System.out.println(pathList.get(i).getId());
			}
		}
	}

	/**
	 * Creates the shortest path between start and end node and stores it in a pathList
	 * Worst case time complexity: O(n)
	 * 
	 * @param start	the node to start from
	 * @param end	the node to end at
	 * @throws NullPointerException
	 */
	public static void getPath(Node start, Node end) throws NullPointerException {
		pathList.add(end);
		while(!pathList.contains(start)) {
			pathList.add(pathList.get(pathList.size()-1).getParent());
		}
	}

}
