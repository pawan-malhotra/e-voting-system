package com.team33.evotingsystem.repository;

import com.team33.evotingsystem.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails,Integer> {
}
