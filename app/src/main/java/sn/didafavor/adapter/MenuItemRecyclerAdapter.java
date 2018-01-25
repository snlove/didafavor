package sn.didafavor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sn.didafavor.R;
import sn.didafavor.data.CategoryData;

/**
 * Created by pc on 2018/1/9.
 */

public class MenuItemRecyclerAdapter extends RecyclerView.Adapter<MenuItemRecyclerAdapter.MenuItemVH> {


    private List<CategoryData> lists;
    private Context context;

    public MenuItemRecyclerAdapter(Context context, List<CategoryData> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public MenuItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.category_header, parent, false);
            return  new MenuItemVH(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.menu_category, parent, false);
            return  new MenuItemVH(view);
        }
    }

    @Override
    public void onBindViewHolder(MenuItemVH holder, int position) {
               if (getItemViewType(position) == 2) {
                   holder.item_icon.setImageResource(lists.get(position-1).getResource_id());
                   holder.item_title.setText(lists.get(position-1).getTitle());
               }
    }

    @Override
    public int getItemCount() {
        return lists.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    public class MenuItemVH extends RecyclerView.ViewHolder {

        private ImageView item_icon;
        private TextView item_title;
        private ImageView personHead;
        private TextView tv_phone;
        private TextView tv_email;

        public MenuItemVH(View itemView) {
            super(itemView);
            if (getItemViewType() == 1) {
                personHead = itemView.findViewById(R.id.iv_personHead);
                tv_phone = itemView.findViewById(R.id.tv_phone);
                tv_email = itemView.findViewById(R.id.tv_email);
            } else {
                item_icon = itemView.findViewById(R.id.iv_item_icon);
                item_title = itemView.findViewById(R.id.tv_item_title);
            }


        }
    }
}
