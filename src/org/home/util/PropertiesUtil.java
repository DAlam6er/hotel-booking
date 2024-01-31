package org.home.util;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.Properties;

@UtilityClass
public final class PropertiesUtil {
  private static final Properties PROPERTIES = new Properties();

  static {
    loadProperties();
  }

  public static String get(String key) {
    return PROPERTIES.getProperty(key);
  }

  private static void loadProperties() {
    try (var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
      PROPERTIES.load(inputStream);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
