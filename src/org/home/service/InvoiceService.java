package org.home.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.home.dao.InvoiceDao;
import org.home.dto.InvoiceDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InvoiceService {
  private static final InvoiceService INSTANCE = new InvoiceService();
  private final InvoiceDao invoiceDao = InvoiceDao.getInstance();

  public static InvoiceService getInstance() {
    return INSTANCE;
  }

  public List<InvoiceDto> findAll() {
    return invoiceDao.findAll().stream()
        .map(invoice -> new InvoiceDto(invoice.getId(), invoice.getGuest().toString(), invoice.getPaymentStatus().getStatus()))
        .collect(toList());
  }
}
