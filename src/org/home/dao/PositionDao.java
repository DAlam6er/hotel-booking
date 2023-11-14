package org.home.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.home.entity.Position;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PositionDao implements Dao<Integer, Position> {
  private static final PositionDao INSTANCE = new PositionDao();

  public static PositionDao getInstance() {
    return INSTANCE;
  }

  @Override
  public List<Position> findAll() {
    return null;
  }

  @Override
  public Optional<Position> findById(Integer id) {
    return Optional.empty();
  }

  @Override
  public Position save(Position entity) {
    return null;
  }

  @Override
  public void update(Position entity) {

  }

  @Override
  public boolean delete(Integer id) {
    return false;
  }
}
