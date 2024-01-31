package org.home.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.home.entity.RoomAllocation;
import org.home.util.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomAllocationDao implements Dao<Long, RoomAllocation> {
  private static final RoomAllocationDao INSTANCE = new RoomAllocationDao();
  private final RoomDao roomDao = RoomDao.getInstance();
  private final GuestDao guestDao = GuestDao.getInstance();
  private static final String FIND_BY_GUEST_ID_SQL = """
      SELECT guest_id,
             room
      FROM hotel_bookings.public.room_allocation
      WHERE guest_id = ?
      """;

  private static final String FIND_BY_ROOM_ID_SQL = """
      SELECT guest_id,
             room
      FROM hotel_bookings.public.room_allocation
      WHERE room = ?
      """;

  public static RoomAllocationDao getInstance() {
    return INSTANCE;
  }

  @Override
  public List<RoomAllocation> findAll() {
    return null;
  }

  @Override
  public Optional<RoomAllocation> findById(Long id) {
    return Optional.empty();
  }

  @SneakyThrows
  public Optional<RoomAllocation> findByGuestId(Long guestId) {
    try (var connection = ConnectionPool.get();
         var preparedStatement = connection.prepareStatement(FIND_BY_GUEST_ID_SQL)) {
      preparedStatement.setLong(1, guestId);
      return findById(preparedStatement);
    }
  }

  @SneakyThrows
  public Optional<RoomAllocation> findByRoomId(String roomId) {
    try (var connection = ConnectionPool.get();
         var preparedStatement = connection.prepareStatement(FIND_BY_ROOM_ID_SQL)) {
      preparedStatement.setString(1, roomId);
      return findById(preparedStatement);
    }
  }

  @SneakyThrows
  private Optional<RoomAllocation> findById(PreparedStatement preparedStatement) {
    var resultSet = preparedStatement.executeQuery();
    RoomAllocation roomAllocation = null;
    if (resultSet.next()) {
      roomAllocation = buildRoomAllocation(resultSet);
    }
    return Optional.ofNullable(roomAllocation);
  }

  private RoomAllocation buildRoomAllocation(ResultSet resultSet) throws SQLException {
    var connection = resultSet.getStatement().getConnection();

    var guestId = resultSet.getLong("guest_id");
    var roomId = resultSet.getString("room");

    var guest = guestDao.findById(guestId, connection).orElse(null);
    var room = roomDao.findById(roomId, connection).orElse(null);

    return RoomAllocation.builder()
        .room(room)
        .guest(guest)
        .build();
  }

  @Override
  public RoomAllocation save(RoomAllocation entity) {
    return null;
  }

  @Override
  public void update(RoomAllocation entity) {

  }

  @Override
  public boolean delete(Long id) {
    return false;
  }
}
