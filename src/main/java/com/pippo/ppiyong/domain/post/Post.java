package com.pippo.ppiyong.domain.post;

import com.pippo.ppiyong.type.BaseTimeEntity;
import com.pippo.ppiyong.type.Region;
import com.pippo.ppiyong.type.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "Post")
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //재난 종류
    @Enumerated(EnumType.STRING)
    private Type type;

    private String title;

    private String content;

    private Integer views;

    @Enumerated(EnumType.STRING)
    private Region region;

}
