package com.example.recyclerviewallinone;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter implements Filterable {

    private Context context;

    private ArrayList<BaseModelClass> arrayList;
    private ArrayList<BaseModelClass> tempList;
    private ArrayList<BaseModelClass> filteredList = new ArrayList<>();
    private ModelClass modelClass;

    //radiobutton
    private boolean isNewRadioButtonChecked = false;
    private int lastRadioButtonCheckedPosition = -1;

    //single item selection
    int singleitemSelectionPosition = -1;

    public MyAdapter(MainActivity context, ArrayList<BaseModelClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        tempList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        modelClass = (ModelClass) arrayList.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.textView.setText(modelClass.getName());

        bindMultipleItemSelection((ViewHolder) holder);
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public Filter getFilter() {
        return searchFilter;
    }

    private Filter searchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String query = constraint.toString();
            if (query.isEmpty()) {
                //tempList ha apla backup ahe. jevha search query empty hoil tevha hya backup madhun
                //original list baher kadhaychi which is the Full proof list.
                arrayList = tempList;
            } else {
                //filtered list la empty karaycha after each typed or deleted character and punha
                //filtered list loop laun populate karaychi as done below.
                filteredList.clear();

                for (BaseModelClass item : tempList) {
                    modelClass = (ModelClass) item;
                    if (modelClass.getName().toLowerCase().contains(query.toLowerCase().trim())) {
                        filteredList.add(item);
                    }
                }
                //finally arraylist ch publish hote, so filtered list arraylist madhe takaychi.
                arrayList = filteredList;
            }

            //ha part fakta publishResults() paryanta arraylist la pohochavnyasathi ahe.
            FilterResults results = new FilterResults();
            results.values = arrayList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            arrayList = (ArrayList<BaseModelClass>) results.values;
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ConstraintLayout rowItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.textView = itemView.findViewById(R.id.textView);
            this.rowItem = itemView.findViewById(R.id.rowitem);

            rowItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, String.valueOf(arrayList.get(getAdapterPosition())),
                            Toast.LENGTH_SHORT).show();
                    handleMultipleItemSelection(getAdapterPosition());
                }
            });
        }
    }

    private void handleRadiobuttonChecks(int adapterPosition) {
        //call this method from viewholder inside radio.setOnClickListener()

//        isNewRadioButtonChecked = true;
//        arrayList.get(lastRadioButtonCheckedPosition).setSelected(false);
//        arrayList.get(adapterPosition).setSelected(true);
//        lastRadioButtonCheckedPosition = adapterPosition;
//        notifyDataSetChanged();

    }

    private void handleCheckBoxTicks(View v, int adapterPosition) {
        //call this method from viewholder inside checkbox.setOnClickListener()

//        boolean isChecked = ((CheckBox) v).isChecked();
//
//        if (isChecked) {
//            arrayList.get(adapterPosition).setSelected(true);
//        } else {
//            arrayList.get(gadapterPosition).setSelected(false);
//        }
//        notifyDataSetChanged();
    }

    private void handleSingleItemSelection(int adapterPosition) {
        //call this method from viewholder inside rowItem.setOnClickListener()

        if (adapterPosition == RecyclerView.NO_POSITION) {
            return;
        }

        notifyItemChanged(singleitemSelectionPosition);
        singleitemSelectionPosition = adapterPosition;
        notifyItemChanged(singleitemSelectionPosition);
    }

    private void handleMultipleItemSelection(int adapterPosition) {
        ModelClass modelClass = (ModelClass) arrayList.get(adapterPosition);

        if (modelClass.isRowSelected()) {
            modelClass.setRowSelected(false);
        } else {
            modelClass.setRowSelected(true);
        }
        notifyDataSetChanged();
    }

    private void bindRadioButton(ModelClass modelClass, ViewHolder holder) {
        //call this method from bindViewHolder()

//        if (isNewRadioButtonChecked) {
//            holder.radioButton.setChecked(modelClass.isSelected());
//        } else {
//            if (holder.getAdapterPosition() == 0) {
//                holder.radioButton.setChecked(true);
//                lastRadioButtonCheckedPosition = 0;
//            }
//        }
    }

    private void bindCheckBox(ModelClass modelClass, ViewHolder holder) {
        //call this method from bindViewHolder()

        //holder.checkBox.setChecked(modelClass.isSelected());
    }

    private void bindSingleItemSelection(ViewHolder holder, int position) {
        //call this method from bindViewHolder()

        if (singleitemSelectionPosition == position) {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.highlight));
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private void bindMultipleItemSelection(ViewHolder holder) {
        if (modelClass.isRowSelected()) {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.highlight));
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }
}
