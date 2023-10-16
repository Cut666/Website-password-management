package view;

import controller.Login;
import controller.Register;

import java.io.IOException;
import java.util.Scanner;

public class LoginAndRegister {
    public static void view() throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("----------------------------------------------------------------");
            System.out.println("\t\tapp quản lý mật khẩu website".toUpperCase());
            System.out.println("----------------------------------------------------------------");
            System.out.println("1/ đăng nhập");
            System.out.println("2/ đăng ký");
            System.out.println("----------------------------------------------------------------");
            System.out.println("nhâp số để thao tác");
            int n = 0;
            try {
                n = sc.nextInt();
            } catch (Exception e) {
                n =0;
            }
            // để ngoài try k thì sẽ lỗi lặp vô hạn
            sc.nextLine();

            switch (n) {
                case 1:
                    System.out.println("\t\tđăng nhâp".toUpperCase());
                    Login.Login();
                    break;
                case 2:
                    System.out.println("\t\tđăng ký".toUpperCase());
                    Register register = Register.getInstance();
                    register.signUp();
                    break;
                default:
                    System.out.println("nhập lại số");
            }
        }
    }
}
