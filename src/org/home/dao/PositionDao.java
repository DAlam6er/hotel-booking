package org.home.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.home.entity.Position;
import org.home.util.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PositionDao implements Dao<Integer, Position> {
  private static final PositionDao INSTANCE = new PositionDao();
  private static final String FIND_BY_ID_SQL = """
      SELECT id,
            position_name,
            salary
      FROM hotel_bookings.public.position
      WHERE id = ?
      """;

  public static PositionDao getInstance() {
    return INSTANCE;
  }

  @Override
  public List<Position> findAll() {
    return null;
  }

  @Override
  @SneakyThrows
  public Optional<Position> findById(Integer id) {
    try (var connection = ConnectionPool.get()) {
      return findById(id, connection);
    }
  }

  @SneakyThrows
  public Optional<Position> findById(Integer id, Connection connection) {
    try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
      preparedStatement.setInt(1, id);

      var resultSet = preparedStatement.executeQuery();
      Position position = null;
      if (resultSet.next()) {
        position = buildPosition(resultSet);
      }
      return Optional.ofNullable(position);
    }
  }

  private Position buildPosition(ResultSet resultSet) throws SQLException {
    return Position.builder()
        .id(resultSet.getInt("id"))
        .positionName(resultSet.getString("position_name"))
        .salary(resultSet.getBigDecimal("salary"))
        .build();
  }

  @Override
  public Position save(Position entity) {
    return null;
  }

  @Override
  public void update(Position entity) {

  }

  @Override
  public boolean delete(Integer id) {
    return false;
  }
}
