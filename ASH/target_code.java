import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class target_code {
    public static void parse() throws FileNotFoundException {
        File file = new File("C:\\Users\\Ashlyn\\Desktop\\Basic Java programs\\pass2\\src\\pass1.txt");
        Scanner sc = new Scanner(file);
        String[] prog = new String[30];
        String[] LC = new String[12];
        String[] sym = new String[4];
        int sym_count=0;
        int count = 0;
        boolean trip=true;

        //Input Reading from file
        while(sc.hasNext()){
            prog[count]=sc.nextLine();
            String[] temp = prog[count].split("[\\s]");
            if(count == 0) {
                LC[count] = "0";
            }
            else{
                LC[count] = temp[0];
                prog[count]=prog[count].replace(temp[0],"");
            }
            //System.out.println(prog[count]);
            count++;
        }

        //Output part
        System.out.println("========================Target Code=======================");
        for(int i=0;i< prog.length;i++){
            if(prog[i]==null){
                break;
            }
            if(prog[i].contains("(AD,")){
                System.out.println("-   -   -");
            }else{
                String[] temp = prog[i].split("[\\s]");
                for(int j=0;j< temp.length;j++){
                    if(temp[j].contains("(S,") && trip==true){
                        sym[sym_count]=LC[i];
                        System.out.print("-   -   -");
                        sym_count++;
                        break;
                    }else if(temp[j].contains("(IS,")){
                        System.out.print(temp[j].charAt(4));
                        System.out.print(temp[j].charAt(5));
                        if(temp.length==3)
                            System.out.print("\t00");
                        trip=false;
                    }else if(temp[j].contains("(RG,")){
                        System.out.print("\t");
                        System.out.print(temp[j].charAt(4));
                        System.out.print(temp[j].charAt(5));
                        trip=false;
                    }else if(temp[j].contains("(S,") && trip==false){
                        System.out.print("\t");
                        int ind = Integer.parseInt(Character.toString(temp[j].charAt(3)));
                        System.out.print(sym[ind]);
                    }
                }
                System.out.println();
                trip=true;
            }
        }

    }
    public static void main(String args[]) throws FileNotFoundException{
        parse();
    }
}
