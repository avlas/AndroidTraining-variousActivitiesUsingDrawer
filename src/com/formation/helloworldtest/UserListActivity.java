package com.formation.helloworldtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class UserListActivity extends Activity {

	private CheckBox isActive;
	private User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_list);

		user = (User) getIntent().getExtras().get("user");

		ImageView logo = (ImageView) findViewById(R.id.user_logo);
		logo.setImageResource(user.getPhoto());

		TextView fullname = (TextView) findViewById(R.id.user_fullname);
		fullname.setText(user.getFirstName() + " " + user.getLastName());
	}

	protected void onPause() {
		super.onPause();
		setContentView(R.layout.activity_user_list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.users, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
