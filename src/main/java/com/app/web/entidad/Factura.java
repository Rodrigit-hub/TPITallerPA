package com.app.web.entidad;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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


    public String getImpuesto() {
        return formatWithDecimals(impuesto.multiply(BigDecimal.valueOf(100)));
    }

    public String getSubTotal() {
        return formatWithDecimals(subTotal);
    }

    public String getTotal() {
        return formatWithDecimals(total);
    }

    private String formatWithDecimals(BigDecimal value) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.##");
        return decimalFormat.format(value);
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