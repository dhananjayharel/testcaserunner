import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommandParser{
	private String[] parsedCommand;
	private String homeDir;
	private String lastDir;
	private String currentDir;
	private String fileSep;

///		
	Process p;
	StringBuffer sb;
	
//	static Logger log = Log.getLogger(CommandParser.class);
	
	public CommandParser(String homeDir){
		parsedCommand = null;
		this.homeDir = homeDir;
		this.lastDir = homeDir;
		this.currentDir = homeDir;
		this.fileSep = System.getProperty("file.separator");
	}
	
	public CommandParser(String homeDir, String lastDir){
		parsedCommand = null;
		this.homeDir = homeDir;
		this.lastDir = lastDir;
		this.currentDir = homeDir;
		this.fileSep = System.getProperty("file.separator");
		
	}
	
	public CommandParser(){
		this("");
	}
	
	public boolean parse(String command){
		///{dj:@14/4/2011 commented for testing(We think we dont need to handle multiple commands so dont split it)
		///parsedCommand = command.split(";");
		parsedCommand=new String[] {command};
		///}
		return !(parsedCommand == null || parsedCommand.length < 1);
	}

	public String exec(String[] command, String currentDir, Process application_arg, boolean runApp,String[] inputStrings) throws Exception{
		// TODO: use specific try/catch here
		
					//Log.info(Utils.COMMANDPARSER, null, command[0]);
		
		if(command == null) return "";
		
///		
		Process application = null;
		String result = ""; 
		InputStream in = null;
		InputStream er = null;
		//dj:
		boolean bReadyFirstTime=false;
		String newPath="";
		//Log.info(Utils.COMMANDPARSER, null, "in exec bReadyFirstTime:"+bReadyFirstTime);
		
		String OS = System.getProperty("os.name").toLowerCase();
		
		for(int i = 0; i < command.length; i++){ 
			
			String strCd = command[i].trim().toLowerCase();
		/*	if(strCd.length() > 1 && strCd.substring(0,2).equals("cd")){
				strCd = command[i].trim();
				result += strCd.length() > 2? parseCd(strCd.substring(2).trim(), currentDir): parseCd(null, currentDir);
				continue;
			}
		*/	
											//Log.debug(Utils.COMMANDPARSER, null,OS);			
			
			
								//Log.debug(Utils.COMMANDPARSER, null,"about to run ..");
								
																			//Log.debug(Utils.COMMANDPARSER, null,command[i]);			
		

                      //Log.debug(Utils.COMMANDPARSER, null,""+application_arg);			
		application = application_arg;
		//Log.debug(Utils.COMMANDPARSER, null,currentDir);
					//command[i]="java -classpath \"santosh/Lab3-4;santosh/Lab3-4/lib/spring.jar;santosh/Lab3-4/lib/commons-logging-1.1.1.jar\" part4.test.TestLargeBalance";
					//command[i]="java -classpath \"santosh/lab01;santosh/lab01/lib/spring.jar;santosh/lab01/lib/commons-logging-1.1.1.jar\" com.oaktreeair.ffprogram.FrequentFlierProgram";
					command[i]=command[i].replace("|",";");
					///dj added log
			//Log.debug(Utils.COMMANDPARSER, null,"dj:Command[i] outside if: "+command[i]);
		if(application==null)						
		{
			///dj added log
			//Log.debug(Utils.COMMANDPARSER, null,"dj:Command[i] inside if: "+command[i]);
			
			if (OS.indexOf("windows") > -1) {
				///{dj:added block below:on12/4/2011
				///purpose:to fix the bug "spring examples not working" building -ok but giving blank screen on run
				///application = Runtime.getRuntime().exec(new String[] {"cmd.exe","/C",command[i]}, null, new File(currentDir));
				ProcessBuilder builder = new ProcessBuilder("cmd.exe","/C",command[i]);
				builder.directory(new File(currentDir));
				builder.redirectErrorStream(true);
				application = builder.start();
				///}
				
			}
			
								
			
			else {
			

				String cmd;

	
 
                 ///{dj:added
				 //command[i]=command[i].replace("|",";");			
				 ///}
				//cmd = "sudo -u " + go.getSid() + " " + command[i];
				//original lin commented below
				///{dj:@13/3/2013 modified userspath to avoid UNIX file system limit of having only 23k sub dirs at same level for any dir
				//old line:cmd = "sudo /scripts/kk_chrootenv.sh /usr/local/tomcat/apache-tomcat-5.5.27/webapps/leopard/users/" + go.getSid() + ";sudo chroot /usr/local/tomcat/apache-tomcat-5.5.27/webapps/leopard/users/" + go.getSid() + " /bin/su kkuser" + " /bin/rbash -c \"" + command[i] + "\"" + ";sudo /scripts/kk_umchenv.sh "+ go.getSid()+ ";sudo /scripts/kk_umchenv.sh "+ go.getSid();
				
				try{
				newPath="";
                }
				
				catch(Exception e){
				
				}
				///}
				//new line
				cmd = command[i];


				ProcessBuilder builder = new ProcessBuilder("sh","-c",cmd);
				builder.directory(new File(currentDir));
				builder.redirectErrorStream(true);
                application = builder.start();
                //give intput string 
		       giveInput(inputStrings, application);	
				

			}
			
		}
		else
		{
			
			giveInput(command, application);								
		}
			
					
			
			if(application == null) continue;
			p = application;
			

				
			
sb = new StringBuffer(8192);			
///

if(runApp)
{



//boring error stream stuff, check only for first time - not when inputs have started, will just slow down the system unnecessarily
if(application_arg==null)
{
				BufferedReader b = new BufferedReader(new InputStreamReader(application.getErrorStream()),10);

       

				///String linha = b.read();
int linha = -1;




        //Log.debug(Utils.COMMANDPARSER, null,"after first readline");
        
        boolean bReady = true;


				
				//while(linha != 13 && linha !=-1 && linha != 10)
								while(bReady){
								
//is it ready?
bReady = b.ready();
int nAttempt=0;



if(!bReady)
{


//Log.debug(Utils.COMMANDPARSER, null,"NOT READY!!!!!!!!!");			

//cool your heels for a while
while (!bReady && nAttempt < 5)
{

try {
Thread.sleep(50);
			}catch(InterruptedException e){
//Log.debug(Utils.COMMANDPARSER, null,"in exceprion!!!!!!!!!");						
				Thread.currentThread().interrupt();
			}

	//if (sb.length()==0) Log.debug(Utils.COMMANDPARSER, null,"WE ARE ACTUALLY IN!!!!!!!!!|p="+application);			
	bReady = b.ready();
	nAttempt++;
}


if(!bReady) break;
}




								
				
				linha = b.read();

String c = new Character((char)linha).toString();



				
					sb.append(c);
					
					

					



				}
				
//is it ready?
boolean bool = b.ready();
	
}



{
				BufferedReader b = new BufferedReader(new InputStreamReader(application.getInputStream()),10); 

				///String linha = b.read();
int linha = -1;




        //Log.debug(Utils.COMMANDPARSER, null,"after first readline");
        
        boolean bReady = true;
         ///{dj modification:just a hack to get the long running examples output like spring-hibernate
		 int loopCount=15;
		 //Log.debug(Utils.COMMANDPARSER, null,"the cmd is:"+command[i]);
		String regex="persistence.jar";
		String regex2="python ";
		String regex3="/bin/jruby";
		//Log.debug(Utils.COMMANDPARSER, null,"REGEX to test command"+command[i]);
     Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
	 Pattern pattern2 = Pattern.compile(regex2,Pattern.CASE_INSENSITIVE);
	 Pattern pattern3 = Pattern.compile(regex3,Pattern.CASE_INSENSITIVE);
	 Matcher m=pattern.matcher(command[i]);
	 Matcher m2=pattern2.matcher(command[i]);
	 Matcher m3=pattern3.matcher(command[i]);
	 if(m.find())
	 { loopCount=100;
	 }
	 else
	 if(m2.find())
	 { loopCount=16;
	 //Log.debug(Utils.COMMANDPARSER, null,"loopCount increased for pytnon");
	 }
	 else
	 if(m3.find())
	 { loopCount=16;
	  //Log.debug(Utils.COMMANDPARSER, null,"loopCount increased for ruby");
	 }
     ///}

				
				//while(linha != 13 && linha !=-1 && linha != 10)
								while(bReady){
								
//is it ready?
bReady = b.ready();
int nAttempt=0;
if(!bReady)
{

//Log.debug(Utils.COMMANDPARSER, null,"NOT READY!!!!!!!!!");			

//cool your heels for a while

while (!bReady && nAttempt < loopCount)
{

try {//Log.debug(Utils.COMMANDPARSER, null,"app thread obj::"+application);	
if(bReadyFirstTime==true){
//Log.debug(Utils.COMMANDPARSER, null,"bReadyFirstTime second time.."+bReadyFirstTime);
Thread.sleep(50);
}
else
if(bReadyFirstTime==false){
//Log.debug(Utils.COMMANDPARSER, null,"bReadyFirstTime first time..");
Thread.sleep(200);
}
			}catch(InterruptedException e){
//Log.debug(Utils.COMMANDPARSER, null,"in exceprion!!!!!!!!!");						
				Thread.currentThread().interrupt();
			}

	//if (sb.length()==0) Log.debug(Utils.COMMANDPARSER, null,"WE ARE ACTUALLY IN2!!!!!!!! |p="+application);			
	bReady = b.ready();
	//Log.debug(Utils.COMMANDPARSER, null,"bReadyFirstTime isbready? "+bReady+"|p="+application);
	
	nAttempt++;
}
if(!bReady) break;
}



								
			    	//dj:
				if(bReady){
				bReadyFirstTime=true;
				//Log.debug(Utils.COMMANDPARSER, null,"bReadyFirstTime assigned to true");
				}
				linha = b.read();
				

String c = new Character((char)linha).toString();
//Log.debug(Utils.COMMANDPARSER, null,"bReadyFirstTime isbready? "+bReady+"and char="+c);
//Log.debug(Utils.COMMANDPARSER, null,"dj:Lnha Char:" + c);
//Log.debug(Utils.COMMANDPARSER, null,c);
//Log.debug(Utils.COMMANDPARSER, null,""+linha);


				
					sb.append(c);
					
					

					




			//Log.debug(Utils.COMMANDPARSER, null,"are we in?");			

        //Log.debug(Utils.COMMANDPARSER, null,"after next readline");
				}
				
//is it ready?
boolean bool = b.ready();
//Log.debug(Utils.COMMANDPARSER, null,""+bool);
				

//Log.debug(Utils.COMMANDPARSER, null,"after sb and before esb");			

}
}//if runapp
else
{
//Log.debug(Utils.COMMANDPARSER, null,"app is falser");		
try{			
			//if (application_arg==null)
			
			
		    in = application.getInputStream();
		    er = application.getErrorStream();
		    int ch;
		    StringBuffer sb = new StringBuffer(1024);
		    while ((ch = in.read())!=-1){
		    	sb.append((char) ch);
		    }
		    while ((ch = er.read())!=-1){
		    	sb.append((char) ch);
		    }
		    
		    result += sb;


		    }catch (Exception e) { //Log.error(Utils.COMMANDPARSER,null,"Exception not yet exited in CP!");
			}
		    
		   }

		    
		    		    			//Log.debug(Utils.COMMANDPARSER, null,"after all reads");			
		    
		    result += sb;
				//Log.debug(Utils.COMMANDPARSER, null,"Result is:"+result+"|p="+application+"|="+newPath);	
		}
		
	
   	 	return result;
	}
	
	public String exec(String command, String currentDir, Process application, boolean runApp, String[] inpuStrings) throws Exception{
	
	//Log.info(Utils.COMMANDPARSER,null,"before parse");
	
		if(!parse(command))
			return "Invalid command\n";
			
			//Log.debug(Utils.COMMANDPARSER, null,"after parse");
		
		this.currentDir = currentDir;
		return exec(parsedCommand, currentDir, application, runApp,inpuStrings);
		
					//Log.debug(Utils.COMMANDPARSER, null,"after exec");
	}
	
	/**
	 * @param strCd
	 * @param currentDir
	 * @return
	 */
	public String parseCd(String strCd, String currentDir){
		String tmpCurDir = "";
		File dir = null;
		String parentDir = "";
		String msg = "";
		String strCdCmd = strCd;
		
		if(strCd != null && (strCd.indexOf("\"") == 0 && strCd.lastIndexOf("\"") == strCd.length() -1)){
			strCdCmd = strCd.substring(1,strCd.length()-1);
		}

		if(strCdCmd == null || strCdCmd.equals("~")){
			lastDir = currentDir;
			tmpCurDir = homeDir;
		}else if(strCdCmd.equals("-")){
			tmpCurDir = lastDir;
			lastDir = currentDir;
		}else if(strCdCmd.equals("..")){
			dir = new File(currentDir);
			parentDir = dir.exists() && dir.isDirectory()? dir.getParent(): currentDir;
			
			lastDir = currentDir;
			tmpCurDir = parentDir;
		}else if(strCdCmd.equals(fileSep)){
			lastDir = currentDir;
			tmpCurDir = homeDir;
		}else{
			msg = "";
			dir = new File(currentDir +fileSep+ strCdCmd);
			
			if(!dir.exists()){
				msg = "Directory not found\n";
				return msg;
			}else if(!dir.isDirectory()){	
				msg = "Not a directory\n";
				return msg;
			}
			
			strCdCmd = currentDir +fileSep+ strCdCmd;
			
			lastDir = currentDir;
			tmpCurDir = strCdCmd;
		}
		
		// TODO: verify if user is in own directory, if not, return user to its directory
		if(!tmpCurDir.startsWith(this.homeDir)){
			tmpCurDir = currentDir;
		}
		
		
		this.currentDir = tmpCurDir;
			
		return msg;
	}
	
	public void setHomeDir(String homeDir){
		this.homeDir = homeDir;
	}
	
	public void setHomeDirandDefaults(String homeDir){
		this.homeDir = homeDir;
		this.lastDir = this.lastDir.equals("")? homeDir: this.lastDir;
		this.currentDir = this.currentDir.equals("")? homeDir: this.currentDir;
	}
	
	public String getCurrentPath(){
		return this.currentDir;
	}
	
	public String getLastPath(){
		return this.lastDir;
	}
	
		public Process getApplication(){
		return p;
	}

	
void	giveInput(String[] command, Process application)
{
				final OutputStream os = application.getOutputStream();
                final OutputStreamWriter osw = new OutputStreamWriter( os );
                final BufferedWriter bw = new BufferedWriter( osw, 100/* buffsize in chars */ );
                String line;
                try
                    {
                    // C/C++ can read this with getc
					//Log.debug(Utils.COMMANDPARSER, null,"given input");
                    bw.write( command[0] + "\n" );
                    //bw.write( "some more text\n" );
                    bw.flush();
                    }
                catch ( IOException e )
                    {
                   // Log.info(Utils.COMMANDPARSER,null, "exception problem writing spawn input" + e.getMessage() );
                    //System.exit( 1 );
                    }
                // returning from run kills the thread.
}


//dj:plain command caller
public String call_process(String command,String currentDir) throws Exception{
//Log.debug(Utils.COMMANDPARSER,null,"in command_process:"+command);
	String result = ""; 
		InputStream in = null;
		InputStream er = null;
		Process	application=null;
		try{
	ProcessBuilder builder = new ProcessBuilder("sh","-c",command);
				builder.directory(new File(currentDir));
				builder.redirectErrorStream(true);
			application = builder.start();
			}
				catch (Exception e){
				//Log.error(Utils.COMMANDPARSER,null,"android:process exception");
				}
				try{			
			//if (application_arg==null)
			
			
		    in = application.getInputStream();
		    er = application.getErrorStream();
		    int ch;
		    StringBuffer sb = new StringBuffer(1024);
		    while ((ch = in.read())!=-1){
		    	sb.append((char) ch);
		    }
		    while ((ch = er.read())!=-1){
		    	sb.append((char) ch);
		    }
		    
		    result += sb;

           
		    }catch (Exception e) { //Log.error(Utils.COMMANDPARSER,null,"android error occured");
			}
				
				
			return result;	
				
}
	
}
