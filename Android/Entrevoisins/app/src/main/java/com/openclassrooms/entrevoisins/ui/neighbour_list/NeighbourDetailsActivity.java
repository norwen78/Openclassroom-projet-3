package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
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
    @BindView(R.id.neighbourPhoto)
    ImageView mImageAvatar;
    @BindView(R.id.neighbourNameover)
    TextView mNamePhoto;
    @BindView(R.id.neighbourName)
    TextView mNameCard1;
    @BindView(R.id.neighbourContact)
    TextView mContact;
    @BindView(R.id.fav)
    FloatingActionButton favorite;
    private Neighbour mNeighbour;
    private Neighbour neighbour;
    private NeighbourApiService mApiService;
    private ImageButton buttonBack;
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
        initClick();
        checkFavorite();
    }

    private void initView() {
        mNamePhoto.setText(neighbour.getName());
        mNameCard1.setText(neighbour.getName());
        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .into(mImageAvatar);
        mContact.setText("www.facebook.fr/" + neighbour.getName());
    }

    private void initClick() {
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        favorite.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!neighbour.getFavorite()) {
                    favorite.setImageResource(R.drawable.ic_baseline_star_full_24);
                    mApiService.addFavorite(neighbour);
                } else {
                    favorite.setImageResource(R.drawable.ic_baseline_star_border_24);
                    mApiService.removeFavorite(neighbour);
                }
            }
        }));
    }

    private void checkFavorite() {
        if (!neighbour.getFavorite()) {
            favorite.setImageResource(R.drawable.ic_baseline_star_border_24);
        } else {
            favorite.setImageResource(R.drawable.ic_baseline_star_full_24);
        }
    }

}
