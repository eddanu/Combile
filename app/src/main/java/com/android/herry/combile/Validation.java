package com.android.herry.combile;

import java.util.regex.Pattern;

public class Validation {
    public static final Pattern
            EMAIL_PATTERN =
            Pattern.compile(
                    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"+    //matches a string which can contain
                            // a to z, A to Z, 0 to 9 and the characters
                            //"+", ".", "_", "%", "-".
                            // The string can be between 1 and 256 characters long.
                            // (why the + symbol twice, I don't know)
                            "\\@"+                             //matches the @ symbol in the email address
                            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"+ // matches the first part after the @ symbol which starts with a character
                            // that is one of a-Z, A-Z or 0-9,
                            // followed by characters that can also contain a hyphen('-').
                            //E.g. 'stack-overflow'. This string can be 1 to 65 characters long
                            // (including the first character)
                            "("+
                            "\\."+                             //matches the . itself (the . in google.com)
                            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"+ // matches a similar string as the above, which can be upto 25 characters long
                            ")+"                               //The entire part after the . is enclosed in ()+,
                    // to indicate that this can repeat as many times as needed
                    //(e.g. mymachine.mydepartment.mycompany.com)
            );

    public static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +                 //at least 1 digit
                    "(?=.*[a-zA-Z])" +              //any letter
                    "(?=\\S+$)" +                   //no white space
                    ".{8,}" +                       //at least 8 characters -> . itu artinya terima semua karakter apapun itu
                                                    //8 minimal nya (, ) more terserah mau sampe berapapun
                    "$"
            );

    public static final Pattern NOHP_PATTERN =
            Pattern.compile("^" +
                    "[0-9]{8,12}" +                  //harus angka
                    "$"
            );

    public static final Pattern DOB_PATTERN =
            Pattern.compile("^" +
                    "(0[1-9]|[12]\\d|3[01])" +      //tanggal
                    "-" +
                    "(0[1-9]|1[0-2])" +             //bulan
                    "-" +
                    "[12]\\d{3}"                    //tahun
            );

}


