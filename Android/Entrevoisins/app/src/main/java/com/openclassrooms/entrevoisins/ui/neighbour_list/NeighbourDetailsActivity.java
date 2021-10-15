package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_NEIGHBOUR_ID = "EXTRA_NEIGHBOUR_INDEX";
    private Neighbour neighbour;
    private NeighbourApiService mApiService;
    private ImageButton buttonBack;

    @BindView(R.id.neighbourPhoto)
    ImageView mImageAvatar;
    @BindView(R.id.neighbourNameover)
    TextView mNamePhoto;
    @BindView(R.id.neighbourName)
    TextView mNameCard1;
    @BindView(R.id.neighbourContact)
    TextView mContact;
    @BindView(R.id.fav)
    FloatingActionButton fav;
    private long neighbourId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_neighbour);
        neighbourId = getIntent().getLongExtra(EXTRA_NEIGHBOUR_ID, 0);
        mApiService = DI.getNeighbourApiService();
        buttonBack = findViewById(R.id.buttonBack);
        ButterKnife.bind(this);
        neighbour = mApiService.getNeighboursById(neighbourId);
        initView();
        initListener();
        buttonBack.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              finish();
          }
        });
    }
    private void initListener() {
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mApiService.changeStatus(neighbour.getId());
                neighbour = mApiService.getNeighboursById(neighbourId);
                initView();
            }
        });
    }
    private void initView() {
        mNamePhoto.setText(neighbour.getName());
        mNameCard1.setText(neighbour.getName());
        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .into(mImageAvatar);
        mContact.setText("www.facebook.fr/" + neighbour.getName());
        initStarView();
    }

    private void initStarView() {
        if (neighbour.isFavorite()){
            fav.setImageResource(R.drawable.ic_baseline_star_full_24);
        } else {
            fav.setImageResource(R.drawable.ic_baseline_star_border_24);
        }
    }

}
