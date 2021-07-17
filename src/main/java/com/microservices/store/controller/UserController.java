package com.microservices.store.controller;

import com.microservices.store.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {


    protected static final  List<UserDTO> users = new ArrayList<>();

    @PostConstruct
    public void initiateList() {
        UserDTO userDTO = new UserDTO();
        userDTO.setNome("Eduardo");
        userDTO.setCpf("123");
        userDTO.setEndereco("Rua a");
        userDTO.setEmail("eduardo@email.com");
        userDTO.setTelefone("1234-3454");
        userDTO.setDataCadastro(new Date());
        UserDTO userDTO2 = new UserDTO();
        userDTO2.setNome("Luiz");
        userDTO2.setCpf("456");
        userDTO2.setEndereco("Rua b");
        userDTO.setEmail("luiz@email.com");
        userDTO.setTelefone("1234-3455");
        userDTO.setDataCadastro(new Date());
        UserDTO userDTO3 = new UserDTO();
        userDTO3.setNome("Bruna");
        userDTO3.setCpf("678");
        userDTO3.setEndereco("Rua c");
        userDTO.setEmail("bruna@email.com");
        userDTO.setTelefone("1234-3456");
        userDTO.setDataCadastro(new Date());
        users.add(userDTO);
        users.add(userDTO2);
        users.add(userDTO3);
    }

    @GetMapping("/")
    public String getMensagem(){
        return "Spring boot is working";
    }


    @GetMapping("/users")
    public List<UserDTO> getUsuarios(){ return users;}

    @GetMapping("/users/{cpf}")
    public UserDTO getUserFilter(@PathVariable String cpf){

        for(UserDTO userFilter : users){
            if(userFilter.getCpf().equals(cpf)){
                return userFilter;
            }
        }
        return null;
    }

    @PostMapping("/newUser")
    public UserDTO save(@RequestBody UserDTO userDTO){
        userDTO.setDataCadastro(new Date());
        users.add(userDTO);
        return userDTO;
    }

    @DeleteMapping("/users/{cpf}")
    public Boolean deleteUser(@PathVariable String cpf){
        for (UserDTO userFilter: users) {
           if(userFilter.getCpf().equals(cpf)) {
               users.remove(userFilter);
               return true;
           }
        }
        return false;
    }
}
