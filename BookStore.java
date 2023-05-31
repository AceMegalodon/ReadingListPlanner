import java.util.HashMap; 
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

class BookStore extends ReadingListItemStore {
 
 private static Map<String, List<String>> readingListHashMap = new HashMap<String, List<String>>();
    
    public BookStore() {}
    
    public BookStore(String file) throws IOException{
     
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String item = reader.readLine();
            while(item != null) {
                if(item == null) {break;}
                String key = item.substring(0,1);
                item = item.toLowerCase();
                this.put(key.toLowerCase(), item);
                item = reader.readLine();
                }
            }
        catch (IOException exception) {
            System.err.println(exception);
        }
        finally {
            reader.close();
        }
    
    }
    
    public BookStore(String file, int maxPrefixLength) throws IOException {
        
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String item = reader.readLine();
            while(item != null) {
                if(item == null) {break;}
                for (int i = 0; maxPrefixLength >= i; i++) {
                    String key = item.substring(0,i);
                    item = item.toLowerCase();
                    this.put(key.toLowerCase(), item);
                    }
                item = reader.readLine();
            }
        }
        catch (IOException exception) {
            System.err.println(exception);
        }
        finally {
            reader.close();
        }
    
    }
    public String getRandomItem(String key) {
        key = key.toLowerCase();
        if (readingListHashMap.get(key) == null) {return null;}
        Random rand = new Random();
        List<String> stringList = readingListHashMap.get(key);
        int randomNumber = rand.nextInt(0, stringList.size());
        String string = stringList.get(randomNumber);
        String bookType = "";
        int resultantNumber = Integer.valueOf(string.substring(string.length()-4, string.length()));
        if (resultantNumber >= 1990) {
            string += " (contemporary)";
        }
        else if (resultantNumber >= 1900) {
            string += " (modern)";
        } 
        else if (resultantNumber < 1900 && resultantNumber > 0) {
            string += " (classic)";
        }
        else { 
            string += " error type ";
            System.out.println("Error");
            }
        return string;
    }
    public void put(String key, String item) {
        key = key.toLowerCase();
        item = item.replaceFirst(key, key.toUpperCase());
        List<String> list = new ArrayList<String>();
        list.add(item);
        if (readingListHashMap.get(key) != null) {
            for (String s : readingListHashMap.get(key)) {
                    list.add(s);
                }
        }
        readingListHashMap.put(key, list);
    }
}
