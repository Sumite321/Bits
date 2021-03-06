package OODRefCwk;
import java.util.*;
import java.io.*;

/**
 * This class implements the behaviour expected from the BITS system
 * as required for 6COM1037 Referred/Deferred cwk - June 2018 and 
 * specified in the Management interface
 * 
 * @author - Liban
 * @version 
 */
public class ITManager  implements Management 
{
// Declare fields
    private String ManagerName;
    private double budget;
    private HashMap<Integer,Job> allJobs = new HashMap<>();// one collection for all jobs
    private HashMap<String,Staff> staffToHire = new HashMap<>();// one collection for all hirable staff
    private HashMap<String,Staff> teamMembers = new HashMap<>();// one collection for all team members
    private HashMap<Integer,Double> bestChoice = new HashMap<>();// one collection for all jobs

//**************** BITS ************************** 
    /** Constructor requires the name of the trainee manager and initial budget. Staff
     * and jobs are also set up,  with all staff set to "available" for hire.
     * @param trainee the name of the trainee manager running the simulation
     * @param budget the initial budget allocated to the project account
     */
     public ITManager(String trainee, int budget)
     {
        this.ManagerName = trainee;
        this.budget = budget;
        setupTasks();
        setupStaff();
        
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
        
        String isOverdrawn = "Project is not overdrawn";
        
        if(isOverdrawn()){
            isOverdrawn = "Project is overdrawn";
        }
        
        return "Manager name: " + ManagerName + "\n" +
                "Available funds: " + budget + "\n" +
                "Team members: \n" + getTeam() + "\n" + 
                isOverdrawn;
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
        
        if(getAccount()<=0 && teamMembers.isEmpty()){return true;}
        
        return false;
    }

    //********************** All Jobs************************* 
    /** Returns true if the number represents a job
     * @param num is the reference number of the job
     * @returns true if the reference number represents a job
     **/
    public boolean isJob(int num) {

        boolean found = false;

        for (Job j : allJobs.values()) {
            if (j.getUNumber() == num) {
                found = true;
            }
        }
        return found;
    }

    
    /** Returns a String representation of all jobs 
     * @return returns a String representation of all jobs
     **/
    public String getAllJobs() {

        StringBuilder jobDetails = new StringBuilder();

        for (Job j : allJobs.values()) {
            {
                jobDetails.append("Job number: " + j.getUNumber() + " "
                        + "Job type: " + j.getType() + " "
                        + "Job difficulty: " + j.getLevel() + " "
                        + "Penalty: " + j.getPenalty() + " "
                        + "Hours to complete: " + j.getHours() + "\n"
                );
            }
        }
        return jobDetails.toString();
    }
       
    public Job getJobReference(int jobNo) {

        Job found = null;

        if (isJob(jobNo)) {

            for (Job job : allJobs.values()) {

                if (job.getUNumber() == jobNo) {

                    found = job;
                }
            }

        }

        return found;

    }
    
    
//*********************** All Staff *************************    
    
    /** Returns details of a staff member with the given name, 
     * (staff may be in or out of the team)
     * @param name the name of the required staff member
     * @return details of a staff member with the name specified 
     * in the parameter
     **/
    public String getStaffMember(String name){
        
        StringBuilder staffDetails = new StringBuilder();

        for (Staff s : staffToHire.values()) {
            if (staffExists(name)) {
                staffDetails.append("Staff name: " + s.getUName() + " " + 
                        "Experience level: " + s.getExperience() + " " +
                        "Retainer: " + s.getRetainer() + " " + 
                        "Hourly rate " + s.getRate()+ " " + 
                        "State:" + s.getState());
                if (s instanceof Analyst ){staffDetails.append(" " + ((Analyst) s).isCanProgram() + " " + "Analyst");}
                if (s instanceof Technician ){staffDetails.append(" " + ((Technician) s).isIsCiscoo() + " " + "Technician");}
                if (s instanceof Programmer ){staffDetails.append( " " + "Programmer");}

            } else {
                staffDetails.append("No such staff");
            }
        }

        return staffDetails.toString();
    }
    
    private boolean staffExists(String name) {

        boolean found = false;

        for (Staff s : staffToHire.values()) {
            if (s.getUName().equals(name)) {
                found = true;
            }
        }
        return found;

    }
    
    private Staff getStaffReference(String name) {

        Staff toReturn = null;
        
        if (staffExists(name)) {
            for (Staff s : staffToHire.values()) {
                if (s.getUName().equals(name)) {
                    toReturn = s;
                }
            }
        }
        return toReturn;
    }
    
