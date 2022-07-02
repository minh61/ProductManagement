package com.ghtk.minhvt27.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ResponseData {
    private Boolean sucess;
    private String message;
    private List<?> data;
}
