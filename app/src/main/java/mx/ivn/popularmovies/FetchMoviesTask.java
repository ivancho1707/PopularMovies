package mx.ivn.popularmovies;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ivancho on 7/31/16.
 *
 */
public class FetchMoviesTask extends AsyncTask<String, Void, Movie[]> {

    private final String LOG_TAG = this.getClass().getSimpleName();
    private Context context;

    FetchMoviesTask(Context context){
        this.context = context;
    }

    @Override
    protected Movie[] doInBackground(String... params) {
        if (params.length == 0) {
            return null;
        }
        String type = params[0];

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String movieResultJsonStr = null;
        try {
            final String TMDB_BASE_URL = "http://api.themoviedb.org/3/movie";
            final String API_KEY = "api_key";

            Uri builtUri = Uri.parse(TMDB_BASE_URL).buildUpon()
                    .appendPath(type)
                    .appendQueryParameter(API_KEY, BuildConfig.THE_MOVIE_DATABASE_API_KEY)
                    .build();

            URL url = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            movieResultJsonStr = buffer.toString();
        } catch (IOException e){
            Log.e(LOG_TAG, "Error", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        try {
            return getMovieDataFromJson(movieResultJsonStr);
        } catch (Exception e /*JSONException e*/) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Movie[] movies) {
        Toast toast = Toast.makeText(context, String.valueOf(movies.length), Toast.LENGTH_SHORT);
        toast.show();
    }

    private Movie[] getMovieDataFromJson(String movieResultJsonStr) throws JSONException {
        final String MOVIE_RESULTS = "results";
        final String POSTER_URL = "poster_path";
        final String SYNOPSIS = "overview";
        final String RELEASE_DATE = "release_date";
        final String ORIGINAL_TITLE = "original_title";
        final String TITLE = "title";
        final String USER_RATING = "vote_average";

        JSONObject forecastJson = new JSONObject(movieResultJsonStr);
        JSONArray movieJsonArray = forecastJson.getJSONArray(MOVIE_RESULTS);

        Movie[] movieArray = new Movie[movieJsonArray.length()];

        for (int i = 0; i < movieJsonArray.length(); i++) {
            JSONObject movieJson = movieJsonArray.getJSONObject(i);
            movieArray[i] = new Movie(
                    movieJson.getString(POSTER_URL),
                    movieJson.getString(SYNOPSIS),
                    movieJson.getString(RELEASE_DATE),
                    movieJson.getString(ORIGINAL_TITLE),
                    movieJson.getString(TITLE),
                    movieJson.getDouble(USER_RATING)
            );
        }

        return movieArray;
    }
}
