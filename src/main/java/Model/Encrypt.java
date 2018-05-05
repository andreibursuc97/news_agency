package Model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {

    public byte[] code(String text)
    {
        MessageDigest digest = null;

        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            return hash;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;

    }

    public String codeToString(byte[] hash)
    {
        StringBuilder parola = new StringBuilder();
        //StringBuilder parolaUser = new StringBuilder();
        for (byte b : hash) {
            parola.append(String.format("%02X ", b));
        }

        return parola.toString();
    }


}
