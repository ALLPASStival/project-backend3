package com.itstime.allpasstival.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itstime.allpasstival.enums.PostCategory;
import com.itstime.allpasstival.enums.ResponseState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private Long likes;

    @Enumerated(EnumType.STRING)
    private ResponseState state;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "festival_id")
    @JsonIgnore
    private Festival festival;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<LikedPost> likedPosts  = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<Comment> comments  = new ArrayList<>();

    public void changeState(ResponseState responseState){
        this.state = responseState;
    }
    public void changeLike(Long like){
        this.likes =like;
    }
}
