package com.pippo.ppiyong.domain.post;

import com.pippo.ppiyong.type.BaseTimeEntity;
import com.pippo.ppiyong.type.Region;
import com.pippo.ppiyong.type.Type;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Post")
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    //재난 종류
    @Enumerated(EnumType.STRING)
    private Type type;

    private String title;

    private String content;

    private Integer views;

    @Enumerated(EnumType.STRING)
    private Region region;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;

    public Post (Type type, String title, String content, Region region, LocalDateTime createDate) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.region = region;
    }

}
