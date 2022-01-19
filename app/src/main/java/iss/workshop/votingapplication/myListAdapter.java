package iss.workshop.votingapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class myListAdapter extends ArrayAdapter<Object> {

    private final Context context;
    protected ArrayList<Superhero> superheroes;

    public myListAdapter(Context context, ArrayList<Superhero> superheroes) {
        super(context, R.layout.row); //apply all to the row xml

        this.context = context;
        this.superheroes = superheroes;

        addAll(new Object[superheroes.size()]);
    }


    //get the View cell (i.e. our row)  and set the content
    public View getView(int pos, View view, @NonNull ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE); //row.xml is to be inflated
            view = inflater.inflate(R.layout.row, parent, false);
        }
        // set the image for ImageView
        TextView idTextView = view.findViewById(R.id.superheroIdText);

        idTextView.setText(superheroes.get(pos).getId());

        TextView superheroNameTextView = view.findViewById(R.id.superheroNameText);
        superheroNameTextView.setText(superheroes.get(pos).getName());

        return view;
    }
}

