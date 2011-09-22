package com.touchtype_fluency.examples.borachio_warehouse;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BorachioWarehouseAdapter extends BaseAdapter {
    private BorachioWarehouse _warehouseActivity;
    
    public BorachioWarehouseAdapter(BorachioWarehouse w) {
        _warehouseActivity = w;
    }
    
    @Override
    public int getCount() {
        return _warehouseActivity.getWarehouse().getInventory().size();
    }

    @Override
    public Object getItem(int index) {
        return _warehouseActivity.getWarehouse().getInventory().get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public View getView(int index, View convertView, ViewGroup parent) {

        // Set up Android's list view component
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(_warehouseActivity).inflate(R.layout.list_item, null);
            
            holder = new ViewHolder();
            holder.mProduct = (TextView) convertView.findViewById(R.id.product_label);
            holder.mQuantity = (TextView) convertView.findViewById(R.id.qty_label);
            
            convertView.setTag(holder);            
            
        } else {
            holder = (ViewHolder) convertView.getTag();            
        }
        
        // Now read the data and add it to the list view item
        Pair<String, Integer> data = _warehouseActivity.getWarehouse().getInventory().get(index);
        holder.mProduct.setText(data.first);
        holder.mQuantity.setText(data.second.toString());
        
        return convertView;
    }
    
    static class ViewHolder {
        TextView mProduct;
        TextView mQuantity;
    }
}
