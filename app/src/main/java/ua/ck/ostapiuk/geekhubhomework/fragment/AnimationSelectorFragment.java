package ua.ck.ostapiuk.geekhubhomework.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ua.ck.ostapiuk.geekhubhomework.R;

public class AnimationSelectorFragment extends Fragment {

    private OnAnimationSelectedListener mListener;
    private Button fadeOut;
    private Button fadeIn;
    private Button blink;
    private Button zoomIn;

    public AnimationSelectorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_animation_selector, container, false);
        fadeOut = (Button) rootView.findViewById(R.id.fade_out_select);
        fadeIn = (Button) rootView.findViewById(R.id.fade_in_select);
        blink = (Button) rootView.findViewById(R.id.blink_select);
        zoomIn = (Button) rootView.findViewById(R.id.zoom_in_select);
        fadeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onAnimationSelected(R.anim.fade_out);
            }
        });
        fadeIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onAnimationSelected(R.anim.fade_in);
            }
        });
        blink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onAnimationSelected(R.anim.blink);
            }
        });
        zoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onAnimationSelected(R.anim.zoom_in);
            }
        });
        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnAnimationSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnAnimationSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnAnimationSelectedListener {
        public void onAnimationSelected(int id);
    }

}
