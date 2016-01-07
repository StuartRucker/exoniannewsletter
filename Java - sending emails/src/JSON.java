import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSON
{
	
    public static String getNews() throws IOException
    {
    		
    	String inner = readFile("inner", StandardCharsets.UTF_8);
    	String outer = readFile("outer", StandardCharsets.UTF_8);
    	
        JSONObject jsonObject = new JSONObject(getNewArticles());
        JSONArray articleArray = jsonObject.getJSONArray("posts");
        //for each new article
        
        String innerConcat ="";
        for(int i =0; i < articleArray.length(); i ++){
        	JSONObject article = articleArray.getJSONObject(i);
        	
        	String innerCopy = inner;
        	innerCopy=innerCopy.replaceAll("~##Title##~", article.getString("title"));
        	innerCopy=innerCopy.replaceAll("~##Article##~", article.getString("content").substring(0,500)+ "...");
        	innerCopy=innerCopy.replaceAll("~##Link##~", article.getString("url") );
       
        	
        	innerConcat += innerCopy;
        }
        
        outer = outer.replaceAll("~##innerHTML##~", innerConcat);
        
        return outer;
        
      
       
    	
    }
    public static String getNewArticles(){
    		try{
    		URL oracle = new URL("http://theexonian.com/?json=get_recent_posts");
          BufferedReader in = new BufferedReader(
          new InputStreamReader(oracle.openStream()));

          String inputLine;
          String concat = "";
          while ((inputLine = in.readLine()) != null)
              concat += inputLine;
          	
          in.close();
          return concat;
    		}catch(Exception e){
    			System.out.println("Couldn't get data from web");
    			return null;
    		}
    }
   
    static String readFile(String path, Charset encoding) 
    		  throws IOException 
    		{
    		  byte[] encoded = Files.readAllBytes(Paths.get(path));
    		  return new String(encoded, encoding);
    		}
}