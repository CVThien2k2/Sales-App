package com.example.appbanhang.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.appbanhang.fragment.HistoryFragment;
import com.example.appbanhang.fragment.Kid1Fragment;
import com.example.appbanhang.fragment.Kid2Fragment;
import com.example.appbanhang.fragment.ManFragment;
import com.example.appbanhang.fragment.WomenFragment;

public class ViewPagerAdapterHistory extends FragmentStateAdapter {
    public ViewPagerAdapterHistory(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ViewPagerAdapterHistory(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ViewPagerAdapterHistory(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: {
                HistoryFragment fragment = new HistoryFragment();
                // Truyền dữ liệu vào Fragment thông qua Bundle
                Bundle args = new Bundle();
                args.putString("data", "Chờ xác nhận");
                fragment.setArguments(args);

                return fragment;
            }
            case 1:
            {
                HistoryFragment fragment = new HistoryFragment();
                // Truyền dữ liệu vào Fragment thông qua Bundle
                Bundle args = new Bundle();
                args.putString("data", "Đang vận chuyển");
                fragment.setArguments(args);

                return fragment;
            }
            case 2:
            {
                HistoryFragment fragment = new HistoryFragment();
                // Truyền dữ liệu vào Fragment thông qua Bundle
                Bundle args = new Bundle();
                args.putString("data", "Đã hủy hàng");
                fragment.setArguments(args);

                return fragment;
            }
            case 3:
            {
                HistoryFragment fragment = new HistoryFragment();
                // Truyền dữ liệu vào Fragment thông qua Bundle
                Bundle args = new Bundle();
                args.putString("data", "Đã giao hàng");
                fragment.setArguments(args);

                return fragment;
            }
            default:
            {
                HistoryFragment fragment = new HistoryFragment();
                // Truyền dữ liệu vào Fragment thông qua Bundle
                Bundle args = new Bundle();
                args.putString("data", "Chờ xác nhận");
                fragment.setArguments(args);
                return fragment;
            }
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
    @Override
    public long getItemId(int position) {
        // Đảm bảo mỗi lần chuyển sang trang khác, Fragment sẽ được tạo lại từ đầu
        return super.getItemId(position);
    }


}
