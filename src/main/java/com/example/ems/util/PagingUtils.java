package com.example.ems.util;

import org.springframework.stereotype.Component;

@Component
public class PagingUtils {

    private final Integer defaultPageLength = 10;

    public Integer getNextPage(Integer page) {
        if (page == null) {
            page = 0;
        } else if (page > 0) {
            page = page - 1;
        }
        return page;
    }

    public Integer getPageLength(Integer pageLength) {
        if (pageLength == null) {
            pageLength = defaultPageLength;
        }
        return pageLength;
    }
}
