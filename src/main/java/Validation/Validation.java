package Validation;

import java.util.regex.Pattern;

public class Validation {
   
    public static boolean checkPhone(String phone) {
    String phoneRegex = "^5\\d{9}$";
        return Pattern.matches(phoneRegex, phone);
    }
        
    public static boolean checkEmail(String email) {
    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return Pattern.matches(emailRegex, email);
    }
   
    public static boolean checkPassword(String password) {
    String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        return Pattern.matches(passwordRegex, password);
    }
   
    public static boolean checkUsername(String username) {
        String usernameRegex = "^[a-zA-Z][a-zA-Z0-9]{2,20}$";
        return Pattern.matches(usernameRegex, username);
    }
   
    public static boolean checkTc(String tc) {
        // tc 11 haneli olmalı
        if (tc == null || tc.length() != 11) {
            return false;
        }

        // bütün karakterler sayı olmalı
        if (!tc.matches("\\d{11}")) {
            return false;
        }
       
        // tc no ilk karakter 0 olamaz
        if (tc.charAt(0) == '0') {
            return false;
        }

        // string'i int array'e cevirme
        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = Character.getNumericValue(tc.charAt(i));
        }

        // TC kimlik algoritması
        int sumOdd = digits[0] + digits[2] + digits[4] + digits[6] + digits[8];
        int sumEven = digits[1] + digits[3] + digits[5] + digits[7];

        // 10. basamak kontrolü
        int tenthDigitCheck = (7 * sumOdd - sumEven) % 10;
        if (tenthDigitCheck != digits[9]) {
            return false;
        }

        // 11. basamak kontrolü
        int sumFirstTen = 0;
        for (int i = 0; i < 10; i++) {
            sumFirstTen += digits[i];
        }
        int eleventhDigitCheck = sumFirstTen % 10;
        if (eleventhDigitCheck != digits[10]) {
            return false;
        }

        return true;
    }
   
    public static boolean checkDate(String date) {
        // tarih formatı YYYY-AA-GG
        String dateRegex = "^\\d{4}-\\d{2}-\\d{2}$";
        if (!Pattern.matches(dateRegex, date)) {
            return false;
        }

        try {
            java.time.LocalDate.parse(date);
            return true;
        } catch (java.time.format.DateTimeParseException e) {
            return false;
        }
    }
    
    public static boolean checkTime(String time) {
        // saat formatı hh:dd:ss
        String timeRegex = "^\\d{2}:\\d{2}:\\d{2}$";
        if (!Pattern.matches(timeRegex, time)) {
            return false;
        }

        try {
            java.time.LocalTime.parse(time);
            return true;
        } catch (java.time.format.DateTimeParseException e) {
            return false;
        }
    }
}