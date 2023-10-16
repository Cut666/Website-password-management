import controller.HomeController;
import controller.Register;
import model.Account;
import model.AccountWebsite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String []args) throws Exception {
        System.out.println(Decrypt("21232f297a57a5a743894a0e4a801fc3"));

    }
    public static String Decrypt(String md5_hash) throws Exception {

        String api_key = "YOUR_VIP_KEY";
        URL md5online = new URL("https://www.md5online.org/api.php?d=1&p="+api_key+"&h="+md5_hash);
        BufferedReader in = new BufferedReader(new InputStreamReader(md5online.openStream()));

        String result = "";
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            result = result+inputLine;
        in.close();

        return result;
    }
    public int  myMethod(int param, String string) {
        System.out.println();
        return 0;
    }
    public char myMethod(int param) {
        System.out.println();
        return 'c';
    }
}
