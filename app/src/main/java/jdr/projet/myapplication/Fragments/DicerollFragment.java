package jdr.projet.myapplication.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import jdr.projet.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DicerollFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DicerollFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DicerollFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Button rollButton;
    private Spinner spinner_diceTypes;
    private EditText diceCountInput;
    private TextView resultString;

    public DicerollFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DicerollFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DicerollFragment newInstance(String param1, String param2) {
        DicerollFragment fragment = new DicerollFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initRollDiceController();
        super.onViewCreated(view, savedInstanceState);
    }

    private void initRollDiceController(){
        // Get a reference to all the needed controls
        resultString = getView().findViewById(R.id.diceResult);
        spinner_diceTypes = getView().findViewById(R.id.diceType);
        rollButton = getView().findViewById(R.id.buttonRoll);
        diceCountInput = getView().findViewById(R.id.diceCount);

        // Listener for the roll button
        rollButton.setOnClickListener(this);
        //spinner_diceTypes.setOnItemSelectedListener(this);
        //spinner_diceTypes.setSelection(0);

        // Accept only numbers
        diceCountInput.setInputType(InputType.TYPE_CLASS_NUMBER);

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.buttonRoll:
                if(diceCountInput.getText().toString().equals(""))
                    diceCountInput.setText("1");
                int sideCount = getSides(spinner_diceTypes.getSelectedItem().toString());
                int diceCount = Integer.parseInt(diceCountInput.getText().toString());
                int totalResult = 0;
                StringBuilder sb = new StringBuilder();
                for(int i=1; i<= diceCount; i++){
                    int res = (int) (Math.random() * 100);
                    res = (res % sideCount) + 1;
                    totalResult += res;
                    sb.append(res);
                    if(i==diceCount)
                        sb.append("=");
                    else
                        sb.append("+");
                }
                sb.append(totalResult);
                if(diceCount > 1)
                    resultString.setText(sb.toString());
                else
                    resultString.setText(totalResult+"");
        }

    }

    private int getSides(String diceName){
        switch(diceName){
            case "D4":
                return 4;
            case "D8":
                return 8;
            case "D10":
                return 10;
            case "D12":
                return 12;
            case "D20":
                return 20;
            case "D100":
                return 100;
        }
        return 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_diceroll, container, false);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
