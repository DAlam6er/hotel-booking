package org.home.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.home.entity.ServiceProvided;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServiceProvidedDao implements Dao<Long, ServiceProvided> {
  private static final ServiceProvidedDao INSTANCE = new ServiceProvidedDao();

  public static ServiceProvidedDao getInstance() {
    return INSTANCE;
  }

  @Override
  public List<ServiceProvided> findAll() {
    return null;
  }

  @Override
  public Optional<ServiceProvided> findById(Long id) {
    return Optional.empty();
  }

  @Override
  public ServiceProvided save(ServiceProvided entity) {
    return null;
  }

  @Override
  public void update(ServiceProvided entity) {

  }

  @Override
  public boolean delete(Long id) {
    return false;
  }
}
