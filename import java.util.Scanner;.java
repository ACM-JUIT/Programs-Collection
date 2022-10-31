import java.util.Scanner;  
   
public class PyramidPattern1  
{  
    public static void main(String ar[])  
    {  
        Scanner s = new Scanner(System.in);  

        System.out.println("Enter number of rows for the Pyramid:");  
   
        int nrows = s.nextInt();  
   
        int rowCount = 1;  
   
        System.out.println("Pyramid Pattern: ");  
    
   
        for (int i = nrows; i > 0; i--)  
        {  
   
            for (int j = 1; j <= i; j++)  
            {  
                System.out.print(" ");  
            }   
   
            for (int j = 1; j <= rowCount; j++)  
            {  
                System.out.print(rowCount+" ");  
            }  
   
            System.out.println();  
   
   
            rowCount++;  
        }  
    }  
}  