package fr.saman.demostyles.dagger;

import dagger.Component;
import fr.saman.demostyles.MainActivity;

/**
 * Created by Saman on 15/11/2017.
 */

@Component(modules = TypeWritterModule.class)
public interface TypeWritterComponent {

    void inject(MainActivity activity);
}
