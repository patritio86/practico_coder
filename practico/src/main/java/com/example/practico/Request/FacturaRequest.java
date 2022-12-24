package com.example.practico.Request;

import java.util.Date;
import java.util.Objects;

public class FacturaRequest {

    private Date fecha;
    private int cantidad;
    private float total;

    //Generate constructor
    public FacturaRequest() {
    }

    public FacturaRequest(Date fecha, int cantidad, float total) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.total = total;
    }

    //Generate set and get

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

    //Generate toString, equals and hash


    @Override
    public String toString() {
        return "FacutaRequest{" +
                "fecha=" + fecha +
                ", cantidad=" + cantidad +
                ", total=" + total +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacturaRequest that = (FacturaRequest) o;
        return cantidad == that.cantidad && Float.compare(that.total, total) == 0 && Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, cantidad, total);
    }
}
