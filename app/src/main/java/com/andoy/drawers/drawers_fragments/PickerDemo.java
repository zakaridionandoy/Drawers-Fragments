package com.andoy.drawers.drawers_fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class PickerDemo extends Fragment implements View.OnClickListener
{
    private EditText dateEditText;
    private EditText timeEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View layout = inflater.inflate(R.layout.picker_fragment, container, false);

        dateEditText = layout.findViewById(R.id.date_editText);
        timeEditText = layout.findViewById(R.id.time_editText);

        dateEditText.setOnClickListener(PickerDemo.this);
        timeEditText.setOnClickListener(PickerDemo.this);

        return layout;
    }

    @Override
    public void onClick(View v)
    {
        int id = v.getId();
        switch (id){
            case R.id.date_editText:
                DialogFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getFragmentManager(), "datePicker");
                break;

            case R.id.time_editText:
                DialogFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.show(getFragmentManager(), "timePicker");
                break;
        }
    }
    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener
    {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }
        public void onTimeSet(TimePicker view, int hourOfDay, int minute)
        {

            String time = "";

            if(hourOfDay > 12){
                time = (hourOfDay - 12) + ":" + minute + " PM";
            } else if(hourOfDay == 12){
                time = hourOfDay + ":" + minute + " PM";
            } else if(hourOfDay == 0){
                time = "12:" + minute + " AM";
            } else {
                time = hourOfDay + ":" + minute + " AM";
            }
            Toast.makeText(getActivity(), time, Toast.LENGTH_SHORT).show();
        }
    }
    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
    {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {

            final Calendar c = Calendar.getInstance();
            int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH);
            int year = c.get(Calendar.YEAR);

            return new DatePickerDialog(getActivity(), this, year, month, dayOfMonth);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
        {
            month += 1;
            Toast.makeText(getActivity(), year+"-"+month+"-"+dayOfMonth, Toast.LENGTH_SHORT).show();
        }
    }
}

