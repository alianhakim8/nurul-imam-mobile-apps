//package com.iai.nurulimam.Adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.iai.nurulimam.R;
//
//import java.util.List;
//
//public class TeacherListAdapter extends RecyclerView.Adapter<TeacherListAdapter.GuruViewHolder> {
//
//    private Context context;
//    private List<TeacherListModel> teacherLists;
//    private ItemClickListener itemClickListener;
//
//    public TeacherListAdapter(Context context, List<TeacherListModel> teacherList, ItemClickListener itemClickListener) {
//        this.context = context;
//        this.teacherLists = teacherList;
//        this.itemClickListener = itemClickListener;
//    }
//
//    @NonNull
//    @Override
//    public GuruViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.teacher_list_item, parent, false);
//        return new GuruViewHolder(view, itemClickListener);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull GuruViewHolder holder, int position) {
//        TeacherListModel listGuru = teacherLists.get(position);
//        holder.tv_name.setText(listGuru.getName());
//        holder.tv_nik.setText(listGuru.getEmail());
//        holder.tv_status.setText(listGuru.getStatus());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return teacherLists.size();
//    }
//
//    static class GuruViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        TextView tv_name, tv_nik, tv_status;
//        CardView card_item;
//        ItemClickListener itemClickListener;
//
//        GuruViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
//            super(itemView);
//            tv_name = itemView.findViewById(R.id.tv_teacherName);
//            tv_nik = itemView.findViewById(R.id.tv_teacherNik);
//            tv_status = itemView.findViewById(R.id.tv_teacherStatus);
//            card_item = itemView.findViewById(R.id.card_item);
//
//            this.itemClickListener = itemClickListener;
//            card_item.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View v) {
//            itemClickListener.onItemClick(v, getAdapterPosition());
//
//        }
//    }
//
//    public interface ItemClickListener {
//        void onItemClick(View view, int position);
//    }
//}
