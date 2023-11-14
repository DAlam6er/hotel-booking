package org.home.dto;

import lombok.EqualsAndHashCode;
import org.home.entity.Guest;
import org.home.entity.PaymentStatus;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public record InvoiceDto(@EqualsAndHashCode.Include Long id, String guestInfo, String paymentStatus) {
}
