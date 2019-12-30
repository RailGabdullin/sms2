package com.gabdullin.rail.smsmessanegr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telephony.SmsMessage;
import android.text.format.DateFormat;

public class smsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) return;

        Object[] pdus = (Object[]) intent.getExtras().get("pdus");
        SmsMessage[] messages = new SmsMessage[pdus.length];
        for (int i = 0; i < pdus.length; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], intent.getExtras().getString("format"));
            }
            else
            {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }
        }
        String smsFromPhone = messages[0].getDisplayOriginatingAddress();
        StringBuilder body = new StringBuilder();
        for (int i = 0; i < messages.length; i++) {
            body.append(messages[i].getMessageBody());
        }
        String bodyText = body.toString();
        addToList(smsFromPhone, bodyText, messages[0].getTimestampMillis(), false, context);
    }

    private void addToList(String smsFromPhone, String bodyText, long timestampMillis, boolean mine, Context context) {
        SMSList.getSMSList().add(new SMS(smsFromPhone, bodyText, DateFormat.format("HH:mm", timestampMillis).toString(), mine));
        MainFragment.updateSMSListView();
    }
}
