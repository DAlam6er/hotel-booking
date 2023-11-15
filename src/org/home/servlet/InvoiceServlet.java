package org.home.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.home.service.InvoiceService;

import java.io.IOException;

@WebServlet("/invoice")
public class InvoiceServlet extends HttpServlet {
  private final InvoiceService invoiceService = InvoiceService.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/html; charset=UTF-8");
    try (var printWriter = resp.getWriter()) {
      printWriter.write("<h1>Заявки на оплату</h1>");
      printWriter.write("<ul>");
      invoiceService.findAll().forEach(invoiceDto -> printWriter.write("""
          <li>
            <a href="/guest-info?guestId=%d">%s</a>: %s
          </li>
          """.formatted(invoiceDto.guestId(), invoiceDto.guestInfo(), invoiceDto.paymentStatus())));
      printWriter.write("</ul>");
    }
  }
}
