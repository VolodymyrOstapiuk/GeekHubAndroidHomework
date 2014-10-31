package ua.ck.ostapiuk.geekhubhomework.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import ua.ck.ostapiuk.geekhubhomework.R;
import ua.ck.ostapiuk.geekhubhomework.fragment.AnimationSelectorFragment;
import ua.ck.ostapiuk.geekhubhomework.fragment.AnimationViewerFragment;

public class AnimationsExamplesActivity extends Activity implements AnimationSelectorFragment.OnAnimationSelectedListener {
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations_examples);
        AnimationSelectorFragment animationSelectorFragment = new AnimationSelectorFragment();
        manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, animationSelectorFragment);
        if (findViewById(R.id.animation_container) != null) {
            AnimationViewerFragment animationViewerFragment = new AnimationViewerFragment();
            transaction.add(R.id.animation_container, animationViewerFragment);
        }
        transaction.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.animations_examples, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_main_activity) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAnimationSelected(int id) {
        if (findViewById(R.id.animation_container) != null) {
            FragmentTransaction transaction = manager.beginTransaction();
            Bundle args = new Bundle();
            args.putInt(AnimationViewerFragment.ANIMATION_ID, id);
            AnimationViewerFragment animationViewerFragment = new AnimationViewerFragment();
            animationViewerFragment.setArguments(args);
            transaction.replace(R.id.animation_container, animationViewerFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            FragmentTransaction transaction = manager.beginTransaction();
            Bundle args = new Bundle();
            args.putInt(AnimationViewerFragment.ANIMATION_ID, id);
            AnimationViewerFragment animationViewerFragment = new AnimationViewerFragment();
            animationViewerFragment.setArguments(args);
            transaction.replace(R.id.container, animationViewerFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
