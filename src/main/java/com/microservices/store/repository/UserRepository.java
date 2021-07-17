package com.microservices.store.repository;

import com.microservices.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> queryByNameLike(String nome);

    User findByCpf(String cpf);
}
