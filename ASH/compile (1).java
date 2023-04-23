import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class compile {

    static int LC=0;
    static String st[] = new String[50];
    static int lin_count[] = new int[20];
    static int li_co=0;
    static String MOT[][] = {
            {"STOP","(IS,00)"}, {"ADD","(IS,01)"}, {"SUB","(IS,02)"},{"MULTI","(IS,03)"},{"MOVER","(IS,04)"},
            {"MOVEM","(IS,05)"}, {"COMP","(IS,06)"}, {"BC","(IS,07)"}, {"DIV","(IS,08)"}, {"READ","(IS,09)"},
            {"PRINT","(IS,10)"}
    };
    static String REG[][] = {
            {"AREG","(RG,01)"},{"BREG","(RG,02)"},{"CREG","(RG,03)"},{"DREG","(RG,04)"}
    };
    static String[][] SymTab = new String[10][3];

    public static void read() throws FileNotFoundException{
        File file = new File("C:\\Users\\Ashlyn\\Desktop\\Basic Java programs\\compile\\src\\prog.txt");

        int sym=0;

        try {
            Scanner sc = new Scanner(file);

            for(int i=0; sc.hasNextLine();i++){
                st[i] = sc.next();
            }
            System.out.println("Pass 1");
            System.out.println("LC\t Object Code");
            for(int i=0;i<st.length;i++){

               // System.out.println(st[i].contains("REG"));
                if(st[i].equals("START")){
                    LC = Integer.parseInt(st[i+1]);
                    lin_count[li_co] = LC;
                    li_co++;
                    System.out.print("\t (AD,01) ");
                    System.out.print("(c,"+LC+")");
                    System.out.println();
                    i++;
                }
                else if(st[i].equals("DS")){
                    SymTab[sym][0] = st[i-1];
                    SymTab[sym][1] = Integer.toString(LC);
                    SymTab[sym][2] = Integer.toString(sym);
                    System.out.println(LC+"\t "+"(S,"+sym+")"+"(DL,01)"+"(c,"+st[i+1]+")");
                    LC++;
                    lin_count[li_co] = LC;
                    li_co++;
                    sym++;
                }
                else if(st[i].equals("END")){
                    System.out.println();
                    System.out.println(LC+"\t (AD,02)");
                }
                else if(st[i].contains("REG")){
                    st[i] = st[i].replace(",","");
                   searchREG(st[i]);
                }
                else if(st[i].equals("A")){
                    if(st[i+1].equals("DS")){
                        continue;
                    }else{
                        System.out.print("(S,"+SymTab[0][2]+")");
                    }
                }
                else if(st[i].equals("B")){
                    if(st[i+1].equals("DS")){
                        continue;
                    }else{
                        System.out.print("(S,"+SymTab[1][2]+")");
                    }
                }
                else if(st[i].equals("C")){
                    if(st[i+1].equals("DS")){
                        continue;
                    }else{
                        System.out.print("(S,"+SymTab[2][2]+")");
                    }
                }
                else{
                        searchMOT(st[i]);
                    }

                }

        }

        catch(Exception e){
            System.out.println();
        }
        System.out.println("\nSYMBOL TABLE");
        System.out.println("Index\tSymbol\tAddress");
        for(int i=0;i< sym;i++){
            System.out.println(SymTab[i][2]+"\t\t"+SymTab[i][0]+"\t\t"+SymTab[i][1]);
        }

        //targetcode();


    }
    public static int searchMOT(String st){

        for(int i=0;i< MOT.length;i++){

            if(st.equals(MOT[i][0])){
                if(LC!=504)
                    System.out.println();

                System.out.print(LC+"\t"+" "+MOT[i][1]);
                LC++;
                lin_count[li_co] = LC;
                li_co++;
                return 0;
            }
        }
        return 0;
    }
    public static void searchREG(String st){

        for(int i=0; i< REG.length;i++){
            if(st.equals(REG[i][0])){
                System.out.print(REG[i][1]);

            }
        }
    }

//    static int ind;
//    public static void targetcode(){
//        int x;
//        int temp=0;
//        int lc=0;
//        System.out.println("\nPass 2");
//        System.out.println("LC\t\tTarget Code");
//        for(int i=0;i< st.length;i++){
//
//            if(st[i]==null)
//                break;
//
//            x=validate(st[i],0);
//            if(x==0){
//                System.out.print(lin_count[lc]+"\t\t"+MOT[ind][1].charAt(4));
//                System.out.print(MOT[ind][1].charAt(5)+" ");
//
//
//
//                if(validate(st[i+1],1)==1){
//                    System.out.print("00 "+SymTab[ind][1]);
//                    System.out.println();
//                    temp=1;
//                    lc++;
//                    continue;
//                }
//
//                x=validate(st[++i].replace(",",""),2);
//
//                if(x==2){
//                    System.out.print(REG[ind][1].charAt(4));
//                    System.out.print(REG[ind][1].charAt(5)+" ");
//
//                }
//                x=validate(st[++i],1);
//                lc++;
//                    if(x==1){
//                        System.out.print(SymTab[ind][1]);
//                    }
//            }else if(temp==1){
//                temp = 0;
//                continue;
//            }else{
//
//
//                if(i==0) {
//                    System.out.println("\t\t- - -");
//                    i += 1;
//                }
//                else {
//                    System.out.println(lin_count[lc]+"\t\t- - -");
//                    i += 2;
//                    lc++;
//                }
//                continue;
//            }
//            System.out.println();
//        }
//
//        }
//
//
//    public static int validate(String str, int x){
//        if(x==0){
//            for(ind=0;ind< MOT.length;ind++){
//                if(str.equals(MOT[ind][0]))
//                    return 0;
//            }
//        }else if(x==1){
//            for(ind=0;ind< SymTab.length;ind++){
//                if(str.equals(SymTab[ind][0]))
//                    return 1;
//            }
//        }else{
//            for(ind=0;ind<REG.length;ind++){
//                if(str.equals(REG[ind][0]))
//                    return 2;
//            }
//        }
//        return -1;
//    }

    public static void main(String args[]) throws FileNotFoundException{
        read();
    }
}
