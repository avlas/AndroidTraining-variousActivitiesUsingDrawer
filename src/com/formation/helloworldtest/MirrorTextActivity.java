package com.formation.helloworldtest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MirrorTextActivity extends Activity {

	TextView redirectedView;
	TextView reversedView;
	EditText toReverseView;
	SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		preferences = this.getSharedPreferences ("SavedMirrorInputs", Context.MODE_WORLD_WRITEABLE);
		
//		if (savedInstanceState != null) {
//			super.onRestoreInstanceState(savedInstanceState);
//		} else {
			super.onCreate(savedInstanceState);
//		}					
		setContentView(R.layout.activity_mirror_text);
		
		redirectedView = (TextView) findViewById(R.id.label_redirected);
		redirectedView.setText(R.string.text_changed);
		
		toReverseView = (EditText) findViewById(R.id.textarea_toReverse);
		reversedView = (TextView) findViewById(R.id.label_reversed);

		toReverseView.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				StringBuffer reversedSb = new StringBuffer(s);
				reversedView.setText(reversedSb.reverse().toString());
			}

			@Override
			public void afterTextChanged(Editable s) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
		});

		toReverseView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				toReverseView.setText("");
				reversedView.setText("");
			}
		});

		Button backButton = (Button) findViewById(R.id.button_back);
		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent mainActivity = new Intent(MirrorTextActivity.this, MainActivity.class);
				startActivity(mainActivity);
			}
		});
	}

	protected void onPause() {
		super.onPause();
		setContentView(R.layout.activity_mirror_text);
		
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString("toReverse", toReverseView.getText().toString());
		editor.putString("reversed", reversedView.getText().toString());
		editor.commit();
		
//		Bundle savedInstanceState = new Bundle();
//		savedInstanceState.putString("toReverse", toReverseView.getText().toString());
//		savedInstanceState.putString("reversed", reversedView.getText().toString());
//		super.onSaveInstanceState(savedInstanceState);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		String toReverse = preferences.getString("toReverse", "");
		toReverseView.setText(toReverse);
		
		String reversed = preferences.getString("reversed", "");
		reversedView.setText(reversed);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mirror_text, menu);
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
