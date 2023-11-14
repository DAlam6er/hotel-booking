package org.home.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.home.entity.Employee;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeDao implements Dao<Long, Employee> {
  private static final EmployeeDao INSTANCE = new EmployeeDao();

  public static EmployeeDao getInstance() {
    return INSTANCE;
  }

  @Override
  public List<Employee> findAll() {
    return null;
  }

  @Override
  public Optional<Employee> findById(Long id) {
    return Optional.empty();
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
