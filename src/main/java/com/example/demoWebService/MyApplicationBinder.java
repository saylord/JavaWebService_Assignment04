package com.example.demoWebService;

import org.glassfish.jersey.internal.inject.AbstractBinder;
import com.example.demoWebService.data.PostgresDB;
import com.example.demoWebService.data.interfaces.IDB;
import com.example.demoWebService.repositories.MedicineRepository;
import com.example.demoWebService.repositories.interfaces.IMedicineRepository;

public class MyApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(PostgresDB.class).to(IDB.class);
        bind(MedicineRepository.class).to(IMedicineRepository.class);
    }
}
