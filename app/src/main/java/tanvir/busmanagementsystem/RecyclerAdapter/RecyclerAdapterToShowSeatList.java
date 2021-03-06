package tanvir.busmanagementsystem.RecyclerAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;

import tanvir.busmanagementsystem.BuyTicket;
import tanvir.busmanagementsystem.MOdelClass.SeatInfoMC;
import tanvir.busmanagementsystem.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by USER on 01-Feb-17.
 */

public class RecyclerAdapterToShowSeatList extends RecyclerView.Adapter<RecyclerAdapterToShowSeatList.RecyclerViewHolder> {


    Context context;
    ArrayList<String> seatNumber;
    TextView CounterTextView;
    Button buyTicket;
    int scheduleId;
    String busSeatPrice;
    ArrayList<SeatInfoMC> seatInfoMCS;
    Integer totalseat;

    public RecyclerAdapterToShowSeatList(Context context, TextView counterText, Button buyTicket, int scheduleId, String busSeatPrice, ArrayList<SeatInfoMC> seatInfoMCS, Integer totalseat) {
        this.context = context;
        this.CounterTextView = counterText;
        seatNumber = new ArrayList<>();
        this.buyTicket = buyTicket;
        this.scheduleId = scheduleId;
        this.busSeatPrice = busSeatPrice;
        this.seatInfoMCS = seatInfoMCS;
        this.totalseat = totalseat;


    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seatlist_recyclerview, parent, false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, context, CounterTextView, buyTicket, seatInfoMCS);
        return recyclerViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {


        if (seatInfoMCS.size() > 0) {



            for (int i = 0; i < seatInfoMCS.size(); i++) {
                String seat = seatInfoMCS.get(i).getSeatNumber();

                char c = seat.charAt(0);

                int row = c - 65;

                if (row == position) {

                    char d = seat.charAt(1);



                    int seatNum = Character.getNumericValue(d);



                    if (seatNum == 1) {
                        holder.firstSeatBooked.setVisibility(View.VISIBLE);
                        holder.firstSeatEmpty.setVisibility(View.INVISIBLE);

                    } else if (seatNum == 2) {
                        holder.secondSeatBooked.setVisibility(View.VISIBLE);
                        holder.secondSeatEmpty.setVisibility(View.INVISIBLE);

                    } else if (seatNum == 3) {
                        holder.thirdSeatBooked.setVisibility(View.VISIBLE);
                        holder.thirdSeatEmpty.setVisibility(View.INVISIBLE);

                    } else {
                        holder.fourthSeatBooked.setVisibility(View.VISIBLE);
                        holder.fourthSeatEmpty.setVisibility(View.INVISIBLE);
                    }

                }




            }


        }


        if (position == (totalseat - 1))
            seatNumber.clear();


    }

