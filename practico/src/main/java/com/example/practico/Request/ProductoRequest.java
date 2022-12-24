package com.example.practico.Request;

import java.util.Objects;

public class ProductoRequest {
    private String descripcion;
    private int cantidad;
    private float precio;

    //Generate constructor
    public ProductoRequest() {
    }

    public ProductoRequest(String descripcion, int cantidad, float precio) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    //Generate set and get

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
    //Generate toString, equals and hash

    @Override
    public String toString() {
        return "ProductoRequest{" +
                "descripcion='" + descripcion + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoRequest that = (ProductoRequest) o;
        return cantidad == that.cantidad && Float.compare(that.precio, precio) == 0 && Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descripcion, cantidad, precio);
    }
}
