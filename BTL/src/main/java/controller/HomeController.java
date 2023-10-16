package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.AccountWebsite;
import model.CreateKeyPair;
import model.DataConnect;
import model.DataPath;

import javax.crypto.Cipher;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;


public class HomeController {

    public static void addAccount(AccountWebsite accountWebsite) throws Exception {
        try {
            String json = DataConnect.readFile(DataPath.ACCOUNTWEBSITE.getValue());
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<AccountWebsite>>() {
            }.getType();

            // data trong file chuyen thanh list AccountWebsite
            ArrayList<AccountWebsite> accountWebsites = gson.fromJson(json, type);
//        AccountWebsite[] arr = gson.fromJson(json,AccountWebsite[].class);
//        List<AccountWebsite> accountWebsites = Arrays.asList(arr);

            // check list
            //th1: list rỗng thì luôn add tài khoản đó vào file
            //th2: list không rỗng thì lại chia 2 th
            //th2-1: list đã chứa tài khoản sắp thêm vào (check toan bộ list- chỉ cần chứa 1)---> trả về
            //th2-2: list chưa chứa tài khoản sắp thêm vào (ở trên sai hết --> chưa trả về--> add vào)
            if (accountWebsites == null) {
                accountWebsites = new ArrayList<>();
                accountWebsites.add(accountWebsite);
                String newJson = gson.toJson(accountWebsites);
                DataConnect.writeFile(DataPath.ACCOUNTWEBSITE.getValue(), newJson);
                System.out.println("Lưu tài khoản thành công:");
                System.out.println("Website: " + accountWebsite.getUrl());
                System.out.println("Username: " + accountWebsite.getUserName());
                System.out.println("Password: " + CreateKeyPair.Decrypt(accountWebsite.getPassWord()));
            } else {
                for (AccountWebsite acc : accountWebsites) {
                    if (acc.getUrl().equals(accountWebsite.getUrl()) && acc.getUserName().equals(accountWebsite.getUserName())) {
                        System.out.println("bạn đã lưu tài khoản này cho " + accountWebsite.getUrl());
                        return;
                    }
                }
                accountWebsites.add(accountWebsite);
                String newJson1 = gson.toJson(accountWebsites);
                DataConnect.writeFile(DataPath.ACCOUNTWEBSITE.getValue(), newJson1);
                System.out.println("Lưu tài khoản thành công:");
                System.out.println("Website: " + accountWebsite.getUrl());
                System.out.println("Username: " + accountWebsite.getUserName());
                System.out.println("Password: " + CreateKeyPair.Decrypt(accountWebsite.getPassWord()));
            }
        } catch (Exception e) {
            System.out.println("không được nhập khoảng trống");
        }

    }

    public static void deleteAccount(AccountWebsite accountWebsite) throws IOException {
        try {
            AccountWebsite tmpAcc = new AccountWebsite(" ", " ", " ");
            String json = DataConnect.readFile(DataPath.ACCOUNTWEBSITE.getValue());
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<AccountWebsite>>() {
            }.getType();
            ArrayList<AccountWebsite> accountWebsites = gson.fromJson(json, type);

            /* database rong */
            if (accountWebsites == null) {
                System.out.println("Chưa có tài khoản nào được lưu ");
            }
            /* database co du lieu */
            else {
                int deleteFlag = 0;
                boolean check1 = false;
                boolean check2 = false;
                /* check account ton tai */
                for (AccountWebsite index : accountWebsites) {
                    if (index.getUrl().equals(accountWebsite.getUrl()) && index.getUserName().equals(accountWebsite.getUserName())) {
                        tmpAcc = index;
                        deleteFlag++;
                    }
                }
                /* Neu ton tai thi xoa */
                if (deleteFlag != 0) {
                    /* neu co N account giong nhau. -> phai xoa N lan */
                    for (int index = 0; index < deleteFlag; index++) {
                        accountWebsites.remove(tmpAcc);
                    }
                    String newDatabase = gson.toJson(accountWebsites);
                    DataConnect.writeFile(DataPath.ACCOUNTWEBSITE.getValue(), newDatabase);
                    System.out.println("Xóa thành công");

                } else System.out.println("Tài khoản cần xóa không tồn tại");
            }
        } catch (Exception e) {
            System.out.println("không được nhập khoảng trống");
        }

    }

    public static void updateAccount(AccountWebsite oldAcc, AccountWebsite newAcc) throws Exception {
        try{
            deleteAccount(oldAcc);
            addAccount(newAcc);
        }catch (Exception e){
            System.out.println("không được nhập khoảng trống");
        }
    }

    public static void infoAccount() {
        try {
            String json = DataConnect.readFile(DataPath.ACCOUNTWEBSITE.getValue());
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<AccountWebsite>>() {
            }.getType();
            ArrayList<AccountWebsite> accountWebsites = gson.fromJson(json, type);
            if (accountWebsites == null) {
                System.out.println("Chưa có tài khoản dc lưu");
            } else {
                for (AccountWebsite accountWebsite : accountWebsites) {
                    System.out.println("url: " + accountWebsite.getUrl() + ", username: " + accountWebsite.getUserName() + ", password: " + CreateKeyPair.Decrypt(accountWebsite.getPassWord()));
                }
            }
        } catch (Exception e) {
            System.out.println("lỗi password");
        }

    }


    public static void loginWebsite(String urlWeb) {
        try {
            String json = DataConnect.readFile(DataPath.ACCOUNTWEBSITE.getValue());
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<AccountWebsite>>() {
            }.getType();
            ArrayList<AccountWebsite> accountWebsites = gson.fromJson(json, type);
            if (accountWebsites == null) {
                System.out.println("Chưa có tài khoản dc lưu");
            } else {
                for (AccountWebsite accountWebsite : accountWebsites) {
                    if (accountWebsite.getUrl().equals(urlWeb)) {
                        System.out.println("username: " + accountWebsite.getUserName() + ", password: " + CreateKeyPair.Decrypt(accountWebsite.getPassWord()));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("lỗi password");
        }

    }

}
