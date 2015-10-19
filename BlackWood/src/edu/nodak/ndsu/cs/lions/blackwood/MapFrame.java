package edu.nodak.ndsu.cs.lions.blackwood;

import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapKit.DefaultProviders;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.DefaultTileFactory;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.TileFactoryInfo;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointPainter;
import org.jdesktop.swingx.mapviewer.WaypointRenderer;

import com.borland.jbcl.layout.XYConstraints;
import com.borland.jbcl.layout.XYLayout;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.event.MouseInputListener;

/**
 * Map with waypoints gathered from moo and tiles from moo web server
 * 
 * @author C. Roemmich
 * @year 2011
 *
 * ADD/UPDATE WAYPOINT:
 *   ;$g.map:add_waypoint(-38.410558250946075, 31.11328125, -43.06888777416961, 36.73828125, #2139)
 *   Added waypoint for Riverside DryGoods Store (#2139)
 *   => 0
 *
 * REMOVE WAYPOINT:
 *   ;$g.map:remove_waypoint(#2139)
 *   Removed waypoint for Riverside DryGoods Store (#2139)
 *   => 0
 *   
 * UPDATE TILES: 
 *   Map MUST be square
 *   Use ImageTileCutter stored on the web server with the tiles
 *   Over-write the title on the web server
 */

