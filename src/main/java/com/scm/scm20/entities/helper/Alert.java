package com.scm.scm20.entities.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Alert {
    private String content;
    @Builder.Default
    private Severity severity = Severity.blue;
}
