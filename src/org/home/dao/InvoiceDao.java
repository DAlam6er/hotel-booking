package org.home.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.home.entity.Invoice;
import org.home.exception.DaoException;
import org.home.util.ConnectionPool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InvoiceDao implements Dao<Long, Invoice> {
  private static final InvoiceDao INSTANCE = new InvoiceDao();
  private final GuestDao guestDao = GuestDao.getInstance();
  private final PaymentStatusDao paymentStatusDao = PaymentStatusDao.getInstance();
  private static final String FIND_ALL_SQL = """
      SELECT id,
            guest_id,
            payment_status
      FROM hotel_bookings.public.invoice
      """;

  public static InvoiceDao getInstance() {
    return INSTANCE;
  }

  @Override
  public List<Invoice> findAll() {
    List<Invoice> invoices = new ArrayList<>();
    try (var connection = ConnectionPool.get();
         var statement = connection.prepareStatement(FIND_ALL_SQL)) {
      var resultSet = statement.executeQuery();
      while (resultSet.next()) {
        invoices.add(buildInvoice(resultSet));
      }
    } catch (SQLException ex) {
      throw new DaoException(ex);
    }
    return invoices;
  }

  private Invoice buildInvoice(ResultSet resultSet) throws SQLException {
    var connection = resultSet.getStatement().getConnection();

    var guestId = resultSet.getLong("guest_id");
    var paymentStatusId = resultSet.getInt("payment_status");

    var guest = guestDao.findById(guestId, connection).orElse(null);
    var paymentStatus = paymentStatusDao.findById(paymentStatusId, connection).orElse(null);
    return Invoice.builder()
        .id(resultSet.getLong("id"))
        .guest(guest)
        .paymentStatus(paymentStatus)
        .build();
  }

  @Override
  public Optional<Invoice> findById(Long id) {
    return Optional.empty();
  }

  @Override
  public Invoice save(Invoice entity) {
    return null;
  }

  @Override
  public void update(Invoice entity) {

  }

  @Override
  public boolean delete(Long id) {
    return false;
  }
}
