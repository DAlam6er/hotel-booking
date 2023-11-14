package org.home.entity;

import lombok.*;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter @Setter
public class Position {
  private Integer id;
  private String positionName;
  private BigDecimal salary;
}
