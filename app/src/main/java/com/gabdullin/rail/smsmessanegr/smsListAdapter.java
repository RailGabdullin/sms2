package com.gabdullin.rail.smsmessanegr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class smsListAdapter extends RecyclerView.Adapter<smsListAdapter.smsListHolder> {
    private SMSList smsList = SMSList.getSMSList();

    @NonNull
    @Override
    public smsListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new smsListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sms_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull smsListHolder holder, int position) {
        holder.bind(smsList.get(position));
    }

    @Override
    public int getItemCount() {
        return smsList.size();
//        return 3;
    }

    class smsListHolder extends RecyclerView.ViewHolder {

        TextView smsFrom;
        TextView smsText;
        TextView smsDate;
        LinearLayout smsLayout;

        public smsListHolder(@NonNull View itemView) {
            super(itemView);
            smsFrom = itemView.findViewById(R.id.smsFrom);
            smsText = itemView.findViewById(R.id.smsText);
            smsDate = itemView.findViewById(R.id.smsDate);
            smsLayout = itemView.findViewById(R.id.smsLayout);
        }

        public void bind(SMS sms){
            if(sms.isMine()) smsLayout.setGravity(5);
            smsFrom.setText(sms.getFrom());
            smsText.setText(sms.getText());
            smsDate.setText(sms.getDate());
        }
    }
}
