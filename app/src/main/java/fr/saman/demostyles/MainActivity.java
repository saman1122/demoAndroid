package fr.saman.demostyles;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import fr.saman.demostyles.dagger.DaggerTypeWritterComponent;
import fr.saman.demostyles.dagger.TypeWritter;
import fr.saman.demostyles.dagger.TypeWritterModule;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.titleMain) TextView titleMain;
    @BindView(R.id.image) ImageView image;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.subTitle) TextView subTitle;
    @BindString(R.string.sub_title) String subTitleSTR;
    @BindString(R.string.title) String titleSTR;

    //Pokemon add
    @BindView(R.id.pokemonName) TextView pokemonName;
    @BindView(R.id.pokemonHeight) TextView pokemonHeight;
    @BindView(R.id.pokemonWeight) TextView pokemonWeight;
    @BindView(R.id.pokemonImage) ImageView pokemonImage;

    //Dagger
    @Inject
    TypeWritter typeWritter;

    private Context ctx;

    //Font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = this;

        //Bind
        ButterKnife.bind(this);
        title.setText(titleSTR);
        subTitle.setText(subTitleSTR);

        //Picasso
        String url = "http://www.bmw.fr/dam/brandBM/common/newvehicles/i-series/i8/2014/introduction/visions-01.jpg.resource.1427211292658.jpg";
        Picasso.with(this)
                //.load(R.drawable.photo2)
                //.resize(50,50)
                //.centerCrop()
                .load(url)
                .into(image);


//        //Property Animation
//        Animator anim = AnimatorInflater.loadAnimator(this, R.animator.anim);
//
//        anim.setTarget(titleMain);
//        anim.start();

//        //Animator Java
//        ObjectAnimator anim = ObjectAnimator.ofFloat(titleMain, View.TRANSLATION_X, 0,-100);
//        anim.setDuration(1000);
//        anim.start();

        //ViewPropertyAnimator
        titleMain.animate()
                .translationX(100)
                .translationY(100)
                .alpha(1)
                .setDuration(2000)
                .setInterpolator(new AccelerateDecelerateInterpolator());

        testRetrofit();

        //Dagger
        DaggerTypeWritterComponent
                .builder()
                .typeWritterModule(new TypeWritterModule(this))
                .build()
                .inject(this);

        typeWritter.type();

    }


    private void testRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://pokeapi.co/api/v2/")
                .build();

        PokemonService service = retrofit.create(PokemonService.class);

        Call<Pokemon> call = service.getPokemonById(2);

        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()) {
                    Pokemon pokemon = response.body();
                    Sprites sprites = pokemon.getSprites();
                    if (pokemon != null) {

                        pokemonName.setText(pokemon.getName());
                        pokemonHeight.setText(String.valueOf(pokemon.getHeight()));
                        pokemonWeight.setText(String.valueOf(pokemon.getWeight()));

                        //if (sprites != null && ! sprites.getFrontDefault().equals("null"))
                            Picasso.with(ctx)
                                    .load(sprites.getFrontDefault())
                                    .into(pokemonImage);
                    }
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.i(" ","Failure to get pokemon: " + t.getMessage());
            }
        });
    }

}