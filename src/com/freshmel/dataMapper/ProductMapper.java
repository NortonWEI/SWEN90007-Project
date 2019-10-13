package com.freshmel.dataMapper;

import com.freshmel.dbc.DataBaseConnection;
import com.freshmel.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductMapper implements IProductMapper<Product> {

    private static IProductMapper<Product> instance;

    public Connection conn = new DataBaseConnection().getConnection();
    public PreparedStatement pstmt;

    public static IProductMapper getInstance(String sessionId) {
        if(instance == null) {
            instance = new ProductLockingMapper(new ProductMapper(),sessionId);
        }
        return instance;
    }

    /**
     * insert a new product into databse
     * @param product with the product info
     * @return if insert successfully return true
     *         if insert failed return false
     * */
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

    /**
     * find products in database by venderId
     *
     * @param venderId
     * @return return a list of products from database that published by the vender
     * */
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

    /**
     * find products in database by productId
     *
     * @param productId
     * @return if the product in database return the product
     *         if not in database return null
     * */
    public Product findByProductID(Long productId) throws SQLException {
        String sql = "SELECT id,name,photo,description,price,createDate,state,type,vender_id,inventory FROM product WHERE id=?";
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setLong(1, productId);
        ResultSet rs = pstmt.executeQuery();
        Product product = null;
        if (rs.next()){
            product = new Product();
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
        }
        return product;
    }

    /**
     * delete product by productId
     * @param product with productId info
     * @return if delete successfully return true
     *         if delete failed return false
     * */
    public boolean deleteByProductId(Product product) throws SQLException {
        String sql = "DELETE FROM product WHERE id=?";
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setLong(1, product.getId());
        return pstmt.executeUpdate() > 0;
    }


    /**
     * update product
     * @param product with the new info need to update
     * @return if update successfully return true
     *         if update failed return false
     * */
    public boolean updateProduct(Product product) throws SQLException {
        String sql = "UPDATE product SET name=?,photo=?,description=?,price=?,state=?,type=?,inventory=? WHERE id=?" ;
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setString(1, product.getName());
        pstmt.setString(2, product.getPhoto());
        pstmt.setString(3, product.getDescription());
        pstmt.setFloat(4, product.getPrice());
        pstmt.setInt(5, product.getState());
        pstmt.setString(6, product.getType());
        pstmt.setInt(7, product.getInventory());
        pstmt.setLong(8, product.getId());
        return pstmt.executeUpdate() > 0 ;
    }

    /**
     * update product quantity
     * @param product with the new info need to update
     * @return if update successfully return true
     *         if update failed return false
     * */
    public boolean updateProductQuantity(Product product) throws SQLException {
        String sql = "UPDATE product SET inventory=? WHERE id=? AND ?>0" ;
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setInt(1, product.getInventory());
        pstmt.setLong(2, product.getId());
        pstmt.setInt(3, product.getInventory());
        return pstmt.executeUpdate() > 0 ;
    }

    /**
     * get all products that are on sale
     *
     * @return return a list of products that are on sale
     * */
    public List<Product> getAllProduct() throws SQLException {
        String sql = "SELECT id,name,photo,description,price,createDate,state,type,vender_id,inventory FROM product WHERE state=1";
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

    /**
     * get products by type
     * @param type the type of products
     * @return a list of that type of products
     * */
    public List<Product> getByType(String type) throws SQLException {
        String sql = "SELECT id,name,photo,description,price,createDate,state,type,vender_id,inventory FROM product WHERE type=? AND state=1";
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

    public List<Product> getBySearch(String query) throws SQLException {
        String sql = "SELECT id,name,photo,description,price,createDate,state,type,vender_id,inventory FROM product WHERE name LIKE ? AND state=1";
        pstmt = conn.prepareStatement(sql) ;
        pstmt.setString(1, "%" + query + "%");
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

    public List<Product> getByRank(String rank) throws SQLException {
        String sql = "";

        if (rank.equals("price")) {
            sql = "SELECT id,name,photo,description,price,createDate,state,type,vender_id,inventory FROM product ORDER BY price ASC";
        } else if (rank.equals("inv")) {
            sql = "SELECT id,name,photo,description,price,createDate,state,type,vender_id,inventory FROM product ORDER BY inventory ASC";
        }
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
}
