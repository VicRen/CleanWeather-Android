package ren.vic.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import ren.vic.presentation.autolocation.AutoLocationActivity;
import ren.vic.presentation.entercity.EnterCityActivity;

public class Navigator {

    @Inject
    Navigator() {
    }

    public void navigateToEnterCity(Context context) {
        if (context != null) {
            context.startActivity(new Intent(context, EnterCityActivity.class));
        }
    }

    public void navigateToAutoLocation(Context context) {
        if (context != null) {
            context.startActivity(new Intent(context, AutoLocationActivity.class));
        }
    }
}
