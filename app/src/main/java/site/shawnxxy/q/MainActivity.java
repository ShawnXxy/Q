package site.shawnxxy.q;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

	private GuestListAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		RecyclerView waitlistRecyclerView;
		waitlistRecyclerView = findViewById(R.id.guests_list);
		waitlistRecyclerView.setLayoutManager(new LinearLayoutManager(this));

	}
}
