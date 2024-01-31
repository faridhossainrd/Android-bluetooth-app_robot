package com.example.farid.wi_btcontroll;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.style.Circle;

public class Dilogbox {
    final AlertDialog alertDialog;
   Button button;
   ProgressBar progressBar;
   public Dilogbox(Activity context)
    {
        final AlertDialog.Builder alertbox=new AlertDialog.Builder(context);

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.loding,null);
        alertbox.setView(view);
        alertDialog= alertbox.create();


        button=(Button)view.findViewById(R.id.butn);
      //  progressBar=(ProgressBar)view.findViewById(R.id.progrs);

        Circle circle=new Circle();
        circle.setScale(0.3f);
        circle.setColor(Color.BLUE);
     //   progressBar.setIndeterminateDrawable(circle);
        alertDialog.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    public void demis()
    {
        alertDialog.dismiss();
    }

}
