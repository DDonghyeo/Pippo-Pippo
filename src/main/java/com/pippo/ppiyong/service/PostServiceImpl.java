package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.DisasterMessage;
import com.pippo.ppiyong.domain.LatestInfo;
import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.repository.LatestInfoRepository;
import com.pippo.ppiyong.repository.PostRepository;
import com.pippo.ppiyong.type.Region;
import com.pippo.ppiyong.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    LatestInfoRepository latestInfoRepository;

    public void save(DisasterMessage message) {
        try {
            String msg = message.getMsg();
            postRepository.save(new Post(getType(msg), extractTitle(msg), extractContent(msg), getRegion(message.getLocationName())));
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
            if(content.contains("호우")) {
                return Type.RAIN;
            }
            if(content.contains("폭염") || content.contains("고온")) {
                return Type.HOT;
            }
            if(content.contains("태풍")) {
                return Type.WIND;
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
}
