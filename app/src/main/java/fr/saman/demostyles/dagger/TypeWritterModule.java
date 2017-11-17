package fr.saman.demostyles.dagger;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Saman on 15/11/2017.
 */

@Module
public class TypeWritterModule {

    private Context ctx;

    public TypeWritterModule(Context ctx) {
        this.ctx = ctx;
    }

    @Provides
    TypeWritter provideTypeWritter(Paper paper, Ink ink) {
        return new TypeWritterImpl(ctx,paper,ink);
    }
}
