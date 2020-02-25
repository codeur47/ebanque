package io.yoropapers.ebanque.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.yoropapers.ebanque.model.Recipient;
import org.springframework.stereotype.Repository;

/**
 * RecipientDao
 */
@Repository
public interface RecipientDao extends JpaRepository<Recipient, Long>{
    List<Recipient> findAll();
    List<Recipient> findAllByUserUsername(String username);
    Recipient findRecipientByLastNameAndFirstName(String lastname, String firstname);
    Recipient findRecipientById(Long Id);

}