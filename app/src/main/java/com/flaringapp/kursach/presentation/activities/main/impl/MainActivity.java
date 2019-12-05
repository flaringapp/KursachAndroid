package com.flaringapp.kursach.presentation.activities.main.impl;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.flaringapp.kursach.R;
import com.flaringapp.kursach.app.di.Di;
import com.flaringapp.kursach.presentation.activities.main.IMainPresenter;
import com.flaringapp.kursach.presentation.activities.main.IMainView;
import com.flaringapp.kursach.presentation.activities.main.navigation.Screen;
import com.flaringapp.kursach.presentation.fragments.home.impl.HomeFragment;
import com.flaringapp.kursach.presentation.fragments.inputData.impl.InputDataFragment;
import com.flaringapp.kursach.presentation.fragments.tree.impl.TreeFragment;
import com.flaringapp.kursach.presentation.mvp.BaseActivity;

public class MainActivity extends BaseActivity<IMainPresenter> implements IMainView {

    @NonNull
    @Override
    protected IMainPresenter providePresenter() {
        return Di.inject(IMainPresenter.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onInitPresenter();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer, HomeFragment.newInstance())
                .commit();

        presenter.onStart();
    }

    @Override
    public void openScreen(Screen screen, Object... data) {
        Fragment fragment = null;

        switch (screen) {
            case HOME:
                fragment = HomeFragment.newInstance();
                break;
            case INPUT_DATA:
                fragment = InputDataFragment.newInstance();
                break;
            case TREE:
                fragment = TreeFragment.newInstance(
                        (String) data[0],
                        (String) data[1],
                        (String) data[2]
                );
                break;
        }

        openFragment(fragment);
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(
                        R.anim.screen_enter_anim,
                        R.anim.screen_exit_anim,
                        R.anim.screen_enter_pop_anim,
                        R.anim.screen_exit_pop_anim
                )
                .add(R.id.fragmentContainer, fragment)
                .commit();
    }
}
