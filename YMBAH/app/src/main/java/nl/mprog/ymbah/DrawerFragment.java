package nl.mprog.ymbah;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Jan Geestman 10375406.
 */
public class DrawerFragment extends Fragment{
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ListView mDrawerList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nav_drawer, container,
                false);
//        addDrawerItems();

    }

//    private void addDrawerItems() {
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mDrawerList = (ListView) findViewById(R.id.navList);
//        String[] activityArray = {"House", "Dig Sand", "Chop Wood", "Saw Planks"};
//        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activityArray);
//        mDrawerList.setAdapter(mAdapter);
//
//    }
}
