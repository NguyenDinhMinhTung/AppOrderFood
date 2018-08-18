package com.example.megas.apporderfood.FragmentApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.megas.apporderfood.CustomAdapter.AdapterHienThiBanAn;
import com.example.megas.apporderfood.DAO.BanAnDAO;
import com.example.megas.apporderfood.DTO.BanAnDTO;
import com.example.megas.apporderfood.R;
import com.example.megas.apporderfood.ThemBanAnActivity;

import java.util.List;

public class HienThiBanAnFragment extends Fragment {

    public static final int REQUEST_CODE_THEM = 111;
    GridView gvHienThiBanAn;
    List<BanAnDTO> banAnDTOList;
    BanAnDAO banAnDAO;
    AdapterHienThiBanAn adapterHienThiBanAn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthibanan, container, false);
        setHasOptionsMenu(true);

        gvHienThiBanAn = view.findViewById(R.id.gvHienThiBanAn);

        banAnDAO=new BanAnDAO(getActivity());
        banAnDTOList=banAnDAO.layTatCaBanAn();

        adapterHienThiBanAn=new AdapterHienThiBanAn(getContext(),R.layout.layout_item_hienthibanan,banAnDTOList);
        gvHienThiBanAn.setAdapter(adapterHienThiBanAn);
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.itThemBanAn:
                Intent intent = new Intent(getActivity(), ThemBanAnActivity.class);
                startActivityForResult(intent, REQUEST_CODE_THEM);
                break;
        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itemThemBanAn = menu.add(1, R.id.itThemBanAn, 1, R.string.thembanan);
        itemThemBanAn.setIcon(R.drawable.add);
        itemThemBanAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_THEM && resultCode == Activity.RESULT_OK) {
            boolean kiemtra = data.getBooleanExtra("ketquathem", false);
            if (kiemtra) {
                banAnDTOList=banAnDAO.layTatCaBanAn();
                adapterHienThiBanAn.notifyDataSetChanged(banAnDTOList);

                Toast.makeText(getActivity(), getResources().getString(R.string.themthanhcong), Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getActivity(), getResources().getString(R.string.themthatbai), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
