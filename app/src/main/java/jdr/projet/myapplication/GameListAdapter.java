package jdr.projet.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jdr.projet.myapplication.DataBase.DbContract;

/**
 * Created by rabit on 12-03-18.
 */

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameViewHolder>{
    private Cursor mCursor;
    private Context mContext;

    public GameListAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.game_list_item, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position))
            return;

        String name = mCursor.getString(mCursor.getColumnIndex(DbContract.GameEntries.NAME));

        long id = mCursor.getLong(mCursor.getColumnIndex(DbContract.GameEntries._ID));

        holder.nameTextView.setText(name);
        //holder.bubbleTextView.setText(name.charAt(0));
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            this.notifyDataSetChanged();
        }
    }

    class GameViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView bubbleTextView;

        public GameViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
            bubbleTextView = (TextView) itemView.findViewById(R.id.bubble_text_view);
        }
    }
}
