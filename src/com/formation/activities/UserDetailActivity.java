package com.formation.activities;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class UserDetailActivity extends Activity {

	public static SharedPreferences preferences;
	private User selectedUser;
	private CheckBox isActive;

	// Storage permissions
	private static final int REQUEST_EXTERNAL_STORAGE = 1;
	private static String[] PERMISSIONS_STORAGE = { Manifest.permission.READ_EXTERNAL_STORAGE,
			Manifest.permission.WRITE_EXTERNAL_STORAGE };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_detail);

		preferences = this.getSharedPreferences("saveUserDetail", Context.MODE_WORLD_WRITEABLE);

		selectedUser = (User) getIntent().getExtras().get("requestUser");

		initView();

		Button backButton = (Button) findViewById(R.id.button_back);
		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences.Editor editor = preferences.edit();
				editor.putBoolean("isActive", selectedUser.isActive());
				editor.commit();

				Intent usersActivity = new Intent(UserDetailActivity.this, UsersActivity.class);
				usersActivity.putExtra("responseUser", selectedUser);
				setResult(Activity.RESULT_OK, usersActivity);
				finish();
			}
		});
	}

	private void initView() {
		ImageView logo = (ImageView) findViewById(R.id.detail_logo);
		logo.setImageResource(selectedUser.getPhoto());

		TextView fullname = (TextView) findViewById(R.id.detail_fullname);
		fullname.setText(selectedUser.getFirstName() + " " + selectedUser.getLastName());

		isActive = (CheckBox) findViewById(R.id.detail_isActive);
		isActive.setChecked(selectedUser.isActive());
		if (selectedUser.isActive() == Boolean.TRUE) {
			isActive.setText("Actif");
		} else {
			isActive.setText("Inactif");
		}
		addListenerOnIsActive();

		readExternLogoAfterStoragePermissionsVerification();
	}

	private void addListenerOnIsActive() {
		isActive.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (((CheckBox) v).isChecked()) {
					selectedUser.setActive(Boolean.TRUE);
					isActive.setText("Actif");
				} else {
					selectedUser.setActive(Boolean.FALSE);
					isActive.setText("Inactif");
				}
				readExternLogoAfterStoragePermissionsVerification();
			}
		});
	}

	public void readExternLogoAfterStoragePermissionsVerification() {
		if (ContextCompat.checkSelfPermission(UserDetailActivity.this,
				Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

			if (ActivityCompat.shouldShowRequestPermissionRationale(UserDetailActivity.this,
					Manifest.permission.READ_EXTERNAL_STORAGE)) {

			} else {
				ActivityCompat.requestPermissions(UserDetailActivity.this,
						new String[] { Manifest.permission.READ_EXTERNAL_STORAGE }, REQUEST_EXTERNAL_STORAGE);
			}
		} else {
			if (selectedUser.isActive() == Boolean.TRUE) {
				readExternLogo("active");
			} else {
				readExternLogo("inactive");
			}
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
		switch (requestCode) {
		case REQUEST_EXTERNAL_STORAGE: {
			if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				if (selectedUser.isActive() == Boolean.TRUE) {
					readExternLogo("active");
				} else {
					readExternLogo("inactive");
				}
			} else {
			}
			return;
		}
		}
	}

	public void readExternLogo(String photoName) {
		String dirName = "Download";
		String photo = photoName + ".jpg";

		File photoFile = new File(File.separator + "sdcard" + File.separator + dirName + File.separator + photo);
		InputStream iStream;
		try {
			iStream = new BufferedInputStream(new FileInputStream(photoFile));
			Bitmap bm = BitmapFactory.decodeStream(iStream);
			iStream.close();

			ImageView sdcardLogo = (ImageView) findViewById(R.id.detail_extern_logo);
			sdcardLogo.setImageBitmap(bm);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
