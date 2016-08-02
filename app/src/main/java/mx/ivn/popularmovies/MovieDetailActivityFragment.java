package mx.ivn.popularmovies;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailActivityFragment extends Fragment {

    public MovieDetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        Movie movie = (Movie) bundle.getSerializable("movieData");

        ImageView bannerImageView = (ImageView) rootView.findViewById(R.id.detail_banner);
        Picasso.with(getContext()).load(movie.getBannerUrl()).placeholder(R.drawable.placeholder_banner)
                .fit().into(bannerImageView);

        ImageView posterImageView = (ImageView) rootView.findViewById(R.id.detail_poster);
        Picasso.with(getContext()).load(movie.getPosterUrl()).placeholder(R.drawable.placeholder_poster)
                .fit().into(posterImageView);

        TextView titleTextView = (TextView) rootView.findViewById(R.id.detail_title);
        titleTextView.setText(movie.getTitle());

        TextView originalTitleTextView = (TextView) rootView.findViewById(R.id.detail_original_title);
        originalTitleTextView.setText(movie.getOriginalTitle());

        TextView releaseDateTextView = (TextView) rootView.findViewById(R.id.detail_release_date);
        releaseDateTextView.setText(movie.getReleaseDate());

        TextView ratingTextView = (TextView) rootView.findViewById(R.id.detail_rating);
        ratingTextView.setText(String.valueOf(movie.getUserRating()).concat("★"));

        TextView synopsisTextView = (TextView) rootView.findViewById(R.id.detail_synopsis);
        synopsisTextView.setText(movie.getSynopsis());


        return rootView;
    }
}
