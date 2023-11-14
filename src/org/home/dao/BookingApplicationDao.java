package org.home.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.home.entity.BookingApplication;
import org.home.exception.DaoException;
import org.home.util.ConnectionPool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookingApplicationDao implements Dao<Long, BookingApplication> {
  private static final BookingApplicationDao INSTANCE = new BookingApplicationDao();
  private final RoomNumberOfPlacesTypeDao roomPlacesDao = RoomNumberOfPlacesTypeDao.getInstance();
  private final RoomAgeTypeDao roomAgeTypeDao = RoomAgeTypeDao.getInstance();
  private final RoomInteriorTypeDao roomInteriorTypeDao = RoomInteriorTypeDao.getInstance();
  private final RoomBedTypeDao roomBedTypeDao = RoomBedTypeDao.getInstance();
  private final ApplicationStatusDao applicationStatusDao = ApplicationStatusDao.getInstance();
  private static final String FIND_ALL_SQL = """
      SELECT id,
            room_number_of_places_type,
            room_age_type,
            room_interior_type,
            room_bed_type,
            application_status
      FROM hotel_bookings.public.booking_application
      """;

  public static BookingApplicationDao getInstance() {
    return INSTANCE;
  }

  @Override
  public List<BookingApplication> findAll() {
    List<BookingApplication> bookingApplications = new ArrayList<>();
    try (var connection = ConnectionPool.get();
         var statement = connection.prepareStatement(FIND_ALL_SQL)) {
      var resultSet = statement.executeQuery();
      while (resultSet.next()) {
        bookingApplications.add(buildBookingApplication(resultSet));
      }
    } catch (SQLException ex) {
      throw new DaoException(ex);
    }
    return bookingApplications;
  }

  private BookingApplication buildBookingApplication(ResultSet resultSet) throws SQLException {
    var connection = resultSet.getStatement().getConnection();

    var roomPlacesTypeId = resultSet.getInt("room_number_of_places_type");
    var roomAgeTypeId = resultSet.getInt("room_age_type");
    var roomInteriorTypeId = resultSet.getInt("room_interior_type");
    var roomBedTypeId = resultSet.getInt("room_bed_type");
    var applicationStatusId = resultSet.getInt("application_status");

    var roomPlacesType = roomPlacesDao.findById(roomPlacesTypeId, connection).orElse(null);
    var roomAgeType = roomAgeTypeDao.findById(roomAgeTypeId, connection).orElse(null);
    var roomInteriorType = roomInteriorTypeDao.findById(roomInteriorTypeId, connection).orElse(null);
    var roomBedType = roomBedTypeDao.findById(roomBedTypeId, connection).orElse(null);
    var applicationStatus = applicationStatusDao.findById(applicationStatusId, connection).orElse(null);

    return BookingApplication.builder()
        .id(resultSet.getLong("id"))
        .roomNumberOfPlacesType(roomPlacesType)
        .roomAgeType(roomAgeType)
        .roomInteriorType(roomInteriorType)
        .roomBedType(roomBedType)
        .applicationStatus(applicationStatus)
        .build();
  }

  @Override
  public Optional<BookingApplication> findById(Long id) {
    return Optional.empty();
  }

  @Override
  public BookingApplication save(BookingApplication entity) {
    return null;
  }

  @Override
  public void update(BookingApplication entity) {

  }

  @Override
  public boolean delete(Long id) {
    return false;
  }
}
