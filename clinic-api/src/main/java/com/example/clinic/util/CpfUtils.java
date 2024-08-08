package com.example.clinic.util;

public class CpfUtils {
    public static boolean validateCPF(String cpf) {
        if(cpf == null || cpf.length() != 11) return false;
        if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999"))
            return false;

        if(cpf.charAt(9) != validateDigit(cpf.substring(0, 9)))
            return false;
        if(cpf.charAt(10) != validateDigit(cpf.substring(0, 10)))
            return false;

        return true;
    }

    private static char validateDigit(String cpf) {
        int total = 0;
        int multiplier = cpf.length() + 1;
        for(int i = 0; i < cpf.length(); i++) {
            total += Character.getNumericValue(cpf.charAt(i)) * (multiplier - i);
        }
        int digit = 11 - (total % 11);
        if(digit == 10 || digit == 11) digit = 0;
        return (char)(digit + 48);
    }
}
