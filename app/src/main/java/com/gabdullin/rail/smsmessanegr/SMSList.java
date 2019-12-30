package com.gabdullin.rail.smsmessanegr;

import java.util.ArrayList;

public class SMSList extends ArrayList<SMS>{

    private static SMSList smsList;

    private SMSList(){
    }

    public static SMSList getSMSList(){
        if(smsList == null) smsList = new SMSList();
        return smsList;
    }
}
