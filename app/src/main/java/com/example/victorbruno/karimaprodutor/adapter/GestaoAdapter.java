package com.example.victorbruno.karimaprodutor.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.victorbruno.karimaprodutor.R;
import com.parse.ParseObject;

import java.util.ArrayList;


/**
 * Created by VictorBruno on 13/04/17.
 */

public class GestaoAdapter extends ArrayAdapter<ParseObject> {
    private Context mContext;
    private CollapsingToolbarLayout collapsingToolbarLayout;


    public GestaoAdapter(Context c, ArrayList<ParseObject> objects) {

        super(c, 0, objects);
        this.mContext = c;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);

        return super.getView(position, convertView, parent);
    }
}
