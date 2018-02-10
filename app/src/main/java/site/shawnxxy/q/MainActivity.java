package site.shawnxxy.q;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import site.shawnxxy.q.data.TestUtil;
import site.shawnxxy.q.data.WaitlistContract;
import site.shawnxxy.q.data.WaitlistDbHelper;

public class MainActivity extends AppCompatActivity {

	private GuestListAdapter mAdapter;

	private SQLiteDatabase mDb;

	private EditText mNewGuestNameEditText;
	private EditText mNewPartySizeEditText;

	private final static String LOG_TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		RecyclerView waitlistRecyclerView;
		// set local attributes to corresponding views
		waitlistRecyclerView = findViewById(R.id.guests_list);
		mNewGuestNameEditText = findViewById(R.id.person_name_editext);
		mNewPartySizeEditText = findViewById(R.id.party_size_editext);

		// set layout for the recyclerview, because it is a list we are using the linear layout
		waitlistRecyclerView.setLayoutManager(new LinearLayoutManager(this));

		WaitlistDbHelper dbHelper = new WaitlistDbHelper(this);
		mDb = dbHelper.getWritableDatabase();
//		TestUtil.insertFakeData(mDb);
		Cursor cursor = getAllGuests();

		// create an adapter for that cursor to display the data
		mAdapter = new GuestListAdapter(this, cursor);
		// Link the adapter tot he RecyclerView
		waitlistRecyclerView.setAdapter(mAdapter);

	}

	/**
	 *  This method is called when user clicks on the Add to waitlist button
	 */
	public void addToList(View view) {
		if (mNewGuestNameEditText.getText().length() == 0 || mNewPartySizeEditText.getText().length() == 0) {
			return;
		}
		int partySize = 1;
		try {
			partySize = Integer.parseInt(mNewPartySizeEditText.getText().toString());
		} catch (NumberFormatException ex) {
			Log.e(LOG_TAG, "Failed to parse party size text to number: " + ex.getMessage());
		}

		// Add guest info to mDb
		addNewGuest(mNewGuestNameEditText.getText().toString(), partySize);

		// Update the cursor in the adapter to trigger UI to display the new list
		mAdapter.swapCursor(getAllGuests());

		// Clear UI text fields
		mNewPartySizeEditText.clearFocus();
		mNewGuestNameEditText.getText().clear();
		mNewPartySizeEditText.getText().clear();
	}

	private long addNewGuest(String name, int partySize) {
		ContentValues cv = new ContentValues();
		cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, name);
		cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, partySize);
		return mDb.insert(WaitlistContract.WaitlistEntry.TABLE_NAME, null, cv);
	}

	/**
	 *  Query the mDb and get all guests from the waitlist table
	 */
	private Cursor getAllGuests() {
		return mDb.query(
				WaitlistContract.WaitlistEntry.TABLE_NAME,
				null,
				null,
				null,
				null,
				null,
				WaitlistContract.WaitlistEntry.COLUMN_TIMESTAMP
		);
	}
}
