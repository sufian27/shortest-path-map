import java.util.LinkedList;
import java.util.List;
/**
 * @author Sufian Mushtaq
 * NetID: smushtaq
 * Email: smushtaq@u.rochester.edu
 */
public class Node implements Comparable {
	private double longitude;
	private double latitude;
	private String id;
	private double distance = Double.MAX_VALUE;
	public boolean visited = false;
	private Node parent = null;
	private List<Edge> neighborIds = new LinkedList<Edge>();
	public Node(double longitude, double latitude, String id) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.id = id;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Point{longitude="+longitude+", latitude=" + latitude +", id=" + id + ", neighborIds="+ neighborIds + ", distance="
				+ distance + ", visited=" + visited + ", parent=" + parent+ "}"; 
	}
	public List<Edge> getNeighborIds() {
		return neighborIds;
	}
	public void setNeighborIds(List<Edge> neighborId) {
		this.neighborIds = neighborId;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	@Override
	public int compareTo(Object node) {
		if (distance < ((Node)node).distance) 
            return -1; 
        if (distance > ((Node)node).distance) 
            return 1; 
        return 0; 
	}
}
