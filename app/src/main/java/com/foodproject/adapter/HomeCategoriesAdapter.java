package com.foodproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodproject.R;
import com.foodproject.model.Category;

import java.util.ArrayList;
import java.util.List;
/* 메인화면 - 카테고리 바로 보이는 곳 */
public class HomeCategoriesAdapter extends RecyclerView.Adapter<HomeCategoriesAdapter.ItemHolder>{

    private List<Category> categories = new ArrayList<>();
    private Context context;
    private final OnCategoryClickListener mListener;

    public HomeCategoriesAdapter(Context context){
        this.context = context;

        try {
            this.mListener = ((OnCategoryClickListener) context);
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement OnPlaceClickListener.");
        }

        String[] categoryNames = {"과일", "채소",
                "쌀/잡곡", "정육/계란", "수산", "견과류", "유제품", "장류/양념", "건강음료", "떡"};

        int images_array[] = {
                R.drawable.category_fruit_apple,
                R.drawable.category_vegetable,
                R.drawable.category_rice,
                R.drawable.category_meategg,
                R.drawable.category_fish,
                R.drawable.category_nuts,
                R.drawable.category_milk,
                R.drawable.category_sauce,
                R.drawable.category_healthy,
                R.drawable.category_ricecake,
        };

        for (int i = 0; i < 10; i++){
            Category category = new Category(categoryNames[i], images_array[i]);
            categories.add(category);
        }
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_category_item, viewGroup, false);
        ItemHolder holder = new ItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        final Category category =  categories.get(position);

        holder.mCategoryName.setText(category.getCategoryName());

        holder.mCategoryImage.setImageResource(category.getCategoryDrawable());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.onCategoryClickListener(category);
            }
        });
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mCategoryName;
        public ImageView mCategoryImage;
        public View mView;


        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            mCategoryName = itemView.findViewById(R.id.category_name);
            mCategoryImage = itemView.findViewById(R.id.category_photo);
        }

        @Override
        public void onClick(View v) {}
    }

    public interface OnCategoryClickListener {
        void onCategoryClickListener(Category category);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
