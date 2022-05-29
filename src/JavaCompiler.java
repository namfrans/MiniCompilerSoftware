import java.util.Scanner;
import java.util.StringTokenizer;

public class JavaCompiler {
    public static char t11, t12, t21, t22, t31, t32, t41, t42;
    public static String tt1, tt2, m_div, n_div, m_mult, n_mult, m_plus, n_plus, m_minus, n_minus;
    public static String l_div1, l_div2, l_div3, l_mult1, l_mult2, l_mult3, l_plus1, l_plus2, l_plus3, l_minus1, l_minus2, l_minus3;
    public static int mult1=0, mult2=0;
    public static boolean translate = false;
    public static boolean correct = false;
    public static void main(String[] args) {
        String str1;

        Scanner input = new Scanner(System.in);
        int count = 1;

        System.out.println("                                        CTE711S COMPILER TECHNIQUES FINAL PROJECT");
        System.out.println("                                        =========================================");

        while(true){
            System.out.println("ENTER NEXT STRING === #"+count);
            System.out.println("-Every String/line must end with a semicolon (;)");
            System.out.println("-Enter String (Containing 0 to 9 and/or operators: +,/,*,-)");
            System.out.println("-Enter No Space in b/w chara & end the string with semicolon(;)ie 5-4+9*8/2; for Full(7 stages) results of compilation");
            System.out.println("-or Enter Space in b/w char with semicolon(;) at end of String ie 5 - 4 + 9 * 8 / 2 ; for the result of arithmetic expression");
            System.out.println("-Or Type 99 and press Enter to Quit:");
            str1 = input.nextLine();
            if (str1.equals("99")){
                System.exit(1);
            }
            //Fixing compiler stage 1 & 2 not working on a string without space
            String[] strArr = str1.split("");
            String joinedStr = String.join(" ", strArr);

            StringTokenizer st = new StringTokenizer(joinedStr," ");
            StringTokenizer st1 = new StringTokenizer(joinedStr," ");
            StringTokenizer st2 = new StringTokenizer(joinedStr," ");
            StringTokenizer str = new StringTokenizer(joinedStr," ");

            CharSequence semiColon = ";";
            boolean stHasSemiColon = str1.contains(semiColon);

            String K0 = " ", K1= " ", K2 = " ", K3 = " ", K4 = " ", K5 = " ", K6 = " ", K7 = " ", K8 = " ", K9 = " ";
            String E0 = " ", E1= " ", E2 = " ", E3 = " ", E4 = " ", E5 = " ", E6 = " ", E7 = " ", E8 = " ", E9 = " ";
            String zero = "0", one = "1", two = "2", three = "3", four = "4", five = "5", six = "6", seven = "7", eight = "8", nine = "9";

            //Token detect and extract
            while (str.hasMoreTokens()) {

                String token = str.nextElement().toString();

                if (token.equals(zero)) { E0 = token; K0="E0";}
                if (token.equals(one)) { E1 = token; K1="E1";}
                if (token.equals(two)) { E2 = token; K2="E2";}
                if (token.equals(three)) { E3 = token; K3="E3";}
                if (token.equals(four)) { E4 = token; K4="E4";}
                if (token.equals(five)) { E5 = token; K5="E5";}
                if (token.equals(six)) { E6 = token; K6="E6";}
                if (token.equals(seven)) { E7 = token; K7="E7";}
                if (token.equals(eight)) { E8 = token; K8="E8";}
                if (token.equals(nine)) { E9 = token; K9="E9";}
            }
            int error_flag = 0, error_flag1 = 0, error_flag2 = 0, error_flag3 = 0, error_flag4 =0, error_flag5 = 0, error_flag6 = 0, error_flag7 = 0, error_flag8 = 0, error_flag9 = 0,  error_flag10 = 0, error_flag11 = 0, error_flag12 = 0, error_flag13 = 0, error_flag14 = 0, i = 0;
            String attribute = " ", error_type0 = " ", error_type1 = " ", error_type2 = " ", error_type3 = " ", error_type4 = " ", error_type5 = " ", error_type6 = " ", error_type7 = " ", error_type8 = " ", error_type9 = " ", error_type10 = " ", error_type11 = " ", error_type12 = " ", error_type13 = " ", semi_colon_error = " ";
            String minus = "-", div = "/", plus = "+", mult = "*", questionMark = "?", dollarSign = "$", andSign = "&", percentage = "%";
            String mult_div = "*/", add_div = "+/", sub_div = "-/", add_mult = "+*", sub_mult = "-*", div_mult = "/*";
            String sub_add = "-+", div_add = "/+", mult_add = "*+", div_sub = "+-", mult_sub = "*-", add_sub = "+-";
            String slash_zero = "/0", zero_slash = "0/", and = "&", double_and = "&&", double_colons = ";;";
            String deliv0 ,deliv1, deliv2, deliv3, deliv4, deliv5, deliv6, deliv7, deliv8, deliv9;
            String rep0, rep2, rep3, rep4, rep5, rep6, rep7, rep8, rep9, rep10, rep11, rep12, rep13, rep14, rep15, rep16, rep17, rep18, rep19,rep20;
            //If semi-colon is not included.
            if (stHasSemiColon == false) {
                translate = false;
                error_flag = 1;
                semi_colon_error = "SEMANTIC ERROR- two operators (*,-,+,/) or numbers (0,1,..9) cannot be written together! Moreso semicolon (;) is needed at the end of each line.";

            }
            //LEXICAL ANALYSIS STARTS HERE
//            Handling error
            int error_count = 0;
            while (st2.hasMoreTokens()){

                String token1 = st2.nextElement().toString();

                if (str1.contains(sub_div) || str1.contains(add_mult) || str1.contains(sub_mult)
                        || str1.contains(div_mult) || str1.contains(sub_add) || str1.contains(div_add)
                        || str1.contains(mult_add) || str1.contains(div_sub)
                        || str1.contains(mult_sub) || str1.contains(add_sub)
                        || str1.contains(mult_div) || str1.contains(add_div)
                        || str1.contains(zero_slash) || str1.contains(double_colons))
                {
                    translate = false; error_flag1 = 1; error_type0 = "SEMANTIC ERROR: Use of two operators together ie */, +*, *-, +s, etc, not permitted ";
                }
                if (token1.equals(and) || token1.equals(double_and))
                {
                    translate = false; error_flag2 = 1; error_type1 = "SEMANTIC ERROR: Use of and-sign or double and-sign ie &, && etc, not permitted";
                }
                if (token1.equals(slash_zero)) {
                    translate = false; error_flag3 = 1; error_type2 = "SEMANTIC ERROR: division by zero ie 6/0, not permitted";
                }
                if ( error_flag == 1 ) {
                    translate = false; error_flag4 = 1; error_type3 = "SEMANTIC ERROR- two operators (*,-,+,/) or numbers (0,1,..9) cannot be written together! Moreso semicolon (;) is needed at the end of each line";
                }
                if (token1.equals(questionMark) || token1.equals(dollarSign) || token1.equals(andSign) || token1.equals(percentage)){
                    translate = false; error_flag5 = 1;error_type4 = "SYNTAX ERROR: use of special characters i.e ?,$,&,%";
                }
                if (str1.isEmpty()){
                    translate = false; error_flag6 = 1; error_type5 = "Error-Entering should not be empty!";
                }
                if(str1.contains(" ")){
                    translate = false; error_flag7 = 1; error_type6 = "Spaces in between String will only show the result of arithmetic expression!";
                }
                if (token1.matches("^[a-zA-Z]*$")){
                    translate = false; error_flag8 = 1; error_type7 = "SYNTAX ERROR: Alphabets a-z or A-Z not allowed. String should be Nos 0,1,2..9 & operators +,-,*,/";
                }
                if (token1.equals("=")){
                    translate = false; error_flag9 = 1; error_type8 = "SYNTAX ERROR: Assignment symbol not allowed. String should be Nos 0,1,2..9 & operators +,-,*,/";
                }
                if (str1.matches("^[0-9]{2}[-+/*][0-9]+[;]$")){
                    translate = false; error_flag10 = 1; error_type9 = "SEMANTIC ERROR- two operators (*,-,+,/) or numbers (0,1,..9) cannot be written together! Moreso semicolon (;) is needed at the end of each line";
                }
                if (!str1.contains("+") && !str1.contains("-") && !str1.contains("*") && !str1.contains("/")){
                    translate = false; error_flag11 = 1; error_type10 = "Error-Invalid String! There is no operator in the String ( ie +, /, -, *,)";
                }
                if (str1.matches("^[0-9][-+/*][0-9]{2}+[;]$")){
                    translate = false; error_flag12 = 1; error_type11 = "SEMANTIC ERROR- two operators (*,-,+,/) or numbers (0,1,..9) cannot be written together! Moreso semicolon (;) is needed at the end of each line";
                }
                if ( error_flag1 == 0 && error_flag2 == 0 && error_flag3 == 0 && error_flag4 == 0 &&
                        error_flag5 == 0 && error_flag6 == 0 && error_flag7 == 0 && error_flag8 == 0 &&
                        error_flag9 == 0 && error_flag10 == 0 && error_flag11 == 0 && error_flag12 == 0)
                {
                    correct = true;
                    translate = true;
                }else{
                    correct = false;
                }
            }
            if (translate == true){
                System.out.println("");
                System.out.println("------------> STAGE 1: LEXICAL ANALYSIS-Scanner <------------");
                System.out.println("SYMBOL TABLE WHICH COTNTAINS TOKENS IDENTIFIED AND THEIR ATTRIBUTEs:");
                System.out.println("");
            }
            while (st.hasMoreTokens())
            {
                i = i+1;
                String stTokens = st.nextElement().toString();
                //Token attribute assignment
                if (translate == true){
                    if (stTokens.equals(zero)  || stTokens.equals(one) || stTokens.equals(two) || stTokens.equals(three) || stTokens.equals(four) || stTokens.equals(five) || stTokens.equals(six) || stTokens.equals(seven) || stTokens.equals(seven) || stTokens.equals(eight) || stTokens.equals(nine)) {
                        attribute = "identifier";
                    }
                    if (stTokens.equals(div) || stTokens.equals(minus) || stTokens.equals(plus) || stTokens.equals(mult)) {
                        attribute = "Operator";
                    }
                    System.out.println("TOKEN" + "#" + i + " " + stTokens + " " + attribute);
                }
            }
            if (translate == true){
                System.out.println("THE TOTAL NUMBER OF TOKENS: " + st1.countTokens());
                //End of Lexical analysis
                System.out.println("");
            }

            //SYNTAX ANALYSIS STARTS HERE
            if (translate == true){
                System.out.println("GIVEN THE GRAMMAR: E=E1 | E=E1*E2 | E=E1+E2 | E=digit | E={0,1,2,3,4,5,6,7,8,9}");
                System.out.println("------------> STAGE 2: SYNTAX ANALYSIS-Parser <------------");
                System.out.println("DERIVATION FOR : " + str1);
                deliv0 = str1.replace(E0, K0);
                deliv1 = deliv0.replace(E1, K1);
                deliv2 = deliv1.replace(E2, K2);
                deliv3 = deliv2.replace(E3, K3);
                deliv4 = deliv3.replace(E4, K4);
                deliv5 = deliv4.replace(E5, K5);
                deliv6 = deliv5.replace(E6, K6);
                deliv7 = deliv6.replace(E7, K7);
                deliv8 = deliv7.replace(E8, K8);
                deliv9 = deliv8.replace(E9, K9);
                System.out.println(deliv9);
                if (E0.equals(zero)) {
                    rep0 = deliv9.replace("E0", "digit0");
                    System.out.println(rep0);
                    deliv9 = rep0;
                }
                if (E1.equals(one)) {
                    rep2 = deliv9.replace("E1", "digit1");
                    System.out.println(rep2);
                    deliv9 = rep2;
                }
                if (E2.equals(two)) {
                    rep3 = deliv9.replace("E2", "digit2");
                    System.out.println(rep3);
                    deliv9 = rep3;
                }
                if (E3.equals(three)) {
                    rep4 = deliv9.replace("E3", "digit3");
                    System.out.println(rep4);
                    deliv9 = rep4;
                }
                if (E4.equals(four)) {
                    rep5 = deliv9.replace("E4", "digit4");
                    System.out.println(rep5);
                    deliv9 = rep5;
                }
                if (E5.equals(five)) {
                    rep6 = deliv9.replace("E5", "digit5");
                    System.out.println(rep6);
                    deliv9 = rep6;
                }
                if (E6.equals(six)) {
                    rep7 = deliv9.replace("E6", "digit6");
                    System.out.println(rep7);
                    deliv9 = rep7;
                }
                if (E7.equals(seven)) {
                    rep8 = deliv9.replace("E7", "digit7");
                    System.out.println(rep8);
                    deliv9 = rep8;
                }
                if (E8.equals(eight)) {
                    rep9 = deliv9.replace("E8", "digit8");
                    System.out.println(rep9);
                    deliv9 = rep9;
                }
                if (E9.equals(nine)) {
                    rep10 = deliv9.replace("E9", "digit9");
                    System.out.println(rep10);
                    deliv9 = rep10;
                }
                if (E0.equals(zero)) {
                    rep11 = deliv9.replace("digit0", "0");
                    System.out.println(rep11);
                    deliv9 = rep11;
                }
                if (E1.equals(one)) {
                    rep12 = deliv9.replace("digit1", "1");
                    System.out.println(rep12);
                    deliv9 = rep12;
                }
                if (E2.equals(two)) {
                    rep13 = deliv9.replace("digit2", "2");
                    System.out.println(rep13);
                    deliv9 = rep13;
                }
                if (E3.equals(three)) {
                    rep14 = deliv9.replace("digit3", "3");
                    System.out.println(rep14);
                    deliv9 = rep14;
                }
                if (E4.equals(four)) {
                    rep15 = deliv9.replace("digit4", "4");
                    System.out.println(rep15);
                    deliv9 = rep15;
                }
                if (E5.equals(five)) {
                    rep16 = deliv9.replace("digit5", "5");
                    System.out.println(rep16);
                    deliv9 = rep16;
                }
                if (E6.equals(six)) {
                    rep17 = deliv9.replace("digit6", "6");
                    System.out.println(rep17);
                    deliv9 = rep17;
                }
                if (E7.equals(seven)) {
                    rep18 = deliv9.replace("digit7", "7");
                    System.out.println(rep18);
                    deliv9 = rep18;
                }
                if (E8.equals(eight)) {
                    rep19 = deliv9.replace("digit8", "8");
                    System.out.println(rep19);
                    deliv9 = rep19;
                }
                if (E9.equals(nine)) {
                    rep20 = deliv9.replace("digit9", "9");
                    System.out.println(rep20);
                }
                //End of Syntax analysis
                System.out.println("");
            }

            //SEMANTIC ANALYSIS STARTS HERE
            System.out.println("------------>STAGE 3: SEMANTIC ANALYSIS<------------");
            if (correct == true) {
                System.out.println("CONCLUSION-->This expression:  "+str1+" is Syntactically and Sematically correct");
            }
            if (correct == false) {
                System.out.println("CONCLUSION-->Wrong expression: "+str1+"  No Derivation done! PLS RE-ENTER A VALID STRING");
            }
            if (error_flag == 1){
                error_count += 1;
                System.out.println("#"+error_count+" "+ semi_colon_error);
            }
            if (error_flag1 == 1) {
                error_count += 1;
                System.out.println("#"+error_count+" "+ error_type0 + "\n"+ error_type4);
            }
            if (error_flag2 == 1) {
                error_count += 1;
                System.out.println("#"+error_count+" "+ error_type1);
            }
            if (error_flag3 == 1)   {
                error_count += 1;
                System.out.println("#"+error_count+" "+ error_type2);
            }
            if (error_flag4 == 1 ) {
                error_count += 1;
                System.out.println("#"+error_count+" "+ error_type3);
            }
            if (error_flag5 == 1) {
                error_count += 1;
                System.out.println("#"+error_count+" "+ error_type4);
            }
            if (error_flag6 == 1) {
                error_count += 1;
                System.out.println("#"+error_count+" "+ error_type5);
            }
            if (error_flag7 == 1) {
                error_count += 1;
                System.out.println("#"+error_count+" "+ error_type6);
            }
            if (error_flag8 == 1) {
                error_count += 1;
                System.out.println("#"+error_count+" "+ error_type7);
            }
            if (error_flag9 == 1) {
                error_count += 1;
                System.out.println("#"+error_count+" "+ error_type8);
            }
            if (error_flag10 == 1) {
                error_count += 1;
                System.out.println("#"+error_count+" "+ error_type9);
            }
            if (error_flag11 == 1) {
                error_count += 1;
                System.out.println("#"+error_count+" "+ error_type10);
            }
            if (error_flag12 == 1){
                error_count += 1;
                System.out.println("#"+error_count+" "+ error_type11);
            }
            if (correct == false){
                System.out.println("==================================THE ORIGINAL INPUT STRING IS: "+ str1 +" ======================================");
            }
            //End of semantic analysis.
            //FRONT-END OF THE COMPILER ENDS

            //BACK-END OF THE COMPILER BEGINS

            //Match the inputs
            if (translate == true){
                int len1 = str1.length();
                int k,j=0, jk;
                System.out.println("------------>STAGE4: INTERMEDIATE CODE REPRESENTATION (ICR)<------------");
                for(k = 0; k < len1; k++)
                {     mult1 = 0; mult2 = 0;
                    for(k = 0; k < len1; k++)
                    {
                        if( str1.charAt(k) == '/')
                        { t11 = str1.charAt(k - 1); t12 = str1.charAt(k + 1);
                            j++;
                            System.out.println("t"+j+"= " + t11 + str1.charAt(k) + t12);
                            l_div1 = "LDA"+" "+str1.charAt(k + 1);
                            l_div2 = "DIV"+" "+str1.charAt(k - 1);
                            l_div3 = "STR"+" "+"t"+j;
                            m_div = "DIV"+ " "+ "t"+j+","+ " "+str1.charAt(k + 1)+","+ " " +str1.charAt(k - 1);
                            n_div = "001"+" "+"011"+" "+"11" +" "+Integer.toBinaryString(j);
                        }
                    }
                    for(k = 0; k <len1; k++)
                    { mult1 = 0; mult2 = 0;
                        if( str1.charAt(k) == '*')
                        {
                            t21 = str1.charAt(k - 1); t22= str1.charAt(k + 1);
                            if (t21 == t11) {tt1 = "t"+j; mult1 = 1;}
                            if (t21 == t12) {tt1 = "t"+j; mult1 = 1;}
                            if (t21 == t31) {tt1 = "t"+j; mult1 = 1;}
                            if (t21 == t32) {tt1 = "t"+j; mult1 = 1;}
                            if (t21 == t41) {tt1 = "t"+j; mult1 = 1;}
                            if (t21 == t42) {tt1 = "t"+j; mult1 = 1;}

                            if (t22 == t11) {tt2 = "t"+j; mult2 = 2;}
                            if (t22 == t12) {tt2 = "t"+j; mult2 = 2;}
                            if (t22 == t31) {tt2 = "t"+j; mult2 = 2;}
                            if (t22 == t32) {tt2 = "t"+j; mult2 = 2;}
                            if (t22 == t41) {tt2 = "t"+j; mult2 = 2;}
                            if (t22 == t42) {tt2 = "t"+j; mult2 = 2;}

                            if ( mult2 == 2 && mult1 == 0) {
                                j++; System.out.println("t"+j+"= " + t21 + str1.charAt( k ) + tt2);
                                m_mult="MUL"+ " "+ "t"+j+","+ " "+t21+","+ " " +tt2;
                                l_mult1="LDA"+" "+t21;
                                l_mult2="MUL"+" "+tt2;
                                l_mult3="STR"+" "+"t"+j;
                            }
                            if ( mult1 == 1 && mult2 == 2) {
                                j++; jk=j-2;
                                tt1 = "t"+jk;
                                System.out.println("t"+j+"= " + tt1 + str1.charAt(k) + tt2);
                                m_mult="MUL"+ " "+ "t"+j+","+ " "+tt1+","+ " " +tt2;
                                l_mult1="LDA"+" "+tt1;
                                l_mult2="MUL"+" "+tt2;
                                l_mult3="STR"+" "+"t"+j;
                            }
                            if ( mult1 == 1 && mult2 == 0) {
                                j++; System.out.println("t"+j+"= " + tt1 + str1.charAt(k) + t22);
                                m_mult="MUL"+ " "+ "t"+j+","+ " "+tt1+","+ " " +t22;
                                l_mult1="LDA"+" "+tt1;
                                l_mult2="MUL"+" "+t22;
                                l_mult3="STR"+" "+"t"+j;
                            }
                            if ( mult1 == 0 && mult2 == 0) {
                                j++;
                                System.out.println("t"+j+"= " + t21 + str1.charAt(k) + t22);
                                m_mult="MUL"+ " "+ "t"+j+","+ " "+t21+","+ " " +t22;
                                l_mult1="LDA"+" "+t21;
                                l_mult2="MUL"+" "+t22;
                                l_mult3="STR"+" "+"t"+j;
                            }
                            n_mult="001"+" "+"010"+" "+"10" +" "+Integer.toBinaryString(j);
                        }
                    }
                    for(k = 0; k < len1; k++)
                    { mult1=0; mult2=0;
                        if( str1.charAt(k) == '+')
                        {
                            t31 = str1.charAt(k - 1); t32= str1.charAt( k + 1);
                            if (t31 == t11) {tt1 = "t"+j; mult1=1;}
                            if (t31 == t12) {tt1 = "t"+j; mult1=1;}
                            if (t31 == t21) {tt1 = "t"+j; mult1=1;}
                            if (t31 == t22) {tt1 = "t"+j; mult1=1;}
                            if (t31 == t41) {tt1 = "t"+j; mult1=1;}
                            if (t31 == t42) {tt1 = "t"+j; mult1=1;}

                            if (t32 == t11) {tt2 = "t"+j; mult2=2;}
                            if (t32 == t12) {tt2 = "t"+j; mult2=2;}
                            if (t32 == t21) {tt2 = "t"+j; mult2=2;}
                            if (t32 == t22) {tt2 = "t"+j; mult2=2;}
                            if (t32 == t41) {tt2 = "t"+j; mult2=2;}
                            if (t32 == t42) {tt2 = "t"+j; mult2=2;}

                            if ( mult2==2 && mult1==0) {
                                j++;
                                System.out.println("t"+j+"= " + t31 + str1.charAt(k) + tt2);
                                m_plus="ADD"+ " "+ "t"+j+","+ " "+t31+","+ " " +tt2;
                                l_plus1="LDA"+" "+t31;
                                l_plus2="ADD"+" "+tt2;
                                l_plus3="STR"+" "+"t"+j;
                            }
                            if ( mult1==1 && mult2==2) {
                                j++;
                                jk=j-2; tt1 = "t"+jk;
                                System.out.println("t"+j+"= " + tt1 + str1.charAt(k) + tt2);
                                m_plus="ADD"+ " "+ "t"+j+","+ " "+tt1+","+ " " +tt2;
                                l_plus1="LDA"+" "+tt1;
                                l_plus2="ADD"+" "+tt2;
                                l_plus3="STR"+" "+"t"+j;
                            }
                            if ( mult1==1 && mult2==0) {
                                j++;
                                System.out.println("t"+j+"= " + tt1 + str1.charAt(k) + t32);
                                m_plus="ADD"+ " "+ "t"+j+","+ " "+tt1+","+ " " +t32;
                                l_plus1="LDA"+" "+tt1;
                                l_plus2="ADD"+" "+t32;
                                l_plus3="STR"+" "+"t"+j;
                            }
                            if ( mult1==0 && mult2==0) {
                                j++;
                                System.out.println("t"+j+"= " + t31 + str1.charAt(k) + t32);
                                m_plus = "ADD"+ " "+ "t"+j+","+ " "+t31+","+ " " +t32;
                                l_plus1 = "LDA"+" "+t31;
                                l_plus2="ADD"+" "+t32;
                                l_plus3 = "STR"+" "+"t"+j;
                            }
                            n_plus="001"+" "+"010"+" "+"11" +" "+Integer.toBinaryString(j);
                        }
                    }
                    for( k = 0; k < len1; k++)
                        if( str1.charAt(k) == '-' )
                        { mult1=0; mult2=0;
                            t41 = str1.charAt(k - 1); t42= str1.charAt(k + 1);
                            if (t41 == t11) {tt1 = "t"+j; mult1=1;}
                            if (t41 == t12) {tt1 = "t"+j; mult1=1;}
                            if (t41 == t21) {tt1 = "t"+j; mult1=1;}
                            if (t41 == t22) {tt1 = "t"+j; mult1=1;}
                            if (t41 == t31) {tt1 = "t"+j; mult1=1;}
                            if (t41 == t32) {tt1 = "t"+j; mult1=1;}

                            if (t42 == t11) {tt2 = "t"+j; mult2=2;}
                            if (t42 == t12) {tt2 = "t"+j; mult2=2;}
                            if (t42 == t21) {tt2 = "t"+j; mult2=2;}
                            if (t42 == t22) {tt2 = "t"+j; mult2=2;}
                            if (t42 == t31) {tt2 = "t"+j; mult2=2;}
                            if (t42 == t32) {tt2 = "t"+j; mult2=2;}

                            if ( mult2==2 && mult1==0) {
                                j++;
                                System.out.println("t"+j+"= " + t41 + str1.charAt(k) + tt2);
                                m_minus="SUB"+ " "+ "t"+j+","+ " "+t41+","+ " " +tt2;l_minus1="LDA"+" "+t41;
                                l_minus2="SUB"+" "+tt2;l_minus3="STR"+" "+"t"+j;
                            }
                            if ( mult1==1 && mult2==2) {
                                j++;
                                jk = j-2;
                                tt1 = "t"+jk;
                                System.out.println("t"+j+"= " + tt1 + str1.charAt(k) + tt2);
                                m_minus="SUB"+ " "+ "t"+j+","+ " "+tt1+","+ " " +tt2;
                                l_minus1="LDA"+" "+tt1;l_minus2="SUB"+" "+tt2;l_minus3="STR"+" "+"t"+j;
                            }
                            if ( mult1==1 && mult2==0) {
                                j++;
                                System.out.println("t"+j+"= " + tt1 + str1.charAt(k) + t42);
                                m_minus="SUB"+ " "+ "t"+j+","+ " "+tt1+","+ " " +t42;
                                l_minus1="LDA"+" "+tt1;
                                l_minus2="SUB"+" "+t42;
                                l_minus3="STR"+" "+"t"+j;}
                            if ( mult1==0 && mult2==0) {
                                j++;
                                System.out.println("t"+j+"= " + t41 + str1.charAt(k) + t42);
                                m_minus="SUB"+ " "+ "t"+j+","+ " "+t41+","+ " " +t42;
                                l_minus1="LDA"+" "+t41;
                                l_minus2="SUB"+" "+t42;
                                l_minus3="STR"+" "+"t"+j;
                            }
                            n_minus="001"+" "+"011"+" "+"01" +" "+Integer.toBinaryString(j);
                        }
                }

                System.out.println("------------>STAGE5: CODE GENERATION<------------");
                if (str1.contains("/")) {
                    System.out.println(l_div1);System.out.println(l_div2);System.out.println(l_div3);
                }
                if (str1.contains("*")) {
                    System.out.println(l_mult1);System.out.println(l_mult2);System.out.println(l_mult3);
                }
                if (str1.contains("+")) {
                    System.out.println(l_plus1);System.out.println(l_plus2);System.out.println(l_plus3);
                }
                if (str1.contains("-")) {
                    System.out.println(l_minus1);System.out.println(l_minus2);System.out.println(l_minus3);
                }

                System.out.println("------------>STAGE6: CODE OPTIMISATION<------------");
                if (str1.contains("/")) System.out.println(m_div);
                if (str1.contains("*")) System.out.println(m_mult);
                if (str1.contains("+")) System.out.println(m_plus);
                if (str1.contains("-")) System.out.println(m_minus);

                System.out.println("------------>STAGE7: TARGET MACHINE CODE<------------");
                if (str1.contains("/")) System.out.println(n_div);
                if (str1.contains("*")) System.out.println(n_mult);
                if (str1.contains("+")) System.out.println(n_plus);
                if (str1.contains("-")) System.out.println(n_minus);
                System.out.println("==================================END OF COMPILATION======================================");
                System.out.println("==================================THE ORIGINAL INPUT STRING IS:"+ str1 +" ======================================");
            }
            count++;
        }
    }
}
