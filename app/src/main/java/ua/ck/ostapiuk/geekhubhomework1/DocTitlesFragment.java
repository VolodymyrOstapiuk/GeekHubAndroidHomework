package ua.ck.ostapiuk.geekhubhomework1;


import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DocTitlesFragment extends ListFragment {
    private OnDocTitleSelectedListener listener;
    private String[] titles;
    private ArrayAdapter<String> adapter;
    private ListView listView;

    public DocTitlesFragment() {
        // Required empty public constructor
    }

    public interface OnDocTitleSelectedListener {
        public void onDocTitleSelected(int position);
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
        titles = getResources().getStringArray(R.array.titles);
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.listview_template, R.id.textViewList, titles);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        listener.onDocTitleSelected(position);
    }
}
