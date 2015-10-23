import sun.misc.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class ImageHide {

    public static void main(String args[]){
        //Set these
        String key = "anything";
        int buffer = 20;
        int random = 200;
        //Set the Key and Buffer. Buffer is for length of string. Random is how many times to make random bytes


        String toEncrypt = "Very Secret Text";

        long seed = key.hashCode();
        Random gen = new Random();
        gen.setSeed(seed);

        int entropy = 1000;
        double omega = gen.nextDouble()*entropy+buffer;


        System.out.println((int)omega);


        Path path = Paths.get("C:\\Steganograhpy\\src\\panda.bmp");
        try {
            byte[] data = Files.readAllBytes(path);
            byte[] data_d = data;

            System.out.println(data.length);
            int aval = (int) (data.length / omega);

            System.out.println("Bytes can write with current Omega: " + aval + "\n");
            if (aval < toEncrypt.length()){
                System.out.println("Failed. Not enough Bytes. Terminating");
                System.exit(0);
              }
            else{
                System.out.println("Success, enough bytes. toEncrypt: "+toEncrypt.length());
            }

            //Randomizer

            System.out.println((((int)omega/5)+((int)(Math.random()*5))));
            int b = 0;
            for(int i=(int)omega;i<data.length; i=i+(((int)omega/4)+((int)(Math.random()*20)))){
                if(b<random) {
                    Random r = new Random();
                    char f = (char) (r.nextInt(47) + 48);
                    data_d[i] = (byte)f;

                    System.out.println(i);

                }
                else{
                    break;
                }
                b++;
            }
            System.out.println("Randomized "+b+" Byte(s)");

            //Actual byte-setter
            int a = 0;
            for(int i=(int)omega;i<data.length; i=i+(int)omega){

                System.out.println("old=" + data[i]);

                if(a<toEncrypt.length()) {
                    data_d[i] = (byte)toEncrypt.charAt(a);
                    System.out.println(("Place " +i+"---"+ data_d[i]));
                }
                else{

                    break;
                }

                System.out.println("new=" + data_d[i]);

                a++;
            }
            System.out.println("\n"+a+" Byte(s) Changed");

            FileOutputStream output = new FileOutputStream(new File("C:\\Steganograhpy\\src\\encimage.bmp"));



            output.write(data);
            output.close();
        }catch(Exception e){e.printStackTrace();}






    }





}
