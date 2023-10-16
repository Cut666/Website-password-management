package controller;

import com.google.gson.Gson;
import model.Account;
import model.CreateKeyPair;
import model.DataConnect;
import model.DataPath;
import view.Home;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Login {
    public static void Login() throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("Tên tài khoản: ");
        String email = sc.nextLine();

        System.out.println("Mật khẩu: ");
        String passWord = sc.nextLine();

        String json = DataConnect.readFile(DataPath.ACCOUNT.getValue());
        Gson gson = new Gson();
        Account[] accs = gson.fromJson(json, Account[].class);
        List<Account> listAcc = Arrays.asList(accs);

        for (Account acc : listAcc) {
            if (acc.getEmail().equals(email) && CreateKeyPair.Decrypt(acc.getPassWord()).equals(passWord)) {
                System.out.println("đăng nhập thành công");
                Home.view();
                return;
            }
        }
        System.out.println("Tên đăng nhập hoặc mật khẩu sai, vui lòng thử lại!!!");
        System.out.println("Nếu chưa có tài khoản, bạn có thể đăng ký");
    }
}
