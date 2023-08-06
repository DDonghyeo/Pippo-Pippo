package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.DisasterMessage;
import com.pippo.ppiyong.domain.LatestInfo;
import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.repository.LatestInfoRepository;
import com.pippo.ppiyong.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
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
            postRepository.save(new Post(null, extractTitle(msg), extractContent(msg), null));
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

    private void setType(String content) {
        try {
            //content.contains
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
