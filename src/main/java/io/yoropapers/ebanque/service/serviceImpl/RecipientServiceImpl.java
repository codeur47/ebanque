package io.yoropapers.ebanque.service.serviceImpl;

import io.yoropapers.ebanque.dao.RecipientDao;
import io.yoropapers.ebanque.model.Recipient;
import io.yoropapers.ebanque.service.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipientServiceImpl implements RecipientService {

    private RecipientDao recipientDao;

    @Autowired
    public RecipientServiceImpl(RecipientDao recipientDao) {
        this.recipientDao = recipientDao;
    }

    @Override
    public void save(Recipient recipient) {
        recipientDao.save(recipient);
    }

    @Override
    public List<Recipient> findAll() {
        return recipientDao.findAll();
    }

    @Override
    public List<Recipient> findAllByUserUsername(String username) {
        return recipientDao.findAllByUserUsername(username);
    }

    @Override
    public Recipient findRecipientByLastNameAndFirstName(String lastname, String firstname) {
        return recipientDao.findRecipientByLastNameAndFirstName(lastname,firstname);
    }

    @Override
    public Recipient findRecipientById(Long Id) {
        return recipientDao.findRecipientById(Id);
    }

    @Override
    public void deleteRecipient(Recipient recipient) { recipientDao.delete(recipient);}
}
