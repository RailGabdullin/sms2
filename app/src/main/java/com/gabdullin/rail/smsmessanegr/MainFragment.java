package com.gabdullin.rail.smsmessanegr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.InputType;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Date;

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
                    if(SMSList.getSmsAddress().equals("Диалог пуст")){
                        insertAddress(view.getContext());
                        return;
                    }
                    SmsManager.getDefault().sendTextMessage(SMSList.getSmsAddress(), null, ((EditText) view.findViewById(R.id.smsTextField)).getText().toString(), null, null);
                    SMSList.getSMSList().add(new SMS("me", ((EditText) view.findViewById(R.id.smsTextField)).getText().toString(), DateFormat.format("k:mm", new Date()).toString(), true));
                    ((EditText) view.findViewById(R.id.smsTextField)).setText(null);
                    updateSMSListView();
                }
            }
        });
    }

    private void insertAddress(Context context) {
        AlertDialog.Builder insertAddressDialog = new AlertDialog.Builder(getContext());
        insertAddressDialog.setTitle("Введите номер адресата");
        final EditText enterAddressNumber = new EditText(context);
        enterAddressNumber.setInputType(InputType.TYPE_CLASS_PHONE);
        insertAddressDialog.setView(enterAddressNumber);
        insertAddressDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SMSList.setAddress(enterAddressNumber.getText().toString());
            }
        });
        insertAddressDialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateSMSListView();
    }

    //Этот метод обновляет список сообщений при приходе-отправке новых сообщений.
    //Он используется в smsReceiver и лисенере кнопки send в MainFragment
    //Реализация через статичный метод явно не лучшая, буду благодарен за подсказку как улучшить
    public static void updateSMSListView() {
        recyclerView.setAdapter(new smsListAdapter());
    }
}
