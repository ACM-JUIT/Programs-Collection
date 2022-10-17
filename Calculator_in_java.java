package com.company;

import java.util.Objects;
import java.util.Scanner;

class CannotDivideZeroException extends Exception{
    @Override
    public String toString() {
        return "Cannot Divide by Zero!";
    }
}
class MaxInputException extends Exception {
    @Override
    public String toString() {
        return "Max Input Exceed!";
    }
}
class  MaxMultiplierReachedException extends Exception{
    @Override
    public String toString() {
        return "Max Multiplication Inout Reached!";
    }
}

public class Calculator_in_java {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        float a;
        float b;
        String c;
        System.out.print("Enter first number: ");
        a = sc.nextFloat();
        System.out.print("Enter operation: ");
        c = sc.next();
        System.out.print("Enter second number: ");
        b = sc.nextFloat();
        if(Objects.equals(c, "+")){
            try{
                if((a>100000 || b>100000)){
                    throw new MaxInputException();
                }
                else{
                    System.out.println("The sum of two number is: " + (a + b));
                }
            }
            catch (Exception e){
                System.out.println(e.toString());
            }
        }
        else if(Objects.equals(c, "-")){
            try{
                if((a>100000 || b>100000)){
                    throw new MaxInputException();
                }
                else{
                    System.out.println("The subtraction of two number is: " + (a - b));
                }
            }
            catch (Exception e){
                System.out.println(e.toString());
            }
        }
        else if(Objects.equals(c, "*")){
            try{
                if((a>7000 || b>7000)){
                    throw new MaxMultiplierReachedException();
                }
                else{
                    System.out.println("The product of two numbers is: " + (a*b));
                }
            }
            catch (Exception e){
                System.out.println(e.toString());
            }
        }
        else if(Objects.equals(c, "/")){
            try{
                if((a>100000 || b>100000)){
                    throw new MaxInputException();
                }
                else if((b == 0)){
                    throw new CannotDivideZeroException();
                }
                else{
                    System.out.println("The division of two numbers is: " + (a/b));
                }
            }
            catch (Exception e){
                System.out.println(e.toString());
            }
        }
        else{
            System.out.println("Invalid Input");

        }


    }
}

