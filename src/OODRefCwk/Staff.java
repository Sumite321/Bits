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
public abstract class Staff {
    
    private String UName;
    private int experience;
    private int retainer;
    private double rate;
    private StaffState state;

    public Staff(String UName, int experience, int retainer, double rate) {
        this.UName = UName;
        this.experience = experience;
        this.retainer = retainer;
        this.rate = rate;
    }

    public String getUName() {
        return UName;
    }

    public int getExperience() {
        return experience;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setRetainer(int retainer) {
        this.retainer = retainer;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getRetainer() {
        return retainer;
    }

    public double getRate() {
        return rate;
    }

    public StaffState getState() {
        return state;
    }

    public void setState(StaffState state) {
        this.state = state;
    }

}
