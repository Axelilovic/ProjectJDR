package jdr.projet.myapplication.Fragments;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;

import jdr.projet.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SoundManagerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SoundManagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SoundManagerFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Button buttonR, buttonF, buttonPlay, buttonPause, buttonStop;

    public SoundManagerFragment() {
        // Required empty public constructor
    }

    MediaPlayer mMediaPlayer;
    private int resumePosition;
    HashMap<Integer, Integer> mHashMap= null;
    int soundId = 1;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SoundManagerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SoundManagerFragment newInstance(String param1, String param2) {
        SoundManagerFragment fragment = new SoundManagerFragment();
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
        initSoundManagerController();
        super.onViewCreated(view, savedInstanceState);
    }

    private void initSoundManagerController(){
        buttonF=(Button) getView().findViewById(R.id.buttonF);
        buttonR=(Button) getView().findViewById(R.id.buttonR);
        buttonPause=(Button) getView().findViewById(R.id.buttonPause);
        buttonPlay=(Button) getView().findViewById(R.id.buttonPlay);
        buttonStop=(Button) getView().findViewById(R.id.buttonStop);

        mHashMap = new HashMap<>();
        mHashMap.put(1, R.raw.ambience);
        mHashMap.put(2, R.raw.hell);
        mHashMap.put(3, R.raw.town);

        buttonStop.setOnClickListener(this);
        buttonPlay.setOnClickListener(this);
        buttonPause.setOnClickListener(this);
        buttonF.setOnClickListener(this);
        buttonR.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.buttonF:
                soundId+=1;
                mMediaPlayer.reset();
                if (soundId > 3) {
                    soundId = 1;
                }
                Toast.makeText(getContext(), "Next sound " + soundId,Toast.LENGTH_SHORT).show();
                if (mMediaPlayer!=null){
                    mMediaPlayer = MediaPlayer.create(getContext(), mHashMap.get(soundId));
                    mMediaPlayer.start();
                }
                break;
            case R.id.buttonR:
                soundId -=1;
                mMediaPlayer.reset();
                if (soundId < 1) {
                    soundId = 3;
                }
                Toast.makeText(getContext(), "Previous sound " + soundId,Toast.LENGTH_SHORT).show();
                if (mMediaPlayer!=null){
                    mMediaPlayer = MediaPlayer.create(getContext(), mHashMap.get(soundId));
                    mMediaPlayer.start();
                }
                break;
            case R.id.buttonPlay:
                Toast.makeText(getContext(), "Playing sound",Toast.LENGTH_SHORT).show();
                if (mMediaPlayer==null) {
                    mMediaPlayer = MediaPlayer.create(getContext(), mHashMap.get(soundId));
                } else {
                    mMediaPlayer.seekTo(resumePosition);
                    mMediaPlayer.start();
                }
                break;
            case R.id.buttonPause:
                Toast.makeText(getContext(), "Pausing sound",Toast.LENGTH_SHORT).show();
                if (mMediaPlayer!=null && mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                    resumePosition = mMediaPlayer.getCurrentPosition();
                }
                break;
            case R.id.buttonStop:
                Toast.makeText(getContext(), "Stopping sound",Toast.LENGTH_SHORT).show();
                if (mMediaPlayer == null) return;
                if (mMediaPlayer!=null) {
                    mMediaPlayer.stop();
                }
                break;
        }
    }

    public void onStop() {
        super.onStop();
        if (mMediaPlayer!=null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sound_manager, container, false);
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
