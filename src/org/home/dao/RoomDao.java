package org.home.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.home.entity.*;
import org.home.exception.DaoException;
import org.home.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomDao implements Dao<String, Room> {
  private static final RoomDao INSTANCE = new RoomDao();
  private final RoomAgeTypeDao roomAgeTypeDao = RoomAgeTypeDao.getInstance();
  private final RoomInteriorTypeDao roomInteriorTypeDao = RoomInteriorTypeDao.getInstance();
  private final RoomBedTypeDao roomBedTypeDao = RoomBedTypeDao.getInstance();
  private final RoomNumberOfPlacesTypeDao roomNumberOfPlacesTypeDao = RoomNumberOfPlacesTypeDao.getInstance();
  private final EmployeeDao employeeDao = EmployeeDao.getInstance();

  private static final String FIND_BY_ID_SQL = """
      SELECT id,
            floor,
            room_number_of_places_type,
            room_age_type,
            room_interior_type,
            room_bed_type,
            occupied,
            employee_code,
            cost
      FROM hotel_bookings.public.room
      WHERE id = ?
      """;

  public static RoomDao getInstance() {
    return INSTANCE;
  }

  @Override
  public List<Room> findAll() {
    return null;
  }

  @Override
  public Optional<Room> findById(String id) {
    try (var connection = ConnectionPool.get()) {
      return findById(id, connection);
    } catch (SQLException ex) {
      throw new DaoException(ex);
    }
  }

  public Optional<Room> findById(String id, Connection connection) {
    try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
      preparedStatement.setString(1, id);

      var resultSet = preparedStatement.executeQuery();
      Room room = null;
      if (resultSet.next()) {
        room = buildRoom(resultSet);
      }
      return Optional.ofNullable(room);
    } catch (SQLException ex) {
      throw new DaoException(ex);
    }
  }

  private Room buildRoom(ResultSet resultSet) throws SQLException {
    var connection = resultSet.getStatement().getConnection();

    var roomAgeTypeId = resultSet.getInt("room_age_type");
    var roomInteriorTypeId = resultSet.getInt("room_interior_type");
    var roomBedTypeId = resultSet.getInt("room_bed_type");
    var roomNumberOfPlacesTypeId = resultSet.getInt("room_number_of_places_type");
    var employeeId = resultSet.getLong("employee_code");

    var employee = employeeDao.findById(employeeId, connection).orElse(null);
    var roomAgeType = roomAgeTypeDao.findById(roomAgeTypeId, connection).orElse(null);
    var roomInteriorType = roomInteriorTypeDao.findById(roomInteriorTypeId, connection).orElse(null);
    var roomNumberOfPlacesType = roomNumberOfPlacesTypeDao.findById(roomNumberOfPlacesTypeId, connection).orElse(null);
    var roomBedType = roomBedTypeDao.findById(roomBedTypeId, connection).orElse(null);

    return Room.builder()
        .id(resultSet.getString("id"))
        .floor((int) resultSet.getShort("floor"))
        .roomNumberOfPlacesType(roomNumberOfPlacesType)
        .roomAgeType(roomAgeType)
        .roomInteriorType(roomInteriorType)
        .roomBedType(roomBedType)
        .occupied((int) resultSet.getShort("occupied"))
        .employee(employee)
        .cost(resultSet.getBigDecimal("cost"))
        .build();
  }

  @Override
  public Room save(Room entity) {
    return null;
  }

  @Override
  public void update(Room entity) {

  }

  @Override
  public boolean delete(String id) {
    return false;
  }
}
