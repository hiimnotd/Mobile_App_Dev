package vn.edu.usth.twitterclient;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Profile extends Fragment {
    Button editProfile;

    ImageView avatarView;
    TextView nameView, locationView, descriptionView, userLinkView;

    FirebaseAuth Auth;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference reference;

    public static Profile newInstance() {
        Profile fragment = new Profile();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        editProfile = view.findViewById(R.id.edit_profile);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditProfile.class));
            }
        });

        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users");

        avatarView = view.findViewById(R.id.AvatarImage);
        nameView = view.findViewById(R.id.full_name);
        descriptionView = view.findViewById(R.id.description);
        locationView = view.findViewById(R.id.location);
        userLinkView = view.findViewById(R.id.url);

        if (user != null) {
            Query query = reference.orderByChild("email").equalTo(user.getEmail());
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot ds : snapshot.getChildren()){
                        String name = "" + ds.child("name").getValue();
                        String description = "" + ds.child("description").getValue();
                        String location = "" + ds.child("location").getValue();
                        String userLink = "" + ds.child("url").getValue();
                        String avatar = "" + ds.child("image").getValue();

                        nameView.setText(name);
                        descriptionView.setText(description);
                        locationView.setText(location);
                        userLinkView.setText(userLink);
                        try{
                             Picasso.get().load(avatar).into(avatarView);
                        }
                        catch (Exception e){
                            Picasso.get().load(R.drawable.sample_avatar).into(avatarView);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        return view;
    }

    private void checkUserStatus(){
        FirebaseUser user = Auth.getCurrentUser();

        if (user == null){
            startActivity(new Intent(getActivity(), HomePage.class));
        }

    }
}
