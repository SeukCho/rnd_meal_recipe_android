package com.example.rndmealrecipe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity extends AppCompatActivity {
    private TextView mealNameTextView, categoryTextView, areaTextView, instructionsTextView, ingredientsTextView;
    private ImageView mealImageView;
    private String apiURL = "https://www.themealdb.com/api/json/v1/1/random.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mealNameTextView = findViewById(R.id.mealNameTextView);
        categoryTextView = findViewById(R.id.categoryTextView);
        areaTextView = findViewById(R.id.areaTextView);
        instructionsTextView = findViewById(R.id.instructionsTextView);
        ingredientsTextView = findViewById(R.id.ingredientsTextView);
        mealImageView = findViewById(R.id.mealImageView);

        new FetchMealTask().execute(apiURL);
    }
    private class FetchMealTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(params[0])
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            MealApi mealApi = retrofit.create(MealApi.class);
            Call<MealResponse> call = mealApi.getRandomMeal();

            call.enqueue(new Callback<MealResponse>() {
                @Override
                public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                    if (response.isSuccessful()) {
                        Meal meal = response.body().getMeals().get(0);
                        displayMeal(meal);
                    }
                }

                @Override
                public void onFailure(Call<MealResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });

            return null;
        }

        private void displayMeal(Meal meal) {
            mealNameTextView.setText(meal.getStrMeal());
            categoryTextView.setText("Category: " + meal.getStrCategory());
            areaTextView.setText("Area: " + meal.getStrArea());
            instructionsTextView.setText("Instructions: " + meal.getStrInstructions());

            StringBuilder ingredients = new StringBuilder();
            for (int i = 1; i <= 20; i++) {
                String ingredient = meal.getIngredient(i);
                String measure = meal.getMeasure(i);
                if (ingredient != null && !ingredient.isEmpty()) {
                    ingredients.append(ingredient).append(" - ").append(measure).append("\n");
                }
            }
            ingredientsTextView.setText("Ingredients:\n" + ingredients.toString());

            Picasso.get().load(meal.getStrMealThumb()).into(mealImageView);
        }
    }
}