       /**Returns a String representation of all staff available for hire
     * @return a String representation of all staff available for hire
     **/
    public String getAvailableStaff(){
        
        StringBuilder availableStaff = new StringBuilder();
        
        for(Staff s: staffToHire.values()){
            availableStaff.append("Staff name: " + s.getUName() + " " + 
                        "Experience level: " + s.getExperience() + " " +
                        "Retainer: " + s.getRetainer() + " " + 
                        "Hourly rate " + s.getRate() + " " +
                        "State: " + s.getState().toString() + "\n");
        }
        
        return availableStaff.toString();
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
    public String hireStaff(String name) {

        String message = "No staff found";

        if (staffExists(name)) { // check if the staff exists
            Staff toHire = getStaffReference(name); // get the object reference to variable
            if (toHire.getRetainer() <= getAccount()) { //check if there is enough money
                budget = budget - toHire.getRetainer(); // deduct the retainer from account
                toHire.setState(StaffState.WORKING);
                teamMembers.put(toHire.getUName(), toHire);
                message = toHire.getUName() + "has been hired for " + toHire.getRetainer() + "\n" + "Current account balance: " + getAccount();
                staffToHire.remove(toHire.getUName());
                //staffToHire.r
            }else{
            message = "Not enough money";
            }
        }

        return message;
    }
    
        
   /** Returns true if the staff with the specified name 
     * is in the team, false otherwise.
     * @param name is the name of the staff
     * @return true if the staff with the name is in the team, 
     * false otherwise.
     **/
    public boolean isInTeam(String name){
      
        boolean found = false;
        
        for (Staff s : teamMembers.values()) {
            if (s.getUName().equals(name)) {
                found = true;
            } 
        }
        return found;
    }

    private Staff getStaffTeamReference(String name) {

        Staff toReturn = null;
        
        if (isInTeam(name)) {
            for (Staff s : teamMembers.values()) {
                if (s.getUName().equals(name)) {
                    toReturn = s;
                }
            }
        }
        return toReturn;
    }
    /**Returns a String representation of the staff in the project team
     * (including those on holiday), or the message "No staff hired"
     * @return a String representation of the staff in the project team
     **/
    public String getTeam() {

        StringBuilder showTeam = new StringBuilder();

        if (teamMembers.isEmpty()) {
            showTeam.append("No staff hired");
        } else {

            for (Staff s : teamMembers.values()) {
                showTeam.append("Staff name: " + s.getUName() + " "
                        + "Experience level: " + s.getExperience() + " "
                        + "Retainer: " + s.getRetainer() + " "
                        + "Hourly rate: " + s.getRate() + " "
                        + "State: " + s.getState().toString() + "\n");
            }
        }
        return showTeam.toString();
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
    public String doJob(int jbNo) {

        Double toDeduct = 0.0;
        String message = "";
        Staff toRemove = null;
        // go through the membersOfTeam 
        // get their especialisation
        // check if it complies with the job type
        //display message
        // update account
        bestChoice.clear();
        if (isJob(jbNo)) {
            Job jobRef = getJobReference(jbNo);
            JobType type = jobRef.getType(); // Get the job reference, EX: Software
            for (Staff staff : teamMembers.values()) { // Go through the collection
                if(staff.getState() != StaffState.ONHOLIDAY){
                if (type == JobType.SOFTWARE || type == JobType.DESIGN) { // if the type is Software, Programmer can do it and Analyst can do it if True.

                    if (staff instanceof Programmer) { // if a programmer exists
                        if (jobRef.getLevel() >= staff.getExperience()) {
                            message = "not completed due to staff inexperience";
                            toDeduct = jobRef.getPenalty() * -1.0;
                            bestChoice.put(2,toDeduct);
                        } else {
                            message = "Job completed by" + staff.getUName(); // job completed
                            toDeduct = staff.getRate() * jobRef.getHours();
                            bestChoice.put(1,toDeduct);
                            //teamMembers.remove(staff.getUName());
                        }
                    }
                    if (staff instanceof Analyst) { // if a Analyst exists
                        
                         if (jobRef.getLevel() >= staff.getExperience()) {
                            message = "not completed due to staff inexperience";
                            toDeduct = jobRef.getPenalty() * -1.0;
                            bestChoice.put(2,toDeduct);
                        }else{
                        
                        if (((Analyst) staff).isCanProgram() && type == JobType.SOFTWARE) { // if the Analyst can program
                            message = "Job completed by" + staff.getUName(); // job done if software + can program
                            toDeduct = staff.getRate() * jobRef.getHours();
                            bestChoice.put(1,toDeduct);
                            //teamMembers.remove(staff.getUName());
                        } else {
                            message = "Job completed by" + staff.getUName(); // job done for design
                            toDeduct = staff.getRate() * jobRef.getHours();
                            bestChoice.put(1,toDeduct);
                        }
                    }
                    }
                    if (staff instanceof Technician) {
                        message = "No staff available";
                        toDeduct = jobRef.getPenalty() * -1.0 ;
                        bestChoice.put(3,toDeduct);
                    }

                } else if (type == JobType.HARDWARE && staff instanceof Technician) {
                     if (jobRef.getLevel() >= staff.getExperience()) {
                            message = "not completed due to staff inexperience";
                            toDeduct = jobRef.getPenalty() * -1.0;
                            bestChoice.put(2,toDeduct);
                    }else{
                    message = "Job completed by" + staff.getUName();
                    toDeduct += staff.getRate() * jobRef.getHours();
                    bestChoice.put(1,toDeduct);
                    toRemove = staff;
                     }
                } else  {
                    message = "No staff available";
                    toDeduct = jobRef.getPenalty() * -1.0;
                    bestChoice.put(3,toDeduct);
                }
            }else{
                 message = "No staff available";
                    toDeduct = jobRef.getPenalty() * -1.0;
                    bestChoice.put(3,toDeduct);
                
                }
            }

        } else {
            message = "No such Job";
        }
        
        
        if(teamMembers.size() > 1){
            if(bestChoice.containsKey(1)){
            budget = budget + bestChoice.get(1);
            teamMembers.remove(toRemove.getUName());
            toRemove.setState(StaffState.ONHOLIDAY);
            message = "Job Completed by" + toRemove.getUName();
            } else if(bestChoice.containsKey(2) ){
            budget = budget + bestChoice.get(2);
            message = "not completed due to staff inexperience";
            }else if(bestChoice.containsKey(3)){
            budget = budget + bestChoice.get(3);
            message = "not completed due to staff inexperience";
            }

        }else{
        budget = budget + toDeduct;}
        return message;
    }

  
    /**Staff rejoin the team after holiday by setting state to "working" 
     * @param the name of the staff rejoining the team after holiday
     */
    public String staffRejoinTeam(String name){
        
        String message = "";
        if(isInTeam(name)){
          getStaffTeamReference(name).setState(StaffState.WORKING);
          message = "Status changed to Working";
       }else{
          message = "Staff does not exist";
        
        }
        
        return message;
    }
    
     //****************** private methods for Task 6.1 functionality*******************
    //*******************************************************************************
    private void setupStaff() {
        // add all from the specs
        //Staff alan = new Analyst("Alan",2,300,30,StaffState.AVAILABLE,false);

        staffToHire.put("Alan", new Analyst("Alan", 2, 300, 30, false));
        staffToHire.put("Bob", new Technician("Bob", 2, 100, 30, false));
        staffToHire.put("Ceri", new Technician("Ceri", 4, 250, 40, true));
        staffToHire.put("Dan", new Programmer("Dan", 2, 300, 20));
        staffToHire.put("Ela", new Programmer("Ela", 7, 200, 20));
        staffToHire.put("Fela", new Analyst("Fela", 6, 300, 90, false));
        staffToHire.put("Gita", new Programmer("Gita", 2, 200, 20));
        staffToHire.put("Hela", new Technician("Hela", 8, 450, 40, false));
        staffToHire.put("Ian", new Analyst("Ian", 4, 300, 60, false));

        for (Staff makeAvailable : staffToHire.values()) {
            makeAvailable.setState(StaffState.AVAILABLE);

        }

    }

    private void setupTasks()
    {
        // add all from the specs
                
        allJobs.put(0,new Job(100,JobType.DESIGN,10,3,200));
               
        Job job2 = new Job(101,JobType.HARDWARE,20,3,150);
        Job job3 = new Job(102,JobType.SOFTWARE,30,3,100);
        Job job4 = new Job(103,JobType.DESIGN,25,9,250);
        Job job5 = new Job(104,JobType.SOFTWARE,15,7,350);
        Job job6 = new Job(105,JobType.HARDWARE,35,8,300);
        Job job7 = new Job(106,JobType.HARDWARE,20,5,400);
        
        //allJobs.put(job1.getUNumber(),job1);
        allJobs.put(job2.getUNumber(),job2);
        allJobs.put(job3.getUNumber(),job3);
        allJobs.put(job4.getUNumber(),job4);
        allJobs.put(job5.getUNumber(),job5);
        allJobs.put(job6.getUNumber(),job6);
        allJobs.put(job7.getUNumber(),job7);

    }
}
