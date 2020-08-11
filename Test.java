import java.util.Scanner; 
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 import java.io.BufferedInputStream;
import java.io.*;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 
public class Test { 
      static double totalPrio = 0;
     static List<Long> passedTestcases = new ArrayList<Long>();
     
      static long score=0;
    public static void main(String args[]) { 

        //System.out.println("pathis=|"+args[0].trim()+"|");
        //System.out.println("command|"+args[1].trim()+"|");
        
      String op= runTestCases(args[1].trim(), args[0].trim());
    

        System.out.println(op);



        /*
  CommandParser cmdParser = new CommandParser(null, null);
        JSONParser parser = new JSONParser();
 try (FileReader reader = new FileReader("testcase.json")){
          Object obj = parser.parse(reader);
 
            JSONArray employeeList = (JSONArray) obj;
         Iterator<JSONObject> iterator = employeeList.iterator();
         while(iterator.hasNext()) {
          //  System.out.println(iterator.next());
            String input = (String)iterator.next().get("input");
            String output = cmdParser.exec("java Challenge", args[0].trim(), null, true,new String[]{input});
                   String opdata=output;
                   opdata=opdata.substring(opdata.lastIndexOf(":")+1);
                    opdata=opdata.replaceAll("^(\r?\n)*","");
                    opdata=opdata.replaceAll("\n*$","");
                    opdata=opdata.trim();
                   opdata=opdata.replaceAll(System.getProperty("line.separator"),"<br>");
        
            System.out.println("o/p::::|"+opdata+"|");
        }

          
    }catch(Exception ee){
        System.out.println(ee);
    }
    */

     
               try{
              // String output = cmdParser.exec("java Challenge", args[0].trim(), null, true,new String[]{"one"});
              //  output+= cmdParser.exec("java Challenge", args[0].trim(), null, true,new String[]{"two"});
              // output+= cmdParser.exec("java Challenge", args[0], null, true,new String[]{"thre"});

              //  String output2 = cmdParser.exec("java Echo < testcase2.tc", "/home/project/java21days/1594472712697/echotest", null, true);
             //    output = cmdParser.exec("java Echo < testcase1.tc", "/home/project/java21days/1594472712697/echotest", null, true);
             //    output2 = cmdParser.exec("java Echo < testcase2.tc", "/home/project/java21days/1594472712697/echotest", null, true);
               
             //   System.out.println(output);
              // System.out.println("output2");
               


//System.out.println("<result>Great job!</result><br><br>");
//System.out.println("Perfect result. No bugs found.12313");
//System.out.println("<br/><br><b>TestCase Table666666:</b><br/>");
//System.out.println("<table border=1 class=testcase><tr class=testcase_header><td>Our Input</td><td>Expected Output</td><td>Your code's output</td></tr><tr class=right_entry><td>John </td><td>Hello John</td><td>Hello John</td></tr><tr class=right_entry><td>Mike </td><td>Hello Mike</td><td>Hello Mike</td></tr><tr class=right_entry><td>ny </td><td>Hello ny</td><td>Hello ny</td></tr></table>");

               }catch(Exception e){
                   e.printStackTrace();
               }
               
    } 
    
        
    //----------Code to gerate testcase table in HTML format
 public static String WriteTestCaseTable(String rowEntries){
    String str="<table border=1 class=testcase>"+
    "<tr class=testcase_header><td>Our Input</td><td>Expected Output</td><td>Your code's output</td></tr>"+rowEntries+"</table>";
  return str;
 }
 public static String addEnteries(String input,String expected_output,String actual_output,String rowClass){
  //input=input.replaceAll("\r\n","<span class=newl>\\\\n</span>");
  expected_output=expected_output.replaceAll("\r\n","<span class=newl> </span>");
  actual_output=actual_output.replaceAll("\r\n","<span class=newl> </span>");
 return "<tr class="+rowClass+"><td>"+input+"</td><td>"+expected_output+"</td><td>"+actual_output+"</td></tr>";
 }

