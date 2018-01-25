package sn.didafavor.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.List;

import sn.didafavor.Api;
import sn.didafavor.R;
import sn.didafavor.data.Movie;

/**movie adapter
 * Created by pc on 2018/1/16.
 */

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MoviteContentVH> {

    private List<Movie> datas;
    private Context context;

    public MovieRecyclerAdapter(List<Movie> datas) {
        this.datas = datas;
    }

    public MovieRecyclerAdapter(Context context, List<Movie> datas) {
        this.context = context;
        this.datas = datas;
    }


    @Override
    public MoviteContentVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new MoviteContentVH(view);
    }

    @Override
    public void onBindViewHolder(MoviteContentVH holder, int position) {
        if (holder != null && position >=0) {
            holder.movieTitle.setText(datas.get(position).getTitle());
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .priority(Priority.HIGH);

            Glide.with(context)
                    .asBitmap()
                    .load(Api.getPosterPath(datas.get(position).getPosterPath()))
                    .apply(options)
                    .into(new BitmapImageViewTarget(holder.moviePoster) {
                        @Override
                        public void onResourceReady(Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                            super.onResourceReady(bitmap, transition);
                            Palette.from(bitmap).generate(palette -> setBackgroundColor(palette, holder));
                        }
                    });
        }

    }

    private void setBackgroundColor(Palette palette, MoviteContentVH holder) {
        Palette.Swatch mostPolulation = null;
        if (palette != null){
            for (Palette.Swatch swatch : palette.getSwatches()){
                if (mostPolulation == null || swatch.getPopulation() > mostPolulation.getPopulation()){
                    mostPolulation = swatch;
                }
            }
        }
        if (mostPolulation != null){
                int endColor = mostPolulation.getTitleTextColor();

               holder.movieTitle.setBackgroundColor(palette.getVibrantColor(context.getResources().getColor(R.color.black_translucent)));
               holder.movieTitle.getBackground().setAlpha(200);
        } else {
            holder.movieTitle.setBackgroundColor(context.getResources().getColor(R.color.theme_color_primary));

        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class MoviteContentVH extends RecyclerView.ViewHolder{

        private ImageView moviePoster;
        private TextView  movieTitle;
        public MoviteContentVH(View itemView) {
            super(itemView);
            moviePoster = itemView.findViewById(R.id.iv_movie_poster);
            movieTitle = itemView.findViewById(R.id.tv_movie_title);
        }
    }
}
