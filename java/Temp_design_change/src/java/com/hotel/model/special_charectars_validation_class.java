package com.hotel.model;

/*
 * - special_charectar_calidation_class.java
 * - Author: Mohammed Mohsen --- largbody@hotmail.com
 * - Priject: Hotel Reservation & Account Managment System for Daffodiles Palace Hotel
 * - Date: 06-06-2006
 * - Purpose: used to valid if the string has any special characters such as . or ' to prevent any conflict in the database queries
 * - Refernce: www.google.com
 */

public class special_charectars_validation_class {
    
    
    private String [] spl_char; //special charactar array
    private String msg; //error message
    
    public special_charectars_validation_class() {
        
        setSpl_char(new String[149]);
        getSpl_char()[0] = "�";
        getSpl_char()[1] = "�";
        getSpl_char()[2] = "�";
        getSpl_char()[3] = "�";
        getSpl_char()[4] = "�";
        getSpl_char()[5] = "�";
        getSpl_char()[6] = "�";
        getSpl_char()[7] = "�";
        getSpl_char()[8] = "�";
        getSpl_char()[9] = "�";
        getSpl_char()[10] = "�";
        getSpl_char()[11] = "?";
        getSpl_char()[12] = "?";
        getSpl_char()[13] = "?";
        getSpl_char()[14] = "?";
        getSpl_char()[15] = "?";
        getSpl_char()[16] = "?";
        getSpl_char()[17] = "?";
        getSpl_char()[18] = "?";
        getSpl_char()[19] = "?";
        getSpl_char()[20] = "�";
        getSpl_char()[21] = "!";
        getSpl_char()[22] = "\"";
        getSpl_char()[23] = "#";
        getSpl_char()[24] = "$";
        getSpl_char()[25] = "%";
        getSpl_char()[26] = "&";
        getSpl_char()[27] = "'";
        getSpl_char()[28] = "(";
        getSpl_char()[29] = ")";
        getSpl_char()[30] = "*";
        getSpl_char()[31] = "+";
        getSpl_char()[32] = ",";
        getSpl_char()[33] = "-";
        getSpl_char()[34] = ".";
        getSpl_char()[35] = "/";
        getSpl_char()[36] = ":";
        getSpl_char()[37] = ";";
        getSpl_char()[38] = "<";
        getSpl_char()[39] = "=";
        getSpl_char()[40] = ">";
        getSpl_char()[41] = "?";
        getSpl_char()[42] = "@";
        getSpl_char()[43] = "[";
        getSpl_char()[44] = "\\";
        getSpl_char()[45] = "]";
        getSpl_char()[46] = "^";
        getSpl_char()[47] = "_";
        getSpl_char()[48] = "`";
        getSpl_char()[49] = "{";
        getSpl_char()[50] = "|";
        getSpl_char()[51] = "}";
        getSpl_char()[52] = "~";
        getSpl_char()[53] = "�";
        getSpl_char()[54] = "�";
        getSpl_char()[55] = "�";
        getSpl_char()[56] = "�";
        getSpl_char()[57] = "�";
        getSpl_char()[58] = "�";
        getSpl_char()[59] = "�";
        getSpl_char()[60] = "�";
        getSpl_char()[61] = "�";
        getSpl_char()[62] = "�";
        getSpl_char()[63] = "�";
        getSpl_char()[64] = "�";
        getSpl_char()[65] = "�";
        getSpl_char()[66] = "�";
        getSpl_char()[67] = "�";
        getSpl_char()[68] = "�";
        getSpl_char()[69] = "�";
        getSpl_char()[70] = "�";
        getSpl_char()[71] = "�";
        getSpl_char()[72] = "�";
        getSpl_char()[73] = "�";
        getSpl_char()[74] = "�";
        getSpl_char()[75] = "�";
        getSpl_char()[76] = "�";
        getSpl_char()[77] = "�";
        getSpl_char()[78] = "�";
        getSpl_char()[79] = "�";
        getSpl_char()[80] = "�";
        getSpl_char()[81] = "�";
        getSpl_char()[82] = "�";
        getSpl_char()[83] = "�";
        getSpl_char()[84] = "�";
        getSpl_char()[85] = "�";
        getSpl_char()[86] = "�";
        getSpl_char()[87] = "�";
        getSpl_char()[88] = "�";
        getSpl_char()[89] = "�";
        getSpl_char()[90] = "�";
        getSpl_char()[91] = "�";
        getSpl_char()[92] = "�";
        getSpl_char()[93] = "�";
        getSpl_char()[94] = "�";
        getSpl_char()[95] = "�";
        getSpl_char()[96] = "�";
        getSpl_char()[97] = "�";
        getSpl_char()[98] = "�";
        getSpl_char()[99] = "�";
        getSpl_char()[100] = "�";
        getSpl_char()[101] = "�";
        getSpl_char()[102] = "�";
        getSpl_char()[103] = "�";
        getSpl_char()[104] = "�";
        getSpl_char()[105] = "�";
        getSpl_char()[106] = "�";
        getSpl_char()[107] = "�";
        getSpl_char()[108] = "�";
        getSpl_char()[109] = "�";
        getSpl_char()[110] = "�";
        getSpl_char()[111] = "�";
        getSpl_char()[112] = "�";
        getSpl_char()[113] = "�";
        getSpl_char()[114] = "�";
        getSpl_char()[115] = "�";
        getSpl_char()[116] = "�";
        getSpl_char()[117] = "�";
        getSpl_char()[118] = "�";
        getSpl_char()[119] = "�";
        getSpl_char()[120] = "�";
        getSpl_char()[121] = "�";
        getSpl_char()[122] = "�";
        getSpl_char()[123] = "�";
        getSpl_char()[124] = "�";
        getSpl_char()[125] = "�";
        getSpl_char()[126] = "�";
        getSpl_char()[127] = "�";
        getSpl_char()[128] = "�";
        getSpl_char()[129] = "�";
        getSpl_char()[130] = "�";
        getSpl_char()[131] = "�";
        getSpl_char()[132] = "�";
        getSpl_char()[133] = "�";
        getSpl_char()[134] = "�";
        getSpl_char()[135] = "�";
        getSpl_char()[136] = "�";
        getSpl_char()[137] = "�";
        getSpl_char()[138] = "�";
        getSpl_char()[139] = "�";
        getSpl_char()[140] = "�";
        getSpl_char()[141] = "�";
        getSpl_char()[142] = "�";
        getSpl_char()[143] = "�";
        getSpl_char()[144] = "�";
        getSpl_char()[145] = "�";
        getSpl_char()[146] = "�";
        getSpl_char()[147] = "�";
        getSpl_char()[148] = "�";
        
        
    }
    
