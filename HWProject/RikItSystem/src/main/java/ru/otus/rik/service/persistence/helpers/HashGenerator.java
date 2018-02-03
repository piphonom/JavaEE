package ru.otus.rik.service.persistence.helpers;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashGenerator {

    private MessageDigest messageDigest;
    private static Charset charset = StandardCharsets.UTF_8;
    private String salt = "";

    public HashGenerator() throws NoSuchAlgorithmException {
        messageDigest = MessageDigest.getInstance( "SHA-256" );
    }

    public HashGenerator update(byte[] data) {
        messageDigest.update(data);
        return this;
    }

    public HashGenerator update(String data) {
        return this.update(data.getBytes(charset));
    }

    public HashGenerator setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public String digest() {
        this.update(salt);
        byte[] digest = messageDigest.digest();
        return Base64.getEncoder().encodeToString(digest);
    }

    public void reset() {
        messageDigest.reset();
    }
}
