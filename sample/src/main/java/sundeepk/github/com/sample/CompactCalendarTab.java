package sundeepk.github.com.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CompactCalendarTab extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerMonthViewAdapter recyclerAdapter;

    private static final String TAG = "MainActivity";
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private CompactCalendarView compactCalendarView;
    private ActionBar toolbar;
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View v =inflater.inflate(R.layout.month_fragment,container,false);
        recyclerView = (RecyclerView) v.findViewById(R.id.calendarMonthList);

//        compactCalendarView = (CompactCalendarView) v.findViewById(R.id.compactcalendar_view);
//        loadEvents(compactCalendarView);
//        compactCalendarView.invalidate();
//        logEventsByMonth(compactCalendarView);
//        // below line will display Sunday as the first day of the week
//        compactCalendarView.setShouldShowMondayAsFirstDay(false);

        recyclerAdapter = new RecyclerMonthViewAdapter(getActivity(),3);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }

//    public static ArrayList<CompactCalendarView> initMonthViews(View v){
////        CompactCalendarView pattern = R.layout.month_row.f R.id.compactcalendar_view;
////        ArrayList<CompactCalendarView> monthViews = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            CompactCalendarView view = pattern;
//            monthViews.add(view);
//        }
//
//        return monthViews;
//    }
    @Override
    public void onResume() {
        super.onResume();
//        toolbar.setTitle(dateFormatForMonth.format(new Date()));
//        compactCalendarView.setCurrentDate(new Date());
    }

    private void loadEvents(CompactCalendarView compactCalendarView) {
        addEvents(compactCalendarView, -1);
        addEvents(compactCalendarView, Calendar.DECEMBER);
        addEvents(compactCalendarView, Calendar.AUGUST);
    }

    private void logEventsByMonth(CompactCalendarView compactCalendarView) {
        Log.d(TAG, "Events for current month: " + compactCalendarView.getEventsForMonth(new Date()));
        currentCalender.setTime(new Date());
        currentCalender.set(Calendar.DAY_OF_MONTH, 1);
        currentCalender.set(Calendar.MONTH, Calendar.JANUARY);
        Log.d(TAG, "Events for Jan month: " + compactCalendarView.getEventsForMonth(currentCalender.getTime()));
    }

    private void addEvents(CompactCalendarView compactCalendarView, int month) {
        currentCalender.setTime(new Date());
        currentCalender.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = currentCalender.getTime();
        for (int i = 0; i < 6; i++) {
            currentCalender.setTime(firstDayOfMonth);
            if (month > -1) {
                currentCalender.set(Calendar.MONTH, month);
            }
            currentCalender.add(Calendar.DATE, i);
            setToMidnight(currentCalender);
            long timeInMillis = currentCalender.getTimeInMillis();

            List<Event> events = getEvents(timeInMillis, i);

            compactCalendarView.addEvents(events);
        }
    }

    private List<Event> getEvents(long timeInMillis, int day) {
        if (day < 2) {
            return Arrays.asList(new Event(Color.argb(255, 169, 68, 65), timeInMillis, "Event at " + new Date(timeInMillis)));
        } else if ( day > 2 && day <= 4) {
            return Arrays.asList(
                    new Event(Color.argb(255, 169, 68, 65), timeInMillis, "Event at " + new Date(timeInMillis)),
                    new Event(Color.argb(255, 100, 68, 65), timeInMillis, "Event 2 at " + new Date(timeInMillis)));
        } else {
            return Arrays.asList(
                    new Event(Color.argb(255, 169, 68, 65), timeInMillis, "Event at " + new Date(timeInMillis) ),
                    new Event(Color.argb(255, 100, 68, 65), timeInMillis, "Event 2 at " + new Date(timeInMillis)),
                    new Event(Color.argb(255, 70, 68, 65), timeInMillis, "Event 3 at " + new Date(timeInMillis)));
        }
    }

    private void setToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}