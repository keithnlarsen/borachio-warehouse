package com.touchtype_fluency.examples.borachio_warehouse;

import roboguice.config.AbstractAndroidModule;

public class BorachioWarehouseModule extends AbstractAndroidModule {
    public BorachioWarehouseModule() {
        super();
    }
    
    protected void configure() {
        bind(IWarehouse.class).to(Warehouse.class);
        bind(IOrder.class).to(Order.class);
    }
}
