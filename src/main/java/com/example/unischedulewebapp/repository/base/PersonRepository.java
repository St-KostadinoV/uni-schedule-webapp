package com.example.unischedulewebapp.repository.base;

import com.example.unischedulewebapp.auth.AppUser;
import com.example.unischedulewebapp.model.base.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;
import java.util.Optional;

@NoRepositoryBean
public interface PersonRepository<T extends Person>
        extends JpaRepository<T, Long> {

    Optional<T> findByUserDetails(AppUser userDetails);

    // TODO - edit queries to ignore case

    Collection<T> findByFirstNameContainingOrLastNameContaining(String firstName,
                                                                String lastName);

    Collection<T> findByFirstNameContainingOrMiddleNameContainingOrLastNameContaining(String firstName,
                                                                                      String middleName,
                                                                                      String lastName);
}