    //check if the staring have one or more special character
    public boolean validate(String anotherString){
        for(int i=0; i<anotherString.length(); i++){
            for(int c=0; c<getSpl_char().length; c++){
                if(anotherString.substring(i,i+1).equalsIgnoreCase(getSpl_char()[c])){
                    setMsg("<br>your text contains special character: \" "+getSpl_char()[c]+" \"");
                    return false;
                }
            }
        }
        return true;
    }
    
    //check if the staring have one or more special character, this method will exclude "@" and "." and "-" and "_" for emails
    public boolean validate_email(String email){
        for(int i=0; i<email.length(); i++){
            for(int c=0; c<getSpl_char().length; c++){
                if(!getSpl_char()[c].equalsIgnoreCase("@") && !getSpl_char()[c].equalsIgnoreCase(".") && !getSpl_char()[c].equalsIgnoreCase("_") && !getSpl_char()[c].equalsIgnoreCase("-")){
                    if(email.substring(i,i+1).equalsIgnoreCase(getSpl_char()[c])){
                        setMsg("<br>your email contains illegal character: \" "+getSpl_char()[c]+" \"");
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public String[] getSpl_char() {
        return spl_char;
    }
    
    public void setSpl_char(String[] spl_char) {
        this.spl_char = spl_char;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
}
