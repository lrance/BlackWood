package edu.nodak.ndsu.cs.lions.blackwood;

import org.jdesktop.swingx.mapviewer.GeoPosition;

class MapWaypoint {
	private int room;
	private String name;
	/**
	 * 
	 */
	private GeoPosition topLeftPosition = new GeoPosition(0,0);

	private GeoPosition bottomRightPosition = new GeoPosition(1,-1);
	
	public MapWaypoint(double latitudeTL, double longitudeTL, double latitudeBR, double longitudeBR, int room, String name) {
		this(new GeoPosition(latitudeTL,longitudeTL), new GeoPosition(latitudeBR,longitudeBR), room, name);
	}
	
	public MapWaypoint(GeoPosition topLeft, GeoPosition bottomRight, int room, String name) {
		this.topLeftPosition = topLeft;
		this.bottomRightPosition = bottomRight;
		this.room = room;
		this.name = name;
	}
	
	public GeoPosition getTopLeftPosition() {
		return topLeftPosition;
	}

	public GeoPosition getBottomRightPosition() {
		return bottomRightPosition;
	}
	
	public int getRoom() {
		return room;
	}
	
	public String getName() {
		return name;
	}
}