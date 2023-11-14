package org.home.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Getter @Setter
public class Guest {
  private Long id;
  @ToString.Include
  private String lastName;
  @ToString.Include
  private String firstName;
  @ToString.Include
  private String surname;
  @ToString.Include
  private LocalDate birthday;
  private Gender gender;
  private String address;
  private String passNo;
  private MaritalStatus maritalStatus;
  private LocalDateTime checkIn;
  private LocalDateTime checkOut;
  private Integer durationOfStay;
}
