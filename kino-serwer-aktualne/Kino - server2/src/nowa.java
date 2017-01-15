import java.util.Scanner;

/**
 * Created by SG0226566 on 2017-01-02.
 */
public class nowa {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        while(true){
            System.out.print(".");

            if(in.hasNextLine()){
                System.out.println(in.nextLine());
            }

            System.out.println("/");
        }
    }
}
