package tanvir.busmanagementsystem.RecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tanvir.busmanagementsystem.BustListAV;
import tanvir.busmanagementsystem.MOdelClass.BusINfoMC;
import tanvir.busmanagementsystem.R;
import tanvir.busmanagementsystem.SeatViewActivity;

/**
 * Created by USER on 01-Feb-17.
 */

public class RecyclerAdapterForBusList extends RecyclerView.Adapter<RecyclerAdapterForBusList.RecyclerViewHolder> {


    ArrayList<BusINfoMC> busINfoMCArrayList;
    Context context;

    public RecyclerAdapterForBusList(Context context, ArrayList<BusINfoMC> busINfoMCArrayList) {
        this.context = context;
        this.busINfoMCArrayList = busINfoMCArrayList;

    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_bus_list_rv, parent, false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, context, busINfoMCArrayList);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        ///holder.departureTimeTV.setText(busINfoMCArrayList.get(position).getDepartureTime());
        holder.busNameTV.setText(busINfoMCArrayList.get(position).getBusName());
        holder.busTypeTV.setText(busINfoMCArrayList.get(position).getBusType());
        holder.seatPriceTV.setText(busINfoMCArrayList.get(position).getBusSeatPrice()+"/-");


    }

    @Override
    public int getItemCount() {
        return busINfoMCArrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener

    {
        TextView departureTimeTV;
        TextView busNameTV;
        TextView busTypeTV;
        TextView seatPriceTV;
        ArrayList<BusINfoMC> busINfoMCArrayList;

        Context context;

        CardView cardView;

        public RecyclerViewHolder(View view, final Context context, final ArrayList<BusINfoMC> busINfoMCS) {
            super(view);
            this.busINfoMCArrayList = busINfoMCS;

            this.context = context;

            cardView= view.findViewById(R.id.cardView);
            departureTimeTV = (TextView) view.findViewById(R.id.timeInRV);
            busNameTV = (TextView) view.findViewById(R.id.busNameInRV);
            busTypeTV = (TextView) view.findViewById(R.id.busTypeInRV);
            seatPriceTV = (TextView) view.findViewById(R.id.seatPriceInRV);

            cardView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();


            Intent intent = new Intent(context, SeatViewActivity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            context.startActivity(intent);

        }

    }


}
