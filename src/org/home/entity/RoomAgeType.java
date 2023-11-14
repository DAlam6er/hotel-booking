package org.home.entity;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter @Setter
public class RoomAgeType {
  private Integer id;
  private String abbr;
  private String description;
}
