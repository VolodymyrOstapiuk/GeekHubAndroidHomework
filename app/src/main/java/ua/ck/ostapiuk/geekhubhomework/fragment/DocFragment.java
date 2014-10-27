package ua.ck.ostapiuk.geekhubhomework.fragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ua.ck.ostapiuk.geekhubhomework.R;


public class DocFragment extends Fragment {
    private TextView docText;
    public final static String DOC_ID = "id";
    public final static String DOC_ARRAY_ID = "array_id";
    private String[][] allDocs;
    private String[] docs;
    private int arrayId;
    public DocFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.docs = getResources().getStringArray(R.array.docs);
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            updateDoc(args.getInt(DOC_ID));
            arrayId = args.getInt(DOC_ARRAY_ID);
            getDocsFromID(arrayId);
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
        getDocsFromID(arrayId);
        docText.setText(docs[position]);
    }

    public void getDocsFromID(int ID) {
        TypedArray titlesArray = getResources().obtainTypedArray(R.array.docs);
        allDocs = new String[titlesArray.length()][];
        for (int i = 0; i < titlesArray.length(); ++i) {
            int id = titlesArray.getResourceId(i, 0);
            if (id > 0) {
                allDocs[i] = getResources().getStringArray(id);
            } else {
                // something wrong with the XML
            }
        }
        titlesArray.recycle(); // Important!
        docs = allDocs[ID];
    }
}
