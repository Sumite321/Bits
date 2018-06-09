/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODRefCwk;

/**
 *
 * @author admins
 */
public class Analyst extends Staff{

    private boolean canProgram;
    
    public Analyst(String UName, int experience, int retainer, double rate, boolean program) {
        super(UName, experience, retainer, rate);
        this.canProgram = program;
    }
}
