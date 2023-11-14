package org.home.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.home.dao.BookingApplicationDao;
import org.home.dto.BookingApplicationDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookingApplicationService {
  private static final BookingApplicationService INSTANCE = new BookingApplicationService();
  private final BookingApplicationDao bookingApplicationDao = BookingApplicationDao.getInstance();

  public static BookingApplicationService getInstance() {
    return INSTANCE;
  }

  public List<BookingApplicationDto> findAll() {
    return bookingApplicationDao.findAll().stream()
        .map(bookingApplication -> new BookingApplicationDto(bookingApplication.getId(),
            """
                номер (%s) для %s. %s. Кровать: %s.
                """.formatted(
                bookingApplication.getRoomInteriorType().getAbbr(),
                bookingApplication.getRoomAgeType().getDescription(),
                bookingApplication.getRoomNumberOfPlacesType().getDescription(),
                bookingApplication.getRoomBedType().getAbbr()),
            bookingApplication.getApplicationStatus().getStatus()))
        .collect(toList());
  }
}
