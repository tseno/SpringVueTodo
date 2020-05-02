package com.example.demo.entity;

import lombok.Value;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Value
public class Task {
    Long id;
    String title;
    String dueDate;

    String getDueDateString() {
        // Z時間をデフォルトゾーンに変換して、"yyyy-MM-dd"形式の文字列にする
        return OffsetDateTime.ofInstant(OffsetDateTime.parse(dueDate).toInstant(), ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    Boolean checked;
}
