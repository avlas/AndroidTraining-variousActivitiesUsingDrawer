package com.formation.helloworldtest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class UsersActivity extends Activity {

	static final int PICK_USER = 1;
	private ListView listUsers;
	private User selectedUser;
	private static int selectedPosition = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_users);

		List<User> users = buildUsers();

		listUsers = (ListView) findViewById(R.id.users);
		// ArrayAdapter adapter = new ArrayAdapter<User>(listUsers.getContext(),
		// R.layout.activity_user_listitem, R.id.user, users);
		// listUsers.setAdapter(adapter);

		ListArrayAdapter adapter = new ListArrayAdapter(listUsers.getContext(), R.layout.activity_user_list, users);
		listUsers.setAdapter(adapter);

		addListenerOnUsers();
	}

	private void addListenerOnUsers() {
		listUsers.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				selectedPosition = position;
				selectedUser = (User) parent.getItemAtPosition(position);

				Intent userActivity = new Intent(UsersActivity.this, UserDetailActivity.class);
				userActivity.putExtra("user", selectedUser);
				startActivityForResult(userActivity, PICK_USER);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Check which request we're responding to
		if (requestCode == PICK_USER) {
			if (requestCode == RESULT_OK) {
				User resultUser = (User) data.getExtras().get("result");
				if (resultUser != null) {
					LayoutInflater inflater = LayoutInflater.from(listUsers.getContext());
					View userView = inflater.inflate(R.layout.activity_user_list, listUsers, false);

					if (resultUser.isActive()) {
						// listUsers.getItemAtPosition(selectedPosition);
						userView.setBackgroundColor(R.color.green);
					} else {
						userView.setBackgroundColor(R.color.red);
					}
				}
			}
		}
	}

	protected void onPause() {
		super.onPause();
		setContentView(R.layout.activity_users);
	}

	private List<User> buildUsers() {
		List<User> users = new ArrayList<User>();
		users.add(new User("Tata", "Tete", R.drawable.nature, true));
		users.add(new User("Tete", "Titi", R.drawable.nature, false));
		users.add(new User("Titi", "Toto", R.drawable.nature, true));
		users.add(new User("Toto", "Tutu", R.drawable.nature, true));
		users.add(new User("Tutu", "Tata", R.drawable.nature, false));
		return users;
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
