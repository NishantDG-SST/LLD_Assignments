package com.example.map;

import java.util.ArrayList;
import java.util.List;

public class MapDataSource {

    public static List<MapMarker> loadMarkers() {

        List<MapMarker> markers = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {

            String shape = (i % 2 == 0) ? "PIN" : "CIRCLE";
            String color = (i % 3 == 0) ? "RED" : "BLUE";
            int size = 12;
            boolean filled = true;

            MarkerStyle style = MarkerStyleFactory.getStyle(shape, color, size, filled);

            markers.add(new MapMarker(
                    style,
                    12.9 + i * 0.001,
                    77.5 + i * 0.001,
                    "Marker-" + i
            ));
        }

        return markers;
    }
}