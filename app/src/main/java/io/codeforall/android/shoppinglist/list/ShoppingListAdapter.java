package io.codeforall.android.shoppinglist.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.codeforall.android.shoppinglist.R;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    private final List<Item> data;
    private final Context context;

    public ShoppingListAdapter(List<Item> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(data.get(position).getName());
        holder.description.setText(data.get(position).getNote());
        holder.removeButton.setOnClickListener(v -> {
            int viewPosition = holder.getBindingAdapterPosition();
            if (viewPosition != RecyclerView.NO_POSITION) {
                remove(viewPosition);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private void remove(int id) {
        data.remove(id);
        notifyItemRemoved(id);
    }

    public void add(String name, String description){
        int index = getItemCount();
        data.add(new Item(index, name, description));
        notifyItemInserted(index);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView description;
        private final Button removeButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.card_name);
            this.description = itemView.findViewById(R.id.card_description);
            this.removeButton = itemView.findViewById(R.id.card_remove_button);
        }
    }
}
