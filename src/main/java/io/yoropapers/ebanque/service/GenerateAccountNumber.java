package io.yoropapers.ebanque.service;

/**
 * GenerateAccountNumber
 */
public interface GenerateAccountNumber {
    String generateAccountNumber(String typeCode, String userInitial);
}