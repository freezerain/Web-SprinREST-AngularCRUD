package controller;

import model.dao.Articulos;
import model.service.IArticulosCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping("/")
public class ArticulosController {

    private IArticulosCRUD articulosFacade;

    @Autowired
    public ArticulosController(IArticulosCRUD articulosFacade) {
        this.articulosFacade = articulosFacade;
    }

    //Landing page for server
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /* All CRUD Operations
        also returning updated getRequest
        until Observable RxJs library will be implemented
     */

    @GetMapping("/articulos")
    public List<Articulos> getAllArticulos() {
        System.out.println("Recieved get All request");
        return articulosFacade.findAll();
    }

    @PostMapping("/articulos")
    public List<Articulos> addArticulo(@RequestBody Articulos articulos) {
        System.out.println("Recieved articulo");
        articulosFacade.create(articulos);
        return articulosFacade.findAll();
    }

    @PutMapping("/articulos")
    public List<Articulos> updateArticulo(@RequestBody Articulos articulos) {
        System.out.println("Recieved update on articulo");
        articulosFacade.modify(articulos);
        return articulosFacade.findAll();
    }

    @DeleteMapping("/articulos/{id}")
    public List<Articulos> delete(@PathVariable int id) {
        System.out.println("Recieved delete request of id: " + id);
        Articulos instance = articulosFacade.findById(id);
        articulosFacade.delete(instance);
        return articulosFacade.findAll();
    }
}
