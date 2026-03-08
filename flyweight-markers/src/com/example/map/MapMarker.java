package com.example.map;

public class MapMarker {

    private final MarkerStyle style;
    private final double lat;
    private final double lng;
    private final String label;

    public MapMarker(MarkerStyle style, double lat, double lng, String label) {
        this.style = style;
        this.lat = lat;
        this.lng = lng;
        this.label = label;
    }

    public void render() {
        System.out.println("Rendering Marker: " +
                label + " at (" + lat + "," + lng + ") Style: " + style);
    }

    public MarkerStyle getStyle() {
        return style;
    }
}