package com.itstime.allpasstival.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itstime.allpasstival.enums.PostCategory;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @Column(columnDefinition = "TINYTEXT")
    private String title;
    @Column(columnDefinition = "TEXT")
    private String articleContent;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private PostCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "festival_id", nullable = false)
    @JsonIgnore
    private Festival festival;
}
