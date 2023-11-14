package org.home.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.home.entity.RoomService;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomServiceDao implements Dao<Long, RoomService> {
  private static final RoomServiceDao INSTANCE = new RoomServiceDao();

  public static RoomServiceDao getInstance() {
    return INSTANCE;
  }

  @Override
  public List<RoomService> findAll() {
    return null;
  }

  @Override
  public Optional<RoomService> findById(Long id) {
    return Optional.empty();
  }

  @Override
  public RoomService save(RoomService entity) {
    return null;
  }

  @Override
  public void update(RoomService entity) {

  }

  @Override
  public boolean delete(Long id) {
    return false;
  }
}
