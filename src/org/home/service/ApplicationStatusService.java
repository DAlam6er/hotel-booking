package org.home.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.home.dao.ApplicationStatusDao;
import org.home.dto.ApplicationStatusDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationStatusService {
  private static final ApplicationStatusService INSTANCE = new ApplicationStatusService();
  private final ApplicationStatusDao applicationStatusDao = ApplicationStatusDao.getInstance();

  public static ApplicationStatusService getInstance() {
    return INSTANCE;
  }

  public List<ApplicationStatusDto> findAll() {
    return applicationStatusDao.findAll().stream()
        .map(booking -> new ApplicationStatusDto(booking.getId(), booking.getStatus()))
        .collect(toList());
  }
}
