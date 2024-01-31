package org.home.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.home.entity.RoomInteriorType;
import org.home.util.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomInteriorTypeDao implements Dao<Integer, RoomInteriorType> {
  private static final RoomInteriorTypeDao INSTANCE = new RoomInteriorTypeDao();
  private static final String FIND_BY_ID_SQL = """
      SELECT id,
            abbr,
            description
      FROM hotel_bookings.public.room_interior_type
      WHERE id = ?
      """;

  public static RoomInteriorTypeDao getInstance() {
    return INSTANCE;
  }

  @Override
  public List<RoomInteriorType> findAll() {
    return null;
  }

  @Override
  @SneakyThrows
  public Optional<RoomInteriorType> findById(Integer id) {
    try (var connection = ConnectionPool.get()) {
      return findById(id, connection);
    }
  }

  @SneakyThrows
  public Optional<RoomInteriorType> findById(Integer id, Connection connection) {
    try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
      preparedStatement.setInt(1, id);

      var resultSet = preparedStatement.executeQuery();
      RoomInteriorType roomInteriorType = null;
      if (resultSet.next()) {
        roomInteriorType = buildRoomInteriorType(resultSet);
      }
      return Optional.ofNullable(roomInteriorType);
    }
  }

  private RoomInteriorType buildRoomInteriorType(ResultSet resultSet) throws SQLException {
    return RoomInteriorType.builder()
        .id(resultSet.getInt("id"))
        .abbr(resultSet.getString("abbr"))
        .description(resultSet.getString("description"))
        .build();
  }

  @Override
  public RoomInteriorType save(RoomInteriorType entity) {
    return null;
  }

  @Override
  public void update(RoomInteriorType entity) {

  }

  @Override
  public boolean delete(Integer id) {
    return false;
  }
}
