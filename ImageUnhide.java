import sun.misc.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class ImageUnhide {


    public static void main(String args[]){
        //Set these
        String key = "anything";
        int buffer = 20;

        int predictedLength = 5000;
        //Set the Key and Buffer. Buffer is for length of string.

        String fin = "";
        long seed = key.hashCode();
        Random gen = new Random();
        gen.setSeed(seed);



        int entropy = 1000;
        double omega = gen.nextDouble()*entropy+buffer;


        System.out.println((int)omega);


        Path path = Paths.get("C:\\Steganograhpy\\src\\encimage.bmp");
        try {
            byte[] data = Files.readAllBytes(path);


            System.out.println(data.length);
            int a = 0;
            for(int i=(int)omega;i<data.length; i=i+(int)omega){

                if(a<predictedLength) {
                    char z = (char)data[i];
                    System.out.println("Place " +i+"---"+ data[i] + "---" + z);

                    fin = fin + z;

                }
                else{

                    break;
                }



                a++;
            }
            System.out.println("\n"+a+"Byte(s) Scanned");


        }catch(Exception e){e.printStackTrace();}






    }


}
