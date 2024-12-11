package com.app.web.controlador;

import com.app.web.entidad.Factura;
import com.app.web.entidad.OrdenTrabajo;
import com.app.web.entidad.Servicio;
import com.app.web.services.OrdenTrabajoServicio;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class PdfController {

    @Autowired
    private OrdenTrabajoServicio servicio;

    @GetMapping("/ordentrabajo/factura/{ordenId}/pdf")
    public void generarFacturaPdf(@PathVariable Long ordenId, Model model, HttpServletResponse response) {
        OrdenTrabajo ordenTrabajo = servicio.obtenerOrdenTrabajoPorID(ordenId);
        Factura factura = new Factura(new ArrayList<>(ordenTrabajo.getServicios()));

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=factura.pdf");

        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            // Encabezado
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Paragraph title = new Paragraph("FACTURA", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" ")); // Espacio

            // Datos generales
            Font infoFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
            document.add(new Paragraph("Fecha Actual: " + new Date(), infoFont));
            document.add(new Paragraph("Cliente: " + ordenTrabajo.getCliente().getNombre(), infoFont));
            document.add(new Paragraph("Vehículo: " + ordenTrabajo.getVehiculo().getPatente(), infoFont));

            document.add(new Paragraph(" ")); // Espacio

            // Tabla de servicios
            PdfPTable table = new PdfPTable(2); // 2 columnas
            table.setWidthPercentage(100);
            table.setWidths(new float[]{4, 1}); // Ancho de columnas

            // Encabezados
            PdfPCell header1 = new PdfPCell(new Phrase("Servicio", titleFont));
            PdfPCell header2 = new PdfPCell(new Phrase("Precio", titleFont));
            header1.setHorizontalAlignment(Element.ALIGN_CENTER);
            header2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(header1);
            table.addCell(header2);

            // Agregar servicios
            for (Servicio servicio : factura.getServicios()) {
                table.addCell(new Phrase(servicio.getNombre(), infoFont));
                table.addCell(new Phrase("$" + servicio.getPrecio(), infoFont));
            }

            document.add(table);

            document.add(new Paragraph(" ")); // Espacio

            // Totales
            Font totalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            document.add(new Paragraph("Impuesto: % " + factura.getImpuesto(), totalFont));
            document.add(new Paragraph("Subtotal: $ " + factura.getSubTotal(), totalFont));
            document.add(new Paragraph("Total: $ " + factura.getTotal(), totalFont));

            document.close();

            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

