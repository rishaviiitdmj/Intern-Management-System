package io.javabrains.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.javabrains.Entity.Buddy;

public interface BuddyRepository extends JpaRepository<Buddy, Integer> {

}
