package com.eduit.javaseweb.integrator.dao;

import com.eduit.javaseweb.integrator.models.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    public static final String INSERT = "INSERT INTO productos (precio, descripcion) VALUES (?,?);";
    public static final String UPDATE = "UPDATE productos SET precio=?, descripcion=? WHERE id=?;";
    public static final String DELETE = "DELETE FROM productos WHERE id=?;";
    public static final String GET = "SELECT id, precio, descripcion FROM productos ";

    public void insert(Product product) throws SQLException, IllegalArgumentException {
        if (getByDescription(product.getDescription()) != null) {
            throw new IllegalArgumentException("The product is already exists in the database.");
        }

        try (Connection conn = Dao.getConnection();
                PreparedStatement ps = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDouble(1, product.getPrice());
            ps.setString(2, product.getDescription());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            product.setId(rs.getInt(1));
        }
    }

    public void update(Product product) throws SQLException, IllegalArgumentException {
        if (getById(product) == null) {
            throw new IllegalArgumentException("The product doesn't exist in the database.");
        }

        try (Connection conn = Dao.getConnection();
                PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            ps.setDouble(1, product.getPrice());
            ps.setString(2, product.getDescription());
            ps.setInt(3, product.getId());
            ps.execute();
        }
    }

    public void delete(Product product) throws SQLException, IllegalArgumentException {
        if (getById(product) == null) {
            throw new IllegalArgumentException("The product doesn't exist in the database.");
        }

        try (Connection conn = Dao.getConnection();
                PreparedStatement ps = conn.prepareStatement(DELETE)) {
            ps.setInt(1, product.getId());
            ps.execute();
        }
    }

    public Product getById(Product product) throws SQLException {
        Product find = null;
        try (Connection conn = Dao.getConnection();
                PreparedStatement ps = conn.prepareStatement(GET.concat("WHERE id =?;"))) {
            ps.setInt(1, product.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    find = new Product();
                    find.setId(rs.getInt("id"));
                    find.setPrice(rs.getDouble("precio"));
                    find.setDescription(rs.getString("descripcion"));
                }
            }
        }

        return find;
    }

    public List<Product> getByDescription(String description) throws SQLException {
        Product find;
        List<Product> products = new ArrayList<>();
        try (Connection conn = Dao.getConnection(); // where descripcion like '%test%'
                PreparedStatement ps = conn.prepareStatement(GET.concat("WHERE descripcion like ?;"))) {
            ps.setString(1, "%" + description + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    find = new Product();
                    find.setId(rs.getInt("id"));
                    find.setPrice(rs.getDouble("precio"));
                    find.setDescription(rs.getString("descripcion"));
                    products.add(find);
                }
            }
        }

        return products;
    }

    public List<Product> getAll() throws SQLException {
        final List<Product> products;
        Product find;
        try (final Connection conn = Dao.getConnection();
                final PreparedStatement ps = conn.prepareStatement(GET);
                final ResultSet rs = ps.executeQuery()) {
            rs.last();
            int rows = rs.getRow();
            rs.beforeFirst();
            products = new ArrayList<>(rows);
            while (rs.next()) {
                find = new Product();
                find.setId(rs.getInt("id"));
                find.setPrice(rs.getDouble("precio"));
                find.setDescription(rs.getString("descripcion"));
                products.add(find);
            }
        }

        return products;
    }
}
