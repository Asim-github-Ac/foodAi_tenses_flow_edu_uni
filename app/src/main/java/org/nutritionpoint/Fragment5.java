package org.nutritionpoint;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.tensorflow.lite.examples.detection.R;


public class Fragment5 extends Fragment implements View.OnClickListener{

    Button b1,b2;
    FragmentsCommunicator fc;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f5_layout,container,false);

        b1 = (Button)view.findViewById(R.id.clearBtn);
        b1.setOnClickListener(this);

        b2 = (Button)view.findViewById(R.id.showBtn);
        b2.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        fc = (FragmentsCommunicator) getActivity();

        if (view.getId() == R.id.clearBtn){
            fc.respond("clear", 1);
        }
        else if(view.getId() == R.id.showBtn){
            fc.respond("show", 1);
        }
    }
}