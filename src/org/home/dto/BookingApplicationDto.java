package org.home.dto;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public record BookingApplicationDto(@EqualsAndHashCode.Include Long id, String description,
                                    String applicationStatus) {
}
