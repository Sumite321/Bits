package OODRefCwk;

import java.io.*;
import java.util.*;
/**
 * provides a command line interface
 * 
 * @author A.A.Marczyk
 * @version 25/05/2018
 */
public class ManagerUI
{
    private static Management man1;
    private static Scanner myIn = new Scanner(System.in);
    
    
    public static void main(String[] args)
    {
        int choice;
        String playerName;

        try
        {
            System.out.println("Enter manager's name");
            String mg = myIn.nextLine();
            man1 = new ITManager(mg,1000); // create
            choice = 100;
            while (choice != 0)
            {
                choice = getMenuItem();
                if (choice == 1)
                {
                    System.out.println(man1.toString());
                }
                else if (choice == 2)
                {
                    System.out.println(man1.getAvailableStaff());
                }
                else if (choice == 3)
                {
                    System.out.println("Enter Staff name");
                    String nme = (myIn.nextLine()).trim();
                    if(!man1.isOverdrawn())
                    {
                        System.out.println(man1.hireStaff(nme));
                    }
                    else
                    {
                        System.out.println("Project is overdrawn");
                    }
                } 
                else if (choice == 4)
                {
                    System.out.println(man1.getTeam());
                }
                else if (choice == 5)
                {
                    System.out.println(man1.getAllJobs());
                }
                else if (choice == 6)
                {   
                    System.out.println("Enter number of the job");
                    String jobn = (myIn.nextLine()).trim();
                    man1.doJob(Integer.valueOf(jobn));
                }              
                else if (choice == 7)
                {
                    System.out.println("Enter Staff name");
                    String nme = (myIn.nextLine()).trim();
                    if(man1.isInTeam(nme))
                    {
                        System.out.println(man1.staffRejoinTeam(nme));
                    }  
                }

            }     
        }
        catch (IOException e) {System.out.println (e);}   
        System.out.println("Thank-you");
    }
    
    private static int getMenuItem()throws IOException
    {   
        int choice = 100;  
        System.out.println("Main Menu");
        System.out.println("0. quit");
        System.out.println("1. list project information");
        System.out.println("2. list staff available for hire");
        System.out.println("3. hire staff for team"); 
        System.out.println("4. list staff in the team");
        System.out.println("5. list jobs ");
        System.out.println("6. do a job");
        System.out.println("7. staff rejoin team from holiday");
        
        while (choice < 0 || choice  > 7)
        {
            System.out.println("Enter the number of your choice");
            choice =  myIn.nextInt();
            myIn.nextLine();
        }
        return choice;        
    }  
}