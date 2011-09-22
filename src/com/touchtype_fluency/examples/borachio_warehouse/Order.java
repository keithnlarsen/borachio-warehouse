package com.touchtype_fluency.examples.borachio_warehouse;

import com.google.inject.Inject;

public class Order implements IOrder {
    
    // Dependencies injected by Guice
    private IWarehouse _warehouse;
    
    private String _product;
    private int _quantity;
    
    @Inject
    public Order(IWarehouse warehouse) {
        _warehouse = warehouse;
    }
    
    public void update(String product, int quantity) {
        _product = product;
        _quantity = quantity;
    }
    
    public boolean fill() {
        if(_warehouse.hasInventory(_product, _quantity)) {
            _warehouse.remove(_product, _quantity);
            return true;
        }
        
        return false;
    }
}