    @Override
    public int getItemCount() {
        return totalseat;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder

    {
        ImageView firstSeatEmpty, secondSeatEmpty, thirdSeatEmpty, fourthSeatEmpty;
        ImageView firstSeatBooked, secondSeatBooked, thirdSeatBooked, fourthSeatBooked;
        ImageView firstSeatSelected, secondSeatSelected, thirdSeatSelected, fourthSeatSelected;

        ArrayList<Integer> text;
        Context context;
        TextView counterText;
        Button buyTicket;
        ArrayList<SeatInfoMC> seatInfoMCS;
        String logged;



        public RecyclerViewHolder(final View view, final Context context, final TextView counterText, Button buyTicket, final ArrayList<SeatInfoMC> seatInfoMCS) {
            super(view);
            this.text = text;
            this.counterText = counterText;

            this.context = context;

            SharedPreferences prefs2 = context.getSharedPreferences("loginInfo", MODE_PRIVATE);
            logged = prefs2.getString("isLogged?", "");


            this.buyTicket = buyTicket;

            this.seatInfoMCS = seatInfoMCS;


            firstSeatEmpty = view.findViewById(R.id.firstSeatEmpty);
            secondSeatEmpty = view.findViewById(R.id.secondSeatEmpty);
            thirdSeatEmpty = view.findViewById(R.id.thirdSeatEmpty);
            fourthSeatEmpty = view.findViewById(R.id.fourthSeatEmpty);

            firstSeatBooked = view.findViewById(R.id.firstSeatBooked);
            secondSeatBooked = view.findViewById(R.id.secondSeatBooked);
            thirdSeatBooked = view.findViewById(R.id.thirdSeatBooked);
            fourthSeatBooked = view.findViewById(R.id.fourthSeatBooked);


            firstSeatSelected = view.findViewById(R.id.firstSeatSelected);
            secondSeatSelected = view.findViewById(R.id.secondSeatSelected);
            thirdSeatSelected = view.findViewById(R.id.thirdSeatSelected);
            fourthSeatSelected = view.findViewById(R.id.fourthSeatSelected);


            firstSeatEmpty.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    String seat = returnSeatNumber(position, 1, context);
                    if (seatNumber.size() < 4) {
                        seatNumber.add(seat);
                        CounterTextView.setText(getArrayListAsString());
                        ///Toast.makeText(context, Integer.toString(position), Toast.LENGTH_SHORT).show();

                        firstSeatEmpty.setVisibility(View.INVISIBLE);
                        firstSeatSelected.setVisibility(View.VISIBLE);

                    } else {
                        TastyToast.makeText(context, "You can't select more than four Seat ", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                    }


                }
            });


            secondSeatEmpty.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    String seat = returnSeatNumber(position, 2, context);

                    if (seatNumber.size() < 4) {
                        seatNumber.add(seat);
                        CounterTextView.setText(getArrayListAsString());
                        ///Toast.makeText(context, Integer.toString(position), Toast.LENGTH_SHORT).show();

                        secondSeatEmpty.setVisibility(View.INVISIBLE);
                        secondSeatSelected.setVisibility(View.VISIBLE);

                    } else {
                        TastyToast.makeText(context, "You can't select more than four Seat ", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                    }


                }
            });


            thirdSeatEmpty.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    ///Toast.makeText(context, Integer.toString(position), Toast.LENGTH_SHORT).show();

                    String seat = returnSeatNumber(position, 3, context);
                    if (seatNumber.size() < 4) {
                        seatNumber.add(seat);
                        CounterTextView.setText(getArrayListAsString());

                        thirdSeatEmpty.setVisibility(View.INVISIBLE);
                        thirdSeatSelected.setVisibility(View.VISIBLE);

                    } else {
                        TastyToast.makeText(context, "You can't select more than four Seat ", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                    }


                }
            });


            fourthSeatEmpty.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    ///Toast.makeText(context, Integer.toString(position), Toast.LENGTH_SHORT).show();

                    String seat = returnSeatNumber(position, 4, context);
                    if (seatNumber.size() < 4) {
                        seatNumber.add(seat);
                        CounterTextView.setText(getArrayListAsString());

                        fourthSeatEmpty.setVisibility(View.INVISIBLE);
                        fourthSeatSelected.setVisibility(View.VISIBLE);

                    } else {
                        TastyToast.makeText(context, "You can't select more than four Seat ", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                    }


                }
            });


            firstSeatSelected.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    String seat = returnSeatNumber(position, 1, context);
                    removeFromArraylist(seat);
                    CounterTextView.setText(getArrayListAsString());

                    ////Toast.makeText(context, Integer.toString(position), Toast.LENGTH_SHORT).show();


                    firstSeatSelected.setVisibility(View.INVISIBLE);
                    firstSeatEmpty.setVisibility(View.VISIBLE);


                }
            });

            secondSeatSelected.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    String seat = returnSeatNumber(position, 2, context);
                    removeFromArraylist(seat);
                    CounterTextView.setText(getArrayListAsString());

                    ///Toast.makeText(context, Integer.toString(position), Toast.LENGTH_SHORT).show();

                    secondSeatSelected.setVisibility(View.INVISIBLE);
                    secondSeatEmpty.setVisibility(View.VISIBLE);


                }
            });


            thirdSeatSelected.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    String seat = returnSeatNumber(position, 3, context);
                    removeFromArraylist(seat);
                    CounterTextView.setText(getArrayListAsString());

                    ////Toast.makeText(context, Integer.toString(position), Toast.LENGTH_SHORT).show();

                    thirdSeatSelected.setVisibility(View.INVISIBLE);
                    thirdSeatEmpty.setVisibility(View.VISIBLE);


                }
            });


            fourthSeatSelected.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {


                    int position = getAdapterPosition();

                    String seat = returnSeatNumber(position, 4, context);
                    removeFromArraylist(seat);
                    CounterTextView.setText(getArrayListAsString());

                    ///Toast.makeText(context, Integer.toString(position), Toast.LENGTH_SHORT).show();


                    fourthSeatSelected.setVisibility(View.INVISIBLE);
                    fourthSeatEmpty.setVisibility(View.VISIBLE);


                }
            });

            firstSeatBooked.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {


                    if (logged.contains("yes")) {
                        int position = getAdapterPosition();

                        String seat = returnSeatNumber(position, 1, context);

                        /// Toast.makeText(context, "Position : "+Integer.toString(position), Toast.LENGTH_SHORT).show();

                        showUserInformation(seat);
                    }


                }
            });

            secondSeatBooked.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {

                    if (logged.contains("yes")) {
                        int position = getAdapterPosition();
                        ///Toast.makeText(context, "Position : "+Integer.toString(position), Toast.LENGTH_SHORT).show();

                        ///showUserInformation(position);

                        String seat = returnSeatNumber(position, 2, context);


                        showUserInformation(seat);
                    }


                }
            });

            thirdSeatBooked.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {

                    if (logged.contains("yes")) {
                        int position = getAdapterPosition();

                        String seat = returnSeatNumber(position, 3, context);


                        showUserInformation(seat);
                    }


                    ///Toast.makeText(context, "Position : "+Integer.toString(position), Toast.LENGTH_SHORT).show();

                    ///showUserInformation(position);


                }
            });

            fourthSeatBooked.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {

                    if (logged.contains("yes")) {
                        int position = getAdapterPosition();
                        String seat = returnSeatNumber(position, 4, context);


                        showUserInformation(seat);
                    }


                    //Toast.makeText(context, "Position : "+Integer.toString(position), Toast.LENGTH_SHORT).show();

                    ///showUserInformation(position);


                }
            });


            buyTicket.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BuyTicket.class);
                    intent.putExtra("scheduleId", scheduleId);
                    intent.putExtra("seatNumber", seatNumber);
                    intent.putExtra("seatPrice", busSeatPrice);
                    intent.putExtra("totalSeat", totalseat);
                    if (seatNumber.size() > 0) {
                        context.startActivity(intent);
                    } else
                        TastyToast.makeText(context, "You didn't select any seat ", TastyToast.LENGTH_SHORT, TastyToast.WARNING);


                }
            });


        }

        public String getArrayListAsString() {
            String s = "";

            for (int i = 0; i < seatNumber.size(); i++) {
                s += seatNumber.get(i);

                if (seatNumber.size() != 1 && seatNumber.size() != (i + 1))
                    s += " , ";
            }
            return s;
        }

        public void removeFromArraylist(String seat) {
            int position = seatNumber.indexOf(seat);
            seatNumber.remove(position);

        }

        public String returnSeatNumber(int position, int seatPOsition, Context context) {
            char c = (char) (position + 65);
            String seat = c + Integer.toString(seatPOsition);


            /// Toast.makeText(context, seat, Toast.LENGTH_SHORT).show();

            return seat;

        }

        public void showUserInformation(String seat) {
            ///Toast.makeText(context, "seat : "+seat, Toast.LENGTH_SHORT).show();
            for (int i = 0; i < seatInfoMCS.size(); i++) {
                ///Toast.makeText(context, "seat loop "+ seatInfoMCS.get(i).getSeatNumber(), Toast.LENGTH_SHORT).show();
                if (seatInfoMCS.get(i).getSeatNumber().equals(seat)) {
                    final AlertDialog alertDialog;

                    final View userDetailsDialogView;

                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);

                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    userDetailsDialogView = inflater.inflate(R.layout.user_info_for_admin_rv, null);

                    TextView userNameTV = userDetailsDialogView.findViewById(R.id.userNameTV);
                    TextView contactNoTV = userDetailsDialogView.findViewById(R.id.userContactNOTV);
                    TextView emailTV = userDetailsDialogView.findViewById(R.id.userEmailTV);

                    userNameTV.setText(seatInfoMCS.get(i).getCustomerName());
                    contactNoTV.setText(seatInfoMCS.get(i).getCustomerMobileNumber());
                    emailTV.setText(seatInfoMCS.get(i).getCustomerEmail());

                    dialogBuilder.setView(userDetailsDialogView);
                    alertDialog = dialogBuilder.create();
                    alertDialog.show();
                    break;
                }
            }

        }

    }

}
