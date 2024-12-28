package it.linksmt.academy.messageSenderApp.repositories;

import it.linksmt.academy.messageSenderApp.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByPhone(String phone);

    @Query("select m from Message m where m.phone = :phone")
    List<Message> findByPhoneUsingJPQL(@Param("phone") String phone);

    @Query("select m from Message m where m.user.id = :idUtente")
    List<Message> findByIdUtenteUsingJPQL(@Param("idUtente") long id);

    List<Message> findByPhoneAndBodyOrderByTitle(String phone, String body);

    @Query("select m from Message m where m.phone = :phone and m.body = :body order by m.title")
    List<Message> findByPhoneAndBodyOrderByTitleUsingJPQL(@Param("phone") String phone, @Param("body") String body);
}
