package ua.ck.ostapiuk.geekhubhomework1;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DocFragment extends Fragment {
    private TextView docText;
    public final static String DOC_ID = "id";
    private String[] docs;

    public DocFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.docs = getResources().getStringArray(R.array.doc);
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            updateDoc(args.getInt(DOC_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_doc, container, false);
        this.docText = (TextView) rootView.findViewById(R.id.textView);
        return rootView;
    }

    public void updateDoc(int position) {
        docText.setText(docs[position]);
    }
}
