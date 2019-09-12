package com.freshmel.dataMapper;

import com.freshmel.dbc.DataBaseConnection;
import com.freshmel.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    public Connection conn = new DataBaseConnection().getConnection();
    public PreparedStatement pstmt;

    public boolean insert(Product product) throws SQLException {
        String sql = "INSERT INTO product(name,photo,description,price,createDate,state,type,vender_id,inventory) VALUES (?,?,?,?,?,?,?,?,?)" ;
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setString(1, product.getName());
        pstmt.setString(2, product.getPhoto());
        pstmt.setString(3, product.getDescription());
        pstmt.setFloat(4, product.getPrice());
        pstmt.setTimestamp(5, product.getCreatDate());
        pstmt.setInt(6, product.getState());
        pstmt.setString(7, product.getType());
        pstmt.setLong(8, product.getVenderId());
        pstmt.setInt(9, product.getInventory());
        return pstmt.executeUpdate() > 0;
    }

    public List<Product> findByVenderID(Long venderId) throws SQLException {
        String sql = "SELECT id,name,photo,description,price,createDate,state,type,vender_id,inventory FROM product WHERE vender_id=?";
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setLong(1, venderId);
        ResultSet rs = pstmt.executeQuery();
        List<Product> products = new ArrayList<Product>();
        while (rs.next()){
            Product product = new Product();
            product.setId(rs.getLong(1));
            product.setName(rs.getString(2));
            product.setPhoto(rs.getString(3));
            product.setDescription(rs.getString(4));
            product.setPrice(rs.getFloat(5));
            product.setCreatDate(rs.getTimestamp(6));
            product.setState(rs.getInt(7));
            product.setType(rs.getString(8));
            product.setVenderId(rs.getLong(9));
            product.setInventory(rs.getInt(10));
            products.add(product);
        }
        return products;
    }

    public boolean deleteByProductId(Product product) throws SQLException {
        String sql = "DELETE FROM product WHERE id=?";
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setLong(1, product.getId());
        return pstmt.executeUpdate() > 0;
    }

    public boolean updateProduct(Product product) throws SQLException {
        String sql = "UPDATE product SET name=?,photo=?,description=?,price=?,state=?,type=?,inventory=? WHERE id=?" ;
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setString(1, product.getName());
        pstmt.setString(2, product.getPhoto());
        pstmt.setString(3, product.getDescription());
        pstmt.setFloat(4, product.getPrice());
        pstmt.setInt(5,product.getState());
        pstmt.setString(6, product.getType());
        pstmt.setInt(7, product.getInventory());
        pstmt.setLong(8, product.getId());
        return pstmt.executeUpdate() > 0 ;
    }

    public List<Product> getAllProduct() throws SQLException {
        String sql = "SELECT id,name,photo,description,price,createDate,state,type,vender_id,inventory FROM product";
        pstmt = conn.prepareStatement(sql) ;
        ResultSet rs = pstmt.executeQuery();
        List<Product> products = new ArrayList<Product>();
        while (rs.next()){
            Product product = new Product();
            product.setId(rs.getLong(1));
            product.setName(rs.getString(2));
            product.setPhoto(rs.getString(3));
            product.setDescription(rs.getString(4));
            product.setPrice(rs.getFloat(5));
            product.setCreatDate(rs.getTimestamp(6));
            product.setState(rs.getInt(7));
            product.setType(rs.getString(8));
            product.setVenderId(rs.getLong(9));
            product.setInventory(rs.getInt(10));
            products.add(product);
        }
        return products;
    }

    public List<Product> getByType(String type) throws SQLException {
        String sql = "SELECT id,name,photo,description,price,createDate,state,type,vender_id,inventory FROM product WHERE type=?";
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setString(1, type);
        ResultSet rs = pstmt.executeQuery();
        List<Product> products = new ArrayList<Product>();
        while (rs.next()){
            Product product = new Product();
            product.setId(rs.getLong(1));
            product.setName(rs.getString(2));
            product.setPhoto(rs.getString(3));
            product.setDescription(rs.getString(4));
            product.setPrice(rs.getFloat(5));
            product.setCreatDate(rs.getTimestamp(6));
            product.setState(rs.getInt(7));
            product.setType(rs.getString(8));
            product.setVenderId(rs.getLong(9));
            product.setInventory(rs.getInt(10));
            products.add(product);
        }
        return products;
    }
    
}
