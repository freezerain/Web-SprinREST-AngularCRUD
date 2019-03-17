package model.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "ARTICULOS")
public class Articulos {
    @Id
    @Column(name = "CODIGO_ARTICULO", precision = 5, scale = 0)
    private Integer codigoArticulo;

    @Column(name = "DESCRIPCION_ARTICULO", length = 40)
    private String descripcionArticulo;

    @Column(name = "PRECIO_UNIDAD_ARTICULO", precision = 11, scale = 2)
    private Double precioUnidadArticulo;

    // Constructors
    public Articulos() {
    }

    public Articulos(Integer cod, String dis, Double prec) {
        this.codigoArticulo = cod;
        this.descripcionArticulo = dis;
        this.precioUnidadArticulo = prec;
    }

}
