package fr.saman.demostyles.dagger;


import android.content.Context;
import android.widget.Toast;

/**
 * Created by Saman on 15/11/2017.
 */

public class TypeWritterImpl implements TypeWritter {

    private Context context;
    private Paper paper;
    private Ink ink;

    TypeWritterImpl(Context ctx, Paper paper, Ink ink) {
        this.context = ctx;
        this.paper = paper;
        this.ink = ink;
    }

    @Override
    public void type() {
        Toast.makeText(context,paper.paper() + ink.ink(),Toast.LENGTH_LONG).show();
    }
}
