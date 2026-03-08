package com.example.map;

import java.util.List;

public class App {

    public static void main(String[] args) {

        List<MapMarker> markers = MapDataSource.loadMarkers();

        for (MapMarker marker : markers) {
            marker.render();
        }

        QuickCheck.verify(markers);
    }
}