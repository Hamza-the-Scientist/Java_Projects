import java.util.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class imageCompresser {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // Declare all the variables
        File originImage;
        int qualityOption;
        float Quality = 0.5f;
        int imageCount;
        String Address;

        //Input from the user to choice the option the automatic or manuasl quality compressiom
        System.out.println("------------------------------------------------------------------------");
        System.out.print("1. Automatic Quality Compression \n2. Manual Quality Compression");
        System.out.print("\nEnter the choice : ");
        qualityOption = sc.nextInt();

        if(qualityOption == 1 || qualityOption == 2){
        } else{
            System.out.println("Invalid slected Option.");
            return;
        }
        sc.nextLine();
        //Input From the user to Enter the option to select single or Multiple Images.
        System.out.println("------------------------------------------------------------------------");
        System.out.print("You want to Compressed \n 1. Single image \n 2. Multiple image \n");
        System.out.print("Enter the option : ");
        int Option = sc.nextInt(); 
        sc.nextLine();
    
        if(Option == 1 || Option == 2){//Check the user enter the option Single or Multiple Images
            switch (Option) {
                case 1: 
                        if(qualityOption == 2){ // if user select 2 means they want to enter manual quality so the this block of code execute
                            System.out.println("------------------------------------------------------------------------");
                            System.out.print("Enter the Image Quality you want to reduced in Percetage (40,50) = ");
                            Quality =  1 - sc.nextFloat()/100;
                            sc.nextLine();
                        }        

                        System.out.println("------------------------------------------------------------------------");
                        System.out.print("Enter the Address of the image : ");
                        Address = sc.nextLine();
                        originImage = new File(Address);

                        if (!originImage.exists() || !originImage.isFile()) { // Check the image adrees is valid or not
                            System.out.println("The provided file does not exist.");
                            return;
                        }
                    try{
                        File compressedImage = new File("F:\\Javafx\\imageCompresser\\CompressedImage.jpg");
                        Compressed_the_image(originImage, compressedImage, Quality);
                        System.out.println("Done!!");
                    } catch (IOException e){
                        System.out.println("Invalid Argumnet!");
                    }    
                break;
                case 2:
                    System.out.print("\nHow many images tou have to compressed : "); // input from user to enter the no of images
                    imageCount = sc.nextInt();
                    sc.nextLine();
                    System.out.println();

                    if(qualityOption == 2){ // if user select 2 means they want to enter manual quality so the this block of code execute
                    System.out.print("Enter the Image Quality you want to reduced in Percetage (40,50) = ");
                    Quality =  1 - sc.nextFloat()/100;
                    sc.nextLine();
                    }

                    List<String> addresses = new ArrayList<>();
                    for (int i = 1; i <= imageCount; i++) { 
                        System.out.print("Enter the address of image " + i + ": ");
                        Address = sc.nextLine();
                        addresses.add(Address);
                    }

                    for (int i = 1; i <= imageCount; i++) {
                        File imageFile = new File(addresses.get(i - 1));
                        if (!imageFile.exists() || !imageFile.isFile()) { // Checking the address path is valid or not
                            System.out.println("The provided file does not exist for image " + i);
                            continue;
                        }
                        try{
                            File compressedImage = new File("F:\\Javafx\\imageCompresser\\CompressedImage"+ i +".jpg");
                            Compressed_the_image(imageFile, compressedImage, Quality);
                             System.out.println("Done!!" + "Iamge " + i);
                         } catch (IOException e){
                             System.out.println("Invalid Quality!");
                         }
                    }
                    break;
            }
        } else {
            System.out.println("Invalid Selected Option.");
            return;
        }
    sc.close(); // Scanner Close
    
    }
    // Function to Compressed the Image
    public static void Compressed_the_image(File originImage, File compressedImage, double compressionQuality) throws IOException{
        
        RenderedImage image = ImageIO.read(originImage);
        ImageWriter jpegWriter = ImageIO.getImageWritersByFormatName("jpg").next();

        ImageWriteParam jpegwWriteParam = jpegWriter.getDefaultWriteParam();
        jpegwWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        jpegwWriteParam.setCompressionQuality((float)compressionQuality);

        try(ImageOutputStream output = ImageIO.createImageOutputStream(compressedImage)){
            jpegWriter.setOutput(output);
            IIOImage outputImage = new IIOImage(image, null, null);
            jpegWriter.write(null, outputImage, jpegwWriteParam);
            jpegWriter.dispose();
        }
    }
}