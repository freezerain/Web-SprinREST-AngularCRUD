package model.service;

import model.dao.Articulos;
import model.dao.ArticulosDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("articulosFacade")
@Scope("prototype")
public class ArticulosFacade implements IArticulosCRUD {

    private ArticulosDAO articulosDAO;

    //Constructor
    public ArticulosFacade() {
    }

    @Autowired
    public ArticulosFacade(ArticulosDAO articulosDAO) {
        this.articulosDAO = articulosDAO;
    }


    @Override
    @Transactional(readOnly = true)
    public Articulos findById(Integer id) {
        return articulosDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Articulos> findAll() {
        return articulosDAO.findAll();
    }

    @Override
    @Transactional
    public void modify(Articulos instance) {
        articulosDAO.attachDirty(instance);
    }

    @Override
    @Transactional
    public void delete(Articulos instance) {
        articulosDAO.delete(instance);
    }

    @Override
    @Transactional
    public void create(Articulos instance) {
        articulosDAO.save(instance);
    }

    // Spring accessor
    public void setArticulosDAO(ArticulosDAO articulos) {
        this.articulosDAO = articulos;
    }

}
