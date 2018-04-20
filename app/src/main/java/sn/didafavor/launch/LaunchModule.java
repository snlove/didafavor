package sn.didafavor.launch;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pc on 2018/4/20.
 */
@Module
public class LaunchModule {


    @Provides
    public LaunchPresenter privodePresenter() {
        return null;
    }

    @Provides
    public LaunchInteactor privodeInteactor(){
        return  null;
    }
}
