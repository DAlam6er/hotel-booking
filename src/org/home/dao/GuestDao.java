package org.home.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.home.entity.Gender;
import org.home.entity.Guest;
import org.home.entity.MaritalStatus;
import org.home.exception.DaoException;
import org.home.util.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GuestDao implements Dao<Long, Guest> {
  private static final GuestDao INSTANCE = new GuestDao();
  private static final String FIND_BY_ID_SQL = """
      SELECT id,
            last_name,
            first_name,
            surname,
            birthday,
            gender,
            address,
            pass_no,
            marital_status,
            check_in,
            check_out,
            duration_of_stay
      FROM hotel_bookings.public.guest
      WHERE id = ?
      """;

  public static GuestDao getInstance() {
    return INSTANCE;
  }

  @Override
  public List<Guest> findAll() {
    return null;
  }

  @Override
  public Optional<Guest> findById(Long id) {
    try (var connection = ConnectionPool.get()) {
      return findById(id, connection);
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  public Optional<Guest> findById(Long id, Connection connection) {
    try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
      preparedStatement.setLong(1, id);

      var resultSet = preparedStatement.executeQuery();
      Guest guest = null;
      if (resultSet.next()) {
        guest = buildGuest(resultSet);
      }
      return Optional.ofNullable(guest);
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  private Guest buildGuest(ResultSet resultSet) throws SQLException {
    return Guest.builder()
        .id(resultSet.getLong("id"))
        .lastName(resultSet.getString("last_name"))
        .firstName(resultSet.getString("first_name"))
        .surname(resultSet.getString("surname"))
        .birthday(resultSet.getDate("birthday").toLocalDate())
        .gender(Gender.valueOf(resultSet.getString("gender")))
        .address(resultSet.getString("address"))
        .passNo(resultSet.getString("pass_no"))
        .maritalStatus(MaritalStatus.valueOf(resultSet.getString("marital_status")))
        .checkIn(resultSet.getTimestamp("check_in").toLocalDateTime())
        .checkOut(resultSet.getTimestamp("check_out").toLocalDateTime())
        .durationOfStay((int) resultSet.getShort("duration_of_stay"))
        .build();
  }

  @Override
  public Guest save(Guest entity) {
    return null;
  }

  @Override
  public void update(Guest entity) {

  }

  @Override
  public boolean delete(Long id) {
    return false;
  }
}
