import java.util.*;
public class toweofhanoi {
    public static void TowerofHanoi(int x, String src, String helper, String dest){
        if(x==1){
                System.out.println("Disc "+ x +" transferred from "+src+" to "+dest );
                return;
        }

        TowerofHanoi(x-1, src, dest, helper);
        System.out.println("Disc "+ x +" moved from "+ src +" to "+ helper);
        TowerofHanoi(x-1, helper,src, dest );
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        TowerofHanoi(x,"S", "H", "D");
    }
}
