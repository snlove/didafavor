package sn.didafavor.launch;

import dagger.Subcomponent;

/**
 * Created by pc on 2018/4/20.
 */

@Subcomponent(modules = {LaunchModule.class})
public interface LaunchComponent {

    LaunchFragment inject(LaunchFragment launchFragment);
}
