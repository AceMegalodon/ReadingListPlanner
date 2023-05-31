import java.util.HashMap; 
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

public class ReadingListItemStore {

    private static Map<String, List<String>> readingListHashMap = new HashMap<String, List<String>>();
    
    public ReadingListItemStore() {}
    
    public ReadingListItemStore(String file) throws IOException {
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
    
    public ReadingListItemStore(String file, int maxPrefixLength) throws IOException {
        
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String item = reader.readLine();
            while(item != null) {
                if(item == null) {break;}
                for (int i = 1; maxPrefixLength >= i; i++) {
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
    
    public boolean containsKey(String key) {
        if (readingListHashMap.keySet() == null) {
            return false;
        }
        for (String i : readingListHashMap.keySet()) {
            if (i.equals(key)) {
                return true;
            }
        }
        return false;
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
    
    public String getRandomItem(String key) {
        key = key.toLowerCase();
        if (readingListHashMap.get(key) == null) {return null;}
        Random rand = new Random();
        int randomNumber = rand.nextInt(0, readingListHashMap.get(key).size());
        return readingListHashMap.get(key).get(randomNumber);
    }
    
    public static Map<String, List<String>> getReadingListHashMap () {
        return readingListHashMap;
    }
}






