package org.home.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.home.entity.RoomAllocation;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomAllocationDao implements Dao<Long, RoomAllocation> {
  private static final RoomAllocationDao INSTANCE = new RoomAllocationDao();

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
