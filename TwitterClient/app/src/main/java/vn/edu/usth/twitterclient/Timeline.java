package vn.edu.usth.twitterclient;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Timeline extends Fragment {
    LinearLayout Reaction;
    Boolean likedCheck = false;

    ImageView replyIcon, likeIcon;
    TextView replyText, likeText;

    public static Timeline newInstance() {
        Timeline fragment = new Timeline();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);

        ImageView img = view.findViewById(R.id.tweet2_img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FullsizeImage.class));
            }
        });

        Reaction = view.findViewById(R.id.tweet1);
        Reaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                React();
            }
        });
        return view;
    }

    private void React() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Reaction");

        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(50,50,50,50);


        replyIcon = new ImageView(getActivity());
        replyIcon.setImageDrawable(getResources().getDrawable(R.drawable.reply));
        replyIcon.setId(R.id.ReplyIcon);

        replyText = new TextView(getActivity());
        replyText.setText("Reply");
        replyText.setTextColor(Color.BLACK);
        replyText.setTextSize(20);
        replyText.setPadding(50,0,0,0);
        replyText.setId(R.id.reply);

        LinearLayout replyView = new LinearLayout(getActivity());
        replyView.setOrientation(LinearLayout.HORIZONTAL);
        replyView.setPadding(0,30,0,0);
        replyView.addView(replyIcon);
        replyView.addView(replyText);

        likeIcon = new ImageView(getActivity());
        likeIcon.setImageDrawable(getResources().getDrawable(R.drawable.like));
        
        likeText = new TextView(getActivity());
        likeText.setText("Like");
        likeText.setTextColor(Color.BLACK);
        likeText.setTextSize(20);
        likeText.setPadding(50,0,0,0);
        likeText.setId(R.id.like);

        LinearLayout likeView = new LinearLayout(getActivity());
        likeView.setOrientation(LinearLayout.HORIZONTAL);
        likeView.addView(likeIcon);
        likeView.addView(likeText);

        likeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                like();
            }
        });

        if (likedCheck == true){
            likeIcon.setImageDrawable(getResources().getDrawable(R.drawable.like_red));
            likeText.setText("Unlike");
        }

        linearLayout.addView(likeView);
        linearLayout.addView(replyView);

        builder.setView(linearLayout);

        builder.create().show();
    }

    private void like() {
        if (likedCheck == false){
            likedCheck = true;
            likeIcon.setImageDrawable(getResources().getDrawable(R.drawable.like_red));
            likeText.setText("Unlike");
        }else{
            likedCheck = false;
            likeIcon.setImageDrawable(getResources().getDrawable(R.drawable.like));
            likeText.setText("Like");
        }
    }
}
