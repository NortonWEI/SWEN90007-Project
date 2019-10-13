package com.freshmel.dataMapper;

import com.freshmel.exception.ConcurrencyException;
import com.freshmel.locker.ExclusiveReadLockManager;
import com.freshmel.model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductLockingMapper implements IProductMapper<Product> {
    private IProductMapper<Product> productMapper;
    private String sessionId;

    public ProductLockingMapper(IProductMapper productMapper, String sessionId){
        this.productMapper = productMapper;
        this.sessionId = sessionId;
    }


    @Override
    public List<Product> findByVenderID(Long venderId) throws SQLException, ConcurrencyException {
        // require lock
        ExclusiveReadLockManager.getInstance().acquireLock(venderId,sessionId);
        List<Product> result = this.productMapper.findByVenderID(venderId);
        // release lock
        ExclusiveReadLockManager.getInstance().releaseLock(venderId,sessionId);
        return result;
    }


    @Override
    public boolean insert(Product product) throws SQLException {

        return this.productMapper.insert(product);
    }


    @Override
    public Product findByProductID(Long productId) throws SQLException, ConcurrencyException {
        // require lock
        ExclusiveReadLockManager.getInstance().acquireLock(productId,sessionId);
        Product result = this.findByProductID(productId);
        // release lock
        ExclusiveReadLockManager.getInstance().releaseLock(productId,sessionId);
        return result;
    }

    @Override
    public boolean deleteByProductId(Product product) throws SQLException, ConcurrencyException {
        // require lock
        ExclusiveReadLockManager.getInstance().acquireLock(product.getId(),sessionId);
        boolean result = this.productMapper.deleteByProductId(product);
        // release lock
        ExclusiveReadLockManager.getInstance().releaseLock(product.getId(),sessionId);
        return result;
    }

    @Override
    public boolean updateProduct(Product product) throws SQLException, ConcurrencyException {
        // require lock
        ExclusiveReadLockManager.getInstance().acquireLock(product.getId(),sessionId);
        boolean result = this.productMapper.updateProduct(product);
        // release lock
        ExclusiveReadLockManager.getInstance().releaseLock(product.getId(),sessionId);
        return result;
    }

    @Override
    public boolean updateProductQuantity(Product product) throws SQLException, ConcurrencyException {
        // require lock
        ExclusiveReadLockManager.getInstance().acquireLock(product.getId(),sessionId);
        boolean result = this.productMapper.updateProductQuantity(product);
        // release lock
        ExclusiveReadLockManager.getInstance().releaseLock(product.getId(),sessionId);
        return result;
    }

    @Override
    public List<Product> getAllProduct() throws SQLException, ConcurrencyException {

        // require lock
        ExclusiveReadLockManager.getInstance().acquireLock((long) -1, sessionId);
        List<Product> result = this.productMapper.getAllProduct();
        // release lock
        ExclusiveReadLockManager.getInstance().releaseLock((long) -1, sessionId);
        return result;
    }

    @Override
    public List<Product> getByType(String type) throws SQLException, ConcurrencyException {
        // require lock
        ExclusiveReadLockManager.getInstance().acquireLock((long) -2,sessionId);
        List<Product> result = this.productMapper.getByType(type);
        // release lock
        ExclusiveReadLockManager.getInstance().releaseLock((long) -2,sessionId);
        return result;
    }
}
