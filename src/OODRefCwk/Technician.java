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
    
    private boolean isCiscoo;

    public Technician(String UName, int experience, int retainer, double rate,boolean isC) {
        super(UName, experience, retainer, rate);
        this.isCiscoo = isC;
    }  

    public boolean isIsCiscoo() {
        return isCiscoo;
    }

    public void setIsCiscoo(boolean isCiscoo) {
        this.isCiscoo = isCiscoo;
    }
    
    
    
}
