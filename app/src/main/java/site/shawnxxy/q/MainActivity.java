package site.shawnxxy.q;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	private GuestListAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		RecyclerView waitlistRecyclerView;
		// set local attributes to corresponding views
		waitlistRecyclerView = findViewById(R.id.guests_list);
		// set layout for the recyclerview, because it is a list we are using the linear layout
		waitlistRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		// create an adapter for that cursor to display the data
		mAdapter = new GuestListAdapter(this);
		// Link the adapter tot he RecyclerView
		waitlistRecyclerView.setAdapter(mAdapter);

	}

	/**
	 *  This method is called when user clicks on the Add to waitlist button
	 */
	public void addToList(View view) {

	}
}
