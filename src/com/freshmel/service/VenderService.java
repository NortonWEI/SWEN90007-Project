package com.freshmel.service;

import com.freshmel.dataMapper.ProductMapper;
import com.freshmel.dataMapper.VenderMapper;
import com.freshmel.model.Product;
import com.freshmel.model.Vender;

import java.sql.SQLException;

public class VenderService {

    /**
     * vender register
     * @param vender with email and password
     * @return if register successfully return true
     *         if register failed return false
     * */
    public boolean register(Vender vender) throws SQLException {
        vender.setPhoto("default.png");
        vender.setPhoneNumber(" ");
        vender.setFirstName(" ");
        vender.setLastName(" ");
        VenderMapper venderMapper = new VenderMapper();
        return venderMapper.insert(vender);
    }

    /**
     * vender login
     * @param vender with email and password
     * @return if login successfully return the vender with all info stored in database
     *         if login failed return null
     * */
    public Vender login(Vender vender) throws SQLException {
        VenderMapper venderMapper = new VenderMapper();
        return venderMapper.findByEmailANDPassword(vender);
    }

    /**
     * @param email
     * @return
     * @throws SQLException
     */
    public boolean resetPassword(String email) throws SQLException {
        VenderMapper venderMapper = new VenderMapper();
        return venderMapper.resetPassword(email);
    }

    /**
     * update vender photo
     * @param vender with the new photo info
     * @return if update successfully return true
     *         if failed return false
     * */
    public boolean updatePhoto(Vender vender) throws SQLException {
        VenderMapper venderMapper  = new VenderMapper();
        return venderMapper.updatePhoto(vender);
    }

    /**
     * update vender basic
     * @param vender with the new basic info
     * @return if update successfully return true
     *         if failed return false
     * */
    public boolean updateInfo(Vender vender) throws SQLException {
        VenderMapper venderMapper = new VenderMapper();
        return venderMapper.updateInfo(vender);
    }

    /**
     * vender add new product
     * @param product
     * @return if add successfully return true
     *         if failed return false
     * */
    public boolean addNewProduct(Product product) throws SQLException {
        ProductMapper productMapper = new ProductMapper();
        return productMapper.insert(product);
    }

    /**
     * uodate
     * */
    public boolean updateProduct(Product product) throws SQLException {
        ProductMapper productMapper = new ProductMapper();
        return productMapper.updateProduct(product);
    }

    public boolean deleteProduct(Product product) throws SQLException {
        ProductMapper productMapper = new ProductMapper();
        return productMapper.deleteByProductId(product);
    }
}
