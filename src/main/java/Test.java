import config.SpringDBConfig;
import model.dao.Articulos;
import model.service.IArticulosCRUD;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Random;

class Test {

    void beginTest() {
        System.out.println("Begining test");
        System.out.println("Testing Spring Injection");
        //Spring injection
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringDBConfig.class);
        //Getting all implementations from parent interface
        IArticulosCRUD articulosFacade = context.getBean("articulosFacade", IArticulosCRUD.class);

        System.out.println("Testing Articulos CRUD");
        //Read Articulos from server
        List<Articulos> articulosList = articulosFacade.findAll();
        Articulos articulos = articulosList.get(new Random().nextInt(articulosList.size()));
        articulos.setCodigoArticulo(7777);
        articulos.setDescripcionArticulo("Custom Descripcion");
        //Creating new element in table
        articulosFacade.create(articulos);
        articulos.setDescripcionArticulo("New Desripcion");
        //Updating element in table
        articulosFacade.modify(articulos);
        //Cleaning Up
        articulosFacade.delete(articulos);

        System.out.println("Test finished");
    }

}
