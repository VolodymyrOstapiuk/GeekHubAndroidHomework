package ua.ck.ostapiuk.geekhubhomework.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.app.FragmentManager;

import java.lang.reflect.Array;

import ua.ck.ostapiuk.geekhubhomework.fragment.DocFragment;
import ua.ck.ostapiuk.geekhubhomework.fragment.DocTitlesFragment;
import ua.ck.ostapiuk.geekhubhomework.R;


public class MainActivity extends FragmentActivity implements DocTitlesFragment.OnDocTitleSelectedListener {

    private boolean dualPane;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private FragmentManager manager;
    private CharSequence mTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = getTitle();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.drawer);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listview_nav_drawer_template, R.id.textViewNavDrawer, getResources().getStringArray(R.array.Arrays));
        drawerList.setAdapter(adapter);
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
            /**
             * Called when a drawer has settled in a completely closed state.
             */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            /**
             * Called when a drawer has settled in a completely open state.
             */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(mDrawerToggle);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_animation_activity) {
            Intent intent = new Intent(this, AnimationsExamplesActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDocTitleSelected(int position, int category) {
        if (!getResources().getBoolean(R.bool.isTablet)) {
            Intent intent = new Intent(this, DocumentationViewerActivity.class);
            intent.putExtra(DocFragment.DOC_ID, position);
            intent.putExtra(DocFragment.DOC_ARRAY_ID, category);
            startActivity(intent);

        } else {
        Bundle args = new Bundle();
        args.putInt(DocFragment.DOC_ID, position);
            args.putInt(DocFragment.DOC_ARRAY_ID, category);
            DocFragment docFragment = (DocFragment) getSupportFragmentManager().findFragmentById(R.id.doc_fragment);
            docFragment.getDocsFromID(category);
            docFragment.updateDoc(position);
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            selectItem(i);
        }
    }

    private void selectItem(int position) {
        switch (position) {
            case 0:
                replaceDocTitlesFragmentWithArrayId(0);
                break;
            case 1:
                replaceDocTitlesFragmentWithArrayId(1);
                break;
            case 2:
                replaceDocTitlesFragmentWithArrayId(2);
                break;
        }
    }

    private void replaceDocTitlesFragmentWithArrayId(int id) {
        DocTitlesFragment docTitlesFragment = (DocTitlesFragment) getSupportFragmentManager().findFragmentById(R.id.doc_titles_fragment);
        docTitlesFragment.updateTitleList(id);
        drawerLayout.closeDrawer(Gravity.LEFT);

    }
}
