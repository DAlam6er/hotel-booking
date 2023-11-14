package org.home.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.home.entity.Service;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServiceDao implements Dao<Integer, Service> {
  private static final ServiceDao INSTANCE = new ServiceDao();

  public static ServiceDao getInstance() {
    return INSTANCE;
  }

  @Override
  public List<Service> findAll() {
    return null;
  }

  @Override
  public Optional<Service> findById(Integer id) {
    return Optional.empty();
  }

  @Override
  public Service save(Service entity) {
    return null;
  }

  @Override
  public void update(Service entity) {

  }

  @Override
  public boolean delete(Integer id) {
    return false;
  }
}
