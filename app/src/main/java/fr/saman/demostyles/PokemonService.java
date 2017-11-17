package fr.saman.demostyles;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Saman on 15/11/2017.
 */

public interface PokemonService {
    @GET("pokemon/{id}/")
    Call<Pokemon> getPokemonById(@Path("id") int id);
}
