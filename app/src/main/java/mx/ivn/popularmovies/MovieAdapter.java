package mx.ivn.popularmovies;

import android.content.Context;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ivancho on 7/31/16.
 *
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final String LOG_TAG = this.getClass().getSimpleName();

    private List<Movie> movieList;
    private Context context;

    public MovieAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        Picasso.with(context).load(movie.getPosterUrl()).into(MovieViewHolder.vPoster);
        MovieViewHolder.vTitle.setText(movie.getTitle());
        MovieViewHolder.vRating.setText(String.valueOf(movie.getUserRating()).concat("★"));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        protected static ImageView vPoster;
        protected static TextView vTitle;
        protected static TextView vRating;

        public MovieViewHolder(View itemView) {
            super(itemView);
            vPoster = (ImageView) itemView.findViewById(R.id.poster);
            vTitle = (TextView) itemView.findViewById(R.id.title);
            vRating = (TextView) itemView.findViewById(R.id.rating);
        }
    }
}
