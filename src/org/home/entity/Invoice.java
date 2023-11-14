package org.home.entity;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter @Setter
public class Invoice {
  private Long id;
  private Guest guest;
  private PaymentStatus paymentStatus;
}
