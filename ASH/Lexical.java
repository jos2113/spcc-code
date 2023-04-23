import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lexical {

    static int sym_ind=0,i=0;
    static String[] out=new String[20];
    static String[] sym_tab = new String[20];
    static String[] str = new String[20];

    public static void output() throws FileNotFoundException {
        File file = new File("C:\\Users\\Ashlyn\\Desktop\\Basic Java programs\\LexicalAnalyzer\\src\\sentence.txt");
        Scanner sc = new Scanner(file);
        int lc=0;

        while(sc.hasNextLine()){
            str[lc] = sc.next();
            //System.out.println(str[lc]);
//            if(str[lc].contains(" "))
//                lc++;
            if(str[lc].contains(".")){
                lc++;
                str[lc]=Character.toString(str[lc-1].charAt(str[lc-1].length()-1));
                str[lc-1] = str[lc-1].replace(".","");
               // System.out.println(str[lc-1]);
            }
            //System.out.println(str[lc]);
            lc++;
        }
        int tokens=0;

        boolean is_null = true;

        while (!str[i].equals("$")){
            if(str[i].equals("If")){

                out[++i]="(k)";
                if(is_null){
                    out[++i]="(n,"+(++sym_ind)+")";
                    sym_tab[sym_ind] = str[i-1];
                    out[++i]="(v)";
                    out[++i]="(n,"+(++sym_ind)+")";
                    is_null=false;
                    sym_tab[sym_ind] = str[i-1];
                }else{
                    search();
                    out[++i]="(v)";
                    search();
                }
                tokens+=4;
            }else if(str[i].equals("then")){
                out[++i] = "(k)";
                out[++i] = "(a)";
                search();
                tokens+=3;
            }else if(str[i].equals(".")) {
                out[++i] = "(op)";
                tokens++;
            }

        }

        out[++i]="<eof>";
        System.out.println("Input string");
        for(i=0;i<lc;i++){
            System.out.print(str[i]+" ");
        }
        System.out.println("\n\nOutput");
        for(i=1;i<=tokens+1;i++){
            System.out.print(out[i]+" ");
        }
        System.out.println();
        System.out.println("\nSymbol table"+"\nIndex\tSymbol");
        for(i=1;i<=sym_ind;i++){
            System.out.println(i+"\t\t"+sym_tab[i]);
        }

        System.out.println("\nTotal number of tokens:"+tokens);

    }

    public static void search(){
        for(int j=0;j<sym_ind;j++){
            if(str[i].equals(sym_tab[j])){
                out[++i] = "(n,"+j+")";
                return;
            }
        }
        out[++i]="(n,"+(++sym_ind)+")";
        sym_tab[sym_ind] = str[i-1];
    }

    public static void main(String args[]) throws FileNotFoundException{
        output();
    }

}