     public static String runTestCases(String command,  String path)
    {       
       
        
       
         String SuccessMSG="";
     
        boolean status=false;
        boolean testpassed=true;

       
        
        // SDS end }}
       
      

       // System.out.println("0 ");
        
        try
        {
            
                CommandParser cmdParser = new CommandParser(null, null); 
            JSONParser parser = new JSONParser();
                FileReader reader = new FileReader(path+"/.testcase.json");
               Object obj = parser.parse(reader);
 
            JSONArray employeeList = (JSONArray) obj;
         Iterator<JSONObject> iterator = employeeList.iterator();
            
            //Log.debug("TestCaseManager",null,"1 " + tcsName);
            int counter = 1;

            
          
            
            List<String> outputVal = null;
           
            String fileName = "";
          
                
            
    for (int i=0; i < employeeList.size(); i++) {

               outputVal = new ArrayList<String>();
              //dj:kept here to avoid exception occured bug           
              String output="";
               String[] outputArray ={""};
            JSONObject testcase = (JSONObject)employeeList.get(i);
               
            
             String input = (String)testcase.get("input");
           //  System.out.println((Long)testcase.get("priority"));
             totalPrio = totalPrio + (Long)testcase.get("priority");
            // System.out.println("input"+input);
             input=input.replaceAll(",", "\n");
             output = cmdParser.exec(command, path.trim(), null, true,new String[]{input});    
              
             //System.out.println("output is"+output);
               
               //Log.debug("TestCaseManager",null,"screen output = "+output);              
               
               //{dj:@27/1/2012 just triming inital newline's and last newlines
               //so that it will not come as a part of output 
               try{

               //dj:
                String excepSTR="Exception in thread \"main\" ";
                Pattern pattern =Pattern.compile("^.*:\\s*$",Pattern.MULTILINE);
                Matcher matcher=pattern.matcher(output);
                if(!output.contains(excepSTR))
                    output=matcher.replaceAll("");

                Pattern pattern2=Pattern.compile("^.*:",Pattern.MULTILINE);
                Matcher matcher2=pattern2.matcher(output);
                
                    
                    if(!output.contains(excepSTR))
                    output=matcher2.replaceAll("");
               
               
               
               
                 String str2=output.replaceAll("^\n*","");
                 str2=str2.replaceAll("\n*$","");
                 
                 str2=str2.replaceAll("\n+","\n");
                 
                 str2=str2.replaceAll("^(<br\\s*/\\s*>|</br>|<br\\s*>|<BR\\s*>|</BR\\s*>|<BR\\s*/\\s*>)*","");
                 str2=str2.replaceAll("(<br\\s*/\\s*>|</br>|<br\\s*>|<BR\\s*>|</BR\\s*>|<BR\\s*/\\s*>)*$","");

                str2=str2.replaceAll("^\\s*","");
                str2=str2.replaceAll("\\s*$","");
                 outputArray = str2.split("\n|<br\\s*/\\s*>|</br>|<br\\s*>|<BR\\s*>|</BR\\s*>|<BR\\s*/\\s*>");
                 
                ////System.out.println("TestCaseManager|outputarr|"+str2);  
                //Log.debug("TestCaseManager|str2|",null,str2);
                }catch(Exception e){ //Log.debug("TestCaseManager|EX2:",null,e.getMessage());
            }
               // add the output obtained to an arraylist
               
               try{
            
               for(String val:outputArray)
               {
                
                     if(val.indexOf(":")!=-1){
                        if(val.endsWith(":")) continue;
                        else
                            //val=val.substring(val.indexOf(":")+1);
                            val=val.substring(val.lastIndexOf(":")+1);
                            
                            
                      }
                   outputVal.add(val);
                   //dj:end}
                   //Log.debug("TestCaseManager",null,"6 " + val);                         
               }
               }catch(IndexOutOfBoundsException e){ 
                  //// System.out.println("TestCaseManager|EX:"+e.getMessage());
            }

           // compare the output obtained with the output in the tcs file
               String fullVal =  (String)testcase.get("output");
              //// System.out.println("fullval"+fullVal);
               fullVal=fullVal.replaceAll("\\s+$", "");
               // split the value with ","  as delimiter
               String tcContentSplit[]= fullVal.split("(?!(?<=\\\\))(,)");
              

               int srNo=0;
               for(String val:tcContentSplit)
               {

                   
                   //dj:
                   val=val.replace("\\,",",");
                   String s1 = val;String s2="";
                   
                   //dj:trim output of testcase also
                   s1=s1.trim();
                   try{
                    s2 = outputVal.get(srNo);   
                    s2=s2.replaceAll("(.*)\\s+$", "$1");
                    
                    }catch(Exception e){ System.out.println("TestCaseManager|EX3:"+e.getMessage());}

                
                   
                   
                 
                   String opdata=output;
                   opdata=opdata.substring(opdata.lastIndexOf(":")+1);
                    opdata=opdata.replaceAll("^\n*","");
                    opdata=opdata.replaceAll("\n*$","");
                   opdata=opdata.replaceAll(System.getProperty("line.separator"),"<br/>");
                   
                  
        
           String inputdata="";
        
                        try{
                        inputdata= (String)testcase.get("input");;
                        }
                        catch(Exception e){
    
                        }
                        
                    inputdata=inputdata.replaceAll(System.getProperty("line.separator")," ");
                    inputdata=inputdata.replaceAll(","," ");
                    //String expectedop=s1.replaceAll(","," ");
                    String expectedop=fullVal.replaceAll(",","<br/>");
                    //String actualop=s2.replaceAll(","," ");
                    
                    //dj:trim all white space for expected o/p & actual o/p
                    
                    s1=s1.replaceAll("\\s","");
                    s2=s2.replaceAll("\\s","");
                    
                    
                    
                  if(s1.equals(s2)==false)
                   {
                      // System.out.println("failed|"+s1+"||"+s2+"|");
                    
                String tablestr=WriteTestCaseTable(addEnteries(inputdata,expectedop,opdata.trim(),"wrong_entry"));      
                        testpassed=false;   
                       // we check if there was an error in the output n if yes, then we need to get the msg
                       //String errorMsg = props.getProperty("error"+counter);
                       String errorMsg="";
                       String hints="";
                      
                       
                       errorMsg+="<BR/>";
                       errorMsg+="<span class=tableblock>TestCase Table</span><BR/>";
                       errorMsg+=tablestr;
                  SuccessMSG+=addEnteries(inputdata,expectedop,opdata.trim(),"wrong_entry");
                 
                      
                      // return errorMsg;
                      
                   }
                   else
                   if(s1.equals(s2)==true){
                   //SuccessMSG+=addEnteries(inputdata,expectedop,opdata.trim(),"right_entry");
                  // System.out.println("TestCaseManager|matched:testcaseoutput="+s1+"|ouput="+s2+"|");
                    testpassed=true;
                   }
                   srNo++;
               }
                  if(testpassed==true){
                   String opdata=output.replaceAll(System.getProperty("line.separator"),"<br/>");
                   opdata=opdata.substring(opdata.lastIndexOf(":")+1);
                 String expectedop=fullVal.replaceAll("(?<!\\\\)(,)","<br/>");
                    expectedop=expectedop.replace("\\,",",");
                    String  msg= (String)testcase.get("error");;
                     String inputdata="";
                            try{
                        inputdata =  (String)testcase.get("input");;
                        }
                        catch(Exception e){
                        }
                    inputdata=inputdata.replaceAll(System.getProperty("line.separator")," ");
                    inputdata=inputdata.replaceAll(","," ");
                  SuccessMSG+=addEnteries(inputdata,expectedop,opdata.trim(),"right_entry");
                   passedTestcases.add((Long)testcase.get("priority"));  
                   score+=(Long)testcase.get("priority");
                }

               
               counter++;
            }
            
            if(counter==1)
            return("error: couldnt find any testcase files");
        
    }
        catch(Exception e)
        {
            
            return("error: exception occurred"+e.getMessage());
        }
        String FinalMsg=WriteTestCaseTable(SuccessMSG);
       // System.out.println("total prio"+totalPrio);
        // System.out.println("total score"+score);
        double percentscore=Math.floor(((score/totalPrio)*100));
       // System.out.println("passed"+passedTestcases.toString());
       String msg = "Great job!";
       String submittButton = "<br><br><div id=\"collect_points\" ><center><a href=\"javascript:void(0)\" id=\"submit_button\" onclick=\"submitTest()\" class=\"form-submit\" >Submit Challenge?</a></center></div>";
       if(score==0){
          msg = "Uh-oh"; 
       }
        return ""+msg+"<br>Score:<score>"+percentscore+"</score>%"+FinalMsg + submittButton;
    
    }
} 