package me.dri.workshopmongo.resources;


import me.dri.workshopmongo.domain.Post;
import me.dri.workshopmongo.domain.User;
import me.dri.workshopmongo.dtop.UserDTO;
import me.dri.workshopmongo.services.PostService;
import me.dri.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = postService.findPostById(id);
        return ResponseEntity.ok().body(post);

    }
}
