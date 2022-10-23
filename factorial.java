import java.util.*;

public class factorial {
public static void main(String args[])
  {
     Scanner myObj = new Scanner(System.in);
        System.out.println("What number do you want factorial of?");
        int num = myObj.nextInt();
        myObj.close();

        int n=num, fac=1;
        
        while(n>0){
        fac=fac*n;
        n--;
        }
    System.out.println("Factorial of given number is :"+fac);
  }
}