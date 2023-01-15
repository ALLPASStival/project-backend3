package com.itstime.allpasstival.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PostCategory {
    review("review"),
    recruit("recruit"),
    free("free"),
    service("service");
    private String category;
}
