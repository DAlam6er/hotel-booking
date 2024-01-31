package org.home.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.home.entity.PaymentStatus;
import org.home.util.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentStatusDao implements Dao<Integer, PaymentStatus> {
  private static final PaymentStatusDao INSTANCE = new PaymentStatusDao();
  private static final String FIND_BY_ID_SQL = """
      SELECT id,
            status
      FROM hotel_bookings.public.payment_status
      WHERE id = ?
      """;

  public static PaymentStatusDao getInstance() {
    return INSTANCE;
  }

  @Override
  public List<PaymentStatus> findAll() {
    return null;
  }

  @Override
  @SneakyThrows
  public Optional<PaymentStatus> findById(Integer id) {
    try (var connection = ConnectionPool.get()) {
      return findById(id, connection);
    }
  }

  @SneakyThrows
  public Optional<PaymentStatus> findById(Integer id, Connection connection) {
    try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
      preparedStatement.setInt(1, id);

      var resultSet = preparedStatement.executeQuery();
      PaymentStatus paymentStatus = null;
      if (resultSet.next()) {
        paymentStatus = buildPaymentStatus(resultSet);
      }
      return Optional.ofNullable(paymentStatus);
    }
  }

  private PaymentStatus buildPaymentStatus(ResultSet resultSet) throws SQLException {
    return PaymentStatus.builder()
        .id(resultSet.getInt("id"))
        .status(resultSet.getString("status"))
        .build();
  }

  @Override
  public PaymentStatus save(PaymentStatus entity) {
    return null;
  }

  @Override
  public void update(PaymentStatus entity) {

  }

  @Override
  public boolean delete(Integer id) {
    return false;
  }
}
