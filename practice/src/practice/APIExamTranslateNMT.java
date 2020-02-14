package practice;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class APIExamTranslateNMT {
	private Scanner scan = new Scanner(System.in);
	
    public static void main(String[] args) {
        new APIExamTranslateNMT().koToEng();
        new APIExamTranslateNMT().engToKo();
    }
    
    public void koToEng() {
    	String clientId = "oA7hCfTqadrneK62xlyU";
        String clientSecret = "weYh519TiP";
        try {
        	String str = "";
        	System.out.print("한글이 영어로 변환됩니다. 한글을 입력하세요 : ");
        	str = scan.nextLine();
            String text = URLEncoder.encode(str, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            // post request
            String postParams = "source=ko&target=en&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { 
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            
            int start = response.toString().indexOf("translatedText\":\"") + 17;
            int end = response.toString().lastIndexOf("\"");
            
            String result = response.toString().substring(start, end);
            if(result.contains(".")) {
            	result = result.replace(".", " ");
            	result = result.trim();
            }
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void engToKo() {
    	String clientId = "oA7hCfTqadrneK62xlyU";
        String clientSecret = "weYh519TiP";
        try {
        	String str = "";
        	System.out.print("영어가 한글로 변환됩니다. 영어를 입력하세요 : ");
        	str = scan.nextLine();
            String text = URLEncoder.encode(str, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            // post request
            String postParams = "source=en&target=ko&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { 
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            
            int start = response.toString().indexOf("translatedText\":\"") + 17;           
            int end = response.toString().lastIndexOf("\"");
            
            String result = response.toString().substring(start, end);
            if(result.contains(".")) {
            	result = result.replace(".", " ");
            	result = result.trim();
            }
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}