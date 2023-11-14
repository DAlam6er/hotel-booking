package org.home.exception;

public class DaoException extends RuntimeException {
  public DaoException(Throwable throwable) {
    super(throwable);
  }
}
