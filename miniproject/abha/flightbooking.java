package abha;
import java.io.*;
import java.util.*;  
import java.text.SimpleDateFormat;  
import java.util.Date; 
import java.text.ParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter.*;
import java.time.format.DateTimeFormatter;   

class MyException extends Exception
{
    public MyException(String s)
    {
        // Call constructor of parent Exception
        super(s);
    }
}
//Abstract class for Viewing chart
abstract class Chart{  
    abstract void view(List<flightbooking> filteredlist);  
}  

//Class for booking tickets
class TicketBook extends Chart
{  
    int balance = 1000;
    //Book method
    void book(int flightnumber, List<flightbooking> filteredlist)
    {
        for(flightbooking f:filteredlist)
        { 
            if(f.flight_id == flightnumber){
                int max = 50;
                int min = 1;
                int range = max - min + 1;
                int seat_number = (int)(Math.random() * range) + min;
                balance = balance - f.price;
                try
                {
                    balance = balance - f.price;
                    if(balance <= 0){
                        
                        throw new MyException("Balance is less. No ticket has been booked");
                    }
                    
                }
                catch (MyException ex)
                {
                    System.out.println("Caught");
        
                    // Print the message from MyException object
                    System.out.println(ex.getMessage());
                    break;
                }
                f.status = 1;
                System.out.println("\n\n");
                System.out.println("Your Ticket is Confirmed!!");
                System.out.println("\n----------------------------------Ticket----------------------------------\n");
                System.out.println("Location: "+ f.location);
                System.out.println("Destination: "+ f.destination);
                System.out.println("Date and Time: "+ f.date+ "\t" + f.time);
                System.out.println("Your seat number is " + seat_number  + "\n");
                System.out.println("Your current account balance is: " + balance);
            }
        }  
    }
    void view(List<flightbooking> filteredlist){
        System.out.println("\n----------------------------------Flight Chart----------------------------------\n");
        System.out.println("Flight Id\tLocation\t Destination\t Date\t\tTime\t\t Price\t\t Status");
        for(flightbooking f:filteredlist){
            System.out.println(f.flight_id+"\t\t"+f.location+"\t\t"+f.destination+"\t\t "+f.date+"\t "+f.time+"\t "+f.price + "\t\t" + f.status);
        }
    }
}   
   
class flightbooking extends TicketBook
{   
    // Instance Variables
    String location;
    String destination;
    String date; //just date is stored
    String time; //time is hardcoded
    int flight_id;
    int price; 
    int status;
    
    //All Variables with list 
    public flightbooking(int flight_id, String location, String destination, String date, String time, int price, int status)
    {
        this.flight_id = flight_id;
        this.location = location;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.price = price;
        this.status = status;
    }
    //gets destination from user
    public static String getdestination() 
    { 
        // Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
        System.out.println("Enter your destination: Delhi");
        // String yourdestination = sc.nextLine();
        String yourdestination = "Delhi";
        return yourdestination;
    }
    //gets date from user
    public static String getdate() 
    { 
        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
        System.out.println("Enter preferrable date (dd/mm/yyyy)"); 
        String yourdate = sc.nextLine();
        return yourdate;
    }
    //Main Class
    public static void main(String args[]) {
        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream 
        // Date date = null; 
        System.out.println("Welcome to Fight Booking system!!\n");
        System.out.println("You are being provided with various options: ");
        System.out.println("1. Search a flight and then book a flight");
        System.out.println("2. View flight chart");
        System.out.println("3. Exit from system");
        int choice = sc.nextInt();
        //a list to take inputs for searching flights
        ArrayList<String> searchflight = new ArrayList<String>();
        //Creating list of Players/Cricketers
        List<flightbooking> list=new ArrayList<flightbooking>(); 
        List<flightbooking> filteredlist=new ArrayList<flightbooking>(); 
        //Creating flights 
        flightbooking f1 = new flightbooking(201,"Mumbai", "Delhi", "12/12/2021", "12:30:00", 1200, 0);   
        flightbooking f2 = new flightbooking(203,"Mumbai", "Pune", "14/05/2021", "12:30:00", 300,0);
        flightbooking f3 = new flightbooking(204,"Mumbai", "Chennai", "14/05/2021", "12:30:00", 2300,0); 
        flightbooking f4 = new flightbooking(315,"Mumbai", "Chennai", "14/05/2021", "08:30:00", 2300,0); 
        flightbooking f5 = new flightbooking(403,"Mumbai", "Delhi", "12/12/2021", "2:30:00", 800,0); 
        flightbooking f6 = new flightbooking(520,"Mumbai", "Delhi", "12/12/2021", "7:30:00", 1000, 0);
        flightbooking f7 = new flightbooking(675,"Mumbai", "Delhi", "13/12/2021", "24:30:00", 1800,0);
        flightbooking f8 = new flightbooking(211,"Mumbai", "Delhi", "15/12/2021", "3:30:00", 600,0);
        list.add(f1);
        list.add(f2);
        list.add(f3);
        list.add(f4);
        list.add(f5);
        list.add(f6);
        list.add(f7);
        list.add(f8);
        //searching a flight and then book a flight------------------
        while(choice != 3)
        {
            if(choice == 1)
            {
                System.out.println("You want to search a flight. Enter appropriate details:");

                //enter the current location
                System.out.println("Enter your current location: Mumbai");
                String location = "Mumbai";
                searchflight.add(location);
                //enter destination
                String setdestination = getdestination();
                searchflight.add(setdestination);

                //enter preferrable date
                // SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                // String setdate = getdate();
                // searchflight.add(setdate);
                // try{
                //     if(null != cinput && cinput.trim().length() > 0)
                //     {
                //         date = format.parse(cinput);
                //     }
                // }
                // catch(ParseException e){
                //     System.out.println(e);
                // }
                
                //display the avaliable flights 
                System.out.println("The available flights are:\n"); 
                System.out.println("Flight Id\tLocation\t Destination\t Date\t\tTime\t\t Price\t\t Status");
                for(flightbooking f:list)
                { 
                    if(f.destination == setdestination){
                        System.out.println(f.flight_id+"\t\t"+f.location+"\t\t"+f.destination+"\t\t "+f.date+"\t "+f.time+"\t "+f.price + "\t\t" + f.status);
                        filteredlist.add(f);

                    }
                }      
                System.out.println("\n");
                System.out.println("Do you want to book the flight ? (Enter 'Y' for yes and 'N' for no)");
                String str=sc.next(); 
                char ch=str.charAt(0);  
                if(ch == 'Y'){
                    //-----------------------function to book a flight
                    TicketBook t = new TicketBook();
                    System.out.println("Your currrent account balance is: "+ t.balance);
                    System.out.println("Enter the flight ID to be booked");
                    int flightnumber = sc.nextInt();
                    t.book(flightnumber, filteredlist);
                    System.out.println("\n\nThank you!! We are happy to serve you.");
                }
            }
            //Wants to view flight chart
            if(choice == 2)
            {
                System.out.println("You want to view flight chart.\n");
                Chart obj = new TicketBook(); 
                obj.view(filteredlist);  
                System.out.println("Thank you!! We are happy to serve you.");
            }
            System.out.println("\n Enter the choice");
            choice = sc.nextInt();
        }  
    }
}

