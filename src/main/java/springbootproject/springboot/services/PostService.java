package springbootproject.springboot.services;

import springbootproject.springboot.contracts.repositories.CategoryRepositoryInterface;
import springbootproject.springboot.contracts.repositories.PostRepositoryInterface;
import springbootproject.springboot.contracts.services.PostServiceInterface;
import springbootproject.springboot.models.Post;
import springbootproject.springboot.models.Category;
import springbootproject.springboot.requests.PostRequest;
import java.util.*;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;

@Service
public class PostService implements PostServiceInterface {
    protected PostRepositoryInterface postRepo;
    protected CategoryRepositoryInterface cateRepo;


    public PostService(
        PostRepositoryInterface postRepo,
        CategoryRepositoryInterface cateRepo

    
    ) {
        this.postRepo = userRepo;
        this.cateRepo = roleRepo;
    
    }
    
    @Override
    public void savePost(PostRequest postRequest) {
        Post post = new Post();
        
        post.setTitle(postRequest.getTitle());
        post.setDateTime(postRequest.getDateTime());

        String title = postRequest.getTitle();
        String slug = title.replace(" ", "-");
        post.setPostImage(postRequest.getPostImage());
        post.setCategoryName(postRequest.getCategoryName());
        post.setContent(postRequest.getContent());
        this.postRepo.save(post);
    }

    @Override
    public void deletePost(Long id) {
      
         this.postRepo.delete(id);
          
    }

   

  

    @Override
    public List<PostRequest> getPostDataList() {
        List<post> post = this.postRepo.findAll();
        return posts.stream().map((post) -> convertPosts(post))
            .collect(Collectors.toList());
    }

    private PostRequest convertPosts(Post post) {
        PostRequest posts = new PostRequest();
        posts.setId(post.getId());
        posts.setTitlename(post.getTitle());
        posts.setSlug(post.getSlug());
        posts.setDateTime(post.getDateTime());
        posts.setPostImage(post.getPostImage());
        posts.setCategoryName(post.getCategoryName());
        posts.setContent(post.getContent());

        return posts;
    }


    @Override
    public List<PostRequest> getSearchDataList(String keyWord) {
        List<Post> posts = new ArrayList<>();
        if (keyWord == null || keyWord.isEmpty() || keyWord.isBlank()) {
            users = this.postRepo.findAll();
        } else {
            users = this.postRepo.search(keyWord);
        }
        List<Post> dataSearch = new ArrayList<>();
        for (Post post : posts) {
           Post data = Post.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .slug(post.getSlug())
                    .dateTime(post.getDateTime())
                    .categoryName(post.categoryName())
                    .content(post.getContent())
                    .build();
            dataSearch.add(data);
        }
        return dataSearch;
    }

}
