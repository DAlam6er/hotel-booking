package org.home.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.home.entity.Room;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomDao implements Dao<String, Room> {
  private static final RoomDao INSTANCE = new RoomDao();

  public static RoomDao getInstance() {
    return INSTANCE;
  }

  @Override
  public List<Room> findAll() {
    return null;
  }

  @Override
  public Optional<Room> findById(String id) {
    return Optional.empty();
  }

  @Override
  public Room save(Room entity) {
    return null;
  }

  @Override
  public void update(Room entity) {

  }

  @Override
  public boolean delete(String id) {
    return false;
  }
}
