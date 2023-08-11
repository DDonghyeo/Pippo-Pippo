package com.pippo.ppiyong.domain.post;

import com.pippo.ppiyong.type.BaseTimeEntity;
import com.pippo.ppiyong.type.Category;
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

    @Enumerated(EnumType.STRING)
    private Category category;

    private String title;

    private String content;

    private Integer views;

    @Enumerated(EnumType.STRING)
    private Region region;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;

    public Post (Type type, String title, String content, Region region) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.region = region;
        if(type != null) {
            if(type.equals(Type.HOT) || type.equals(Type.RAIN) || type.equals(Type.WIND) || type.equals(Type.SNOW)) {
                this.category = Category.WEATHER;
            } else if(type.equals(Type.EARTHQUAKE)) {
                this.category = Category.EARTHQUAKE;
            } else if(type.equals(Type.CIVIL)) {
                this.category = Category.CIVIL;
            } else if(type.equals(Type.LOST)) {
                this.category = Category.LOST;
            }
        }
    }
}
