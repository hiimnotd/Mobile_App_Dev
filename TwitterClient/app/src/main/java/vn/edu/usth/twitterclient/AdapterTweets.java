package vn.edu.usth.twitterclient;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.List;

public class AdapterTweets extends RecyclerView.Adapter<AdapterTweets.MyHolder>{
    Context context;
    List<ModelTweets> tweetsList;

    ImageView replyIcon, likeIcon;
    TextView replyText, likeText;

    String likedCheck;
    ImageView img;
    TextView likeStatus;

    public AdapterTweets(Context context, List<ModelTweets> tweetsList) {
        this.context = context;
        this.tweetsList = tweetsList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.tweet, viewGroup, false);
        img = view.findViewById(R.id.TweetLikeIcon);
        likeStatus = view.findViewById(R.id.LikeStatus);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int i) {
        String username = tweetsList.get(i).getUserName();
        String name = tweetsList.get(i).getuName();
        String content = tweetsList.get(i).getpDescription();
        String time = tweetsList.get(i).getpTime();
        likedCheck = tweetsList.get(i).getLike_status();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(time));

        String mYear = String.valueOf(calendar.get(Calendar.YEAR));
        String mMonth = String.valueOf(calendar.get(Calendar.MONTH)+1);
        String mDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

        holder.nameView.setText(name);
        holder.usernameView.setText(username);
        holder.contentView.setText(content);
        holder.timeView.setText(mYear+"/"+mMonth+"/"+mDay);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                React();
            }
        });
    }

    private void React() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Reaction");

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(50,50,50,50);


        replyIcon = new ImageView(context);
        replyIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.reply));
        replyIcon.setId(R.id.ReplyIcon);

        replyText = new TextView(context);
        replyText.setText("Reply");
        replyText.setTextColor(Color.BLACK);
        replyText.setTextSize(20);
        replyText.setPadding(50,0,0,0);
        replyText.setId(R.id.reply);

        LinearLayout replyView = new LinearLayout(context);
        replyView.setOrientation(LinearLayout.HORIZONTAL);
        replyView.setPadding(0,30,0,0);
        replyView.addView(replyIcon);
        replyView.addView(replyText);

        likeIcon = new ImageView(context);
        likeIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.like));

        likeText = new TextView(context);
        likeText.setText("Like");
        likeText.setTextColor(Color.BLACK);
        likeText.setTextSize(20);
        likeText.setPadding(50,0,0,0);
        likeText.setId(R.id.like);

        LinearLayout likeView = new LinearLayout(context);
        likeView.setOrientation(LinearLayout.HORIZONTAL);
        likeView.addView(likeIcon);
        likeView.addView(likeText);

        likeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                like();
            }
        });

        if (likedCheck == "1"){
            img.setImageDrawable(context.getResources().getDrawable(R.drawable.like_red));
            likeIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.like_red));
            likeText.setText("Unlike");
            likeStatus.setText("Liked");
        }

        linearLayout.addView(likeView);
        linearLayout.addView(replyView);

        builder.setView(linearLayout);

        builder.create().show();
    }

    private void like() {
        if (likedCheck == "0"){
            likedCheck = "1";
            img.setImageDrawable(context.getResources().getDrawable(R.drawable.like_red));
            likeIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.like_red));
            likeText.setText("Unlike");
            likeStatus.setText("Liked");
        }else{
            likedCheck = "0";
            img.setImageDrawable(context.getResources().getDrawable(R.drawable.like));
            likeIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.like));
            likeText.setText("Like");
            likeStatus.setText("Like");
        }
    }


    @Override
    public int getItemCount() {
        return tweetsList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView nameView, usernameView, contentView, timeView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            nameView = itemView.findViewById(R.id.FullName);
            usernameView = itemView.findViewById(R.id.username);
            contentView = itemView.findViewById(R.id.content);
            timeView = itemView.findViewById(R.id.TimeTweet);
        }
    }
}
