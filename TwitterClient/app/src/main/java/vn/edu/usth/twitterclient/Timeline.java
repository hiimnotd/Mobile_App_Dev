package vn.edu.usth.twitterclient;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Timeline extends Fragment {
    LinearLayout Reaction;
    Boolean likedCheck = false;

    RecyclerView recyclerView;
    AdapterTweets adapterTweets;
    List<ModelTweets> modelTweetsList;

    public static Timeline newInstance() {
        Timeline fragment = new Timeline();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);

        recyclerView = view.findViewById(R.id.tweetRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        modelTweetsList = new ArrayList<>();

        getAllTweets();

//        Reaction = view.findViewById(R.id.tweet1);
//        Reaction.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                React();
//            }
//        });
        return view;
    }

    private void getAllTweets() {
        final FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Posts");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                modelTweetsList.clear();
                for (DataSnapshot ds: snapshot.getChildren()){
                    ModelTweets modelTweets = ds.getValue(ModelTweets.class);

//                    if (modelTweets.getUid().equals(fUser.getUid())){
//                        modelTweetsList.add(modelTweets);
//                    }
                    modelTweetsList.add(modelTweets);

                    adapterTweets = new AdapterTweets(getActivity(), modelTweetsList);

                    recyclerView.setAdapter(adapterTweets);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
