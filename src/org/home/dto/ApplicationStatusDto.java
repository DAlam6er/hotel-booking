package org.home.dto;

import java.util.Objects;

public record ApplicationStatusDto(Integer id, String status) {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ApplicationStatusDto that = (ApplicationStatusDto) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
