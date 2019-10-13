package com.freshmel.dataMapper;


import com.freshmel.exception.ConcurrencyException;

import java.sql.SQLException;
import java.util.List;

public interface IProductMapper<E> {
    public boolean insert(E product) throws SQLException;
    public List<E> findByVenderID(Long venderId) throws SQLException, ConcurrencyException;
    public E findByProductID(Long productId) throws SQLException, ConcurrencyException;
    public boolean deleteByProductId(E product) throws SQLException, ConcurrencyException;
    public boolean updateProduct(E product) throws SQLException, ConcurrencyException;
    public boolean updateProductQuantity(E product) throws SQLException, ConcurrencyException;
    public List<E> getAllProduct() throws SQLException, ConcurrencyException;
    public List<E> getByType(String type) throws SQLException, ConcurrencyException;
}
