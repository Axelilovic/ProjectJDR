package jdr.projet.myapplication.Fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import jdr.projet.myapplication.Classes.Game;
import jdr.projet.myapplication.DataBase.DbHelper;
import jdr.projet.myapplication.R;
import jdr.projet.myapplication.Fragments.AddNewCharacterSheetFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CharacterSheetsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CharacterSheetsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNewCharacterSheetFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    SQLiteDatabase db;
    EditText editName;
    EditText editEdition;
    EditText editExtension;
    Button valider;
    DbHelper dbHelper;
    CharacterSheetsFragment characterSheetsFragment;

    public AddNewCharacterSheetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CharacterSheetsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CharacterSheetsFragment newInstance(String param1, String param2) {
        CharacterSheetsFragment fragment = new CharacterSheetsFragment();
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
        initAddNewCharacterSheet();
        super.onViewCreated(view, savedInstanceState);
    }

    public void initAddNewCharacterSheet(){
        dbHelper = new DbHelper(getContext());
        db = dbHelper.getWritableDatabase();

        valider = (Button) getView().findViewById(R.id.validation);

        editName = (EditText) getView().findViewById(R.id.editGameName);
        editEdition = (EditText) getView().findViewById(R.id.editEdition);
        editExtension = (EditText) getView().findViewById(R.id.editExtension);

        valider.setOnClickListener((View.OnClickListener) this);

        characterSheetsFragment = new CharacterSheetsFragment();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.validation:
                Game entree1 = new Game(editName.getText().toString(),editEdition.getText().toString(),editExtension.getText().toString());
                dbHelper.addGame(db, entree1);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.currentFragmentLayout, characterSheetsFragment);
                fragmentTransaction.commit();
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_sheets, container, false);
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
