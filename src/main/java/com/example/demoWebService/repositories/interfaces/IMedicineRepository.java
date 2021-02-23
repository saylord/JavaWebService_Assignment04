package com.example.demoWebService.repositories.interfaces;

import com.example.demoWebService.entities.Medicine;

import java.util.List;

public interface IMedicineRepository {
    List<Medicine> searchMedicine(String name);
    Medicine getMedicine(int id);
    boolean addMedicine(Medicine medicine);
    boolean removeMedicine(int id);
    List<Medicine> getAllMedicines();
    boolean removeExpiredMedicine();
    List<Medicine> getAllExpiredMedicines();
}
