package iss.workshop.votingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    protected ArrayList<Superhero> superheros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        superheros = new ArrayList<>();
        superheros.add(new Superhero("1", "Superman", "Flying guy with muscles"));
        superheros.add(new Superhero("2", "Spongebob", "Underwater nuisance"));
        superheros.add(new Superhero("3", "Catbert", "Dilbert but better"));
        superheros.add(new Superhero("4", "Dogecoin", "Saving crypto by day"));

        myListAdapter listViewAdapter = new myListAdapter(this, superheros);

        ListView myListView = findViewById(R.id.myListView);
        if (myListView != null) {
            myListView.setAdapter(listViewAdapter);
            myListView.setOnItemClickListener(this);
        }

        Intent serviceIntent = new Intent(MainActivity.this, MyVotingService.class);
        serviceIntent.setAction("init_superheros");
        serviceIntent.putExtra("superheros", superheros);
        startService(serviceIntent);

        Button seeResultsBtn = findViewById(R.id.seeResultBtn);
        seeResultsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "see results", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
        Intent serviceIntent = new Intent(MainActivity.this, MyVotingService.class);
        serviceIntent.setAction("vote");
        serviceIntent.putExtra("votedSuperhero", superheros.get(pos).getName());
        startService(serviceIntent);

    }
}