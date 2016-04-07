package com.formation.helloworldtest;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UserArrayAdapter extends ArrayAdapter<User> {
	
	private List<User> values;

	public UserArrayAdapter(Context context, int rowLine, List<User> values) {
		super(context, rowLine, values);
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View rowView = inflater.inflate(R.layout.activity_user_row, parent, false);

		User user = values.get(position);
		
		ImageView logoView = (ImageView) rowView.findViewById(R.id.row_logo);
		logoView.setImageResource(user.getPhoto());

		TextView fullnameView = (TextView) rowView.findViewById(R.id.row_fullname);
		fullnameView.setText(user.getFirstName() + " " + user.getLastName());

		if (user.isActive()) {			
			rowView.setBackgroundColor(R.color.green);
		} else {
			rowView.setBackgroundColor(R.color.red);
		}
		return rowView;
	}

/*	public void updateData(List<User> list) {
		super.clear();
		super.addAll(list.toArray(new User[0]));
		
		this.values.clear();
		this.values.addAll(list);
	}*/
}
