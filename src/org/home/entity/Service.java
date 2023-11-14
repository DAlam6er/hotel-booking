package org.home.entity;

import lombok.*;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter @Setter
public class Service {
  private Integer id;
  private String serviceName;
  private BigDecimal cost;
}
