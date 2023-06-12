package io.javabrains.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.javabrains.Entity.Mentor;

public interface MentorRepository extends JpaRepository<Mentor,Integer> {

}
