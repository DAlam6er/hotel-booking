package org.home.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.home.entity.Employee;
import org.home.exception.DaoException;
import org.home.util.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeDao implements Dao<Long, Employee> {
  private static final EmployeeDao INSTANCE = new EmployeeDao();
  private final PositionDao positionDao = PositionDao.getInstance();

  private static final String FIND_BY_ID_SQL = """
      SELECT id,
            last_name,
            first_name,
            surname,
            address,
            phone_number,
            position_id
      FROM hotel_bookings.public.employee
      WHERE id = ?
      """;

  public static EmployeeDao getInstance() {
    return INSTANCE;
  }

  @Override
  public List<Employee> findAll() {
    return null;
  }

  @Override
  public Optional<Employee> findById(Long id) {
    try (var connection = ConnectionPool.get()) {
      return findById(id, connection);
    } catch (SQLException ex) {
      throw new DaoException(ex);
    }
  }

  public Optional<Employee> findById(Long id, Connection connection) {
    try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
      preparedStatement.setLong(1, id);

      var resultSet = preparedStatement.executeQuery();
      Employee employee = null;
      if (resultSet.next()) {
        employee = buildEmployee(resultSet);
      }
      return Optional.ofNullable(employee);
    } catch (SQLException ex) {
      throw new DaoException(ex);
    }
  }

  private Employee buildEmployee(ResultSet resultSet) throws SQLException {
    var connection = resultSet.getStatement().getConnection();

    var positionId = resultSet.getInt("position_id");
    var position = positionDao.findById(positionId, connection).orElse(null);

    return Employee.builder()
        .id(resultSet.getLong("id"))
        .lastName(resultSet.getString("last_name"))
        .firstName(resultSet.getString("first_name"))
        .surname(resultSet.getString("surname"))
        .address(resultSet.getString("address"))
        .phoneNumber(resultSet.getString("phone_number"))
        .position(position)
        .build();
  }

  @Override
  public Employee save(Employee entity) {
    return null;
  }

  @Override
  public void update(Employee entity) {

  }

  @Override
  public boolean delete(Long id) {
    return false;
  }
}
