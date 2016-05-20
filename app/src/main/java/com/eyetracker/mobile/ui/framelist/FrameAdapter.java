package com.eyetracker.mobile.ui.framelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.R;
import com.eyetracker.mobile.model.Frame;

import java.util.List;

import javax.inject.Inject;

import butterknife.OnClick;

/**
 * Created by fabia on 5/9/2016.
 */
public class FrameAdapter extends RecyclerView.Adapter<FrameAdapter.ViewHolder> {

    @Inject
    FrameListPresenter frameListPresenter;

    private Context context;
    private List<Frame> frameList;

    public FrameAdapter(Context context, List<Frame> frameList) {
        this.context = context;
        this.frameList = frameList;

        EyeTrackerApplication.injector.inject(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.frame_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Frame frame = frameList.get(position);
        holder.id = frame.getId();
        Glide.with(context).load(frame.getImage().getUrl()).into(holder.ivImage);
        holder.tvTitle.setText(frame.getTitle());
        holder.tvFilterType.setText(frame.getFilterType().toString());
        holder.tvLeftCoord.setText(frame.getLeftCoordinates().toString());
        holder.tvRightCoord.setText(frame.getRightCoordinates().toString());
    }

    @Override
    public int getItemCount() {
        return frameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView ivImage;
        public TextView tvTitle;
        public TextView tvFilterType;
        public TextView tvLeftCoord;
        public TextView tvRightCoord;
        public Long id;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvFilterType = (TextView) itemView.findViewById(R.id.tvFilterType);
            tvLeftCoord = (TextView) itemView.findViewById(R.id.tvLeftCoord);
            tvRightCoord = (TextView) itemView.findViewById(R.id.tvRightCoord);
        }

        @Override
        public void onClick(View v) {
            frameListPresenter.showDetails(id);
        }
    }

}