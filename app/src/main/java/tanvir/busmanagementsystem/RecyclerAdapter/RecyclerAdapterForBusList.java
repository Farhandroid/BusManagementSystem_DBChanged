package tanvir.busmanagementsystem.RecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;

import tanvir.busmanagementsystem.Database.DatabaseHelper;
import tanvir.busmanagementsystem.MOdelClass.BusINfoMC;
import tanvir.busmanagementsystem.MOdelClass.BusScheduleInfoMC;
import tanvir.busmanagementsystem.R;
import tanvir.busmanagementsystem.SeatViewActivity;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by USER on 01-Feb-17.
 */

public class RecyclerAdapterForBusList extends RecyclerView.Adapter<RecyclerAdapterForBusList.RecyclerViewHolder> {


    ArrayList<BusINfoMC> busINfoMCArrayList;
    ArrayList<BusScheduleInfoMC> busScheduleInfoMCS;
    Context context;

    public RecyclerAdapterForBusList(Context context, ArrayList<BusINfoMC> busINfoMCArrayList,ArrayList<BusScheduleInfoMC> busScheduleInfoMCS) {
        this.context = context;
        this.busINfoMCArrayList = busINfoMCArrayList;
        this.busScheduleInfoMCS=busScheduleInfoMCS;

    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_bus_list_rv, parent, false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, context, busINfoMCArrayList,busScheduleInfoMCS);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        String time = busScheduleInfoMCS.get(position).getDepartureTime()+" - "+busScheduleInfoMCS.get(position).getArrivaleTime();

        ///holder.departureTimeTV.setText(busINfoMCArrayList.get(position).getDepartureTime());
       holder.busTimeTV.setText(time);
        holder.busNameTV.setText(busScheduleInfoMCS.get(position).getBusName());
        holder.busTypeTV.setText(busINfoMCArrayList.get(position).getBusType());
        holder.seatPriceTV.setText(busINfoMCArrayList.get(position).getBusSeatPrice()+"/-");

        ///Toast.makeText(context, "RV busType "+busINfoMCArrayList.get(position).getBusType(), Toast.LENGTH_SHORT).show();


    }

    @Override
    public int getItemCount() {
        return busINfoMCArrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener

    {
        TextView busTimeTV;
        TextView busNameTV;
        TextView busTypeTV;
        TextView seatPriceTV;
        ArrayList<BusINfoMC> busINfoMCArrayList;
        ArrayList<BusScheduleInfoMC> busScheduleInfoMCS;

        Context context;

        LinearLayout linearLayout;

        public RecyclerViewHolder(View view, final Context context, final ArrayList<BusINfoMC> busINfoMCS, final ArrayList<BusScheduleInfoMC> busScheduleInfoMCS) {
            super(view);
            this.busINfoMCArrayList = busINfoMCS;
            this.busScheduleInfoMCS=busScheduleInfoMCS;

            this.context = context;

            linearLayout= view.findViewById(R.id.rvLL);
            busTimeTV = (TextView) view.findViewById(R.id.busTimeInRV);
            busNameTV = (TextView) view.findViewById(R.id.busNameInRV);
            busTypeTV = (TextView) view.findViewById(R.id.busTypeInRV);
            seatPriceTV = (TextView) view.findViewById(R.id.seatPriceInRV);

           linearLayout.setOnClickListener(this);

           linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View v) {


                   SharedPreferences prefs2 = context.getSharedPreferences("loginInfo", MODE_PRIVATE);
                   String logged = prefs2.getString("isLogged?", "");

                   if (logged.contains("yes"))
                   {
                       SharedPreferences.Editor editor2 = context.getSharedPreferences("fromAdmin?",MODE_PRIVATE).edit();
                       editor2.putString("fromAdmin?","no");
                       editor2.apply();


                       View dialogView;
                       final AlertDialog alertDialog;

                       AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                       LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

                       ///LayoutInflater inflater = context.getLayoutInflater();
                       dialogView = inflater.inflate(R.layout.bus_onlongclick_for_admin, null);
                       dialogBuilder.setView(dialogView);
                       alertDialog = dialogBuilder.create();
                       alertDialog.show();

                       TextView delete = dialogView.findViewById(R.id.deleteBusAD);


                       delete.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {

                               int scheduleId  = busScheduleInfoMCS.get(getAdapterPosition()).getBusScheduleInfoPK();

                               DatabaseHelper databaseHelper = new DatabaseHelper(context);

                               Boolean aBoolean = databaseHelper.checkIfBusSeatAvailable(scheduleId);

                               if (aBoolean)
                               {
                                   TastyToast.makeText(context, "You can't delete this schedule\n because this bus isn't empty ", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                                    alertDialog.dismiss();
                               }
                               else
                               {
                                   boolean b = databaseHelper.deleTeBusSchedule(scheduleId);

                                   if (b)
                                   {
                                       alertDialog.dismiss();
                                       busScheduleInfoMCS.remove(getAdapterPosition());
                                       busINfoMCS.remove(getAdapterPosition());
                                       notifyItemRemoved(getAdapterPosition());
                                       notifyItemRangeChanged(getAdapterPosition(),busScheduleInfoMCS.size());
                                       TastyToast.makeText(context, "Delete success", TastyToast.LENGTH_SHORT, TastyToast.WARNING);

                                   }
                                   else
                                   {
                                       alertDialog.dismiss();
                                       TastyToast.makeText(context, "Delete failed", TastyToast.LENGTH_SHORT, TastyToast.WARNING);

                                   }

                               }


                           }
                       });



                   }

                   return true;
               }
           });


        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();


            Intent intent = new Intent(context, SeatViewActivity.class);
            intent.putExtra("seatPrice",busINfoMCArrayList.get(position).getBusSeatPrice());
            intent.putExtra("totalSeat",busINfoMCArrayList.get(position).getTotalBusSeat());

            //Toast.makeText(context, "seatPrice RV : "+busINfoMCArrayList.get(position).getBusSeatPrice(), Toast.LENGTH_SHORT).show();

            intent.putExtra("scheduleId",busScheduleInfoMCS.get(position).getBusScheduleInfoPK());
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            context.startActivity(intent);

        }

    }


}
