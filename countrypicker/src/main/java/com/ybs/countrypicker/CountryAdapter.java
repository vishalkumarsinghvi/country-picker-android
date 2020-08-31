package com.ybs.countrypicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ybs.countrypicker.R.id;
import com.ybs.countrypicker.R.layout;

import java.util.List;

/**
 * Created by mispc1 on 8/29/17.
 */

public class CountryAdapter extends BaseAdapter {

    private Context mContext;
    List<Country> countries;
    LayoutInflater inflater;
    static String countryCodeSelected = "";

    public CountryAdapter(Context context, List<Country> countries) {
        this.mContext = context;
        this.countries = countries;
        this.inflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return this.countries.size();
    }

    public Object getItem(int arg0) {
        return null;
    }

    public long getItemId(int arg0) {
        return 0L;
    }

    public View getView(int position, View view, ViewGroup parent) {
        Country country = (Country)this.countries.get(position);
        if(view == null) {
            view = this.inflater.inflate(layout.row, (ViewGroup)null);
        }

        CountryAdapter.Cell cell = CountryAdapter.Cell.from(view);
        cell.textView.setText(country.getName());
        country.loadFlagByCode(this.mContext);
        if(country.getFlag() != -1) {
            cell.imageView.setImageResource(country.getFlag());
        }
        if (!countryCodeSelected.isEmpty() && country.getCode().equals(countryCodeSelected)) {
            cell.selectIcon.setVisibility(View.VISIBLE);
        } else {
            cell.selectIcon.setVisibility(View.GONE);
        }

        return view;
    }
    public static void setCountryCodeSelected(String codeSelected){
        countryCodeSelected=codeSelected;
    }

    static class Cell {
        public TextView textView;
        public ImageView imageView,selectIcon;

        Cell() {
        }

        static CountryAdapter.Cell from(View view) {
            if(view == null) {
                return null;
            } else if(view.getTag() == null) {
                CountryAdapter.Cell cell = new CountryAdapter.Cell();
                cell.textView = (TextView)view.findViewById(id.row_title);
                cell.imageView = (ImageView)view.findViewById(id.row_icon);
                cell.selectIcon = (ImageView)view.findViewById(id.select_icon);
                view.setTag(cell);
                return cell;
            } else {
                return (CountryAdapter.Cell)view.getTag();
            }
        }
    }

}
