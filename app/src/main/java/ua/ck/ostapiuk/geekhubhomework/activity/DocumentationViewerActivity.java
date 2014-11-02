package ua.ck.ostapiuk.geekhubhomework.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import ua.ck.ostapiuk.geekhubhomework.R;
import ua.ck.ostapiuk.geekhubhomework.fragment.DocFragment;

public class DocumentationViewerActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentation_viewer);
        DocFragment docFragment = (DocFragment) getSupportFragmentManager().findFragmentById(R.id.doc_fragment);
        docFragment.getDocsFromID(getIntent().getExtras().getInt(DocFragment.DOC_ARRAY_ID));
        docFragment.updateDoc(getIntent().getExtras().getInt(DocFragment.DOC_ID));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.documentation_viewer, menu);
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
}
