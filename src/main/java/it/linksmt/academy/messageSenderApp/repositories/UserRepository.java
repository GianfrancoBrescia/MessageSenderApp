package it.linksmt.academy.messageSenderApp.repositories;

import it.linksmt.academy.messageSenderApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
