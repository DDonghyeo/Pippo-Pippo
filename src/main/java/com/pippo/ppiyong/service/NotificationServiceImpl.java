package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.SubCategory;
import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.dto.CategoryResponseDto;
import com.pippo.ppiyong.dto.NotificationResponseDto;
import com.pippo.ppiyong.dto.RegionResponseDto;
import com.pippo.ppiyong.repository.NotificationRepository;
import com.pippo.ppiyong.repository.PostRepository;
import com.pippo.ppiyong.repository.UserRepository;
import com.pippo.ppiyong.type.Category;
import com.pippo.ppiyong.type.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public List<NotificationResponseDto> findAllByRegion(Region region) {
        // 게시물 가져오기
        List<Post> postList = notificationRepository.findAllByRegion(region);
        return postList.stream().map(NotificationResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public List<NotificationResponseDto> findAllByRegionAndCategory(User user) {
        Region userRegion = user.getRegion();
        List<SubCategory> userCategory = user.getSubCategories();

        List<Post> postList = new ArrayList<>();

        for (SubCategory subCategory : userCategory) {
            List<Post> postsForCategory = notificationRepository.findAllByRegionAndCategory(userRegion, subCategory.getCategory());
            postList.addAll(postsForCategory);
        }

        return postList.stream().map(NotificationResponseDto::new).collect(Collectors.toList());
    }


    // 알림 지역 조회
    @Override
    public List<RegionResponseDto> findAllRegions(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElse(null);
        if (user == null) {
            // 유효하지 않은 사용자 처리 로직
            return Collections.emptyList();
        }

        Region userRegion = user.getRegion();
        if (userRegion != null) {
            List<RegionResponseDto> regionResponseList = new ArrayList<>();
            regionResponseList.add(new RegionResponseDto(userRegion)); // 이 부분 수정
            return regionResponseList;
        }

        return Collections.emptyList();
    }

    @Override
    public RegionResponseDto getUserRegionByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null && user.getRegion() != null) {
            return new RegionResponseDto(user.getRegion());
        }
        return null;
    }

    @Override
    public CategoryResponseDto getUserCategoryByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null && user.getSubCategories() != null) {
            List<SubCategory> subCategories = user.getSubCategories();
            int weather = 0;
            int earthquake = 0;
            int civil = 0;
            int lost = 0;
            for (SubCategory subCategory : subCategories) {
                if (subCategory.getCategory().equals(Category.WEATHER)) {
                    weather = 1;
                }
                if (subCategory.getCategory().equals(Category.EARTHQUAKE)) {
                    earthquake = 1;
                }
                if (subCategory.getCategory().equals(Category.CIVIL)) {
                    civil = 1;
                }
                if (subCategory.getCategory().equals(Category.LOST)) {
                    lost = 1;
                }
            }

            return  new CategoryResponseDto(weather, earthquake, civil, lost);
        }

        return null;
    }

    @Override
    public void updateUserRegion(User user, Region region) {
        user.updateRegion(String.valueOf(region));
        userRepository.save(user);
    }

    @Override
    public List<NotificationResponseDto> findAllByRegionAndSingleCategory(Region region, Category category) {
        List<Post> posts = notificationRepository.findAllByRegionAndCategory(region, category);
        return posts.stream()
                .map(NotificationResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationResponseDto> findAllByRegionAndCategories(Region region, List<Category> categories) {
        List<Post> allPosts = new ArrayList<>();

        for (Category category : categories) {
            List<Post> posts = notificationRepository.findAllByRegionAndCategory(region, category);
            allPosts.addAll(posts);
        }

        return allPosts.stream()
                .map(NotificationResponseDto::new)
                .collect(Collectors.toList());
    }

}
