package io.yoropapers.ebanque.service.serviceImpl;

import java.util.Random;

import org.springframework.stereotype.Service;

import io.yoropapers.ebanque.service.GenerateAccountNumber;

import javax.transaction.Transactional;

/**
 * GenerateAccountNumberImpl
 */
@Service
@Transactional
public class GenerateAccountNumberImpl implements GenerateAccountNumber{

    @Override
    public String generateAccountNumber(String typeCode, String userInitial) {
        int min = 1000000000;
		Random r = new Random();
        Integer randomNumber =  r.nextInt((Integer.MAX_VALUE - min) + 1) + min;
        StringBuilder accountNumber = new StringBuilder(userInitial);
        accountNumber = accountNumber.append(typeCode);
        accountNumber = accountNumber.append(randomNumber);
		return accountNumber.toString();
    }

    
}