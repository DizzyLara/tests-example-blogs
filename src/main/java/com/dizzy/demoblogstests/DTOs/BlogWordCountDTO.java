package com.dizzy.demoblogstests.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogWordCountDTO {
    private Long id;
    private String title;
    private String subtitle;
    private int wordCount;
}
