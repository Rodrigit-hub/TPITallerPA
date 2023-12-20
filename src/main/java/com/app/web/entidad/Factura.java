package com.app.web.entidad;
import java.math.BigDecimal;
import java.util.List;

public class Factura {

    private List<Servicio> servicios;
    private BigDecimal subTotal;
    private final BigDecimal impuesto = new BigDecimal("0.21");  // 21% de impuesto
    private BigDecimal total;

    public Factura(List<Servicio> servicios) {
        this.servicios = servicios;
        calcularSubTotal();
        calcularTotal();
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public BigDecimal getImpuesto() {
        return impuesto;
    }

    public BigDecimal getTotal() {
        return total;
    }

    private void calcularSubTotal() {
        // Sumar los precios de los servicios
        subTotal = servicios.stream().map(Servicio::getPrecio).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void calcularTotal() {
        // Calcular el total aplicando el impuesto
        total = subTotal.add(subTotal.multiply(impuesto));
    }
}