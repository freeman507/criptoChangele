package br.edu.unisep.criptochangelle.utils.criptografia;

import java.util.Random;

/**
 * Descrever o motivo da classe ter sido criada.
 *
 * @author Felipe Zanella
 * @since 1.0 (12/04/18)
 */
public class CriptoUtils {

    private static final String ALFABETO = "abcdefghijklmnopqrstuvxywzABCDEFGHIJKLMNOPQRSTUVXYWZ!@#$%*()-_+=";

    private static final String ALFABETO_HEXADECIMAL = "0123456789abcdef";

    private static final Integer TAM = 512;

    public static String criptografa(String string) {
        String criptografada = "";
        byte[] bytes = string.getBytes();
        for (byte b : bytes) {
            String binaryString = Integer.toBinaryString(b);
            Long number = Long.parseLong(binaryString);
            String hexString = Long.toHexString(number);
            char[] charArray = ALFABETO.toCharArray();
            while (hexString.length() < TAM) {
                int rand = new Random().nextInt(ALFABETO.length() - 6) + 6;
                hexString = charArray[rand] + hexString;
            }
            criptografada = hexString + criptografada;
        }
        return criptografada;
    }

    public static String descriptografa(String string) {
        String descriptografada = "";
        int tam = string.length() / TAM;
        for (int i = 0; i < tam; i++) {
            int start = TAM * i;
            int end = (TAM * i) + TAM;
            String substring = string.substring(start, end);
            char[] charArray = substring.toCharArray();
            String hexString = "";
            for (char c : charArray) {
                if (ALFABETO_HEXADECIMAL.contains("" + c)) {
                    hexString += c;
                }
            }
            String bin = "" + Integer.parseInt(hexString, 16);
            int parseInt = Integer.parseInt(bin, 2);
            descriptografada += "" + (char) parseInt;
        }
        return new StringBuffer(descriptografada).reverse().toString();
    }

}
