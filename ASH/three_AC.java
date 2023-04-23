import java.util.Scanner;

public class three_AC {
    public static void read(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Expression:");
        String exp = sc.nextLine();
        exp = exp.replace("*","x");
        String[] temp = exp.split("=");
        String exp2="";
        for(int i=1;i< temp.length;i++){
            exp2+=temp[i];
        }
        String[] thr_ac = new String[4];
        String[] temp2=exp2.split("x");
        temp2[0]=temp2[0].replace("(","");
        temp2[0]=temp2[0].replace(")","");
        temp2[1]=temp2[1].replace("(","");
        temp2[1]=temp2[1].replace(")","");
        System.out.println("Three Address code");
        thr_ac[0]="t1="+temp2[0];
        thr_ac[1]="t2="+temp2[1];
        thr_ac[2]="t3=t1*t2";
        thr_ac[3]="x=t3";
        System.out.println(thr_ac[0]);
        System.out.println(thr_ac[1]);
        System.out.println(thr_ac[2]);
        System.out.println(thr_ac[3]);



    }
    public static void main(String args[]){
        read();
    }

}
