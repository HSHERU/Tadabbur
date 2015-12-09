package ps.social.tadabbur;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RemainderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RemainderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RemainderFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RemainderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RemainderFragment newInstance(int param1, String param2) {
        RemainderFragment fragment = new RemainderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public RemainderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this.getContext());

        View v= inflater.inflate(R.layout.fragment_remainder, container, false);
        TextView tv=(TextView)v.findViewById(R.id.Readtxtview);

        databaseAccess.open();
        String tx=databaseAccess.getpage(mParam1 + 1);
        databaseAccess.close();
        tv.setText(tx);
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/UthmanicHafs1 Ver09.otf");
        tv.setTypeface(font);
        TextView pgn=(TextView)v.findViewById(R.id.pgno);

        pgn.setText(Integer.toString(mParam1+1));
        return v;

        // Inflate the layout for this fragment
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
