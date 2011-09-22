package com.touchtype_fluency.examples.borachio_warehouse;

public interface IOrder {
    void update(String product, int quantity);
    boolean fill();
}
