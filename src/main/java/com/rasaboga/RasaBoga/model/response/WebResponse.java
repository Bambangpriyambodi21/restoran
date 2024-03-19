package com.rasaboga.RasaBoga.model.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WebResponse<T> {
    private String status;
    private String message;
    private PagingResponse paging;
    private T data;
}
