package sn.didafavor.listing;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**define the listing module scope
 * Created by pc on 2018/1/22.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ListingScope {
}
