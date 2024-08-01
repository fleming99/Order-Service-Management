package com.fleming99.customer_microservice.utils;

public class CPFGenerator {

    //Generate the CPF number
    public static String generate() {
        int[] cpf = new int[11];
        for (int i = 0; i < 9; i++) {
            cpf[i] = (int) (Math.random() * 10);
        }

        cpf = generateDigits(cpf);
        return formatCPF(cpf);
    }

    //Generate both final digits
    private static int[] generateDigits(int[] cpf) {
        cpf = calculateDigit(cpf, 9);
        cpf = calculateDigit(cpf, 10);
        return cpf;
    }

    //Calculate the single digit
    private static int[] calculateDigit(int[] cpf, int length) {
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += cpf[i] * ((length + 1) - i);
        }
        int digit = 11 - (sum % 11);
        cpf[length] = (digit > 9) ? 0 : digit;
        return cpf;
    }

    private static String formatCPF(int[] cpf) {
        return String.format("%d%d%d.%d%d%d.%d%d%d-%d%d", cpf[0], cpf[1], cpf[2], cpf[3], cpf[4], cpf[5], cpf[6], cpf[7], cpf[8], cpf[9], cpf[10]);
    }
}
