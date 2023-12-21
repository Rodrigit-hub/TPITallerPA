package com.app.web.controlador;

import com.app.web.entidad.Factura;
import com.app.web.entidad.OrdenTrabajo;
import com.app.web.services.OrdenTrabajoServicio;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

@Controller
public class PdfController {
	
	@Autowired
    private OrdenTrabajoServicio servicio;
	
    @GetMapping("/ordentrabajo/factura/{ordenId}/pdf")
    public void generarFacturaPdf(@PathVariable Long ordenId, Model model, HttpServletResponse response) {
        OrdenTrabajo ordenTrabajo = servicio.obtenerOrdenTrabajoPorID(ordenId);
        Factura factura = new Factura(new ArrayList<>(ordenTrabajo.getServicios()));

        // Configuración para la respuesta HTTP
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=factura.pdf");

        // Crear el documento PDF
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            // Agregar contenido al PDF (puedes personalizar esto según tus necesidades)
            document.add(new Paragraph("Factura para Orden de Trabajo #" + ordenId));
            document.add(new Paragraph("Fecha: " + new Date()));
            document.add(new Paragraph("Subtotal: $" + factura.getSubTotal()));
            document.add(new Paragraph("Impuesto: " + factura.getImpuesto() + "%"));
            document.add(new Paragraph("Total: $" + factura.getTotal()));

            // Puedes agregar más detalles, como los servicios, aquí

            document.close();

            // Escribir el contenido del PDF en la respuesta HTTP
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}