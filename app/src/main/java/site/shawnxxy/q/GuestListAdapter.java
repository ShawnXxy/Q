package site.shawnxxy.q;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by shawn on 2/8/2018.
 */

public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.GuestViewHolder> {

	private Context context;

	private int count;

	/**
	 *  Constructor using the context and the db cursor
	 * @param context
	 */
	public GuestListAdapter(Context context, int count) {
		this.context = context;
		this.count = count;
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

	}

	@Override
	public int getItemCount() {
		return count;
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
