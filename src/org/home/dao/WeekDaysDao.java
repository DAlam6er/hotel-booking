package org.home.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.home.entity.WeekDays;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WeekDaysDao implements Dao<Integer, WeekDays> {
  private static final WeekDaysDao INSTANCE = new WeekDaysDao();

  public static WeekDaysDao getInstance() {
    return INSTANCE;
  }

  @Override
  public List<WeekDays> findAll() {
    return null;
  }

  @Override
  public Optional<WeekDays> findById(Integer id) {
    return Optional.empty();
  }

  @Override
  public WeekDays save(WeekDays entity) {
    return null;
  }

  @Override
  public void update(WeekDays entity) {

  }

  @Override
  public boolean delete(Integer id) {
    return false;
  }
}
