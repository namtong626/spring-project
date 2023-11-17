package springbootproject.springboot.services;

import springbootproject.springboot.contracts.repositories.PostRepositoryInterface;
import springbootproject.springboot.contracts.repositories.CategoryReponsitoryInterface;
import springbootproject.springboot.contracts.services.PostServiceInterface;
import springbootproject.springboot.models.Post;
import springbootproject.springboot.models.Category;
import springbootproject.springboot.requests.PostRequest;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PostService implements PostServiceInterface {
    protected PostReponsitoryInterface postRepo;
    protected CategoryReponsitoryInterface categoryRepo;
   

    public PostService(
        PostReponsitoryInterface postRepo,
        CategoryReponsitoryInterface categoryRepo
        
    ) {
        this.postRepo = userRepo;
        this.categoryRepo = categoryRepo;
       
    }
    
    @Override
    public void savePost(PostRequest postRequest) {
        Post post = new Post();
        
        post.setJobName(postRequest.getJobName());
        post.setJobDescription(postRequest.getJobDescription());
        post.setDateTime(postRequest.getDateTime());
        post.setCity(postRequest.getCity());
        post.setCompany(postRequest.getCompany());
        post.setSalary(postRequest.getSalary());
        post.setExperience(postRequest.getExperience());
        post.setCertificate(postRequest.getCertificate());
        post.setCategoryName(postRequest.getCategoryName());
        post.setAddress(postRequest.getAddress());
        post.setSex(postRequest.getSex());


        this.postRepo.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        this.postRepo.deleteById(postId);
        assertThat(this.postRepo.count()).isEqualTo(1);
    }


    

    @Override
    public List<PostRequest> getPostDataList() {
        List<Post> posts = this.postRepo.findAll();
        return posts.stream().map((post) -> convertUsers(post))
            .collect(Collectors.toList());
    }

    private PostRequest convertPosts(Post post) {
       PostRequest posts = new PostRequest();
        posts.setId(post.getId());
        posts.setJobName(post.getJobName());
        posts.setJobDescription(user.getJobDescription());
        posts.setDateTime(post.getDateTime());
        posts.setCity(post.getCity());
        posts.setSalary(post.getSalary());
        posts.setCompany(post.getCompany());
        posts.setExperience(post.getExperience());
        posts.setCertificate(post.getCertificate());
        posts.setCategoryName(post.getCategoryName());
        posts.setAddress(post.getAddress());
        posts.setSex(post.getSex());
        
        return posts;
    }

}
