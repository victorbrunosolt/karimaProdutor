package com.example.victorbruno.karimaprodutor.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.victorbruno.karimaprodutor.fragment.ContaFragment;
import com.example.victorbruno.karimaprodutor.fragment.GestaoFragment;
import com.example.victorbruno.karimaprodutor.fragment.NotificacoesFragment;
import com.example.victorbruno.karimaprodutor.fragment.TimeLineFragment;

/**
 * Created by VictorBruno on 13/04/17.
 */

public class TabsAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private String[] titles = {"TIME LINE","GESTÃO","NOTIFICAÇÕES","CONTA"};

    public TabsAdapter(FragmentManager fm, Context c) {

        super(fm);
        mContext = c;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;

        if(position == 0){
            frag = new TimeLineFragment();
        }
        if(position == 1){
            frag = new GestaoFragment();
        }
        if(position == 2){
            frag = new NotificacoesFragment();
        }
        if(position == 3){
            frag = new ContaFragment();
        }


        Bundle b = new Bundle();
        b.putInt("position", position);

        frag.setArguments(b);

        return frag;

    }

    @Override
    public int getCount() {
        return titles.length;
    }
    @Override
    public CharSequence getPageTitle(int position) {

        return ( titles[position] );
    }
}

