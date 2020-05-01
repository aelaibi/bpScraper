import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Created by ael on 18/03/2016.
 */
public class FileHelper {

    String filePath = "/Users/admin/octo/BMCI/agoumy/INput";

    @Test
    public void run(){
        System.out.println("Starting ...");

        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            List<String> block  = new ArrayList<String>();
            String siege ="VIDE";
            while ((line = br.readLine()) != null) {


                if(line.contains("ETAT DES COMPTES IRREGULIERS QUOTIDIEN")){

                    //create file
                    if(block.size()>0){
                        Files.write(Paths.get("/Users/admin/octo/BMCI/agoumy/out/out-"+count+"-"+siege), block);
                    }
                    block = new ArrayList<String>();
                    count ++;
                    block.add(line);
                    continue;
                    //System.out.println(line);
                }
                if(line.contains("SIEGE  D")){
                    siege = line.substring(25);
                }
                block.add(line);
            }
            //last block
            Files.write(Paths.get("/Users/admin/octo/BMCI/agoumy/out/out-"+count+"-"+siege), block);

        } catch (IOException e) {
            e.printStackTrace();
        }

        /*try (Scanner scanner = new Scanner(new File(filePath))) {
            int count = 0;
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                if(line.contains("ETAT DES COMPTES IRREGULIERS QUOTIDIEN")){
                    System.out.println(line);
                    count ++;
                }
            }

            System.out.println("nombre de lignes : " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        System.out.println("nombre de lignes : " + count);
        System.out.println("End !");


    }



}
