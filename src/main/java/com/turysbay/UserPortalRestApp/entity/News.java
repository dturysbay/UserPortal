package com.turysbay.UserPortalRestApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "news")
@Getter
@Setter
public class News extends BaseEntity{
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Content is required")
    private String content;

    @Column(name = "user_id")
    private Long createdBy;
}
