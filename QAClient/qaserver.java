//
// A server that provides answers to some questions sent
// from a client (in abbreviated form).  At the moment,
// only five different questions are supported.
//
// Note that the desired port number is supplied as the
// first argument on the command-line.
//
// USAGE: java qaserver <PORT>
//
// where <PORT> is the desired port #.
//
// Author: M. Halper
//

import java.util.*;
import java.net.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class qaserver
{

  public static final String USAGE = "USAGE: java qaserver <PORT>";
  public static int scale = 0;

  public static void main(String[] args)
  {
    
        ServerSocket serverSocket;
        Socket socket;
        String inputLine, inputLine2, inputLine3,inputLine4, answer;
	
	boolean finished;
	
	int port;

     try
     {
	// use the port indicated on command-line

	    port = Integer.parseInt(args[0]);

        // create a server socket

            serverSocket = new ServerSocket(port);

        // Listen for a connection request from a client 

            socket = serverSocket.accept();

	// Establish the input and output streams on the socket

           PrintWriter out = new
                     PrintWriter(socket.getOutputStream(), true);
           Scanner in = new Scanner(new
	           InputStreamReader(socket.getInputStream()));

	// Keep answering questions until the client wants to quit

           finished = false;

        while(!finished)
        {
	   // get a string from the client

             inputLine = in.nextLine();
             inputLine2 = in.nextLine();
             inputLine3 = in.nextLine();
             //inputLine4 = in.nextLine();

	   // If it's "quit," we're done

	     if(inputLine.equalsIgnoreCase("quit"))

		 finished = true;

	     else
	     {
                  
             
                answer = processBigDecimals(inputLine, inputLine2, inputLine3, scale);
                
                out.println(answer);
             }
        }

        out.close();
        in.close();
        socket.close();
        serverSocket.close();
     }
     catch(IOException e)  // socket problems
     {
        System.out.println(e);
     }

     catch(NumberFormatException e)  // port not a number (int)
     {
	System.out.println("First argument must be the port number.");
	System.out.println(USAGE);
     }

     catch(ArrayIndexOutOfBoundsException e)  // no port # given
     {
	System.out.println("Need to supply the port number.");
	System.out.println(USAGE);
     }

  } // end main

/////////////////////////////////////////////////////////////////

public static String processBigDecimals( String op, String x, String y, int scale2){
    try{
        
        String error = "Invalid Operation!!", error2 = "Exponent is not an INT!!", error3 = "Divide by 0",
            checking = "Scale is Set", error4="Scale is wrong";
        
    if(op.equals("setScale"))
    {      
        try{
            scale = Integer.parseInt(x);            
            return checking;
           }
            catch(Exception e){
                return error4;
            }
            
    }
    else{
    BigDecimal num = new BigDecimal(x);
    BigDecimal num2 = new BigDecimal(y);
   
    
    BigDecimal result;
    
    
    
    
    switch(op)
    {              
        
        case "add":
            result = num.add(num2).setScale(scale2, RoundingMode.HALF_UP);                       
            break;
        case "sub":
            result = num.subtract(num2).setScale(scale2, RoundingMode.HALF_UP);
            break;
        case "div":
            if(num2.intValue()==0){
                return error3;
            }
            else{
                result = num.divide(num2, scale2, BigDecimal.ROUND_HALF_UP);
                break;
            }
        case "mult":
            result = num.multiply(num2).setScale(scale2, RoundingMode.HALF_UP);
            break;
        case "pow":
            try{
                int power = num2.intValueExact();
            result = num.pow(power).setScale(scale2, RoundingMode.HALF_UP);
            break;
            }
            catch(Exception e){
                return error2;
            }
            
        default:
            return error;
        
    }    
    return result.toPlainString();
    }
    }
    catch (NumberFormatException e){
        String error4= "INPUT NOT A NUMBER!!!";
        
        return error4;
    }
    
    
    
    
}

/////////////////////////////////////////////////////////////////

} // end qaserver
