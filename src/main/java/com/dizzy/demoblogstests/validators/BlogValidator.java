package com.dizzy.demoblogstests.validators;

import com.dizzy.demoblogstests.entities.Blog;

public class BlogValidator {


    public boolean isValid(Blog blog) {
        boolean titleIsValid = validateTitleLength(blog.getTitle());
        boolean subtitleIsValid = validateSubTitleLength(blog.getSubtitle());
        boolean authorIsNotNull = blog.getAuthor() != null;
        return titleIsValid && subtitleIsValid && authorIsNotNull;
    }

    private boolean validateTitleLength(String title) {
        return title.length() > 8 && title.length() < 65;
    }

    private boolean validateSubTitleLength(String subtitle) {
        return subtitle.length() < 120;
    }
}
