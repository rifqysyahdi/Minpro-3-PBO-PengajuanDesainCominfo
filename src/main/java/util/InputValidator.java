package util;

import java.util.Scanner;

public class InputValidator {

    public static String bacaString(Scanner scanner, String pesan) {
        while (true) {
            System.out.print(pesan);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input tidak boleh kosong, coba lagi.");
        }
    }

    public static int bacaAngka(Scanner scanner, String pesan) {
        while (true) {
            System.out.print(pesan);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }

    public static int bacaAngkaPositif(Scanner scanner, String pesan) {
        while (true) {
            int val = bacaAngka(scanner, pesan);
            if (val > 0) {
                return val;
            }
            System.out.println("Angka harus bernilai positif (lebih dari 0).");
        }
    }

    public static int bacaPilihan(Scanner scanner, String pesan, int min, int max) {
        while (true) {
            int pilihan = bacaAngka(scanner, pesan);
            if (pilihan >= min && pilihan <= max) {
                return pilihan;
            }
            System.out.println("Pilihan tidak valid! Pilih angka " + min + " - " + max + ".");
        }
    }
}