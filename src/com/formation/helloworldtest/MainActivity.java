package com.formation.helloworldtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button mirrorTextBtn = (Button) findViewById(R.id.button_mirrorText);
		mirrorTextBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent mirrorTextActivity = new Intent(MainActivity.this, MirrorTextActivity.class);
				startActivity(mirrorTextActivity);
			}
		});

		Button formBtn = (Button) findViewById(R.id.button_form);
		formBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent formActivity = new Intent(MainActivity.this, FormActivity.class);
				startActivity(formActivity);
			}
		});

		Button usersBtn = (Button) findViewById(R.id.button_users);
		usersBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent usersActivity = new Intent(MainActivity.this, UsersActivity.class);
				startActivity(usersActivity);
			}
		});

		Button contactsBtn = (Button) findViewById(R.id.button_contacts);
		contactsBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent contactsActivity = new Intent(MainActivity.this, ContactsActivity.class);
				startActivity(contactsActivity);
			}
		});
	}

	protected void onPause() {
		super.onPause();
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
