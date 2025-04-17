package com.nathan.nl_finances.repositories;

import com.nathan.nl_finances.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
  }