package com.freshmel.service;

import com.freshmel.dataMapper.CustomerMapper;
import com.freshmel.dataMapper.ProductMapper;
import com.freshmel.dataMapper.VenderMapper;
import com.freshmel.model.Customer;
import com.freshmel.model.Product;
import com.freshmel.model.Vender;

import java.sql.SQLException;

public class VenderService {
    public boolean register(Vender vender) throws SQLException {
        vender.setPhoto("default.png");
        vender.setPhoneNumber(" ");
        vender.setFirstName(" ");
        vender.setLasteName(" ");
        VenderMapper venderMapper = new VenderMapper();
        return venderMapper.insert(vender);
    }
    public Vender login(Vender vender) throws SQLException {
        VenderMapper venderMapper = new VenderMapper();
        return venderMapper.findByEmailANDPassword(vender);
    }

    public boolean updatePhoto(Vender vender) throws SQLException {
        VenderMapper venderMapper  = new VenderMapper();
        return venderMapper.updatePhoto(vender);
    }

    public boolean updateInfo(Vender vender) throws SQLException {
        VenderMapper venderMapper = new VenderMapper();
        return venderMapper.updateInfo(vender);
    }

    public boolean addNewProduct(Product product) throws SQLException {
        ProductMapper productMapper = new ProductMapper();
        return productMapper.insert(product);
    }

    public boolean updateProduct(Product product) throws SQLException {
        ProductMapper productMapper = new ProductMapper();
        return productMapper.updateProduct(product);
    }
}
