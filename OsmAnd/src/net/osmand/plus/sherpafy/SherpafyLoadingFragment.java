package net.osmand.plus.sherpafy;

import net.osmand.plus.OsmandApplication;
import net.osmand.plus.R;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class SherpafyLoadingFragment extends SherlockFragment {
	OsmandApplication app;
	private View view;

	public SherpafyLoadingFragment(OsmandApplication app) {
		this.app = app;
	}

	public View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.sherpafy_loading, container, false);

		app.checkApplicationIsBeingInitialized(getActivity(), (TextView) view.findViewById(R.id.ProgressMessage),
				(ProgressBar) view.findViewById(R.id.ProgressBar), new Runnable() {
					@Override
					public void run() {
						((TextView) view.findViewById(R.id.ProgressMessage)).setVisibility(View.GONE);
						view.findViewById(R.id.ProgressBar).setVisibility(View.GONE);
						((TourViewActivity)getSherlockActivity()).loadingFinished();
					}
				});
		return view;
	}

}