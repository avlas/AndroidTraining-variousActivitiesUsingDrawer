package com.formation.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class UsersActivity extends Activity {

	static final int PICK_USER = 1;
	private ListView usersView;
	private User selectedUser;
	private static int selectedPosition = -1;
	private UserArrayAdapter adapter;
	List<User> users;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_users);

		users = buildUsers();

		usersView = (ListView) findViewById(R.id.list_users);

		adapter = new UserArrayAdapter(usersView.getContext(), R.layout.activity_user_row, users);
		usersView.setAdapter(adapter);

		// ArrayAdapter adapter = new ArrayAdapter<User>(listUsers.getContext(),
		// R.layout.activity_user_list, R.id.user, users);

		addListenerOnList();
	}

	private List<User> buildUsers() {
		List<User> users = new ArrayList<User>();
		users.add(new User("Tata", "Tete", R.drawable.nature));
		users.add(new User("Tete", "Titi", R.drawable.nature));
		users.add(new User("Titi", "Toto", R.drawable.nature));
		users.add(new User("Toto", "Tutu", R.drawable.nature));
		users.add(new User("Tutu", "Tata", R.drawable.nature));
		return users;
	}

	private void addListenerOnList() {
		usersView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				selectedPosition = position;
				selectedUser = (User) parent.getItemAtPosition(position);

				Intent userActivity = new Intent(UsersActivity.this, UserDetailActivity.class);
				userActivity.putExtra("requestUser", selectedUser);
				startActivityForResult(userActivity, PICK_USER);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == PICK_USER) {
			if (resultCode == RESULT_OK) {
				User responseUser = (User) data.getExtras().get("responseUser");
				if (responseUser != null) {
					users.set(selectedPosition, responseUser);
					Log.e("SELECTED_USER", users.get(selectedPosition).toString());

					/*
					 * adapter.updateData(users); Log.e("RESPONSE_USER",
					 * responseUser.toString());
					 */

					View selectedUserView = usersView.getChildAt(selectedPosition);
					boolean isActive = UserDetailActivity.preferences.getBoolean("isActive", responseUser.isActive());
					if (isActive) {
						selectedUserView.setBackgroundColor(R.color.green);
					} else {
						selectedUserView.setBackgroundColor(R.color.red);
					}
					
					adapter = new UserArrayAdapter(usersView.getContext(), R.layout.activity_user_row, users);
					usersView.setAdapter(adapter);

					refreshList();
				}
			}
		}
	}

	private void refreshList() {
		adapter.notifyDataSetChanged();
/*		usersView.invalidate();
		usersView.invalidateViews();*/
	}

	protected void onPause() {
		super.onPause();
		setContentView(R.layout.activity_users);
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
