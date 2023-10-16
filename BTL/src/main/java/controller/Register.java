package controller;


import com.google.gson.Gson;
import model.Account;
import model.CreateKeyPair;
import model.DataPath;
import view.LoginAndRegister;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Register {
    private static Register instance = new Register();

    public static void Register() {

    }

    public static Register getInstance() {
        return instance;
    }

    /**
     * Function: signUp
     * Description: dang ky email, mat khau.
     * Param: none
     * Return:
     * - false : neu email hoac mat khau bi sai hoac trung.
     * - true  : dang ky thanh cong
     **/
    public boolean signUp() throws Exception {
        String email;
        String passWord;
        Scanner sc = new Scanner(System.in);
        System.out.println("Tên tài khoản: ");
        email = sc.nextLine();
        if (checkPatternEmail(email) == false || checkDuplicateEmail(email) == true) {
            System.out.println("Email sai hoac bi trung");
            return false;
        }
        System.out.println("Mật khẩu: ");
        passWord = sc.nextLine();

        if (checkPatternPassWord(passWord) == false) {
            System.out.println("Mat khau sai dinh dang");
            return false;
        }

        List<Account> accounts = new ArrayList<>(this.readFile(DataPath.ACCOUNT.getValue()));
        System.out.println("đăng ký thành công");

        //mã hóa password
        passWord = CreateKeyPair.passwordEncryption(passWord);

        Account account = new Account(email, passWord);
        accounts.add(account);
        Gson gson = new Gson();
        String writeDb = gson.toJson(accounts);
        this.writeDataBase(writeDb);
        LoginAndRegister.view();
        return true;

    }


    /**
     * Function: readFile
     * Description: doc file.
     * Param(in): file path
     * Return: danh sach doi tuong
     **/
    public static List<Account> readFile(String url) throws IOException {
        File file = new File(url);
        FileInputStream fis = new FileInputStream(file);
        String str = "";
        for (byte b : fis.readAllBytes()) {
            str += (char) b + "";
        }
        Gson gson = new Gson();
        Account[] accs = gson.fromJson(str, Account[].class);
        List<Account> listAcc = Arrays.asList(accs);
        return listAcc;
    }

    /**
     * Function: convert2Json
     * Description: convert doi tuong lop Account sang string.
     * Param(in): doi tuong can convert
     * Return: string
     **/
    private String convert2Json(Account input) {
        Gson gson = new Gson();
        String json = gson.toJson(input);
        return json;
    }

    /**
     * Function: writeDataBase
     * Description: Ham ghi mot chuoi vao file.
     * Param(in): chuoi can ghi.
     * Return: n/a
     **/
    private boolean writeDataBase(String input) throws IOException {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(DataPath.ACCOUNT.getValue());
            outputStream.write(input.getBytes());
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
        return true;
    }

    /**
     * Function: checkDuplicateEmail
     * Description: Ham check email dang ky co bi trung lap.
     * Param(in): email can check.
     * Return:
     * - true neu email da duoc dang ky truoc do.
     * - false: neu email chua duoc dang ky truoc do.
     **/
    private boolean checkDuplicateEmail(String input) throws IOException {
        List<Account> accounts = new ArrayList<>(this.readFile(DataPath.ACCOUNT.getValue()));
        for (Account account : accounts) {
            if (input.equals(account.getEmail())) return true;
        }
        return false;
    }

    /**
     * Function: checkPatternEmail
     * Description: Ham check dinh dang email chuan.
     * Param(in): email can check dinh dang
     * Return:
     * - true neu email dung dinh dang
     * - false: neu email sai dinh dang
     **/
    private static boolean checkPatternEmail(String email) {
        Pattern emailPt = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z]+(\\.[a-zA-Z]+){1,3}$");
        return Pattern.matches(String.valueOf(emailPt), email);
    }

    private static boolean checkPatternPassWord(String passWord) {
        Pattern passWordPt = Pattern.compile("[a-zA-Z0-9]+");
        return Pattern.matches(String.valueOf(passWordPt), passWord);
    }

}
