package org.home.dto;

import java.util.Objects;

public record InvoiceDto(Long id, Long guestId, String guestInfo, String paymentStatus) {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    InvoiceDto that = (InvoiceDto) o;
    return Objects.equals(id, that.id);
  }

  public int hashCode() {
    return Objects.hash(id);
  }
}
