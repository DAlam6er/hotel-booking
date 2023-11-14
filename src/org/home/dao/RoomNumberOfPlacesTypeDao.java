package org.home.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.home.entity.RoomNumberOfPlacesType;
import org.home.exception.DaoException;
import org.home.util.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomNumberOfPlacesTypeDao implements Dao<Integer, RoomNumberOfPlacesType> {
  private static final RoomNumberOfPlacesTypeDao INSTANCE = new RoomNumberOfPlacesTypeDao();
  private static final String FIND_BY_ID_SQL = """
      SELECT id,
            capacity,
            abbr,
            description
      FROM hotel_bookings.public.room_number_of_places_type
      WHERE id = ?
      """;

  public static RoomNumberOfPlacesTypeDao getInstance() {
    return INSTANCE;
  }

  @Override
  public List<RoomNumberOfPlacesType> findAll() {
    return null;
  }

  @Override
  public Optional<RoomNumberOfPlacesType> findById(Integer id) {
    try (var connection = ConnectionPool.get()) {
      return findById(id, connection);
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  public Optional<RoomNumberOfPlacesType> findById(Integer id, Connection connection) {
    try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
      preparedStatement.setInt(1, id);

      var resultSet = preparedStatement.executeQuery();
      RoomNumberOfPlacesType roomNumberOfPlacesType = null;
      if (resultSet.next()) {
        roomNumberOfPlacesType = buildRoomNumberOfPlacesType(resultSet);
      }
      return Optional.ofNullable(roomNumberOfPlacesType);
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  private RoomNumberOfPlacesType buildRoomNumberOfPlacesType(ResultSet resultSet) throws SQLException {
    return RoomNumberOfPlacesType.builder()
        .id(resultSet.getInt("id"))
        .capacity(resultSet.getInt("capacity"))
        .abbr(resultSet.getString("abbr"))
        .description(resultSet.getString("description"))
        .build();
  }

  @Override
  public RoomNumberOfPlacesType save(RoomNumberOfPlacesType entity) {
    return null;
  }

  @Override
  public void update(RoomNumberOfPlacesType entity) {

  }

  @Override
  public boolean delete(Integer id) {
    return false;
  }
}
