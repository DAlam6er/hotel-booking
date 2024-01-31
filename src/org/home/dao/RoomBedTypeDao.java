package org.home.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.home.entity.RoomBedType;
import org.home.util.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomBedTypeDao implements Dao<Integer, RoomBedType> {
  private static final RoomBedTypeDao INSTANCE = new RoomBedTypeDao();
  private static final String FIND_BY_ID_SQL = """
      SELECT id,
            abbr,
            description
      FROM hotel_bookings.public.room_bed_type
      WHERE id = ?
      """;

  public static RoomBedTypeDao getInstance() {
    return INSTANCE;
  }

  @Override
  public List<RoomBedType> findAll() {
    return null;
  }

  @Override
  @SneakyThrows
  public Optional<RoomBedType> findById(Integer id) {
    try (var connection = ConnectionPool.get()) {
      return findById(id, connection);
    }
  }

  @SneakyThrows
  public Optional<RoomBedType> findById(Integer id, Connection connection) {
    try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
      preparedStatement.setInt(1, id);

      var resultSet = preparedStatement.executeQuery();
      RoomBedType roomBedType = null;
      if (resultSet.next()) {
        roomBedType = buildRoomBedType(resultSet);
      }
      return Optional.ofNullable(roomBedType);
    }
  }

  private RoomBedType buildRoomBedType(ResultSet resultSet) throws SQLException {
    return RoomBedType.builder()
        .id(resultSet.getInt("id"))
        .abbr(resultSet.getString("abbr"))
        .description(resultSet.getString("description"))
        .build();
  }

  @Override
  public RoomBedType save(RoomBedType entity) {
    return null;
  }

  @Override
  public void update(RoomBedType entity) {

  }

  @Override
  public boolean delete(Integer id) {
    return false;
  }
}
