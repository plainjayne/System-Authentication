import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.nio.file.*;
import java.io.*;
/*
 * User Authentication System that takes emails and passwords. User can either log in or sign up
 * 
 */
class input{
// Data Structure to access passwords and emails
public static HashMap<String, String> logins = new HashMap<String,String>();
// Reads user inputs
public static Scanner input = new Scanner(System.in);
           

    //Runs the entire user authentication system
    //Takes in user inputs and handles logins and sign ups
    public static void main(String args[]){    
 

         try{ 
            //uses txt file to record passwords and emails
            BufferedReader signIns = new BufferedReader(new FileReader("/Users/Jayne/authenSys/passemail.txt/"));
            String acc;
            //puts logins in hashmap for accessibility
            while(signIns.ready()){
                acc = signIns.readLine();
                //seperates email & password
                String[] log = acc.split(" ", 2);
                logins.put(log[1],log[0]);
            }
            signIns.close();
       }

    
       catch(IOException e){
            System.out.println("File Does Not Exist");
        } 

        boolean repeat = true;
        
        //asks user to sign in or log in
        while(repeat){
            System.out.println("-Enter 1 to Sign Up \n-Enter 2 for to Log In:");
            int in = -1;
            try{
                in = Integer.parseInt(input.next());
            }
            catch(NumberFormatException e){
                System.out.println("Enter a number");
            }
           if(in == 1 || in == 2) {
                repeat = false;
            }
            if(in==1){
                signUp();
            }
            else{
                logIn();
            }

        }
        
        input.close();
        
    }

    // Allows user to provide email and password which will then be saved for 
    // future login
    private static void signUp(){

        System.out.println("Sign Up");

        System.out.println("Enter an email :");
        String email = input.next();
        //case where email is unidentified
        if(!logins.containsKey(email)){

            System.out.println("Enter a Password");
            String pswd = input.next();
            logins.put(email, pswd);
            //adds new login to list of emails and passwords
            try{
                Files.write(Paths.get("/Users/Jayne/authenSys/passemail.txt/"), ("\n"+pswd+" "+email).getBytes(),StandardOpenOption.APPEND);
            }

            catch(IOException e){
                System.out.println("File Not Found");
            }
        }
        //case where email is found in logins
        else{
            System.out.println("Email already has an account");
        }

            boolean repeat = true;
            String reply = null;
        while(repeat){
            System.out.println("Would you like to sign in? (Y/N)");
            reply = input.next();
               if(reply.equalsIgnoreCase("Y") || reply.equalsIgnoreCase("N")){
                    repeat = false;
            }
        }
        if(reply.equalsIgnoreCase("Y")){
           logIn(); 
        }
        
    }

    // allows user to log in with email and password already in the system
    private static void logIn(){
        System.out.println("Login");
        System.out.println("Enter Your email :");
        String email = input.next();
        // asks user to login if email is not found
        if(!logins.containsKey(email)){ 
            System.out.println("Email Not Found. Create New Account? (Y/N)");
            boolean repeat = true;
            String reply = null;
            while(repeat){
                reply = input.next();
                   if(reply.equalsIgnoreCase("Y") || reply.equalsIgnoreCase("N")){
                        repeat = false;
                }
            }
            if(reply.equalsIgnoreCase("Y")){
                signUp(); 
            }
        }
        //email verified and checking password 
        else{
            boolean repeat = true;
            while(repeat){
                System.out.println("Enter Your Password");
                String pswd = input.next();
                if(logins.get(email).equals(pswd)){
                    System.out.println("Successfully logged in");
                    repeat = false;
                }
                else{
                    System.out.println("Password incorrect. Try again");
                }
            }
        }
        
    }

    

}