package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PersonRepository
        extends JpaRepository<Person, Long> {
}
