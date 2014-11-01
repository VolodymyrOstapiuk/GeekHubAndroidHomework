package ua.ck.ostapiuk.geekhubhomework.fragment;


import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import ua.ck.ostapiuk.geekhubhomework.R;


public class DocTitlesFragment extends ListFragment {
    private OnDocTitleSelectedListener listener;
    private String[] titles;
    private String[][] allTitles;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    public final static String TITTLES_ID = "Tittle_id";
    private int mTitlesId;
    public DocTitlesFragment() {
        // Required empty public constructor
    }

    public interface OnDocTitleSelectedListener {
        public void onDocTitleSelected(int position, int category);
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            updateTitleList(args.getInt(TITTLES_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doc_titles, container, false);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnDocTitleSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement the OnDocTitleSelectedListener interface");
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitlesId = getArguments().getInt(TITTLES_ID);
            getDocsFromID(mTitlesId);

        } else {
            mTitlesId = 0;
            getDocsFromID(mTitlesId);
        }
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.listview_doctitles_template, R.id.textViewList, titles);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        listener.onDocTitleSelected(position, mTitlesId);
    }

    public void updateTitleList(int id) {
        getDocsFromID(id);
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.listview_doctitles_template, R.id.textViewList, titles);
        setListAdapter(adapter);
    }

    public void getDocsFromID(int ID) {
        TypedArray docsArray = getResources().obtainTypedArray(R.array.titles);
        allTitles = new String[docsArray.length()][];
        for (int i = 0; i < docsArray.length(); ++i) {
            int id = docsArray.getResourceId(i, 0);
            if (id > 0) {
                allTitles[i] = getResources().getStringArray(id);
            } else {
                // something wrong with the XML
            }
        }
        docsArray.recycle(); // Important!
        titles = allTitles[ID];
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
