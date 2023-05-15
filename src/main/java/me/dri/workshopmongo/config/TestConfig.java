package me.dri.workshopmongo.config;

import me.dri.workshopmongo.domain.Post;
import me.dri.workshopmongo.domain.User;
import me.dri.workshopmongo.dtop.AuthorDTO;
import me.dri.workshopmongo.repository.PostRepository;
import me.dri.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;


@Configuration
public class TestConfig  implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;
    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        userRepository.saveAll(Arrays.asList(maria,alex,bob));

        Post post1 = new Post(null, sdf .parse("23/01/2003"), "Partiu viagem", "Vou viajar para SP",  new AuthorDTO(maria));
        Post post2 = new Post(null, sdf .parse("23/01/2003"), "Bom dia", "Acordei feliz hoje", new AuthorDTO(maria));

        postRepository.saveAll(Arrays.asList(post1, post2));




    }
}
