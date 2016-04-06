package com.formation.helloworldtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class UserDetailActivity extends Activity {

	private CheckBox isActive;
	private User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_detail);

		user = (User) getIntent().getExtras().get("user");

		ImageView logo = (ImageView) findViewById(R.id.user_logo);
		logo.setImageResource(user.getPhoto());

		TextView fullname = (TextView) findViewById(R.id.user_fullname);
		fullname.setText(user.getFirstName() + " " + user.getLastName());

		isActive = (CheckBox) findViewById(R.id.checkBox_isActive);
		isActive.setChecked(user.isActive());
		if (user.isActive() == Boolean.TRUE) {
			isActive.setText("Actif");
		} else {
			isActive.setText("Inactif");
		}

		addListenerOnIsActive();

		Button backButton = (Button) findViewById(R.id.button_back);
		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent usersActivity = new Intent(UserDetailActivity.this, UsersActivity.class);
				usersActivity.putExtra("result", user);
				setResult(Activity.RESULT_OK, usersActivity);
			}
		});
	}

	private void addListenerOnIsActive() {
		isActive.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (((CheckBox) v).isChecked()) {
					user.setActive(Boolean.TRUE);
					isActive.setText("Actif");
				} else {
					user.setActive(Boolean.FALSE);
					isActive.setText("Inactif");
				}
			}
		});
	}

	protected void onPause() {
		super.onPause();
		setContentView(R.layout.activity_user_detail);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_detail, menu);
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
