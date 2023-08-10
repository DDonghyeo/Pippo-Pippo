package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.message.DisasterMessage;
import com.pippo.ppiyong.domain.message.LatestInfo;
import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.dto.HomePostResponseDto;
import com.pippo.ppiyong.dto.HomeResponseDto;
import com.pippo.ppiyong.repository.LatestInfoRepository;
import com.pippo.ppiyong.repository.PostRepository;
import com.pippo.ppiyong.type.Region;
import com.pippo.ppiyong.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    LatestInfoRepository latestInfoRepository;

    @Override
    public void save(DisasterMessage message) {
        try {
            String msg = message.getMsg();
            postRepository.save(new Post(getType(msg),
                    extractTitle(msg),
                    extractContent(msg),
                    getRegion(message.getLocationName()),
                    getLocalDateTime(message.getCreateDate())));
            latestInfoRepository.save(new LatestInfo(1L, message.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Post> findById(Long id) {
        try {
            return postRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public HomeResponseDto findPosts(Region region){
        try {
            List<HomePostResponseDto> postList = new ArrayList<>(postRepository.findByRegion(region).stream().map(HomePostResponseDto::new).toList());
            postList.addAll(postRepository.findByRegion(Region.ALL).stream().map(HomePostResponseDto::new).toList());
            postList.sort((p1, p2) -> (int) (p1.getId() - p2.getId()));

            List<HomePostResponseDto> weather = new ArrayList<>();
            List<HomePostResponseDto> earthquake = new ArrayList<>();
            List<HomePostResponseDto> civil = new ArrayList<>();
            List<HomePostResponseDto> lost = new ArrayList<>();

            for(HomePostResponseDto post : postList) {
                if(post.getCategory() == Type.RAIN || post.getCategory() == Type.HOT || post.getCategory() == Type.SNOW || post.getCategory() == Type.WIND) {
                    if(weather.size() < 20)
                        weather.add(post);
                } else if(post.getCategory() == Type.EARTHQUAKE) {
                    if(earthquake.size() < 20)
                        earthquake.add(post);
                } else if(post.getCategory() == Type.CIVIL) {
                    if(civil.size() < 20)
                        civil.add(post);
                } else if(post.getCategory() == Type.LOST){
                    if(lost.size() < 20)
                        lost.add(post);
                }
                if(weather.size() + earthquake.size() + civil.size() + lost.size() >= 80)
                    break;
                //기타 넣을까 말까
            }

            return new HomeResponseDto(weather, earthquake, civil, lost);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isUpdated(String id) {
        try {
            Optional<LatestInfo> latestInfo = latestInfoRepository.findById(1L);
            if(latestInfo.isPresent()) {
                if (Long.parseLong(id) > Long.parseLong(latestInfo.get().getLatestId())){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private String extractTitle(String msg) {
        try {
            int startIndex = msg.indexOf("[") + 1;
            int endIndex = msg.indexOf("]");
            return msg.substring(startIndex, endIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String extractContent(String msg) {
        try {
            int startIndex = msg.indexOf("[");
            int endIndex = msg.indexOf("]") + 1;
            String title = msg.substring(startIndex, endIndex);
            return msg.replace(title, "").trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Type getType(String content) {
        try {
            if(content.contains("태풍")) {
                return Type.WIND;
            }
            if(content.contains("호우") || content.contains("많은 비") || content.contains("많은비")) {
                return Type.RAIN;
            }
            if(content.contains("폭염") || content.contains("고온")) {
                return Type.HOT;
            }
            if(content.contains("대설") || content.contains("폭설")) {
                return Type.SNOW;
            }
            if(content.contains("지진") || content.contains("해일")) {
                return Type.EARTHQUAKE;
            }
            if(content.contains("찾습니다") || content.contains("배회") || content.contains("실종")) {
                return Type.LOST;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Region getRegion(String locationName) {
        try {
            if(locationName.contains("서울특별시") && locationName.contains("제주특별자치도")) { // 서울이랑 제주도 있으면 전국이지 않울까..
                return Region.ALL;
            }

            int idx = locationName.indexOf(" ");
            if(idx == -1) {
                idx = locationName.length();
            }
            return Region.fromString(locationName.substring(0, idx));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private LocalDateTime getLocalDateTime(String createDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            return LocalDateTime.parse(createDate, formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
