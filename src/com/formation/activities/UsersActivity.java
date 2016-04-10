package com.formation.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class UsersActivity extends Activity implements UsersFragment.Callbacks {

	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_users);

		if (findViewById(R.id.userDetailFragment) != null) {
			mTwoPane = true;

			// In two-pane mode, list items should be given the 'activated'
			// state when touched.
			UsersFragment usersFragment = (UsersFragment) getFragmentManager().findFragmentById(R.id.usersFragment);
			usersFragment.setActivateOnItemClick(true);			
		}
	}

	@Override
	public void onItemClick(String id) {
		if (!mTwoPane) {
			Intent detailIntent = new Intent(UsersActivity.this, UserDetailActivity.class);
			detailIntent.putExtra(UserDetailFragment.ARG_USER_ID, id);
			startActivity(detailIntent);
		} else {
			Bundle arguments = new Bundle();
			arguments.putString(UserDetailFragment.ARG_USER_ID, id);

			UserDetailFragment detailFragment = new UserDetailFragment();
			detailFragment.setArguments(arguments);

			getFragmentManager().beginTransaction().replace(R.id.userDetailFragment, detailFragment).commit();
		}		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.users, menu);
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
