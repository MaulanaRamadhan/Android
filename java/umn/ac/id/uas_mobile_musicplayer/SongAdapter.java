package umn.ac.id.uas_mobile_musicplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private Context context;
    private ArrayList<SongInfo> songList;
    private RecyclerItemClickListener listener;

    public SongAdapter(Context context, ArrayList<SongInfo> songList, RecyclerItemClickListener listener){

        this.context = context;
        this.songList = songList;
        this.listener = listener;

    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_song, parent, false);

        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {

        SongInfo song = songList.get(position);
        if(song != null){

            holder.tv_title.setText(song.getSongName());
            holder.bind(song, listener);
        }

    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_title;

        public SongViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.textView_SongName);


        }

        public void bind(final SongInfo song, final RecyclerItemClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickListener(song, getLayoutPosition());
                }
            });
        }

    }

    public interface RecyclerItemClickListener{
        void onClickListener(SongInfo song, int position);
    }
}