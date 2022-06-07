package com.springboot.blog.payload;

import com.springboot.blog.Dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//Razan Yassin
//1182226
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private List<PostDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
