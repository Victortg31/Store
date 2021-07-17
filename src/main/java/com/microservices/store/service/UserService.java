package com.microservices.store.service;

import com.microservices.store.dto.UserDTO;
import com.microservices.store.model.User;
import com.microservices.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<UserDTO> getAll(){
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());
    }

    public UserDTO findById(long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return  UserDTO.convert(user.get());
        }

        return null;
    }

    public UserDTO save(UserDTO userDTO){
        User user = userRepository.save(User.convert(userDTO));

        return UserDTO.convert(user);
    }

    public UserDTO delete(long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.delete(user.get());
        }

        return null;
    }

    public UserDTO findByCpf(String cpf){
        User user = userRepository.findByCpf(cpf);
        if(user != null){
            return UserDTO.convert(user);
        }

        return null;
    }

    public List<UserDTO> queryByName(String name){
        List<User> user = userRepository.queryByNameLike(name);
        return user
                .stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());
    }
}
