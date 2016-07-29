package sundeepk.github.com.sample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.util.ArrayList;

public class RecyclerMonthViewAdapter extends RecyclerView.Adapter<RecyclerMonthViewAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private CompactCalendarView pattern;
    private int monthAmount;
    ArrayList<CompactCalendarView> monthViews = new ArrayList<>();

    public RecyclerMonthViewAdapter(Context context, int amount){
        inflater = LayoutInflater.from(context);
//        this.monthViews = monthViews;
        monthAmount = amount;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.month_row, parent, false);
        pattern = (CompactCalendarView) view.findViewById(R.id.compactcalendar_view);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        CompactCalendarView currentMonthView = monthViews.get(position);
//        holder.compactCalendarView = currentMonthView;
        holder.compactCalendarView = pattern;
        Log.i("MyApp", "get pattern");
    }


    @Override
    public int getItemCount() {
        Log.i("MyApp", String.valueOf(monthAmount));
        return this.monthAmount;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        CompactCalendarView compactCalendarView;
        public MyViewHolder(View itemView) {
            super(itemView);
            Log.i("MyApp", "hehere");
            compactCalendarView = (CompactCalendarView) itemView.findViewById(R.id.compactcalendar_view);
        }
    }
}