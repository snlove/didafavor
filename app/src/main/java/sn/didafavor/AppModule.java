package sn.didafavor;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pc on 2018/1/22.
 */
@Module
public class AppModule {

    private Context context;

    public AppModule(Application application) {
        this.context = application;
    }

    @Singleton
    @Provides
    Context provideContext(){
        return context;
    }

    @Singleton
    @Provides
    Resources provideResources(Context context){
        return  context.getResources();
    }
}
