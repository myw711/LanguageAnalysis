import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;
import java.util.*;
import java.text.*;
public class OverwriteLine {
   public static void main(String args[]) throws IOException {
      //Instantiating the File class
      String filePath = "C:\\Users\\Madigan Wilford\\Documents\\LanguageAnalysis\\federalist.txt";
      //Instantiating the Scanner class to read the file
      Scanner sc = new Scanner(new File(filePath));
      //instantiating the StringBuffer class
      StringBuffer buffer = new StringBuffer();
      //Reading lines of the file and appending them to StringBuffer
      while (sc.hasNextLine()) {
         buffer.insert(0, sc.nextLine()+System.lineSeparator());
      }
      String fileContents = buffer.toString();
      //closing the Scanner object
      sc.close();
      
      String results  = "";
      
      // create arrays with words to examine
      String[] maleWords = {"he", "him", "his", "man", "men", "father", "husband", "manly"};
      String[] femaleWords = {"she", "her", "hers", "woman", "women", "mother", "wife", "womanly"};
      String[] neutralWords = {"person", "people", "society"};
      
      // use function to input strings and tally occurences and return the number
      System.out.println("Male Results");
      for(int i = 0; i < maleWords.length; i++) {
          String word = maleWords[i];
          int num = tallyWords(fileContents, word);
          results = results + "\n" + num;
          System.out.println(word + ": " + num + " occurences");
        }
      
      System.out.println("\nFemale Results");
      for(int i = 0; i < femaleWords.length; i++) {
          String word = femaleWords[i];
          int num = tallyWords(fileContents, word);
          results = results + "\n" + num;
          System.out.println(word + ": " + num + " occurences");
        }
         
      System.out.println("\nNeutral Results");
      for(int i = 0; i < neutralWords.length; i++) {
          String word = neutralWords[i];
          int num = tallyWords(fileContents, word);
          results = results + "\n" + num;
          System.out.println(word + ": " + num + " occurences");
        }
      //instantiating the FileWriter class
      FileWriter writer = new FileWriter("output.txt");      
      writer.append(results);
      writer.flush();
   }
   
   private static int tallyWords(String file, String word) {
       int tally = 0;               
       int fromIndex = 0;
       
       file = file.toLowerCase();
       file = " header " + file + " footer ";
       //System.out.println("test file: " + file);
       word = word.toLowerCase();
       
       
       String pattern = "[\\p{P}\\p{S}\\s0-9]"+word+"[\\p{P}\\p{S}\\s0-9]";
       Pattern p=Pattern.compile(pattern);
       Matcher m=p.matcher(file);
           while(m.find()) {
               tally++;
            }
            
       
       //System.out.println("Tally being returned = " + tally);
       return tally;
 
   }
}