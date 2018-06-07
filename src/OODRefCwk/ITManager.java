package OODRefCwk;
import java.util.*;
import java.io.*;

/**
 * This class implements the behaviour expected from the BITS system
 * as required for 6COM1037 Referred/Deferred Cwk - June 2018 and 
 * specified in the Management interface
 * 
 * @author - put your name here
 * @version 
 */
public class ITManager  implements Management 
{
// Declare fields
    private String ManagerName;
    private double budget;
    

//**************** BITS ************************** 
    /** Constructor requires the name of the trainee manager and initial budget. Staff
     * and jobs are also set up,  with all staff set to "available" for hire.
     * @param trainee the name of the trainee manager running the simulation
     * @param budget the initial budget allocated to the project account
     */
     public ITManager(String trainee, double budget)
     {
        this.ManagerName = trainee;
        this.budget = budget;
     }
    
    
    /**Returns a String representation of the state of the project,
     * including the name of the manager, state of the project account,
     * whether overdrawn or not, the staff in the team (or, "No staff" 
     * if team is empty)
     * @return a String representation of the state of the project,
     * including the name of the manager, state of the project account,
     * whether overdrawn or not, and the staff currently in the 
     * team,(or, "No staff" if team is empty)
     **/
    public String toString(){
        return "";
    }
    
    
    /** Returns the amount of money in the account
     * @returns the amount of money in the account
     */
    public double getAccount(){
        return budget;
    }
    
    /** Returns true if project account <=0 and the team has no staff 
     * who can leave. 
     * @returns true if project account <=0 and the team has no staff 
     * who can leave. 
     */
    public boolean isOverdrawn(){
        return false;
    }

    //********************** All Jobs************************* 
    /** Returns true if the number represents a job
     * @param num is the reference number of the job
     * @returns true if the reference number represents a job
     **/
     public boolean isJob(int num){
        return false;
    }

    
    /** Returns a String representation of all jobs 
     * @return returns a String representation of all jobs
     **/
    public String getAllJobs(){
        return "";
    }
        
    
    
//*********************** All Staff *************************    
    
    /** Returns details of a staff member with the given name, 
     * (staff may be in or out of the team)
     * @param name the name of the required staff member
     * @return details of a staff member with the name specified 
     * in the parameter
     **/
    public String getStaffMember(String name){
        return "";
    }
    
       /**Returns a String representation of all staff available for hire
     * @return a String representation of all staff available for hire
     **/
    public String getAvailableStaff(){
        return "";
    }
    
  
 // ***************** Team Staff ************************   

    /** Allows staff to be added to the team, if there is enough  
     * money in the account for the retainer.The hired staff member's 
     * state is set to "working" and their retainer is deducted from
     * the project account. Return the result of the hire; all messages 
     * should include the staff name and state of the project account
     * @param name is the name of the staff member
     * @return "Not found" if staff not found, "Already hired" if staff 
     * is already hired, "Not enough money" if not enough money in the 
     * account,"Hired" if staff are hired.All messages should include 
     * the staff name and state of the project account
     **/        
    public String hireStaff(String name){
        return "";
    }
    
        
   /** Returns true if the staff with the specified name 
     * is in the team, false otherwise.
     * @param name is the name of the staff
     * @return true if the staff with the name is in the team, 
     * false otherwise.
     **/
    public boolean isInTeam(String name){
        return false;
    }

    
    /**Returns a String representation of the staff in the project team
     * (including those on holiday), or the message "No staff hired"
     * @return a String representation of the staff in the project team
     **/
    public String getTeam(){
        return "";
    }


// ***************** Simulation ************************ 
    /** Retrieves the job with the job reference number, or returns "No 
     * such job".If job exists, finds a staff member in the team who can 
     * do the job.The results of doing a job will be one of the following:
     * "No such Job" - with no further action taken." Job completed by..." 
     * - add the cost of the job to account and include name of staff,  
     * - Job not completed as no staff available 
     * - deduct job penalty from account,
     * -Job not completed due to staff inexperience 
     * - deduct penalty from the account.
     * If a job is not completed and the project account becomes negative,
     * add "Project is overdrawn"  to the output. 
     * @param jbNo is the reference number of the job
     * @return a String showing the result of doing the job(as above)
     */
    public String doJob(int jbNo){
        return "";
    }

    /**Staff rejoin the team after holiday by setting state to "working" 
     * @param the name of the staff rejoining the team after holiday
     */
    public String staffRejoinTeam(String name){
        return "";
    }
    
     //****************** private methods for Task 6.1 functionality*******************
    //*******************************************************************************
    private void setupStaff()
    {
        
    }
    
    private void setupTasks()
    {
        
    }
}
