package com.gabdullin.rail.smsmessanegr;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainFragment extends Fragment {

    static RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, container,false);
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.smsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        updateSMSListView();
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.sendSMS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((EditText) view.findViewById(R.id.smsTextField)).getText().length() > 0) {
                    SmsManager.getDefault().sendTextMessage("+7927432535", null, ((EditText) view.findViewById(R.id.smsTextField)).getText().toString(), null, null);
                    SMSList.getSMSList().add(new SMS("me", ((EditText) view.findViewById(R.id.smsTextField)).getText().toString(), "Time", true));
                    ((EditText) view.findViewById(R.id.smsTextField)).setText(null);
                    updateSMSListView();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        updateSMSListView();
    }

    public static void updateSMSListView() {
        recyclerView.setAdapter(new smsListAdapter());
    }
}
