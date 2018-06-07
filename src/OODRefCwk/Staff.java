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
    
    public String UName;
    public int experience;
    public int retainer;
    public double rate;
    public StaffState state;

    public Staff(String UName, int experience, int retainer, double rate, StaffState state) {
        this.UName = UName;
        this.experience = experience;
        this.retainer = retainer;
        this.rate = rate;
        this.state = state;
    }

    public String getUName() {
        return UName;
    }

    public abstract void setUName(String UName);

    public int getExperience() {
        return experience;
    }

    public abstract void setExperience(int experience);

    public int getRetainer() {
        return retainer;
    }

    public abstract void setRetainer(int retainer);

    public double getRate() {
        return rate;
    }

    public abstract void setRate(double rate);

    public StaffState getState() {
        return state;
    }

    public void setState(StaffState state) {
        this.state = state;
    }
    
    
    
    
    
}
