package net.osmand.plus.download;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TabHost;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import net.osmand.plus.OsmandApplication;
import net.osmand.plus.OsmandSettings;
import net.osmand.plus.R;
import net.osmand.plus.activities.DownloadIndexFragment;
import net.osmand.plus.activities.FavouritesActivity;

/**
 * Created by Denis on 08.09.2014.
 */
public class DownloadActivity extends SherlockFragmentActivity {

	private TabHost tabHost;
	private FavouritesActivity.TabsAdapter mTabsAdapter;
	public static DownloadIndexesThread downloadListIndexThread;
	private DownloadActivityType type = DownloadActivityType.NORMAL_FILE;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		((OsmandApplication) getApplication()).applyTheme(this);
		super.onCreate(savedInstanceState);


		setContentView(R.layout.tab_content);
		OsmandSettings settings = ((OsmandApplication) getApplication()).getSettings();
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();
		ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
		mTabsAdapter = new FavouritesActivity.TabsAdapter(this, tabHost, viewPager, settings);
		mTabsAdapter.addTab(tabHost.newTabSpec("LOCAL_INDEX").setIndicator("Local maps"),
				LocalIndexesFragment.class, null);
		mTabsAdapter.addTab(tabHost.newTabSpec("DOWNLOADS").setIndicator("Downloads"),
				DownloadIndexFragment.class, null);
		tabHost.setCurrentTab(0);
	}

	public DownloadActivityType getType() { return type;}

	public void setType(DownloadActivityType type) { this.type = type;}

	public void changeType(final DownloadActivityType tp) {
		invalidateOptionsMenu();
		if (downloadListIndexThread != null && type != tp) {
			type = tp;
			downloadListIndexThread.runCategorization(tp);
		}
	}
}
