package com.lmzqm.Property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

/**
 * Created by lmzqm on 2017/4/25.
 */
@Component
public class BlogProperty {

    @Value("${com.gyenno.blog.name}")
    private String name;

    @Value("${com.gyenno.blog.title}")
    private String title;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
