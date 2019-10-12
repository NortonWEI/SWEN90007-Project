package com.freshmel.dataMapper;

public interface Mapper<E> {
    public E find(int id);
    public boolean insert(E obj);
    public void update(E obj);
    public void delete(E obj);
}
