package io.yoropapers.ebanque.service;

import io.yoropapers.ebanque.model.Recipient;

import java.util.List;

public interface RecipientService {
    void save(Recipient recipient);
    List<Recipient> findAll();
    List<Recipient> findAllByUserUsername(String username);
    Recipient findRecipientByLastNameAndFirstName(String lastname, String firstname);
    Recipient findRecipientById(Long Id);
    void deleteRecipient(Recipient recipient);
    Recipient findRecipientByAccountNumber(String accountNumber);

}
