package org.home.entity;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter @Setter
public class RoomAllocation {
  private Guest guest;
  private Room room;
}
