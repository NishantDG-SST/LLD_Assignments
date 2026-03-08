package com.example.map;

import java.util.List;

public class QuickCheck {

    public static void verify(List<MapMarker> markers) {

        System.out.println("Total markers: " + markers.size());
        System.out.println("Unique styles: " + MarkerStyleFactory.getUniqueStyleCount());
    }
}