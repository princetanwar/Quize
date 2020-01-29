package com.loveinshayari.quize;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GridAdaptor extends BaseAdapter {

    private int sets= 0;

    public GridAdaptor(int sets) {
        this.sets = sets;
    }

    @Override
    public int getCount() {
        return sets;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convirdView, final ViewGroup parent) {
        View View;
        if (convirdView == null) {
            View = LayoutInflater.from(parent.getContext()).inflate(R.layout.set_item, parent, false);
        } else {
            View = convirdView;
        }
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent qsetionsintent = new Intent(parent.getContext(), QuestionsActivity.class);
                parent.getContext().startActivity(qsetionsintent);
            }
        });
        ((TextView)View.findViewById(R.id.textView)).setText(String.valueOf(i+1));

        return View;
    }
}
