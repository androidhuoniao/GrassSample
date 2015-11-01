package com.grass.component;

import com.grass.model.Vehicle;
import com.grass.module.VehicleModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by grass on 10/31/15.
 */
@Singleton
@Component(modules = {VehicleModule.class})
public interface VehicleComponent {

    Vehicle provideVehicle();

}
