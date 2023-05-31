import java.util.HashMap; 
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

public class ReadingListPlanner {

    private static ReadingListItemStore rlis;
    private static PrizeWinnerStore pws;
    private static BookStore bs;
    
    public static List<String> generate(String input) {
        char [] inputArray = input.toCharArray();
        List<String> stringList = new ArrayList<String>();
        for (int i = 0; input.length() >= i+1;) {
            if (input.length() >= i + 1) {
                stringList.add(rlis.getRandomItem(String.valueOf(inputArray[i])));
                i++;
            }
            if (input.length() >= i + 1) {
                stringList.add(pws.getRandomItem(String.valueOf(inputArray[i])));
                i++;
            }
            if (input.length() >= i + 1) {
                stringList.add(bs.getRandomItem(String.valueOf(inputArray[i])));
                i++;
            }
        }
        return stringList;
    }
    
    public static List<String> generate(String input, int maxPrefixLength) {
        List<String> stringList = new ArrayList<String>();
        String key = "";
        String randomItem = "";
        int i = 0;
        int itemDeterminer = 0;
        for (int q = maxPrefixLength; input.length() != i && q > 0; q = q - 1) {
            if(input.length() >= q) {
                key = input.substring(i, q);
                if (rlis.containsKey(key.toLowerCase())) {
                    i = q;
                    q = q + maxPrefixLength + 1;
                    if (itemDeterminer == 0) {
                        randomItem = rlis.getRandomItem(key.toLowerCase());
                        itemDeterminer++;
                    }
                    else if (itemDeterminer == 1) {
                        randomItem = pws.getRandomItem(key.toLowerCase());
                        itemDeterminer++;
                    }
                    else {
                        randomItem = bs.getRandomItem(key.toLowerCase());
                        itemDeterminer = 0;
                    }
                    // this means that there will always be atleast as many  prize winners and books
                    // as well as min 1 author.
                    stringList.add(randomItem);
                }
            }
        }
        return stringList;
    }
    
    public static void main(String[] args) throws IOException{
        try {
            if (args.length == 2) {
                int maxPrefixLength = Integer.valueOf(args[1]);
                rlis = new ReadingListItemStore("authors.txt", maxPrefixLength);
                pws = new PrizeWinnerStore("prize-winners.txt", maxPrefixLength);
                bs = new BookStore("books.txt", maxPrefixLength);
                List<String> stringList = ReadingListPlanner.generate(args[0].toLowerCase(), maxPrefixLength);
                for (String s : stringList) {
                    System.out.println(s);
                }
            }
            else {
                rlis = new ReadingListItemStore("authors.txt");
                pws = new PrizeWinnerStore("prize-winners.txt");
                bs = new BookStore("books.txt");
            }
            if (args.length == 1) {
                List<String> stringList = ReadingListPlanner.generate(args[0].toLowerCase());
                for (String s : stringList) {
                    System.out.println(s);
                }
            }
        }
        catch (IOException exception) {
            System.err.println(exception);
        }
    }
}
