import java.io.*;
import java.util.*;

public class AnagramSolver {

    private AnagramSolver() {};
    /*
     * 1. Write the contents of the file into an Arraylist.
     * 2. Loop through Arraylist with fori
     * 3. Set currentWord to item i of the Arraylist
     * 4. Set tempWord to item i+1 of the Arraylist
     * 5. Loop through Arraylist with forj
     * 6. If sortedCurrentWord == sortedTempWord, put into the Hashmap Curren
     */
    /**
     * Input: name of text file (containing English words).
     * Output: a hashmap of lists of words that are anagrams.
     * @param filename
     * @return
     */
    public static HashMap<String, ArrayList<String>> anagrams(String filename) {
        HashMap<String, ArrayList<String>> anagrams = new HashMap<String, ArrayList<String>>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            String key;
            String word;
            while (scanner.hasNextLine()) {
                word = scanner.nextLine();
                key = sortString(word);
                if (!anagrams.containsKey(key)) { // This if statement - Suggested by Poe
                    anagrams.put(key, new ArrayList<>());
                }
                anagrams.get(key).add(word);
            }
            Object[] nothing = anagrams.values().toArray(); // Nothing is an array holding all the values in hashmap
            String item;
            for (int i = 0; i < nothing.length; i++) {
                item = nothing[i].toString();
                if (item.length() == 1) {
                    anagrams.remove(i);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not access text file.");
            e.printStackTrace();
        }
        return anagrams;
    }

    private static String sortString(String inpustringtString) { // I got help from Poe to use this method
        char[] sorted = inpustringtString.toCharArray();
        Arrays.sort(sorted);
        return new String(sorted);
    }

    /**
     * Input: hashmap of lists of words that are anagrams.
     * Output: largest list of words in hashmap that are anagrams.
     * @param anagrams
     * @return
     */
    public static ArrayList<String> mostFrequentAnagram(HashMap<String, ArrayList<String>> anagrams) {
        ArrayList<String> largest = new ArrayList<String>(); // Thanks to Poe for suggesting this solution
        for (ArrayList<String> set : anagrams.values()) {
            if (set.size() > largest.size()) {
                largest = set;
            }
        }
        return largest;
    }

    /**
     * Input: hashmap of lists of words that are anagrams.
     * Output: prints all key value pairs in the hashmap.
     * @param anagrams
     */
    public static void printKeyValuePairs(HashMap<String, ArrayList<String>> anagrams) {
        System.out.println(anagrams.entrySet());
    }

    public static void main(String[] args) {
        System.out.println(anagrams("/Users/oliverc/Desktop/11CS S2/CS.11.08.HashMaps.Lab.Anagrams-master/src/main/resources/words_alpha.txt").entrySet());
        System.out.println(mostFrequentAnagram(anagrams("/Users/oliverc/Desktop/11CS S2/CS.11.08.HashMaps.Lab.Anagrams-master/src/main/resources/words_alpha.txt")));
    }

}

