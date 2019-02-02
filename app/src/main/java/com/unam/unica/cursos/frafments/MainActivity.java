package com.unam.unica.cursos.frafments;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.unam.unica.cursos.frafments.Fragments.DosFragment;
import com.unam.unica.cursos.frafments.Fragments.TresFragment;
import com.unam.unica.cursos.frafments.Fragments.UnoFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TresFragment tresfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        setUpViewPager();

        tabLayout.getTabAt(0).setIcon(R.drawable.input_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.output_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.list_24dp);
    }

    private ArrayList<Fragment> AgregarFragment(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        tresfragment = new TresFragment();
        fragments.add(new UnoFragment());
        fragments.add(new DosFragment());
        fragments.add(tresfragment);
        return fragments;
    }
    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), AgregarFragment()));
        tabLayout.setupWithViewPager(viewPager);
    }
}
