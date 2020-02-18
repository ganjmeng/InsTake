package adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingDev.Instagram_video_downloader.MainActivity;
import com.kingDev.Instagram_video_downloader.R;
import com.kingDev.Instagram_video_downloader.dialog_rename;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import net.rdrei.android.dirchooser.DirectoryChooserActivity;
import net.rdrei.android.dirchooser.DirectoryChooserConfig;

import java.io.File;
import java.util.ArrayList;

import func.reg;

/**
 * Created by mac on 26/01/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    ArrayList<arrayAdapter> mData;
    private Activity activity;
    private File file;
    private int[] maid = {R.id.menu,R.id.card_view};

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        private TextView  title;
        private ImageView imagevi , imagePlayer;
        private ImageButton menu;
        private Button play,share,delete;
        private CardView cardView;

        public ViewHolder(View v) {
            super(v);
            imagevi = (ImageView)v.findViewById(R.id.imageView);
            imagePlayer = (ImageView)v.findViewById(R.id.iconplayer);
            title = (TextView)v.findViewById(R.id.title);
            menu = (ImageButton)v.findViewById(R.id.menu);
            cardView = (CardView) v.findViewById(R.id.card_view);

            menu.setOnClickListener(this);
            cardView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            final arrayAdapter vi = mData.get(getAdapterPosition());

            if( maid[0] == v.getId()){

                PopupMenu popup = new PopupMenu(activity, menu);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        if(item.getItemId() == R.id.menu_play){

                            func.player.mPlayer(vi.getFilePath(), activity);

                        }else if (item.getItemId() == R.id.menu_rename){

                            FragmentActivity activityGrand = (FragmentActivity)(activity);
                            FragmentManager fm = activityGrand.getSupportFragmentManager();
                            dialog_rename alertDialog = dialog_rename.newInstance(vi.getFilePath());
                            alertDialog.show(fm, "fragment_alert");

                        }else if (item.getItemId() == R.id.menu_movefile){

                            final Intent chooserIntent = new Intent(
                                    activity,
                                    DirectoryChooserActivity.class);

                            final DirectoryChooserConfig config = DirectoryChooserConfig.builder()
                                    .newDirectoryName(activity.getResources().getString(R.string.foldername))
                                    .allowReadOnlyDirectory(true)
                                    .allowNewDirectoryNameModification(true)
                                    .build();

                            chooserIntent.putExtra(
                                    DirectoryChooserActivity.EXTRA_CONFIG,
                                    config);

                            MainActivity.filepath = vi.getFilePath();

                            activity.startActivityForResult(chooserIntent, 34);

                        }else if (item.getItemId() == R.id.menu_share){

                            func.share.mShare(vi.getFilePath(), activity);

                        }else if (item.getItemId() == R.id.menu_delete){

                            file = new File(vi.getFilePath());

                            if (file.exists()) {

                                boolean del = file.delete();

                                mData.remove(getAdapterPosition());

                                notifyItemRemoved(getAdapterPosition());
                                notifyItemRangeChanged(getAdapterPosition(), mData.size());

                            }
                        }

                        return true;
                    }
                });

                //showing popup menu
                popup.show();

            }else if(maid[1] == v.getId()){

                func.player.mPlayer(vi.getFilePath(), activity);

            }


        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Activity a, ArrayList<arrayAdapter> jData) {
        this.mData = jData;
        this.activity = a;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_videos_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        arrayAdapter jpast = mData.get(position);

        file = new File(jpast.getFilePath());

        if(!file.isDirectory()){

            // display video
            if(!reg.getBack(jpast.getFilePath(), "((\\.mp4|\\.webm|\\.ogg|\\.mpK|\\.avi|\\.mkv|\\.flv|\\.mpg|\\.wmv|\\.vob|\\.ogv|\\.mov|\\.qt|\\.rm|\\.rmvb\\.|\\.asf|\\.m4p|\\.m4v|\\.mp2|\\.mpeg|\\.mpe|\\.mpv|\\.m2v|\\.3gp|\\.f4p|\\.f4a|\\.f4b|\\.f4v)$)").isEmpty()){

                try{
                    Glide.with(activity)
                            .load(file)
                            .placeholder(R.drawable.uo)
                            .into(holder.imagevi);

                }catch (Exception a){

                }

                subStringTitle(holder.title , jpast.getFileName());

                // display audio
            }else if(!reg.getBack(jpast.getFilePath(), "((\\.3ga|\\.aac|\\.aif|\\.aifc|\\.aiff|\\.amr|\\.au|\\.aup|\\.caf|\\.flac|\\.gsm|\\.kar|\\.m4a|\\.m4p|\\.m4r|\\.mid|\\.midi|\\.mmf|\\.mp2|\\.mp3|\\.mpga|\\.ogg|\\.oma|\\.opus|\\.qcp|\\.ra|\\.ram|\\.wav|\\.wma|\\.xspf)$)").
                    isEmpty()){

                holder.imagePlayer.setVisibility(View.GONE);

                subStringTitle(holder.title, jpast.getFileName());

                // display images
            }else if(!reg.getBack(jpast.getFilePath(), "((\\.jpg|\\.png|\\.gif|\\.jpeg|\\.bmp)$)").isEmpty()){

                holder.imagePlayer.setVisibility(View.GONE);
                Picasso.with(activity).load(file).into(holder.imagevi);
                subStringTitle(holder.title , jpast.getFileName());

            }

            Log.e("LoadedFiles", file + "");

        }
    }

    void subStringTitle(TextView mTextView , String mTitle){

        if(reg.getBack(mTitle, "(((?!\\.).)*)").length() > 24){

            mTextView.setText(reg.getBack(reg.getBack(mTitle, "(((?!\\.).)*)"), "(^([\\w\\W]{25}))"));

        }else {

            mTextView.setText(reg.getBack(mTitle, "(((?!\\.).)*)"));
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mData.size();
    }

}