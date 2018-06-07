/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODRefCwk;

/**
 *
 * @author admin
 */
public class Job {
    
    private int UNumber;
    private JobType type;
    private double hours;
    private int level;
    private int penalty;

    public Job(int UNumber, JobType type, double hours, int level, int penalty) {
        this.UNumber = UNumber;
        this.type = type;
        this.hours = hours;
        this.level = level;
        this.penalty = penalty;
    }

    public int getUNumber() {
        return UNumber;
    }

    public void setUNumber(int UNumber) {
        this.UNumber = UNumber;
    }

    public JobType getType() {
        return type;
    }

    public void setType(JobType type) {
        this.type = type;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }
    
    
    
    
}
