package springbootproject.springboot.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import springbootproject.springboot.contracts.services.PostServiceInterface;
import springbootproject.springboot.models.Post;
import springbootproject.springboot.requests.PostRequest;

@Controller
public class PostController {
    
    protected PostService postService;;

    public PostController(PostServiceInterface postService) {
        this.postService = postService;
    }


    @GetMapping("/post/")
    public String index(Model model) {
        PostRequest post = new PostRequest();
        model.addAttribute("post", post);

        return "/post/show";
    }
    

    
  

     @PostMapping("/post/create")
    public ResponseEntity<String> createPost(
        @Valid @ModelAttribute("post") PostRequest postRequest,
        BindingResult result,
        Model model
    ) {
       

        if (result.hasErrors()) {
            model.addAttribute("post", postRequest);
            return ResponseEntity.ok("error data");
		}

		this.postService.savePost(postRequest);

        return ResponseEntity.ok("create successfully");
    }


    @PostMapping("/post/save")
    public ResponseEntity<String> save(
        @Valid @ModelAttribute("savePost") PostRequest postRequest,
        BindingResult result,
        Model model
    ) {
       

        if (result.hasErrors()) {
            model.addAttribute("post", postRequest);
            return ResponseEntity.ok("error data");
		}

		this.postService.savePost(postRequest);

        return ResponseEntity.ok("save successfully");
    }

    @PostMapping("post/delete")
    public ResponseEntity<String> delete(
        @Valid @ModelAttribute("deletePost") PostRequest postRequest,
        BindingResult result,
        Model model
    ) {
       

        if (result.hasErrors()) {
            model.addAttribute("post", postRequest);
            return ResponseEntity.ok("error data");
		}

		this.postService.deletePost(postRequest);

        return ResponseEntity.ok("delete successfully");
    }

    @GetMapping("/post/loadPost")
    public String getPostDataList(Model model) {
        List<Post> posts = this.postService.getPostDataList();
        model.addAttribute("loadPost", posts);

        return "post/post";
    }

    @GetMapping("post/search")
    public String search(@RequestParam("keyword") String keyWord, Model model) {
        List<Post> dataSearch = this.PostServiceInterface.getSearchDataList(keyWord);
        if (dataSearch.isEmpty()) {
            model.addAttribute("errorMessage", "DATA NOT FOUND !");
            return "post/modal/post_table";
        }
        model.addAttribute("posts", dataSearch);
        return "post/modal/post_table";
    }


}
