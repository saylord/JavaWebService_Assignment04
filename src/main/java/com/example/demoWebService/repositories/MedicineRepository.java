package com.example.demoWebService.repositories;

import com.example.demoWebService.data.interfaces.IDB;
import com.example.demoWebService.entities.Medicine;
import com.example.demoWebService.repositories.interfaces.IMedicineRepository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

public class MedicineRepository implements IMedicineRepository {
    @Inject
    private IDB db;

    public MedicineRepository(IDB db) {
        this.db = db;
    }

    @Override
    public List<Medicine> searchMedicine(String name) {
        Connection connection = null;

        try {
            connection = db.getConnection();
            String sql = "SELECT id,name,price,expirationDate,manufacturer,usage FROM public.medicine WHERE name LIKE ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,name);

            ResultSet rset = st.executeQuery();

            List<Medicine> medicines = new LinkedList<>();

            while (rset.next()) {
                Medicine medicine = new Medicine(rset.getInt("id"),
                        rset.getString("name"),
                        rset.getDouble("price"),
                        rset.getDate("expirationDate").toLocalDate(),
                        rset.getString("manufacturer"),
                        rset.getString("usage"));
                medicines.add(medicine);
            }

            return medicines;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Medicine getMedicine(int id) {
        Connection connection = null;

        try {
            connection = db.getConnection();
            String sql = "SELECT id,name,price,expirationDate,manufacturer,usage FROM medicine WHERE id=?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1,id);

            ResultSet rset = st.executeQuery();

            if (rset.next()) {
                Medicine medicine = new Medicine(rset.getInt("id"),
                        rset.getString("name"),
                        rset.getDouble("price"),
                        rset.getDate("expirationDate").toLocalDate(),
                        rset.getString("manufacturer"),
                        rset.getString("usage"));

                return medicine;

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean addMedicine(Medicine medicine) {
        Connection connection = null;

        try {
            connection = db.getConnection();
            String sql = "INSERT INTO medicine(name, price, expirationDate, manufacturer, usage) VALUES(?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, medicine.getName());
            st.setDouble(2, medicine.getPrice());
            st.setDate(3, Date.valueOf(medicine.getExpirationDate()));
            st.setString(4, medicine.getManufacturer());
            st.setString(5, medicine.getUsage());

            st.execute();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean removeMedicine(int id) {
        Connection connection = null;

        try {
            connection = db.getConnection();
            String sql = "DELETE FROM medicine WHERE id=?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, id);
            st.execute();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Medicine> getAllMedicines() {
        Connection connection = null;

        try {
            connection = db.getConnection();
            String sql = "SELECT id,name,price,expirationDate,manufacturer,usage FROM medicine";
            Statement st = connection.createStatement();

            ResultSet rset = st.executeQuery(sql);
            List<Medicine> medicines = new LinkedList<>();
            while (rset.next()) {
                Medicine medicine = new Medicine(rset.getInt("id"),
                        rset.getString("name"),
                        rset.getDouble("price"),
                        rset.getDate("expirationDate").toLocalDate(),
                        rset.getString("manufacturer"),
                        rset.getString("usage"));

                medicines.add(medicine);
            }

            return  medicines;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean removeExpiredMedicine() {
        Connection connection = null;

        try {
            connection = db.getConnection();
            String sql = "DELETE FROM medicine WHERE expirationDate < now()";
            PreparedStatement st = connection.prepareStatement(sql);

            st.execute();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Medicine> getAllExpiredMedicines() {
        Connection connection = null;

        try {
            connection = db.getConnection();
            String sql = "SELECT id,name,price,expirationDate,manufacturer,usage FROM medicine WHERE expirationDate < now()";
            Statement st = connection.createStatement();

            ResultSet rset = st.executeQuery(sql);
            List<Medicine> medicines = new LinkedList<>();
            while (rset.next()) {
                Medicine medicine = new Medicine(rset.getInt("id"),
                        rset.getString("name"),
                        rset.getDouble("price"),
                        rset.getDate("expirationDate").toLocalDate(),
                        rset.getString("manufacturer"),
                        rset.getString("usage"));

                medicines.add(medicine);
            }

            return  medicines;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
}
