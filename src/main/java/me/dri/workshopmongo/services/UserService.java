package me.dri.workshopmongo.services;

import me.dri.workshopmongo.domain.User;
import me.dri.workshopmongo.dtop.UserDTO;
import me.dri.workshopmongo.exception.ObjectNotFoundException;
import me.dri.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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

    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }
}
