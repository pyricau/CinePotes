package com.hackaton.cinepotes;

import static android.widget.Toast.LENGTH_SHORT;
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
import com.googlecode.androidannotations.annotations.res.DimensionPixelSizeRes;
import com.googlecode.androidannotations.annotations.rest.RestService;
import com.hackaton.cinepotes.model.Cinema;
import com.hackaton.cinepotes.model.Cinemas;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;

@EActivity(R.layout.activity_cine_potes)
public class CinePotesActivity extends SlidingActivity {

	@RestService
	CineRestClient restClient;

	@ViewById
	ListView cineList;

	@DimensionPixelSizeRes
	int behindOffset;

	private CinemaAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
	}

	@AfterViews
	void initViews() {
		prepareSlidingMenu();

		adapter = new CinemaAdapter(this);
		cineList.setAdapter(adapter);
		setProgressBarIndeterminateVisibility(true);
		downloadCinemaList();
	}

	private void prepareSlidingMenu() {
		// See https://github.com/jfeinstein10/SlidingMenu
		setBehindContentView(R.layout.behind_cine_potes);
		SlidingMenu slidingMenu = getSlidingMenu();
		slidingMenu.setBehindOffset(behindOffset);
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
