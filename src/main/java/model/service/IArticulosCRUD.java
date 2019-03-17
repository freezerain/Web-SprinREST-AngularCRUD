package model.service;

import model.dao.Articulos;

import java.util.List;


public interface IArticulosCRUD {
    Articulos findById(Integer id);

    List<Articulos> findAll();

    void modify(Articulos instance);

    void delete(Articulos instance);

    void create(Articulos instance);
}
