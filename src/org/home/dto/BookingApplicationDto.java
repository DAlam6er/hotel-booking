package org.home.dto;

import java.util.Objects;

public record BookingApplicationDto(Long id, String description, String applicationStatus) {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BookingApplicationDto that = (BookingApplicationDto) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
