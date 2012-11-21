package com.hackaton.cinepotes;

import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hackaton.cinepotes.model.Cinema;
import com.hackaton.cinepotes.model.Cinemas;

public class CinemaAdapter extends BaseAdapter {

	private List<Cinema> cinemas = Collections.emptyList();
	private final Context context;

	public CinemaAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return cinemas.size();
	}

	@Override
	public Cinema getItem(int position) {
		return cinemas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		TextView textView;
		if (convertView == null) {
			textView = (TextView) LayoutInflater.from(context).inflate(R.layout.list_item_cine, parent, false);
		} else {
			textView = (TextView) convertView;
		}

		Cinema cinema = getItem(position);

		textView.setText(cinema.name);

		return textView;
	}

	public void updateCinemas(Cinemas cinemas) {
		this.cinemas = cinemas;
		notifyDataSetChanged();
	}
}
