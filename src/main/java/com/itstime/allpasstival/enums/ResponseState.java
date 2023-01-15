package com.itstime.allpasstival.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseState {
    done("done"),
    onGoing("onGoing");
    private String state;
}
