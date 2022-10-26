import java.util.*;

public class factorial {
public static void main(String args[])
  {
     Scanner myObj = new Scanner(System.in);
        System.out.println("factorial of:");
        int num = myObj.nextInt();
        myObj.close();

        int n=num, fact=1;
        
        while(n>0){
        fact=fact*n;
        n--;
        }
    System.out.println("factorial is:"+fact);
  }
}