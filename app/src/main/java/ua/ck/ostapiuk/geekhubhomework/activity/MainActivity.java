package ua.ck.ostapiuk.geekhubhomework.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;

import ua.ck.ostapiuk.geekhubhomework.fragment.DocFragment;
import ua.ck.ostapiuk.geekhubhomework.fragment.DocTitlesFragment;
import ua.ck.ostapiuk.geekhubhomework.R;


public class MainActivity extends Activity implements DocTitlesFragment.OnDocTitleSelectedListener {
    private FragmentManager manager;
    private boolean dualPane;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.drawer);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listview_nav_drawer_template, R.id.textViewNavDrawer, getResources().getStringArray(R.array.Arrays));
        drawerList.setAdapter(adapter);
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
        DocTitlesFragment docTitlesFragment = new DocTitlesFragment();
        Bundle args = new Bundle();
        args.putInt(DocTitlesFragment.TITTLES_ID, 0);
        docTitlesFragment.setArguments(args);
        manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, docTitlesFragment);
        if (findViewById(R.id.doc_container) != null) {
            DocFragment docFragment = new DocFragment();
            transaction.add(R.id.doc_container, docFragment);
        }
        transaction.commit();
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDocTitleSelected(int position, int category) {
        if (findViewById(R.id.doc_container) != null) {
            FragmentTransaction transaction = manager.beginTransaction();
            Bundle args = new Bundle();
            args.putInt(DocFragment.DOC_ID, position);
            args.putInt(DocFragment.DOC_ARRAY_ID, category);
            DocFragment docFragment = new DocFragment();
            docFragment.setArguments(args);
            transaction.replace(R.id.doc_container, docFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else {
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle args = new Bundle();
        args.putInt(DocFragment.DOC_ID, position);
            args.putInt(DocFragment.DOC_ARRAY_ID, category);
        DocFragment docFragment = new DocFragment();
        docFragment.setArguments(args);
        transaction.replace(R.id.container, docFragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
        DocTitlesFragment docTitlesFragment = new DocTitlesFragment();
        Bundle args = new Bundle();
        args.putInt(DocTitlesFragment.TITTLES_ID, id);
        docTitlesFragment.setArguments(args);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, docTitlesFragment);
        transaction.commit();
        drawerLayout.closeDrawer(Gravity.LEFT);

    }
}
