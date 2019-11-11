import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JComponent;

/**
 * @author Sufian Mushtaq
 * NetID: smushtaq
 * Email: smushtaq@u.rochester.edu
 */
public class MapCanvas extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Paints the graphics 
	 * Time complexity: O(n)
	 */
	@Override
	public void paintComponent(Graphics g) {
		for (String key : Graph.nodes.keySet()) { //draws the map
			List<Edge> adjList = Graph.nodes.get(key).getNeighborIds();
			for (Edge e : adjList) {
				g.drawLine(Math.abs(getWidth() - scaleLong(Graph.nodes.get(key).getLongitude())),
						Math.abs(getHeight() - scaleLat(Graph.nodes.get(key).getLatitude())),
						Math.abs(getWidth() - scaleLong(Graph.nodes.get(e.endVertexId).getLongitude())),
						Math.abs(getHeight() - scaleLat(Graph.nodes.get(e.endVertexId).getLatitude())));
			}
		}
		
		if (StreetMap.found == true) { //if a path has been found, the path is drawn 
			DecimalFormat df = new DecimalFormat("#.###");
			g.setColor(Color.GREEN);
			g.fillOval(Math.abs(getWidth() - scaleLong(StreetMap.pathList.get(0).getLongitude())) - 5,
					Math.abs(getHeight() - scaleLat(StreetMap.pathList.get(0).getLatitude())) - 5, 10, 10); //makes green circle at destination
			g.setColor(Color.BLUE);
			g.setFont(new Font("Sans Serif", Font.BOLD, 15));
			g.drawString("Distance: " + df.format(StreetMap.pathList.get(0).getDistance()) + " mi", getWidth()/2 - 60, 20);
			g.setColor(Color.YELLOW);
			g.fillOval(Math.abs(getWidth() - scaleLong(StreetMap.pathList.get(StreetMap.pathList.size()-1).getLongitude()))-5,
					Math.abs(getHeight() - scaleLat(StreetMap.pathList.get(StreetMap.pathList.size()-1).getLatitude()))-5, 10, 10); //makes yellow circle at start
			Graphics2D g2 = (Graphics2D)g;
			for (int i = 0; i < StreetMap.pathList.size() - 1; i++) { 
				Node start = StreetMap.pathList.get(i);
				Node end = StreetMap.pathList.get(i+1);
				g2.setColor(Color.RED);
				g2.setStroke(new BasicStroke(3));
				int startX = Math.abs(getWidth() - scaleLong(start.getLongitude()));	
				int startY = Math.abs(getHeight() - scaleLat(start.getLatitude()));
				int endX = Math.abs(getWidth() - scaleLong(end.getLongitude()));	
				int endY = Math.abs(getHeight() - scaleLat(end.getLatitude()));	
				g2.drawLine(startX, startY, endX, endY);
			}
		}
	}
	
	/**
	 * Scales the longitude to the canvas
	 * 
	 * @param lon	the longitude to be scaled
	 * @return		the scaled longitude 
	 */
	public int scaleLong(double lon) {	
		return (int)((lon - StreetMap.minLong)/(StreetMap.maxLong - StreetMap.minLong) * 7*getWidth()/10) + 3*getWidth()/20;
	}
	
	/**
	 * Scales the latitude to the canvas
	 * 
	 * @param lat	the latitude to be scaled
	 * @return		the scaled latitude
	 */
	public int scaleLat(double lat) {
		return (int)((lat - StreetMap.minLat)/(StreetMap.maxLat - StreetMap.minLat) * 9*getHeight()/10) + 1*getHeight()/20;
	}
}
