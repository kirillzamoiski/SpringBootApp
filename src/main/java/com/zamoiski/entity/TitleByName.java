package com.zamoiski.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TitleByName implements Serializable {
    private String title;
    private String name;

    public TitleByName(String title, String name) {
        this.title = title;
        this.name = name;
    }
}
