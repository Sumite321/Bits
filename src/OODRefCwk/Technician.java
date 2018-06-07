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
public class Technician extends Staff{
    
    private boolean isCisco;

    public Technician(String UName, int experience, int retainer, double rate, StaffState state,boolean isC) {
        super(UName, experience, retainer, rate, state);
        this.isCisco = isC;
    }  
}
