package com.hackaton.cinepotes;

import static android.widget.Toast.LENGTH_SHORT;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.rest.RestService;
import com.hackaton.cinepotes.model.Cinema;
import com.hackaton.cinepotes.model.Cinemas;

@EActivity(R.layout.activity_cine_potes)
public class CinePotesActivity extends Activity {

	@RestService
	CineRestClient restClient;

	@ViewById
	ListView cineList;

	private CinemaAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
	}

	@AfterViews
	void initViews() {
		adapter = new CinemaAdapter(this);
		cineList.setAdapter(adapter);
		setProgressBarIndeterminateVisibility(true);
		downloadCinemaList();
	}

	@Background
	void downloadCinemaList() {
		Cinemas cinemas = restClient.getCinemas();
		updateCinemasList(cinemas);
	}

	@UiThread
	void updateCinemasList(Cinemas cinemas) {
		setProgressBarIndeterminateVisibility(false);
		adapter.updateCinemas(cinemas);
	}

	@ItemClick
	void cineListItemClicked(Cinema cinema) {
		Toast.makeText(this, "Clicked on " + cinema.name, LENGTH_SHORT).show();
	}

}
