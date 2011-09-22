package com.touchtype_fluency.examples.borachio_warehouse;

import com.google.inject.Module;
import roboguice.application.RoboApplication;

import java.util.List;

public class BorachioWarehouseApplication extends RoboApplication {
    protected void addApplicationModules(List<Module> modules) {
        modules.add(new BorachioWarehouseModule());
    }
}
