package com.medvid.andrii.diplomawork.landing;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.medvid.andrii.diplomawork.R;
import com.medvid.andrii.diplomawork.data.forecast.Disease;
import com.medvid.andrii.diplomawork.data.forecast.ForecastTableContract;
import com.medvid.andrii.diplomawork.data.forecast.GroupRisk;
import com.medvid.andrii.diplomawork.data.forecast.source.ForecastDataSourceContract;
import com.medvid.andrii.diplomawork.data.forecast.source.ForecastLocalDataSource;
import com.medvid.andrii.diplomawork.data.suggestion.Suggestion;
import com.medvid.andrii.diplomawork.data.suggestion.SuggestionTableContract;
import com.medvid.andrii.diplomawork.data.suggestion.source.SuggestionDataSourceContract;
import com.medvid.andrii.diplomawork.data.suggestion.source.SuggestionLocalDataSource;
import com.medvid.andrii.diplomawork.data.training_sample.TrainingSample;
import com.medvid.andrii.diplomawork.data.training_sample.TrainingSampleTableContract;
import com.medvid.andrii.diplomawork.data.training_sample.entities.Calories;
import com.medvid.andrii.diplomawork.data.training_sample.entities.Pressure;
import com.medvid.andrii.diplomawork.data.training_sample.entities.Sleep;
import com.medvid.andrii.diplomawork.data.user.User;
import com.medvid.andrii.diplomawork.data.user.UserTableContract;
import com.medvid.andrii.diplomawork.data.user.source.UserDataSourceContract;
import com.medvid.andrii.diplomawork.data.user.source.UsersLocalDataSource;
import com.medvid.andrii.diplomawork.login.LoginActivity;
import com.medvid.andrii.diplomawork.network.Forecast;
import com.medvid.andrii.diplomawork.network.ForecastService;
import com.medvid.andrii.diplomawork.network.ForecastsResponseObject;
import com.medvid.andrii.diplomawork.registration.RegistrationActivity;
import com.medvid.andrii.diplomawork.util.RandomUtils;
import com.medvid.andrii.diplomawork.util.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Use the {@link LandingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class LandingFragment extends Fragment implements LandingContract.View, View.OnClickListener {

    private LandingContract.Presenter mPresenter;

    private TextView mLoginTextView;
    private TextView mRegistrationTextView;

    public static LandingFragment newInstance() {
        return new LandingFragment();
    }

    public LandingFragment() {
        // Required empty public constructor
    }

    /**
     * {@link Fragment} Lifecycle methods
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_landing, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi(view);

//        usersTest();
//        suggestionsTest();
        apiForecastCallTest();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /***
     * Listeners
     */

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginTextView:
                showLogin();
                break;
            case R.id.registrationTextView:
                showRegistration();
                break;
        }
    }

    /**
     * {@link LandingContract.View} methods
     */
    @Override
    public void setPresenter(LandingContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);;
    }


    @Override
    public void showLogin() {
        getActivity().startActivity(LoginActivity.getIntent(getActivity()));
    }

    @Override
    public void showRegistration() {
        getActivity().startActivity(RegistrationActivity.getIntent(getActivity()));
    }

    @Override
    public boolean isActive() {
        return isActive();
    }

    /**
     * Private Methods
     */

    private void initUi(@NonNull View rootView) {
        mLoginTextView = (TextView) rootView.findViewById(R.id.loginTextView);
        mRegistrationTextView = (TextView) rootView.findViewById(R.id.registrationTextView);

        mLoginTextView.setOnClickListener(this);
        mRegistrationTextView.setOnClickListener(this);
    }

    private void usersTest()    {
        Log.d("123", UserTableContract.CREATE_TABLE);
        Log.d("123", TrainingSampleTableContract.CREATE_TABLE);
        Log.d("123", ForecastTableContract.CREATE_TABLE);
        Log.d("123", SuggestionTableContract.CREATE_TABLE);
//
        User user = new User(1 ,"email@email.email", "Andrii", "Medvid",
                23, User.Gender.MAN, 1.85, 62,
                Utils.calculateBodyMassIndex(65, 1.85));

        UsersLocalDataSource usersLocalDataSource = UsersLocalDataSource.getInstance(getActivity().getContentResolver());
        usersLocalDataSource.saveUser(user);

        usersLocalDataSource.getUser("1", new UserDataSourceContract.GetUserCallback() {
            @Override
            public void onUserLoaded(@NonNull User user) {
                Log.d("123", user.toString());
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

        usersLocalDataSource.getUsers(new UserDataSourceContract.GetUsersCallback() {

            @Override
            public void onUsersLoaded(@NonNull List<User> users) {
                Log.d("123", users.toString());
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

        usersLocalDataSource.deleteUser("1");

        usersLocalDataSource.saveUser(user);
        User user2 = new User(1 ,"email2@email.email", "Andrii2", "Medvid2",
                23, User.Gender.MAN, 1.85, 62,
                Utils.calculateBodyMassIndex(65, 1.85));
        usersLocalDataSource.saveUser(user2);

        usersLocalDataSource.getUsers(new UserDataSourceContract.GetUsersCallback() {

            @Override
            public void onUsersLoaded(@NonNull List<User> users) {
                Log.d("123", users.toString());
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

        User user3 = new User(1 ,"email2222@email.email", "Andrii2222", "Medvid2222",
                23, User.Gender.MAN, 1.85, 62,
                Utils.calculateBodyMassIndex(65, 1.85));
        usersLocalDataSource.updateUser(user3);

        usersLocalDataSource.getUsers(new UserDataSourceContract.GetUsersCallback() {

            @Override
            public void onUsersLoaded(@NonNull List<User> users) {
                Log.d("123", users.toString());
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    private void suggestionsTest()  {
        Disease disease = new Disease(2, "Infarct");
        GroupRisk groupRisk = new GroupRisk(2, "Middle");

        com.medvid.andrii.diplomawork.data.forecast.Forecast forecast = new com.medvid.andrii.diplomawork.data.forecast.Forecast(0, disease, groupRisk, null);
        ForecastLocalDataSource forecastLocalDataSource
                = ForecastLocalDataSource.getInstance(getActivity().getContentResolver());

        long id = forecastLocalDataSource.saveForecastSample(forecast);

        forecastLocalDataSource.getForecastSample(Long.toString(id), new ForecastDataSourceContract.GetForecastSampleCallback() {
            @Override
            public void onForecastSampleLoaded(@NonNull com.medvid.andrii.diplomawork.data.forecast.Forecast forecast) {
                Log.d("123", "From DB: " + forecast);

                long forecastId = forecast.getId();

                SuggestionLocalDataSource suggestionLocalDataSource
                        = SuggestionLocalDataSource.getInstance(getActivity().getContentResolver());

                suggestionLocalDataSource.saveSuggestions(getSuggestionsList(forecastId));

                suggestionLocalDataSource.getSuggestions(new SuggestionDataSourceContract.GetSuggestionsCallback() {
                    @Override
                    public void onSuggestionsLoaded(@NonNull List<Suggestion> suggestions) {
                        Log.d("123", "From DB: " + suggestions);
                    }

                    @Override
                    public void onDataNotAvailable() {

                    }
                });

            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    private List<Suggestion> getSuggestionsList(long forecastId)   {
        List<Suggestion> suggestionList = new ArrayList<>();

        int suggestionId = 0;
        suggestionList.add(new Suggestion(suggestionId++, "Try to eat less", "EatenCalories", forecastId));
        suggestionList.add(new Suggestion(suggestionId++, "Try to move more", "SpentCalories", forecastId));
        suggestionList.add(new Suggestion(suggestionId++, "Try to walk more", "Distance", forecastId));
        suggestionList.add(new Suggestion(suggestionId++, "Try to eat food with less fat amount", "FatAmount", forecastId));
        suggestionList.add(new Suggestion(suggestionId++, "Try to go to gym", "Weight", forecastId));
        suggestionList.add(new Suggestion(suggestionId++, "Try to normalize your food multiplicity", "FoodMultiplicity", forecastId));

        return suggestionList;
    }

    private void apiForecastCallTest() {

        RandomUtils randomUtils = new RandomUtils();
        int age = randomUtils.nextInt(18, 30);

        double growth = randomUtils.nextDouble(1.0, 2.0);
        double weight = randomUtils.nextDouble(1.0, 2.0);
        double bodyMassIndex = Utils.calculateBodyMassIndex(weight, growth);
        double distance = randomUtils.nextDouble(1.0, 4.0);
        Sleep mSleep = new Sleep(
                randomUtils.nextDouble(6.0, 10.0),// SleepHoursCount
                randomUtils.nextDouble(0.0, 10.0)// SleepQuality
        );

        Calories mCalories = new Calories(
                randomUtils.nextDouble(2000.0, 5000.0),   // SpentCalories
                randomUtils.nextDouble(2000.0, 5000.0)    // EatenCalories
        );
        double foodMultiplicity = randomUtils.nextDouble(3.0, 4.0);
        double fatAmount = randomUtils.nextDouble(-10.0, 10.0);
        double carbohydrateAmount = weight * 4 + randomUtils.nextDouble(-15.0, 15.0);
        double proteinAmount = weight + randomUtils.nextDouble(-10.0, 10.0);
        double vitaminC = randomUtils.nextDouble(55.0, 65.0);
        double sugarLevel = randomUtils.nextDouble(3.0, 5.0);
        double stressLevel = randomUtils.nextDouble(3.0, 5.0);
        double temperature = 36.6;
        Pressure mPressure = new Pressure(
                randomUtils.nextDouble(115.0, 130.0),
                randomUtils.nextDouble(75.0, 85.0)
        );

        double pulse = randomUtils.nextDouble(60.0, 80.0);
        Date timeStamp = new Date();

        TrainingSample trainingSample =
                TrainingSample.getStatisticsTrainingSample(
                        0,
                        age,
                        User.Gender.MAN,
                        growth,
                        weight,
                        bodyMassIndex,
                        distance,
                        mSleep,
                        mCalories,
                        foodMultiplicity,
                        fatAmount,
                        carbohydrateAmount,
                        proteinAmount,
                        vitaminC,
                        sugarLevel,
                        stressLevel,
                        temperature,
                        mPressure,
                        pulse,
                        timeStamp

                );

        Callback<ForecastsResponseObject> callback =
                new Callback<ForecastsResponseObject>() {
                    @Override
                    public void onResponse(Call<ForecastsResponseObject> call,
                                           Response<ForecastsResponseObject> response) {

                        if (response.isSuccessful()) {
                            ForecastsResponseObject object = response.body();
                            List<Forecast> forecastList = object.forecastList;
                            forecastList.isEmpty();
                        } else {
                            // TODO error
                        }
                    }

                    @Override
                    public void onFailure(Call<ForecastsResponseObject> call, Throwable t) {
                        // TODO error
                    }
                };

        ForecastService.getForecast(trainingSample, callback);
    }
}