package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;


public class EncryptionAlgo {
    private static final List<Integer> MY_MESSAGE_INT_LIST = new ArrayList<>();
    private static final Map<Character, Integer> MY_MAP_OF_LETTERS = new HashMap<>();
    private static final  Random RANDOM = new Random();
    private static final int KEY = RANDOM.nextInt(0, 26);
    private static final int SPACE_ASCII = 32;
    private static final int MOD_DIV = 26;

    private EncryptionAlgo(){}

    static {
        int j = 1;

        for(Alphabet letters : Alphabet.values()) {
            MY_MAP_OF_LETTERS.put(letters.toString().charAt(0), j);
            j++;
        }
    }

    private enum Alphabet {
        A, B, C, D, E, F, G,
        H, I, J, K, L, M, N,
        O, P, Q, R, S, T, U,
        V, W, X, Y, Z
    }

    /**
     * Base algorithm for the encryption.
     * @param theMessage the message you want to pass in
     */
    public static List<Integer> encryptMessage(final String theMessage) {
        List<Integer> encryptedMessage = new ArrayList<>();
        
        for(int i = 0; i < theMessage.length(); i++) {
            MY_MESSAGE_INT_LIST.add(MY_MAP_OF_LETTERS.get(theMessage.toUpperCase()
                    .replaceAll("\\p{Punct}", "").charAt(i)));
        }
        
        for(Integer intLetters : MY_MESSAGE_INT_LIST){
            encryptedMessage.add(
                    Objects.requireNonNullElse(intLetters, SPACE_ASCII) * KEY / (KEY % MOD_DIV));
        }

        return encryptedMessage;
    }

    public static void main(String[] args) {
        System.out.println(KEY);
        System.out.println(encryptMessage("my message is super safe"));
    }





}
