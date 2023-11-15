package org.home.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static java.time.format.DateTimeFormatter.ofLocalizedDate;
import static java.time.format.FormatStyle.SHORT;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter @Setter
public class Guest {
  private Long id;
  private String lastName;
  private String firstName;
  private String surname;
  private LocalDate birthday;
  private Gender gender;
  private String address;
  private String passNo;
  private MaritalStatus maritalStatus;
  private LocalDateTime checkIn;
  private LocalDateTime checkOut;
  private Integer durationOfStay;

  @Override
  public String toString() {
    var fio = String.join(" ", lastName, firstName, surname);
    return String.join(", ", fio, birthday.format(ofLocalizedDate(SHORT)));
  }
}
