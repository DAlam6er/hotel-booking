package org.home.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.home.entity.RoomAgeType;
import org.home.util.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomAgeTypeDao implements Dao<Integer, RoomAgeType> {
  private static final RoomAgeTypeDao INSTANCE = new RoomAgeTypeDao();
  private static final String FIND_BY_ID_SQL = """
      SELECT id,
            abbr,
            description
      FROM hotel_bookings.public.room_age_type
      WHERE id = ?
      """;

  public static RoomAgeTypeDao getInstance() {
    return INSTANCE;
  }

  @Override
  public List<RoomAgeType> findAll() {
    return null;
  }

  @Override
  @SneakyThrows
  public Optional<RoomAgeType> findById(Integer id) {
    try (var connection = ConnectionPool.get()) {
      return findById(id, connection);
    }
  }

  @SneakyThrows
  public Optional<RoomAgeType> findById(Integer id, Connection connection) {
    try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
      preparedStatement.setInt(1, id);

      var resultSet = preparedStatement.executeQuery();
      RoomAgeType roomAgeType = null;
      if (resultSet.next()) {
        roomAgeType = buildRoomAgeType(resultSet);
      }
      return Optional.ofNullable(roomAgeType);
    }
  }

  private RoomAgeType buildRoomAgeType(ResultSet resultSet) throws SQLException {
    return RoomAgeType.builder()
        .id(resultSet.getInt("id"))
        .abbr(resultSet.getString("abbr"))
        .description(resultSet.getString("description"))
        .build();
  }

  @Override
  public RoomAgeType save(RoomAgeType entity) {
    return null;
  }

  @Override
  public void update(RoomAgeType entity) {

  }

  @Override
  public boolean delete(Integer id) {
    return false;
  }
}
