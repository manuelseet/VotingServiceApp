package iss.workshop.votingapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyVotingService extends Service {
    public MyVotingService() {
    }

    protected Map<String, Integer> votingMap;
    protected ArrayList<Superhero> superheros;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    public int onStartCommand(Intent intent, int flag, int startId) {
        String actionToDo = intent.getAction();

        if (actionToDo != null) {
            if (actionToDo.equalsIgnoreCase("vote")) {

                addToTally(intent.getStringExtra("votedSuperhero"));
            }
            else if(actionToDo.equalsIgnoreCase("init_superheros")) {
                superheros = (ArrayList<Superhero>) intent.getSerializableExtra("superheros");
                votingMap = new HashMap<>();

                for (Superhero s : superheros) {
                    votingMap.put(s.getName(), 0); //initialise as Zero;
                }
            }
        }
        return super.onStartCommand(intent, flag, startId);
    }

    public void addToTally(String name) {
        int currentVote = votingMap.get(name);
        currentVote++;
        votingMap.put(name, currentVote);
        String msg = name + " now has " + currentVote + " votes";
        Toast.makeText(this, msg, Toast.LENGTH_LONG ).show();
    }



}