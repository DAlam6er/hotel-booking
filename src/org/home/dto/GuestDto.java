package org.home.dto;

public record GuestDto(Long id, String fio, String birthday,
                       String checkIn, String checkOut, String roomNumber) {
}