public class MapFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private XYLayout xYLayout = new XYLayout();
	private JToggleButton jToggleButtonClose = new JToggleButton();

	private boolean debug = false;

	private MooConnection moo;

	private JXMapKit mapKit = new JXMapKit();

	private HashMap<GeoPosition, Box> mapWaypoints = new HashMap<GeoPosition, Box>();
	private Set<MapWaypoint> waypoints = new LinkedHashSet<MapWaypoint>();
	
	private int playerRoom;
	private int playerCloseRoom;

	public MapFrame(MooConnection moo) {
		super("Map of Blackwood");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/Applet/blackwood_icon.gif"));
		this.moo = moo;
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		this.setLayout(xYLayout);
		this.setVisible(true);
		// this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(800, 600));

		mapKit.setMiniMapZoom(3);
		// Set up the map
		TileFactoryInfo info = new TileFactoryInfo(0, // min level
				3, // max allowed level
				3, // max level
				256, // tile size
				true, true, // x/y orientation is normal
				"http://roemmich.cc.ndsu.nodak.edu/blackwood/map", // base url
				"x", "y", "z" // url args for x, y & z
		) {
			public String getTileUrl(int x, int y, int zoom) {
				return this.baseURL + "/" + zoom + "/blackwood." + zoom + "-" + x + "-" + y + ".jpg";
			}
		};
		mapKit.setTileFactory(new DefaultTileFactory(info));
		mapKit.addHierarchyBoundsListener(new ResizeListener());
		mapKit.setDefaultProvider(DefaultProviders.Custom);
		mapKit.setPreferredSize(this.getSize());
		mapKit.getMainMap().setZoomEnabled(false);
		mapKit.setZoomButtonsVisible(false);
		mapKit.setZoomSliderVisible(false);
		mapKit.setMiniMapVisible(false);
		mapKit.getMainMap().setHorizontalWrapped(false);
		moo.addListener(new WaypointMooListener());
		moo.send("get_waypoints $g.map");

		// Keep the waypoints in the right spot
		mapKit.getMainMap().addMouseMotionListener(new WaypointMouseMotionListener());
		if (debug)
			mapKit.getMainMap().addMouseListener(new DebugMouseListener());
		mapKit.getMainMap().addKeyListener(new WaypointKeyListener());

		// Go to 0,0 by default
		mapKit.setAddressLocation(new GeoPosition(0, 0));

		// Remove the default marker at 0,0
		mapKit.getMainMap().setOverlayPainter(null);
		mapKit.getMainMap().repaint();

		// Add the map to the frame
		this.add(mapKit);

		// Close button
		jToggleButtonClose.setText("Done");
		jToggleButtonClose.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jToggleButtonClose_actionPerformed(e);
			}
		});
		this.add(jToggleButtonClose, new XYConstraints(0, 0, 0, 0));
	}

	private void jToggleButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

	/**
	 * Updates the waypoints and player position on the map
	 */
	public void drawOverlay() {
		// Remove current waypoints
		Iterator<Box> boxitr = mapWaypoints.values().iterator();
		while (boxitr.hasNext()) {
			mapKit.getMainMap().remove(boxitr.next());
		}
		
		Set<Waypoint> playerpoints = new HashSet<Waypoint>();

		// Paint the waypoints
		Iterator<MapWaypoint> itr = waypoints.iterator();
		while (itr.hasNext()) {
			MapWaypoint wp = itr.next();
			
			if (wp.getRoom() == playerRoom || wp.getRoom() == playerCloseRoom) {
				double centerLatitude = (wp.getTopLeftPosition().getLatitude() + wp.getBottomRightPosition().getLatitude()) / 2;
				double centerLongitude = (wp.getTopLeftPosition().getLongitude() + wp.getBottomRightPosition().getLongitude()) / 2;
				GeoPosition center = new GeoPosition(centerLatitude, centerLongitude);
				
				playerpoints.add(new Waypoint(center));
				mapKit.getMainMap().setCenterPosition(center);
			}
			
			final Box marker = new Box(BoxLayout.X_AXIS);
			marker.addMouseListener(new WaypointClickListener(this, wp));
			marker.addMouseMotionListener(new WaypointMouseDragListener());
			marker.setFocusable(false);
			if (debug)
				marker.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
			marker.setCursor(new Cursor(Cursor.HAND_CURSOR));
			marker.setToolTipText(((MapWaypoint) wp).getName());
			Point2D tl = mapKit.getMainMap().convertGeoPositionToPoint(wp.getTopLeftPosition());
			Point2D br = mapKit.getMainMap().convertGeoPositionToPoint(wp.getBottomRightPosition());
			Rectangle rect = mapKit.getMainMap().getViewportBounds();
			Point converted_tl = new Point((int) tl.getX() - rect.x, (int) tl.getY() - rect.y);
			Point converted_br = new Point((int) br.getX() - rect.x, (int) br.getY() - rect.y);
			int width = Math.round((long) Math.abs(converted_tl.getX() - converted_br.getX()));
			int height = Math.round((long) Math.abs(converted_tl.getY() - converted_br.getY()));
			Dimension dimension = new Dimension(width, height);
			marker.setMinimumSize(dimension);
			marker.setPreferredSize(dimension);
			marker.setSize(dimension);
			marker.setVisible(true);
			mapWaypoints.put(wp.getTopLeftPosition(), marker);
			mapKit.getMainMap().add(mapWaypoints.get(wp.getTopLeftPosition()));
		}
		
		if (playerpoints.size() == 0) {
			moo.send("get_nearest_room $g.map");
		} else {
		    @SuppressWarnings("rawtypes")
			WaypointPainter<?> painter = new WaypointPainter();
		    painter.setWaypoints(playerpoints);
		    painter.setRenderer(new WaypointRenderer() {
		        public boolean paintWaypoint(Graphics2D g, JXMapViewer map, Waypoint wp) {
		        	String title = "General Location";
		        	if (playerRoom == playerCloseRoom) {
		        		title = "Your Location";
		        	}
		        	//draw tab
		            g.setPaint(new Color(0,0,255,200));
		            Polygon triangle = new Polygon();
		            triangle.addPoint(0,0);
		            triangle.addPoint(11,11);
		            triangle.addPoint(-11,11);
		            g.fill(triangle);
		            int width = (int) g.getFontMetrics().getStringBounds(title, g).getWidth();
		            g.fillRoundRect(-width/2 -5, 10, width+10, 20, 10, 10);
		            
		            //draw text w/ shadow
		            g.setPaint(Color.BLACK);
		            g.drawString(title, -width/2-1, 26-1); //shadow
		            g.drawString(title, -width/2-1, 26-1); //shadow
		            g.setPaint(Color.WHITE);
		            g.drawString(title, -width/2, 26); //text
		            return false;
		        }
		    });
		    painter.setWaypoints(playerpoints);
		    mapKit.getMainMap().setOverlayPainter(painter);
		}
		updateMarkers();
	}

	/**
	 * Update the map and markers when the map is resized
	 */
	private class ResizeListener implements HierarchyBoundsListener {
		@Override
		public void ancestorResized(HierarchyEvent e) {
			mapKit.setPreferredSize(e.getChanged().getSize());
			updateMarkers();
		}

		@Override
		public void ancestorMoved(HierarchyEvent e) {
		}
	}

	/**
	 * Handles click actions on waypoint markers
	 */
	private class WaypointClickListener extends MouseAdapter {
		private JFrame frame;
		private MapWaypoint wp;

		public WaypointClickListener(JFrame frame, MapWaypoint wp) {
			this.frame = frame;
			this.wp = wp;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			moo.send("@move me to #" + wp.getRoom());
			frame.setVisible(false);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			for (MouseListener l : mapKit.getMainMap().getMouseListeners()) {
				MouseEvent me = new MouseEvent(mapKit.getMainMap(), e.getID(), e.getWhen(), e.getModifiers(),
						e.getXOnScreen(), e.getYOnScreen(), e.getClickCount(), false);
				l.mousePressed(me);
			}
		}
	}

	/**
	 * Pass motion event to map to pan even when on a waypoint box
	 */
	private class WaypointMouseDragListener extends MouseMotionAdapter {
		@Override
		public void mouseDragged(MouseEvent e) {
			for (MouseListener l : mapKit.getMainMap().getMouseListeners()) {
				if (l instanceof MouseInputListener) {
					MouseEvent me = new MouseEvent(mapKit.getMainMap(), e.getID(), e.getWhen(), e.getModifiers(),
							e.getXOnScreen(), e.getYOnScreen(), e.getClickCount(), false);
					((MouseMotionListener) l).mouseDragged(me);
				}
			}
			updateMarkers();
		}
	}

	/**
	 * Update the position of the map markers when the map is panned via mouse
	 */
	private class WaypointMouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseMoved(MouseEvent e) {
			updateMarkers();
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			updateMarkers();
		}
	}
	
	/**
	 * Displays coordinates that were clicks on for debugging and quick waypoint adding
	 */
	private class DebugMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			GeoPosition gp = mapKit.getMainMap().convertPointToGeoPosition(e.getPoint());
			System.err.println(gp);
		}
	}

	/**
	 * Update the position of the map markers when the map is panned via
	 * keyboard
	 */
	private class WaypointKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_UP:
			case KeyEvent.VK_DOWN:
				updateMarkers();
				break;
			}
		}
	}

	/**
	 * Updates the map markers
	 */
	private void updateMarkers() {
		Iterator<GeoPosition> itr = mapWaypoints.keySet().iterator();
		while (itr.hasNext()) {
			GeoPosition pos = itr.next();
			Box waypoint = mapWaypoints.get(pos);
			JXMapViewer map = mapKit.getMainMap();
			Point2D gp_pt = map.getTileFactory().geoToPixel(pos, map.getZoom());
			Rectangle rect = map.getViewportBounds();
			Point converted_gp_pt = new Point((int) gp_pt.getX() - rect.x, (int) gp_pt.getY() - rect.y);
			waypoint.setLocation(converted_gp_pt);
		}
	}

	/**
	 * MooListener for the map
	 */
	private class WaypointMooListener implements MooListener {
		@Override
		public void newLine(String line) {
			try {
				String lineArray[] = line.split(" ", 2);
				if (lineArray.length == 1) {
					if (lineArray[0].equalsIgnoreCase("#MAP_CLEARWAYPOINTS")) {
						waypoints = new LinkedHashSet<MapWaypoint>();
						drawOverlay();
					} else if (lineArray[0].equalsIgnoreCase("#MAP_DRAWWAYPOINTS")) {
						drawOverlay();
					} else {
						String a[] = lineArray[0].split("\\|");
						if (a.length > 1) {
							if (a[0].equalsIgnoreCase("#END_ROOM")) {
								playerRoom = Integer.parseInt(a[1]);
								moo.send("get_nearest_room $g.map");
							}
						}
					}
				} else if (lineArray.length == 2) {
					if (lineArray[0].equalsIgnoreCase("#MAP_WAYPOINT")) {
						String a[] = lineArray[1].split("\\|");
						if (a.length == 6) {
							waypoints.add(new MapWaypoint(d(a[0]), d(a[1]), d(a[2]), d(a[3]), i(a[4].replaceFirst("#","")), a[5]));
						}
					} else if (lineArray[0].equalsIgnoreCase("#PLAYER_LOCATION")) {
						playerRoom = Integer.parseInt(lineArray[1].replaceFirst("#", ""));
						playerCloseRoom = Integer.parseInt(lineArray[1].replaceFirst("#", ""));
					} else if (lineArray[0].equalsIgnoreCase("#MAP_NEAREST_ROOM")) {
						playerCloseRoom = Integer.parseInt(lineArray[1].replaceFirst("#", ""));
						drawOverlay();
					}
				}
			} catch (Exception e) {/* This listener is not that important */
			}
		}

		/**
		 * Convert String to Integer
		 */
		private int i(String str) {
			return Integer.parseInt(str);
		}

		/**
		 * Convert String to Double
		 */
		private double d(String str) {
			return Double.parseDouble(str);
		}
	}
}