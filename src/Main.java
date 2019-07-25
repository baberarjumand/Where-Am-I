/**
 * This program fetches the geolocation data of either the current IP address
 * or a provided IP address using IPInfo.IO API and JSON library.
 *
 * This is the second project for the Java Code Clinic (by Carlos Rivas) on Lynda.com.
 *
 * Author:  Baber Arjumand
 * Date:    Jul 22 2019
 */

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println(getLocation(""));
        System.out.println(getLocation("8.8.8.8"));
    }

    private static String getLocation(String ip) {

        String location = getData(ip);
        JSONObject obj = new JSONObject(location);
        String response = "";
        response += "You are in or near the city of " + obj.getString("city") + ", " + obj.getString("country") + "\n";
        response += "Your approximate location on the map:\nhttps://www.google.com/maps/?q=" + obj.getString("loc") + "\n";
        response += "Raw JSON Data:\n";
        response += obj.toString(3) + "\n";
        return response;
    }

    private static String getData(String ip) {

        String response = "";
        if(!ip.equals(""))
            ip = "/" + ip;

        try {
            URL url = new URL("https://ipinfo.io" + ip + "/json");
            URLConnection conn = url.openConnection();
            Scanner sc = new Scanner(new InputStreamReader(conn.getInputStream()));
            while(sc.hasNext())
                response += sc.nextLine();
            sc.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return response;
    }
}
