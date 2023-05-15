package me.dri.workshopmongo.services;


import me.dri.workshopmongo.domain.Post;
import me.dri.workshopmongo.exception.ObjectNotFoundException;
import me.dri.workshopmongo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {


    @Autowired

    private PostRepository postRepository;

    public Post findPostById(String id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text) {
        return postRepository.findByTitle(text);
    }
}
