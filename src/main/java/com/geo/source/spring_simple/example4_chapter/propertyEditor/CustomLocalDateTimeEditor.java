package com.geo.source.spring_simple.example4_chapter.propertyEditor;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * LocalDateTime 配置器
 *
 * @author YanZhen
 * @since 2019-05-24 13:09
 */
public class CustomLocalDateTimeEditor extends PropertyEditorSupport {
  private final String format;
  private final boolean allowEmpty;
  private final int exactDateLength;

  public CustomLocalDateTimeEditor(String format) {
    this.format = format;
    this.allowEmpty = false;
    this.exactDateLength = -1;
  }

  public CustomLocalDateTimeEditor(String format, boolean allowEmpty) {
    this.format = format;
    this.allowEmpty = allowEmpty;
    this.exactDateLength = -1;
  }

  public CustomLocalDateTimeEditor(String format, boolean allowEmpty, int exactDateLength) {
    this.format = format;
    this.allowEmpty = allowEmpty;
    this.exactDateLength = exactDateLength;
  }

  @Override
  public String getAsText() {
    final LocalDateTime localDateTime = (LocalDateTime) getValue();
    if (localDateTime == null) {
      return "";
    }
    return localDateTime.toString();
  }

  @Override
  public void setAsText(@Nullable String text) throws IllegalArgumentException {
    if (this.allowEmpty && !StringUtils.hasText(text)) {
      // Treat empty String as null value.
      setValue(null);
    } else if (text != null && this.exactDateLength >= 0 && text.length() != this.exactDateLength) {
      throw new IllegalArgumentException(
              "Could not parse date: it is not exactly" + this.exactDateLength + "characters long");
    } else {
      if (text != null) {
        setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern(format)));// 以什么格式解析
      }
    }
  }
}
