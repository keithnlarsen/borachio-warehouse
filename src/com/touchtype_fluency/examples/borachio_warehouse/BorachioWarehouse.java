package com.touchtype_fluency.examples.borachio_warehouse;

import roboguice.activity.RoboTabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.inject.Inject;

public class BorachioWarehouse extends RoboTabActivity {

    // Dependencies injected by Guice.
    @Inject
    IWarehouse _warehouse;
    
    private BaseAdapter _listAdapter;
    private ArrayAdapter<String> _spinnerAdapter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        TabHost tabHost = getTabHost();
        LayoutInflater.from(this).inflate(R.layout.main, tabHost.getTabContentView(), true);

        tabHost.addTab(tabHost.newTabSpec("inventory")
                .setIndicator("Inventory")
                .setContent(R.id.listpage));
 
        tabHost.addTab(tabHost.newTabSpec("order")
                .setIndicator("Order")
                .setContent(R.id.orderpage));
         
        _listAdapter = new BorachioWarehouseAdapter(this);
        ListView list = (ListView) findViewById(R.id.inventory);
        list.setAdapter(_listAdapter);
        
        _spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item) {
            
            public String getItem(int position) {
                return _warehouse.getInventory().get(position).first;
            }
            
            public long getItemId(int position) {
                return position;
            }
            
            public int getCount() {
                return _warehouse.getInventory().size();
            }
        };
        _spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.orderspinner);
        spinner.setAdapter(_spinnerAdapter);
    }
    
    public void onClick(View v) {
        String name;
        String qty;
        
        switch(v.getId()) {
            case R.id.addbutton:
                name = ((EditText) findViewById(R.id.name_field)).getText().toString();
                qty = ((EditText) findViewById(R.id.qty_field)).getText().toString();
                _warehouse.add(name, Integer.parseInt(qty));
                _listAdapter.notifyDataSetChanged();
                break;
                
            case R.id.orderbutton:
                name = (String) ((Spinner) findViewById(R.id.orderspinner)).getSelectedItem();
                qty = ((EditText) findViewById(R.id.order_qty)).getText().toString();
                
                IOrder o = ((BorachioWarehouseApplication) getApplication()).getInjector().getInstance(IOrder.class);
                o.update(name, Integer.parseInt(qty));
                if(o.fill()) {
                    Toast placed = Toast.makeText(this, "Order placed!", Toast.LENGTH_SHORT);
                    placed.show();
                    
                } else {
                    Toast failed = Toast.makeText(this, "No stock available!", Toast.LENGTH_SHORT);
                    failed.show();
                }
                break;
                
            default:
                break;
        }
        
        _listAdapter.notifyDataSetChanged();
        _spinnerAdapter.notifyDataSetChanged();
    }    
    
    public IWarehouse getWarehouse() {
        return _warehouse;
    }
}