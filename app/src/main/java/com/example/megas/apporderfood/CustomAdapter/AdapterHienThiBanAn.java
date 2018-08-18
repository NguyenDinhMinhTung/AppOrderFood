package com.example.megas.apporderfood.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.megas.apporderfood.DTO.BanAnDTO;
import com.example.megas.apporderfood.R;

import java.util.List;

public class AdapterHienThiBanAn extends BaseAdapter implements View.OnClickListener {

    Context context;
    int layout;
    List<BanAnDTO> banAnDTOList;
    ViewHolderBanAn viewHolderBanAn;

    public AdapterHienThiBanAn(Context context, int layout, List<BanAnDTO> list) {
        this.context = context;
        this.layout = layout;
        this.banAnDTOList = list;
    }

    @Override
    public int getCount() {
        return banAnDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return banAnDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return banAnDTOList.get(position).getMaBan();
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();

        switch (id){
            case R.id.imBanAn:
                ((ViewHolderBanAn)((View)v.getParent()).getTag()).imGoiMon.setVisibility(View.VISIBLE);
                ((ViewHolderBanAn)((View)v.getParent()).getTag()).imAnButton.setVisibility(View.VISIBLE);
                ((ViewHolderBanAn)((View)v.getParent()).getTag()).imThanhToan.setVisibility(View.VISIBLE);
                int vitri= (int) v.getTag();
                banAnDTOList.get(vitri).setDuocChon(true);
                break;
        }
    }

    public class ViewHolderBanAn {
        ImageView imBanAn, imGoiMon, imThanhToan, imAnButton;
        TextView txtTenBanAn;

        public ViewHolderBanAn(View view) {
            imBanAn = view.findViewById(R.id.imBanAn);
            imGoiMon = view.findViewById(R.id.imGoiMon);
            imThanhToan = view.findViewById(R.id.imThanhToan);
            imAnButton = view.findViewById(R.id.imAnButton);
            txtTenBanAn = view.findViewById(R.id.txtTenBanAn);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_item_hienthibanan, parent, false);
            viewHolderBanAn = new ViewHolderBanAn(view);

            view.setTag(viewHolderBanAn);
        } else {
            viewHolderBanAn= (ViewHolderBanAn) view.getTag();
        }

        if (banAnDTOList.get(position).isDuocChon()){
            viewHolderBanAn.imGoiMon.setVisibility(View.VISIBLE);
            viewHolderBanAn.imThanhToan.setVisibility(View.VISIBLE);
            viewHolderBanAn.imAnButton.setVisibility(View.VISIBLE);
        }
        else{
            viewHolderBanAn.imGoiMon.setVisibility(View.INVISIBLE);
            viewHolderBanAn.imThanhToan.setVisibility(View.INVISIBLE);
            viewHolderBanAn.imAnButton.setVisibility(View.INVISIBLE);
        }
        viewHolderBanAn.imBanAn.setOnClickListener(this);
        BanAnDTO banAnDTO=banAnDTOList.get(position);
        viewHolderBanAn.txtTenBanAn.setText(banAnDTO.getTenBan());
        viewHolderBanAn.imBanAn.setTag(position);

        return view;
    }

    public void notifyDataSetChanged(List<BanAnDTO> list){
        this.banAnDTOList=list;
        this.notifyDataSetChanged();
    }
}
