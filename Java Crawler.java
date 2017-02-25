 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is390;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Rod Flores
 */

public class Is390 {
    
    public static List<String> seeds = new ArrayList<String>(Arrays.asList("https://en.wikipedia.org/wiki/Mammal", 
            "https://en.wikipedia.org/wiki/Yinotheria",
            "https://en.wikipedia.org/wiki/Carnivora",
            "https://en.wikipedia.org/wiki/Even-toed_ungulate",
            "https://en.wikipedia.org/wiki/Dog",
            "https://en.wikipedia.org/wiki/Cat"
            
            ));
    public static ArrayList<String> terms = new ArrayList<String>(Arrays.asList("dog",
    "cat",
    "warm",
    "mammal",
    "warm-blooded",
    "endothermic",
    "hair",
    "fur",
    "ape",
    "Carnivora",
    "mammalia",
    "placenta",
    "mammary",
    "nurse",
    "give birth",
    "live young",
    "afroinsecttiphilia",
    "paenungulata",
    "boreoeutheria",
    "glires",
    "Marsupial",
    "even-toed ungulates",
    "mammalian",
    "cub",
    "vertebrate",
    "milk",
    "sweat glands",
    "temperature regulation",
    "animal",
    "bird",
    "reptile",
    "dinosaur",
    "fish",
    "environment",
    "scale",
    "adapt",
    "species",
    "claw",
    "teeth",
    "egg",
    "kingdom",
    "shell",
    "cell",
    "organism",
    "domain"
            
    ));
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[]args) throws IOException {
        
        
        
        Queue<String> q = new LinkedList<String>();
        List<String> visited = new ArrayList();
        String wiki = "wikipedia.org"; //WIKI URL CHECK STRING
        
        int count = 0;
        
        for( String seed : seeds){
            q.add(seed);
            System.out.println("This is a seed URL: " + seed);
        }
        
        while(!q.isEmpty() && count<=502){
            String url = q.remove();
            
            if(!visited.contains(url))
            {
                if(!url.toLowerCase().contains(wiki.toLowerCase())){// CHECKING TO SEE IS URL HAS THE STRING WIKIPEDIA.ORG
                    visited.add(url);
                    
                }
                else if(url.contains("Template") ||url.contains("File")||url.contains("Help")){
                    visited.add(url);
                }
                else{
                try{
                Document doc = Jsoup.connect(url).get();
                 System.out.println(doc.title());
                
                
                visited.add(url);
                
                
                int rcount = 0;
                String doc2 = doc.toString();
                for(String term : terms){
                    if(doc2.toLowerCase().contains(term.toLowerCase())){  // RELEVANCE CHECKING CASE INSENSITIVE
                        rcount++;                        
                    }                    
                }
                if(rcount >=2){
                    count++;
                
                
                    System.out.println(rcount + "- Terms  &&  Total Crawled Count:" + count);
                    System.out.println(url + "\n\n");
                
                    Elements links = doc.select("a[href]");
                
                
                    for(Element link : links){
                        String url2 = link.absUrl("href");  
                        for(String realUrl : visited){
                            if(url2.contains(realUrl)){
                                break;
                            }
                            else{
                                if(count>450){
                                    continue;
                                }
                                else{
                                q.add(url2);
                                }
                                
                            }
                        }
                    
                    }
                   }
                
                
                try{
                    
                File file = new File (count + "-" + doc.title()); // EACH NEW PAGE HAS A CORRESPONDING NEW FILE WITH A CORRECT TITLE
                FileWriter filewriter = new FileWriter(file);
                
                filewriter.write(rcount + "- Related Terms  &&  Total Pages Crawled Count:" + count + "\n");
                filewriter.write(url + "\n\n");
                filewriter.write(doc2 + "\n\n");
                
                filewriter.flush();
                
                }
                catch(IOException e){
                e.printStackTrace();
                }
                
                }
                catch(MalformedURLException e){
                    continue;
                }
               catch(IOException e){
                   continue;
                }
                }
            }
            
        }
        
        
    }
    
}
