package org.home.entity;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter @Setter
public class BookingApplication {
  private Long id;
  private RoomNumberOfPlacesType roomNumberOfPlacesType;
  private RoomAgeType roomAgeType;
  private RoomInteriorType roomInteriorType;
  private RoomBedType roomBedType;
  private ApplicationStatus applicationStatus;
}
