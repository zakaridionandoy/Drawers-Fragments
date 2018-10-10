package com.andoy.drawers.drawers_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ToggleDemo extends Fragment implements CompoundButton.OnCheckedChangeListener
{

    private boolean toggleButtonValue = false;

    private boolean toggleSwitchValue = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View layout = inflater.inflate(R.layout.toggle_fragment, container, false);

        ToggleButton toggleButton = layout.findViewById(R.id.toggle_button);
        SwitchCompat toggleSwitch = layout.findViewById(R.id.toggle_switch);

        toggleButtonValue = toggleButton.isChecked();
        toggleSwitchValue = toggleSwitch.isChecked();

        toggleButton.setOnCheckedChangeListener(ToggleDemo.this);
        toggleSwitch.setOnCheckedChangeListener(ToggleDemo.this);

        return layout;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        int id = buttonView.getId();
        String messsage = "";

        switch (id){
            case R.id.toggle_button:
                toggleButtonValue = isChecked;
                messsage = isChecked ? "GPS is on" : "GPS is off";
                break;

            case R.id.toggle_switch:
                toggleSwitchValue = isChecked;
                messsage = isChecked ? "Switch is on" : "Switch is off";
                break;
        }

        Toast.makeText(ToggleDemo.this.getActivity(), messsage, Toast.LENGTH_SHORT).show();
    }

}

