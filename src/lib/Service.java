package lib;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 *
 * @author duende84
 */
public class Service {
    
    public static String getGitHubRepoUrl(String username, String token) throws Exception{
        String urlToRead = "https://api.github.com/users/"+username+"?access_token="+token;
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        
        StringBuilder result = new StringBuilder();
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        }
        System.out.println("Users Service Result: "+ result+"\n");
        
        JSONObject jsonObj = new JSONObject(result.toString());
        return jsonObj.get("repos_url").toString();
   }
    
    public static void getGitHubRepos(String urlToRead, String token) throws Exception{
        URL url = new URL(urlToRead+"?access_token="+token);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        
        StringBuilder result = new StringBuilder();
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        }
        System.out.println("Repositories Service Result: "+ result+"\n");
   }
}
