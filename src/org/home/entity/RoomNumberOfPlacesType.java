package org.home.entity;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter @Setter
public class RoomNumberOfPlacesType {
  private Integer id;
  private Integer capacity;
  private String abbr;
  private String description;
}
