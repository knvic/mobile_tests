package com.demoqa.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookModel {
    String isbn;
    String title;
    String subTitle;
    String author;
    @JsonProperty("publish_date")
    String publishDate;
    String publisher;
    String description;
    String website;

    int pages;
}
