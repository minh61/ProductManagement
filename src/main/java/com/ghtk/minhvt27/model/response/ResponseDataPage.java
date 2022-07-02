package com.ghtk.minhvt27.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ResponseDataPage {
    private Boolean sucess;
    private String message;
    private List<?> data;
    private Pagination pagination;
}
