package org.home.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.home.entity.ApplicationStatus;
import org.home.util.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationStatusDao implements Dao<Integer, ApplicationStatus> {
  private static final ApplicationStatusDao INSTANCE = new ApplicationStatusDao();
  private static final String FIND_ALL_SQL = """
      SELECT id,
             status
      FROM hotel_bookings.public.application_status
      """;
  private static final String FIND_BY_ID_SQL = """
      SELECT id,
            status
      FROM hotel_bookings.public.application_status
      WHERE id = ?
      """;

  public static ApplicationStatusDao getInstance() {
    return INSTANCE;
  }

  @Override
  @SneakyThrows
  public List<ApplicationStatus> findAll() {
    List<ApplicationStatus> applicationStatuses = new ArrayList<>();
    try (var connection = ConnectionPool.get();
         var statement = connection.prepareStatement(FIND_ALL_SQL)) {
      var resultSet = statement.executeQuery();
      while (resultSet.next()) {
        applicationStatuses.add(buildApplicationStatus(resultSet));
      }
    }
    return applicationStatuses;
  }

  @Override
  @SneakyThrows
  public Optional<ApplicationStatus> findById(Integer id) {
    try (var connection = ConnectionPool.get()) {
      return findById(id, connection);
    }
  }

  @SneakyThrows
  public Optional<ApplicationStatus> findById(Integer id, Connection connection) {
    try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
      preparedStatement.setInt(1, id);

      var resultSet = preparedStatement.executeQuery();
      ApplicationStatus applicationStatus = null;
      if (resultSet.next()) {
        applicationStatus = buildApplicationStatus(resultSet);
      }
      return Optional.ofNullable(applicationStatus);
    }
  }

  private ApplicationStatus buildApplicationStatus(ResultSet resultSet) throws SQLException {
    return ApplicationStatus.builder()
        .id(resultSet.getInt("id"))
        .status(resultSet.getString("status"))
        .build();
  }

  @Override
  public ApplicationStatus save(ApplicationStatus entity) {
    return null;
  }

  @Override
  public void update(ApplicationStatus entity) {

  }

  @Override
  public boolean delete(Integer id) {
    return false;
  }
}
