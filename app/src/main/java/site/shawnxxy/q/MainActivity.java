package site.shawnxxy.q;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import site.shawnxxy.q.data.TestUtil;
import site.shawnxxy.q.data.WaitlistContract;
import site.shawnxxy.q.data.WaitlistDbHelper;

public class MainActivity extends AppCompatActivity {

	private GuestListAdapter mAdapter;

	private SQLiteDatabase mDb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		RecyclerView waitlistRecyclerView;
		// set local attributes to corresponding views
		waitlistRecyclerView = findViewById(R.id.guests_list);
		// set layout for the recyclerview, because it is a list we are using the linear layout
		waitlistRecyclerView.setLayoutManager(new LinearLayoutManager(this));

		WaitlistDbHelper dbHelper = new WaitlistDbHelper(this);
		mDb = dbHelper.getWritableDatabase();
		TestUtil.insertFakeData(mDb);
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
