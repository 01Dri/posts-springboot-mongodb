package me.dri.workshopmongo.resources;


import me.dri.workshopmongo.domain.Post;
import me.dri.workshopmongo.domain.User;
import me.dri.workshopmongo.dtop.UserDTO;
import me.dri.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {


    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = userService.findAll();
        List<UserDTO> userDTOS = users.stream().map(UserDTO::new).toList();
        return ResponseEntity.ok().body(userDTOS);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User userDTO = userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(userDTO));

    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO) {
        User user = userService.fromDTO(userDTO);
        user = userService.insert(user);
        URI uri = (ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri());
        return ResponseEntity.created(uri).build();

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody UserDTO obj , @PathVariable String id) {
        User user = userService.fromDTO(obj);
        user.setId(id);
        user = userService.update(user);
        return ResponseEntity.noContent().build();

    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        User obj = userService.findById(id);
        return ResponseEntity.ok().body(obj.getPosts());

    }



}
