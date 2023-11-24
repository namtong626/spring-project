package springbootproject.springboot.contracts.services;

import java.util.List;

import springbootproject.springboot.models.Post;
import springbootproject.springboot.requests.PostRequest;

public interface PostServiceInterface {
    void savePost(PostRequest postRequest);
    
    Post findByJobName(String jobName);

    List<PostRequest> getPostDataList();
    List<PostRequest> getSearchDataList(String keyWord);
}
