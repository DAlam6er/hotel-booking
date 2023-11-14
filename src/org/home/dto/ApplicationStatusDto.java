package org.home.dto;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public record ApplicationStatusDto(@EqualsAndHashCode.Include Integer id, String status){
}
