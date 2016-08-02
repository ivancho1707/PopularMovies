package mx.ivn.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        Movie movie = movieList.get(position);
        Picasso.with(context).load(movie.getPosterUrl()).placeholder(R.drawable.placeholder_poster)
                .into(MovieViewHolder.vPoster);
        MovieViewHolder.vTitle.setText(movie.getTitle());
        MovieViewHolder.vRating.setText(String.valueOf(movie.getUserRating()).concat("★"));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie movie = movieList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("movieData", movie);
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
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
