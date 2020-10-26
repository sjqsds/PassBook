package main;

public class Encrypt {
    public String encrypt(String original){
        String ciphertext = "";
        for(char ch : original.toCharArray()){
            ciphertext+=(char)(ch+11);
        }
        return ciphertext;
    }

    public String decode(String ciphertext){
        String original = "";
        for(char ch : ciphertext.toCharArray()){
            original+=(char)(ch-11);
        }
        return original;
    }

}
