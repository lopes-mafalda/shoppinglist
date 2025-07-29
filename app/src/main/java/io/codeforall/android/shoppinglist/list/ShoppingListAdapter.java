package io.codeforall.android.shoppinglist.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.codeforall.android.shoppinglist.R;
import io.codeforall.android.shoppinglist.persistence.model.Item;
import io.codeforall.android.shoppinglist.service.ItemService;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    private List<Item> data;
    private ItemService service;

    public ShoppingListAdapter(ItemService service) {
        this.service = service;
        this.data = service.getAll();
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
        holder.quantity.setText(data.get(position).getQuantity());
        holder.removeButton.setOnClickListener(v -> {
            int adapterPosition = holder.getBindingAdapterPosition();
            Item item = data.get(adapterPosition);
            service.delete(item);
            remove(adapterPosition);
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

    public void add(Item item) {
        data.add(item);
        notifyItemInserted(data.size() - 1);
    }

    public void setData(List<Item> data) {
        this.data = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView quantity;
        private final Button removeButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.card_name);
            this.quantity = itemView.findViewById(R.id.card_quantity);
            this.removeButton = itemView.findViewById(R.id.card_remove_button);
        }
    }
}
