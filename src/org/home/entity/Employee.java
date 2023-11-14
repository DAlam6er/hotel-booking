package org.home.entity;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter @Setter
public class Employee {
  private Long id;
  private String lastName;
  private String firstName;
  private String surname;
  private String address;
  private String phoneNumber;
  private Position position;
}
