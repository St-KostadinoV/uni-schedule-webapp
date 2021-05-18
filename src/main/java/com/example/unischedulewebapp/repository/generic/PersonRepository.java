package com.example.unischedulewebapp.repository.generic;

import com.example.unischedulewebapp.auth.AppUser;
import com.example.unischedulewebapp.model.generic.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PersonRepository<T extends Person>
        extends JpaRepository<T, Long> {
    Optional<T> findByUserDetails(AppUser userDetails);
}
