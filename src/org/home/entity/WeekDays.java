package org.home.entity;

import lombok.*;
import org.home.entity.enums.WeekDay;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter @Setter
public class WeekDays {
  private Integer id;
  private WeekDay weekday;
}
