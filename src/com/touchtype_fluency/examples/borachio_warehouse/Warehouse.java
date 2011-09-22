package com.touchtype_fluency.examples.borachio_warehouse;

import android.util.Pair;
import com.google.inject.Singleton;

import java.util.ArrayList;

@Singleton
public class Warehouse implements IWarehouse {

    ArrayList<Pair<String, Integer>> _data = new ArrayList<Pair<String, Integer>>();
    
    public Warehouse() {
        _data.add(new Pair<String, Integer>("Talisker", 10));
        _data.add(new Pair<String, Integer>("Laphroaig", 30));
    }
    
    public void add(String name, int quantity) {
        int stockNow = 0;
        for(int i = 0; i < _data.size(); ++i) {
            Pair<String, Integer> stockItem = _data.get(i);
            if(stockItem.first.equals(name)) {
                stockNow = stockItem.second;
                _data.remove(i);
                break;
            }
        }
        
        _data.add(new Pair<String,Integer>(name, stockNow + quantity));
    }

    public ArrayList<Pair<String, Integer>> getInventory() {
        return _data;
    }

    public boolean hasInventory(String name, int quantity) {
        for(Pair<String, Integer> stockItem : _data) {
            if(stockItem.first.equals(name) && stockItem.second >= quantity) {
                return true;
            }
        }
        
        return false;
    }

    public void remove(String name, int quantity) {
        for(int i = 0; i < _data.size(); ++i) {
            Pair<String, Integer> stockItem = _data.get(i);
            if(stockItem.first.equals(name)) {
                _data.remove(i);
                int qtyNow = stockItem.second - quantity;
                if (qtyNow > 0) {
                    _data.add(new Pair<String, Integer>(name, qtyNow));
                }
            }
        }
    }
}
