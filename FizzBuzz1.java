import javax.swing.text.Style;
import java.util.Scanner;

class FizzBuzz1{
public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
    int num = sc.nextInt();
    sc.close();
    // int num = 9; //将来的にはScanner使ってみたい
    if (num % 15 == 0) {
        System.out.println("FizzBuzz");
    } else if(num % 5 == 0) {
        System.out.println("Buzz");
    } else if (num % 3 == 0 ){
        System.out.println("Fizz");
    } else  {
        System.out.println(num);
    }
}}