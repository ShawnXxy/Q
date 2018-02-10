package site.shawnxxy.q.data;

import android.provider.BaseColumns;

/**
 * Created by shawn on 2/10/2018.
 */

public class WaitlistContract {

	public static final class WaitlistEntry implements BaseColumns {
		public static final String TABLE_NAME = "waitlist";
		public static final String COLUMN_GUEST_NAME = "guestName";
		public static final String COLUMN_PARTY_SIZE = "partySize";
		public static final String COLUMN_TIMESTAMP = "timestamp";
	}
}
