package com.formation.helloworldtest;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListArrayAdapter extends ArrayAdapter<User> {
	private final List<User> values;

	public ListArrayAdapter(Context context, int rowLine, List<User> values) {
		super(context, rowLine, values);
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View rowView = inflater.inflate(R.layout.activity_user, parent, false);

		ImageView logoView = (ImageView) rowView.findViewById(R.id.user_logo);
		logoView.setImageResource(R.drawable.nature);

		TextView fullnameView = (TextView) rowView.findViewById(R.id.user_fullname);
		fullnameView.setText(values.get(position).getFirstName() + " " + values.get(position).getLastName());

		return rowView;
	}
}
