package com.touchtype_fluency.examples.borachio_warehouse;

import android.util.Pair;

import java.util.ArrayList;

public interface IWarehouse {
    void add(String name, int quantity);
    void remove(String name, int quantity);
    
    boolean hasInventory(String name, int quantity);
    ArrayList<Pair<String, Integer>> getInventory();
}
