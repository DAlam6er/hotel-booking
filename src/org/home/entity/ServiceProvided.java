package org.home.entity;

import lombok.*;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter @Setter
public class ServiceProvided {
  private Long id;
  private Guest guest;
  private Service service;
  private BigDecimal cost;
  private Employee employee;
}
