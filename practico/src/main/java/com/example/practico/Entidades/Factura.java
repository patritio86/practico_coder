package com.example.practico.Entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "FACTURA")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "FECHA")
    private Date fecha;

    @Column(name = "CANTIDAD")
    private int cantidad;

    @Column(name = "TOTAL")
    private float total;

    @JsonBackReference(value = "cliente")
    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;

    // @JsonManagedReference
    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Producto> producto;

    //Generate constructor empty and with attributes


    public Factura() {
    }

    public Factura(long id, Date fecha, int cantidad, float total, Cliente cliente, List<Producto> producto) {
        this.id = id;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.total = total;
        this.cliente = cliente;
        this.producto = producto;
    }

    public Factura(Date fecha, int cantidad, float total) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.total = total;
    }

    //Generate set and get

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProducto() {
        return producto;
    }

    public void setProducto(List<Producto> producto) {
        this.producto = producto;
    }

    //Generate methode toString, equals and hash


    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", cantidad=" + cantidad +
                ", total=" + total +
                ", cliente=" + cliente +
                ", producto=" + producto +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return id == factura.id && cantidad == factura.cantidad && Float.compare(factura.total, total) == 0 && Objects.equals(fecha, factura.fecha) && Objects.equals(cliente, factura.cliente) && Objects.equals(producto, factura.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, cantidad, total, cliente, producto);
    }
}
