package ua.ck.ostapiuk.geekhubhomework.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import ua.ck.ostapiuk.geekhubhomework.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnimationViewerFragment extends Fragment {

    public static final String ANIMATION_ID = "id";
    private TextView animatedTextView;
    private int animationType;

    public AnimationViewerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_animation_viewer, container, false);
        animatedTextView = (TextView) rootView.findViewById(R.id.animatedTextView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            animationType = args.getInt(ANIMATION_ID);
            startSpecifiedAnimation(animationType);
        }
    }

    public void startSpecifiedAnimation(int animationType) {
        animatedTextView.clearAnimation();
        Animation textAnimation = AnimationUtils.loadAnimation(getActivity(), animationType);
        animatedTextView.setVisibility(View.VISIBLE);
        animatedTextView.startAnimation(textAnimation);

    }
}
