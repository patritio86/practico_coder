package com.example.practico.Entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "PRODUCTO")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "Cantidad")
    private int cantidad;
    @Column(name = "PRECIO")
    private float precio;

    @JsonBackReference(value = "factura")
    @ManyToOne(fetch = FetchType.EAGER)
    private Factura factura;

    //Generate constructor

    public Producto() {
    }

    public Producto(long id, String descripcion, int cantidad, float precio, Factura factura) {
        this.id = id;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.factura = factura;
    }

    public Producto(String descripcion, int cantidad, float precio) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    //Generate set and get

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }


    //generate toString, equal and hash

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", factura=" + factura +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id && cantidad == producto.cantidad && Float.compare(producto.precio, precio) == 0 && Objects.equals(descripcion, producto.descripcion) && Objects.equals(factura, producto.factura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion, cantidad, precio, factura);
    }
}
