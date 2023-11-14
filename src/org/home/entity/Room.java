package org.home.entity;

import lombok.*;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter @Setter
public class Room {
  private String id;
  private Integer floor;
  private RoomNumberOfPlacesType roomNumberOfPlacesType;
  private RoomAgeType roomAgeType;
  private RoomInteriorType roomInteriorType;
  private RoomBedType roomBedType;
  private Integer occupied;
  private Employee employee;
  private BigDecimal cost;
}
