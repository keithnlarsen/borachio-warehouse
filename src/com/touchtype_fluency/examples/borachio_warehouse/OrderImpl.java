package com.touchtype_fluency.examples.borachio_warehouse;

import com.google.inject.Inject;

public class OrderImpl implements Order {
    
    // Dependencies injected by Guice
    private Warehouse mWarehouse;
    
    private String mProduct;
    private int mQuantity;
    
    @Inject
    public OrderImpl(Warehouse warehouse) {
        mWarehouse = warehouse;
    }
    
    public void update(String product, int quantity) {
        mProduct = product;
        mQuantity = quantity;
    }
    
    public boolean fill() {
        if(mWarehouse.hasInventory(mProduct, mQuantity)) {
            mWarehouse.remove(mProduct, mQuantity);
            return true;
        }
        
        return false;
    }
}
