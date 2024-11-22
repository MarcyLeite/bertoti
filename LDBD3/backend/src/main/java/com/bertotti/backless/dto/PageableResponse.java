package com.bertotti.backless.dto;

import java.util.List;

public interface PageableResponse<T> {
    public Integer getTotal();
    public List<T> getPage();
}
