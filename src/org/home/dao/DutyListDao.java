package org.home.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.home.entity.DutyList;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DutyListDao implements Dao<Long, DutyList> {
  private static final DutyListDao INSTANCE = new DutyListDao();

  public static DutyListDao getInstance() {
    return INSTANCE;
  }

  @Override
  public List<DutyList> findAll() {
    return null;
  }

  @Override
  public Optional<DutyList> findById(Long id) {
    return Optional.empty();
  }

  @Override
  public DutyList save(DutyList entity) {
    return null;
  }

  @Override
  public void update(DutyList entity) {

  }

  @Override
  public boolean delete(Long id) {
    return false;
  }
}
