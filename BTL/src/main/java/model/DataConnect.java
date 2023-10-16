package model;

import java.io.*;

public class DataConnect {
    public static String readFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        String json = "";
        for (byte b : fis.readAllBytes()) {
            json += (char) b + "";
        }
        return json;
    }
    public static void writeFile(String filePath, String json) throws IOException {
        File file = new File(filePath);
        FileOutputStream fos = new FileOutputStream(file);
        //clear file
        byte[] clear = new byte[0];
        fos.write(clear);
        fos.flush();
        byte[] newJson = new byte[json.length()];
        for(int i =0;i<json.length();i++){
            newJson[i]=(byte)json.charAt(i);
        }
        fos.write(newJson);
        fos.flush();
        fos.close();
    }
}
