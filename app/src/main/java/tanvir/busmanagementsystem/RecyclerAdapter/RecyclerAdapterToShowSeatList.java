package tanvir.busmanagementsystem.RecyclerAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import tanvir.busmanagementsystem.R;

/**
 * Created by USER on 01-Feb-17.
 */

public class RecyclerAdapterToShowSeatList extends RecyclerView.Adapter< RecyclerAdapterToShowSeatList.RecyclerViewHolder> {



    Context context;

    public RecyclerAdapterToShowSeatList(Context context) {
        this.context = context;


    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seatlist_recyclerview, parent, false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, context);
        return recyclerViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        ///holder.firstSeatEmpty.setImageDrawable(context.getDrawable(text.get(position)));

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder

    {
        ImageView firstSeatEmpty, secondSeatEmpty, thirdSeatEmpty, fourthSeatEmpty;
        ImageView firstSeatBooked, secondSeatBooked, thirdSeatBooked, fourthSeatBooked;
        ImageView firstSeatSelected, secondSeatSelected, thirdSeatSelected, fourthSeatSelected;

        ArrayList<Integer> text;
        Context context;


        public RecyclerViewHolder(final View view, final Context context) {
            super(view);
            this.text = text;

            this.context = context;

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

                   

                    firstSeatEmpty.setVisibility(View.INVISIBLE);
                    firstSeatSelected.setVisibility(View.VISIBLE);


                }
            });


            secondSeatEmpty.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                   

                    secondSeatEmpty.setVisibility(View.INVISIBLE);
                    secondSeatSelected.setVisibility(View.VISIBLE);


                }
            });


            thirdSeatEmpty.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                   

                    thirdSeatEmpty.setVisibility(View.INVISIBLE);
                    thirdSeatSelected.setVisibility(View.VISIBLE);


                }
            });



            fourthSeatEmpty.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                   

                    fourthSeatEmpty.setVisibility(View.INVISIBLE);
                    fourthSeatSelected.setVisibility(View.VISIBLE);


                }
            });




            firstSeatSelected.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                   

                    firstSeatSelected.setVisibility(View.INVISIBLE);
                    firstSeatEmpty.setVisibility(View.VISIBLE);


                }
            });

            secondSeatSelected.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                   

                    secondSeatSelected.setVisibility(View.INVISIBLE);
                    secondSeatEmpty.setVisibility(View.VISIBLE);


                }
            });



            thirdSeatSelected.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                   

                    thirdSeatSelected.setVisibility(View.INVISIBLE);
                    thirdSeatEmpty.setVisibility(View.VISIBLE);


                }
            });



           fourthSeatSelected.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                   

                   fourthSeatSelected.setVisibility(View.INVISIBLE);
                   fourthSeatEmpty.setVisibility(View.VISIBLE);


                }
            });
        }

    }
}
