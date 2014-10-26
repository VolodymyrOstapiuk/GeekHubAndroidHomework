package ua.ck.ostapiuk.geekhubhomework1;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceScreen;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends Activity implements DocTitlesFragment.OnDocTitleSelectedListener {
    private FragmentManager manager;
    private boolean dualPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DocTitlesFragment docTitlesFragment = new DocTitlesFragment();
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
    public void onDocTitleSelected(int position) {
        if (findViewById(R.id.doc_container) != null) {
            FragmentTransaction transaction = manager.beginTransaction();
            Bundle args = new Bundle();
            args.putInt(DocFragment.DOC_ID, position);
            DocFragment docFragment = new DocFragment();
            docFragment.setArguments(args);
            transaction.replace(R.id.doc_container, docFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else {
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle args = new Bundle();
        args.putInt(DocFragment.DOC_ID, position);
        DocFragment docFragment = new DocFragment();
        docFragment.setArguments(args);
        transaction.replace(R.id.container, docFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        }
    }
}
