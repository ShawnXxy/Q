package site.shawnxxy.q;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import site.shawnxxy.q.data.WaitlistContract;

/**
 * Created by shawn on 2/8/2018.
 */

public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.GuestViewHolder> {

	private Context context;

	private Cursor cursor;

//	private int count;

	/**
	 *  Constructor using the context and the db cursor
	 * @param context
	 */
	public GuestListAdapter(Context context, Cursor cursor) {
		this.context = context;
//		this.count = count;
		this.cursor = cursor;
	}

	@Override
	public GuestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// Get the RecyclerView item layout
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.guest_list_item, parent, false);
		return new GuestViewHolder(view);
	}

	@Override
	public void onBindViewHolder(GuestViewHolder holder, int position) {
		// Move the cursor to the passed in position, return if moveToPosition returns false
		// Move the cursor to the position of the item to be displayed
		if (!cursor.moveToPosition(position)) {
			return;
		}
		// Call getString on the cursor to get the guest's name
		String name = cursor.getString(cursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME));
		// Call getInt on the cursor to get the party size
		int partySize = cursor.getInt(cursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE));
		// Display the guest name
		holder.nameTextView.setText(name);
		holder.partySizeTextView.setText(String.valueOf(partySize));
	}

	@Override
	public int getItemCount() {
		return cursor.getCount();
	}

	public void swapCursor(Cursor allGuests) {
		if (cursor != null) {
			cursor.close();
		}
		cursor = allGuests;
		if (allGuests != null) {
			this.notifyDataSetChanged();
		}
	}

	/**
	 *  Inner class to hold the views needed to display a single item in the recyclerview
	 */
	class GuestViewHolder extends RecyclerView.ViewHolder {
		// Will display the guest name
		TextView nameTextView;
		// Will display the party size number
		TextView partySizeTextView;

		public GuestViewHolder(View itemView) {
			super(itemView);
			nameTextView = itemView.findViewById(R.id.guest_name_textview);
			partySizeTextView = itemView.findViewById(R.id.party_size_textview);
		}
	}
}
