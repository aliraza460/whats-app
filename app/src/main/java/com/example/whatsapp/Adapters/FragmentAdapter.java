package com.example.whatsapp.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.whatsapp.Fragments.Call;
import com.example.whatsapp.Fragments.Chat;
import com.example.whatsapp.Fragments.Status;

public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new Chat();
            case 1:
                return new Status();
            case 2:
                return new Call();
            default:
                return new Chat();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        String title = null;

        if (position == 0)
        {
            title = "CHATS";
        }
        if (position == 1)
        {
            title = "STATUS";
        }
        if (position == 2)
        {
            title = "CALLS";
        }

        return title;
    }
}
