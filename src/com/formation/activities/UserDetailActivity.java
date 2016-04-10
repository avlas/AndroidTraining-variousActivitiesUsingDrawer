package com.formation.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class UserDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_detail);

		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		if (savedInstanceState == null) {
			// Create the detail fragment and add it to the activity using a fragment transaction	
			
			String userId = getIntent().getStringExtra(UserDetailFragment.ARG_USER_ID);			
			Bundle arguments = new Bundle();
			arguments.putString(UserDetailFragment.ARG_USER_ID, userId);
			
			UserDetailFragment detailFragment = new UserDetailFragment();
			detailFragment.setArguments(arguments);
			
			getFragmentManager().beginTransaction().replace(R.id.userDetailFragment, detailFragment).commit();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.user_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
