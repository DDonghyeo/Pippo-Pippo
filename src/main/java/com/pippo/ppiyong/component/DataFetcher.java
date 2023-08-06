package com.pippo.ppiyong.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pippo.ppiyong.domain.DisasterMessage;
import com.pippo.ppiyong.domain.DisasterMsgResponse;
import com.pippo.ppiyong.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class DataFetcher {

    @Autowired
    PostServiceImpl postService;

    @Scheduled(fixedRate = 10000) // 10초마다 실행
    public void fetchData() {
        try {
            //System.out.println("start");
            WebClient webClient = WebClient.create();
            String url = "http://apis.data.go.kr/1741000/DisasterMsg3/getDisasterMsg1List?ServiceKey=pCmgf8OhRjkmZmoR6CM59bF/iBMCSqQMTyoDW9gNHTF4jVD5nTjnDQqW8SP32z0hyhPvEXa5MesVDkPcaOwKfw==&type=json&pageNo=1&numOfRows=10";

            String data = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            //System.out.println(data);

            ObjectMapper objectMapper = new ObjectMapper();
            List<DisasterMessage> messages = objectMapper.readValue(extractRow(data), DisasterMsgResponse.class).getRow();

            for(int i = messages.size() - 1; i >= 0; i--) {
                DisasterMessage message = messages.get(i);
                //System.out.println(message.getId());
                if(postService.isUpdated(message.getId())){
                    postService.save(message);
                    System.out.println("uploaded new post: " + message.getId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String extractRow(String data) {
        try {
            int startIndex = data.indexOf("{\"row\"");
            int endIndex = data.length() - 2;
            return data.substring(startIndex, endIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
