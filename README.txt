This project has been divided into 5 classes. The following describes all the important variables and methods of every class.

1) Edge: Represents the following attributes and methods of an edge in the graph: 
	- endVertexID: the ID of the ending vertex of the edge. 
	- weight: the distance of the edge.
	- calculateWeight(): Uses the haversine formula to calculate 		the distance between two nodes in the graph. Runs on O(1). 

2) Node implements Comparable: Represents an intersection as a node of a graph using the following attributes and methods: 
	- longitude and latitude
	- id: a unique ID to identify a node. This is used to put the 		nodes in a hashmap. 
	- distance: the distance to the node from the starting edge. 		Initially set to the maximum double value, this is calculated 		using dijkstra's algorithm in the Graph class. 
	- parent: the parent is the node that has relaxed this node in 		dijkstra's algorithm. 
	- neighborIds: adjacency list of type Edge. 
3) Graph: Contains the static graph stored as a HashMap, which can be used to look up a node using its ID.
	- nodes: the HashMap that stores all the nodes. 
	- dijkstra(): implements dijkstra's algorithm. 
4) MapCanvas extends JComponent: This class uses JComponent methods to draw the map, the path, and print the distance on the JFrame in the StreetMap class. The path is printed in a Bold Red line. The source is marked with a yellow circle and the destination is marked with a green destination. The distance is printed on the top center. T
	- paintComponent(): paints the graphics on the JFrame. Runtime: 	O(n).
	- scaleLong(), scaleLat(): methods used for scaling 	cooridinates according to the window. 
5) StreetMap extends JFrame: Contains the main method of the project, creates the JFrame, and gets the user input from the command prompt and handles exceptions and all conditions. This class also prints all the intersections from point A to point B. 
	- pathList: arraylist that contains the nodes that create our 		path between two nodes.
	- getPath(): creates the path between the starting and ending 		nodes. If the method throws a NullPointerException, that means 		that the path does not exist between the points. Runtime: O(n).

ESTIMATED TIMES:
- ur.txt takes less than a second to load any route or just show the map. 
- monroe.txt takes < 3 seconds to load any route or just to show th map.
- nys.txt takes around 6 seconds to load any route or just show the map. 

PROBLEMS I FACED:
1) Scaling the map according to the canvas: I used the minimum and maximum latitude and longitude to scale the coordinates to the map.
2) Dijkstra's Algorithm: I had to alter the algorithm for the NYS map to avoid the OutOfMemoryError. I only store the first vertex and then add the edges from the adjacency list to the PriorityQueue. Also, I break the algorithm when the end vertex is found.

DIRECTIONS FOR COMPILATION:

Run the command: java StreetMap nameOfFile.txt [ --show ] [--directions startIntersection
endIntersection ]

"--show" will display the map and is an optional parameter. "--directions" will calculate the path between startIntersection and endIntersection and is also option. The startIntersection and endIntersection can be seen from the datasets provided along with the project. The nameOfFile can be "ur", "nys" or "monroe".  

Sample Command: java StreetMap ur.txt --show --directions HOYT MOREY
This command will display the path between Hoyt Auditorium and Morey hall on the University of Rochester Campus. 

DATASETS:

Each line consists of 4 pieces of data, as defined below:

- Intersections start with “i”, followed by a unique string ID, and decimal representations of latitude and longitude.

i IntersectionID Latitude Longitude

- Roads start with “r”, followed by a unique string ID, and the IDs of the two intersections it connects.

r RoadID Intersection1ID Intersection2ID

- "nys.txt" contains an encoding of the map of New York State.
- "monroe.txt" contains an encoding of the map of Monroe County.
- "ur.txt" contains an encoding of the map of University of Rochester.
