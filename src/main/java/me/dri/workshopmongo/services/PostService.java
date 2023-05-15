package me.dri.workshopmongo.services;


import me.dri.workshopmongo.domain.Post;
import me.dri.workshopmongo.exception.ObjectNotFoundException;
import me.dri.workshopmongo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public List<Post> fullSerch(String text, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 1000);
        return postRepository.fullSerch(text, minDate, maxDate);
    }
}
