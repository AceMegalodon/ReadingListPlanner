public class ReadingListItemStoreTest {
    public static void main(String [] args) {
        ReadingListItemStore rlis = new ReadingListItemStore();
        rlis.put("a", "Allen Ginsburg");
        rlis.put("be", "Ben Jonson");
        System.out.println(">Case Insensitivity Test:");
        System.out.println(rlis.getRandomItem("a"));
        System.out.println(rlis.getRandomItem("A"));
        System.out.println(rlis.getRandomItem("Be"));
        System.out.println(rlis.getRandomItem("BE"));
        System.out.println(rlis.getRandomItem("bE"));
        System.out.println(rlis.getRandomItem("be"));
        // checks for case insensitivity
        rlis.put("be", "Beryl Bainbridge");
        rlis.put("a", "Anne Brontë");
        System.out.println(">Random-ness Test:");
        for (int i = 0; i < 10; i++) {
            System.out.println(rlis.getRandomItem("a"));
            System.out.println(rlis.getRandomItem("be"));
        }
        // checks for random-ness
        System.out.println(">Null Input Test:");
        System.out.println(rlis.getRandomItem("c") + " == null");
        
    }
}
