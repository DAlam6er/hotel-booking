package org.home.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.home.dao.GuestDao;
import org.home.dao.RoomAllocationDao;
import org.home.dto.GuestDto;
import org.home.entity.Room;
import org.home.entity.RoomAllocation;

import java.util.Optional;

import static java.time.format.DateTimeFormatter.ofLocalizedDate;
import static java.time.format.DateTimeFormatter.ofLocalizedDateTime;
import static java.time.format.FormatStyle.MEDIUM;
import static java.time.format.FormatStyle.SHORT;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GuestService {
  private static final GuestService INSTANCE = new GuestService();
  private final GuestDao guestDao = GuestDao.getInstance();
  private final RoomAllocationDao roomAllocationDao = RoomAllocationDao.getInstance();


  public static GuestService getInstance() {
    return INSTANCE;
  }

  public Optional<GuestDto> findByGuestId(Long guestId) {
    var roomNumber = roomAllocationDao.findByGuestId(guestId).map(RoomAllocation::getRoom).map(Room::getId).orElse(null);
    return guestDao.findById(guestId).map(guest -> {
      var checkout = guest.getCheckOut();

      return new GuestDto(guest.getId(),
          String.join(" ", guest.getLastName(), guest.getFirstName(), guest.getSurname()),
          guest.getBirthday().format(ofLocalizedDate(SHORT)),
          guest.getCheckIn().format(ofLocalizedDateTime(MEDIUM)),
          checkout != null ? checkout.format(ofLocalizedDateTime(MEDIUM)) : "без даты", roomNumber);
    });
  }
}
