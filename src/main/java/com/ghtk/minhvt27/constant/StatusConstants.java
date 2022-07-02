package com.ghtk.minhvt27.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusConstants {
    ACTIVE(1),
    INACTIVE(0);

    private int status;

}
