package org.home.entity;

import lombok.*;
import org.home.entity.enums.WeekDay;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter @Setter
public class DutyList {
  private Long id;
  private Employee employee;
  private WeekDay weekDay;
  private LocalDateTime workStart;
  private LocalDateTime workEnd;
}
