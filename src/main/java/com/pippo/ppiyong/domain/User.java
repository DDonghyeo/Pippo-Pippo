package com.pippo.ppiyong.domain;

import com.pippo.ppiyong.auth.Authority;
import com.pippo.ppiyong.type.BaseTimeEntity;
import com.pippo.ppiyong.type.Category;
import com.pippo.ppiyong.type.Region;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor//(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "User")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String nickName;

    @Enumerated(EnumType.STRING)
    private Region region;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ElementCollection(targetClass = Category.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_sub_categories", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "sub_category")
    private List<Category> sub_categories;

    public User(String email, String password, String nickName, Region region) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.region = region;
    }

    public User encodePassword(PasswordEncoder passwordEncoder){
        password = passwordEncoder.encode(this.password);
        return this;
    }

    public void updatePassword(PasswordEncoder passwordEncoder, String password){
        this.password = passwordEncoder.encode(password);
    }

    public boolean matchPassword(PasswordEncoder passwordEncoder, String checkPassword){
        return passwordEncoder.matches(checkPassword, getPassword());
    }

    public void updateNickName(String nickName) {
        this.nickName = nickName;
    }

    public void updateRegion(String region) {
        this.region = Region.fromString(region);
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public void setSub_categories(List<Category> subCategories) {
        this.sub_categories = subCategories;
    }

}
