package com.example.yiyisman.hotel;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class tabs extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_my);  //se saca porque sino se solapan

        ActionBar actionBar = getSupportActionBar();


        /**INDICAR TITULO Y SUBTITULO**/
        actionBar.setTitle("Manejo de Empleados");


        /**MODO TABS EN ACTIONBAR**/
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        /**CREAR TABS**/
        ActionBar.Tab tab = actionBar.newTab()
                .setText("Empleados")
                .setTabListener(new TabsListener(
                        this, "Empleados", Empleados.class));
        actionBar.addTab(tab);

        tab = actionBar.newTab()
                .setText("Hotel")
                .setTabListener(new TabsListener(
                        this, "Hotel", TiposHotel.class));
        actionBar.addTab(tab);

        tab = actionBar.newTab()
                .setText("Tipo E")
                .setTabListener(new TabsListener(
                        this, "Tipo E", MyActivity.class));
        actionBar.addTab(tab);


    }

    public class TabsListener  implements ActionBar.TabListener {

        private Fragment fragment;
        private final String tag;

        public TabsListener(Activity activity, String tag, Class cls) {
            this.tag = tag;
            fragment = Fragment.instantiate(activity, cls.getName());
        }

        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.replace(android.R.id.content, fragment, tag);
        }

        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.remove(fragment);
        }

        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {}
    }
}