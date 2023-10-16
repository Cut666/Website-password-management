package view;

import controller.HomeController;
import model.AccountWebsite;
import model.CreateKeyPair;

import java.io.IOException;
import java.util.Scanner;

public class Home {
    public static void view() throws Exception {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("----------------------------------------------------------------");
            System.out.println("\t\tapp quản lý mật khẩu website".toUpperCase());
            System.out.println("----------------------------------------------------------------");
            System.out.println("\t\t\t\ttrang chủ".toUpperCase());
            System.out.println("1. lưu thông tin đăng nhập của website");
            System.out.println("2. xóa thông tin đăng nhập của website");
            System.out.println("3. sửa thông tin đăng nhập của website");
            System.out.println("4. xem thông tin tài khoản các website");
            System.out.println("5. đăng nhập website");
            System.out.println("6. đăng xuất");
            System.out.println("----------------------------------------------------------------");
            int n = 0;
            try {
                n = sc.nextInt();
            } catch (Exception e) {
                n=0;
            }
            sc.nextLine();
            switch (n) {
                case 1:
                    System.out.println("\t\tlưu thông tin đăng nhập của website".toUpperCase());
                    System.out.println("url website");
                    String url = sc.next();
                    System.out.println("tên đăng nhập");
                    String userName = sc.next();
                    System.out.println("mật khẩu");
                    String passWord = sc.next();
                    // mã hóa password
                    passWord = CreateKeyPair.passwordEncryption(passWord);

                    AccountWebsite accountWebsite = new AccountWebsite(url,userName,passWord);
                    HomeController.addAccount(accountWebsite);
                    break;
                case 2:
                    System.out.println("\t\txóa tài khoản website".toUpperCase());
                    System.out.println("url website");
                    String url1 = sc.next();
                    System.out.println("tên đăng nhập");
                    String userName1 = sc.next();
                    AccountWebsite accountWebsite1 = new AccountWebsite(url1,userName1,"");
                    HomeController.deleteAccount(accountWebsite1);
                    break;
                case 3:
                    System.out.println("\t\tcập nhật tài khoản".toUpperCase());
                    System.out.println("nhập url của web");
                    String urlWeb = sc.next();
                    System.out.println("tên đăng nhập cũ");
                    String userName2 =sc.next();
                    System.out.println("tên đăng nhập mới");
                    String userName3 = sc.next();
                    System.out.println("mật khẩu mới");
                    String passWord3 = sc.next();
                    AccountWebsite oldAcc = new AccountWebsite(urlWeb,userName2,"");
                    AccountWebsite newAcc = new AccountWebsite(urlWeb,userName3,passWord3);
                    HomeController.updateAccount(oldAcc, newAcc);
                    System.out.println("cập nhật thành công");
                    break;
                case 4:
                    System.out.println("\t\tthông tin các tài khoản".toUpperCase());
                    HomeController.infoAccount();
                    break;
                case 5:
                    System.out.println("\t\tđăng nhập website".toUpperCase());
                    System.out.println("nhập url");
                    String urlWeb1 = sc.next();
                    HomeController.loginWebsite(urlWeb1);
                    break;
                case 6:
                    LoginAndRegister.view();
                    break;
                default:
                    System.out.println("nhập lại số");
            }

        }
    }
}
