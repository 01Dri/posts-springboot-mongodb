package me.dri.workshopmongo.services;

import me.dri.workshopmongo.domain.Post;
import me.dri.workshopmongo.domain.User;
import me.dri.workshopmongo.dtop.UserDTO;
import me.dri.workshopmongo.exception.ObjectNotFoundException;
import me.dri.workshopmongo.repository.PostRepository;
import me.dri.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }


    public User insert(User obj) {
        return userRepository.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        userRepository.deleteById(id);
    }
    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }

    public User update(User user) {
        Optional<User> newUser = userRepository.findById(user.getId());
        updateData(newUser, user);
        return userRepository.save(newUser.get());



    }

    private void updateData(Optional<User> newUser, User user) {
        newUser.get().setName(user.getName());
        newUser.get().setEmail(user.getEmail());
    }



}
