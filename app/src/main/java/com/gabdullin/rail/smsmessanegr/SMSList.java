package com.gabdullin.rail.smsmessanegr;

import java.util.ArrayList;

public class SMSList extends ArrayList<SMS>{

    private static SMSList smsList;
    private static String smsAddress;

    private SMSList(){
    }

    public static SMSList getSMSList(){
        if(smsList == null) smsList = new SMSList();
        return smsList;
    }

    public static String getSmsAddress(){
        if(smsList.size() < 1 && smsAddress == null){
            return "Диалог пуст";
        } else {
            if(smsAddress == null) smsAddress = smsList.get(0).getFrom();
            return smsAddress;
        }
    }

    public static void setAddress(String smsAddress) {
        SMSList.smsAddress = smsAddress;
    }
}
