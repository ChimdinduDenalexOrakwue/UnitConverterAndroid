package com.example.denalex.unitconverter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private Toolbar toolbar;
    private AppBarLayout layout;
    private static boolean nightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextAppearance(this, R.style.MyTitleTextApperance);
        nightMode = Settings.night;


        String fontPath = "fonts/LobsterTwo-Regular.ttf";
        TextView font = (TextView) findViewById(R.id.toolbar_title);
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        font.setTypeface(tf);

        layout = (AppBarLayout) findViewById(R.id.appbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);



        final int pos = tabLayout.getSelectedTabPosition();
        setColor(pos, layout);

        int[] imageResId = {
                R.drawable.ic_action_name,
                R.drawable.weight,
                R.drawable.beaker,
                R.drawable.box,
                R.drawable.thermo,
                R.drawable.martini
        };

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setIcon(imageResId[i]);
        }


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                final int pos = tab.getPosition();
                setColor(pos, layout);

                mViewPager.setCurrentItem(pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                                                       }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



}
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void setColor(int pos, AppBarLayout layout){
        if(nightMode){
            layout.setBackgroundColor(Color.rgb(0,0,0));
        }else{
        if(pos == 0){
            layout.setBackgroundColor(Color.rgb(51,153,255));
        }else if(pos == 1){
            layout.setBackgroundColor(Color.rgb(0,204,102));
        }else if(pos == 2){
            layout.setBackgroundColor(Color.rgb(255,153,255));
        }else if(pos == 3){
            layout.setBackgroundColor(Color.rgb(255, 0, 0));
        }else if(pos == 4){
            layout.setBackgroundColor(Color.rgb(153,51,204));
        }else if(pos == 5){
            layout.setBackgroundColor(Color.rgb(255,102,51));
        }
      }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), Settings.class);
            intent.putExtra("on", nightMode);

            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "LENGTH";
                case 1:
                    return "MASS";
                case 2:
                    return "VOLUME";
                case 3:
                    return "AREA";
                case 4:
                    return "TEMPERATURE";
                case 5:
                    return "COOKING";
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }




        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format));
            final int tab = getArguments().getInt(ARG_SECTION_NUMBER);
            if(tab == 1) {
                if(!nightMode)
                rootView.getRootView().setBackgroundColor(Color.rgb(51,204,255));

                Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
                Spinner spinnerTwo = (Spinner) rootView.findViewById(R.id.spinner2);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                        R.array.length_one, R.layout.spinner_layout);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinnerTwo.setAdapter(adapter);
            } else if(tab == 2){
                if(!nightMode)
                rootView.getRootView().setBackgroundColor(Color.rgb(0,234,0));

                Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
                Spinner spinnerTwo = (Spinner) rootView.findViewById(R.id.spinner2);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                        R.array.mass_one, R.layout.spinner_layout);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinnerTwo.setAdapter(adapter);

            }else if(tab == 3){
                if(!nightMode)
                rootView.getRootView().setBackgroundColor(Color.rgb(255,204,255));

                Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
                Spinner spinnerTwo = (Spinner) rootView.findViewById(R.id.spinner2);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                        R.array.volume_one, R.layout.spinner_layout);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinnerTwo.setAdapter(adapter);
             }else if (tab == 4){
                if(!nightMode)
                rootView.getRootView().setBackgroundColor(Color.rgb(255,153,153));

                Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
                Spinner spinnerTwo = (Spinner) rootView.findViewById(R.id.spinner2);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                        R.array.area_one, R.layout.spinner_layout);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinnerTwo.setAdapter(adapter);
             }else if (tab == 5){
                if(!nightMode)
                rootView.getRootView().setBackgroundColor(Color.rgb(204,153,255));

                Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
                Spinner spinnerTwo = (Spinner) rootView.findViewById(R.id.spinner2);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                        R.array.temp_one, R.layout.spinner_layout);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinnerTwo.setAdapter(adapter);
            }else if (tab == 6){
                if(!nightMode)
                rootView.getRootView().setBackgroundColor(Color.rgb(255,153,102));

                Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
                Spinner spinnerTwo = (Spinner) rootView.findViewById(R.id.spinner2);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                        R.array.cooking_one, R.layout.spinner_layout);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinnerTwo.setAdapter(adapter);
            }

            Button calculate = (Button) rootView.findViewById(R.id.button);
            calculate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText valueOne = (EditText) rootView.findViewById(R.id.editText);
                    TextView valueTwo = (TextView) rootView.findViewById(R.id.editText2);
                    Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
                    Spinner spinnerTwo = (Spinner) rootView.findViewById(R.id.spinner2);
                    String check = valueOne.getText().toString();
                    if(!check.isEmpty()) {
                        double first = Double.valueOf(valueOne.getText().toString());
                        String unitOne = spinner.getSelectedItem().toString();
                        String unitTwo = spinnerTwo.getSelectedItem().toString();
                        String end = "";
                        if (tab == 1) {
                            end = "" + convertLength(first, unitOne, unitTwo);
                        }else if(tab == 2){
                            end = "" + convertMass(first, unitOne, unitTwo);
                        }else if (tab == 3){
                            end = "" + convertVolume(first, unitOne, unitTwo);
                        }else if (tab == 4){
                            end = "" + convertArea(first, unitOne, unitTwo);
                        }else if (tab == 5){
                            end = "" + convertTemperature(first, unitOne, unitTwo);
                        }else if (tab == 6){
                            end = "" + convertCooking(first, unitOne, unitTwo);
                        }
                        //String end = "" + convert(first, unitOne, unitTwo);

                        MessageFormat format = new MessageFormat(
                                "{0,choice,0#{0,number,'#,##0.####'}|99999<{0,number,'000000.####E0'}}",
                                Locale.ENGLISH);

                        valueTwo.setText(String.valueOf(format.format(new Object[]{Double.valueOf(end)})));
                    }else {
                        Toast.makeText(getContext(), "ENTER A VALUE", Toast.LENGTH_SHORT).show();

                    }
                }
            });
            return rootView;
        }


        //

        public double convertLength(double valueOne, String unitOne, String unitTwo){
            double conversionFactor = 1.0;
            if(unitOne.equals(unitTwo)){
                conversionFactor = 1.0;
            }else if (unitOne.equals("in")){
                switch (unitTwo){
                    case "ft":
                        conversionFactor = 0.0833333;
                        break;
                    case "kilometer":
                        conversionFactor = 2.54 * (1 / Math.pow(10, 5));
                        break;
                    case "mile":
                        conversionFactor = 1.57828 * (1 / Math.pow(10, 5));
                        break;
                    case "meter":
                        conversionFactor = 0.0254;
                        break;
                    case "centimeter":
                        conversionFactor = 2.54;
                }
            }else if (unitOne.equals("ft")){
                switch (unitTwo){
                    case "in":
                        conversionFactor = 12;
                        break;
                    case "kilometer":
                        conversionFactor = .0003048;
                        break;
                    case "mile":
                        conversionFactor = .0001893939;
                        break;
                    case "meter":
                        conversionFactor = 0.3048;
                        break;
                    case "centimeter":
                        conversionFactor = 30.48;
                }
            }else if (unitOne.equals("kilometer")){
                switch (unitTwo){
                    case "in":
                        conversionFactor = 39370.078740157;
                        break;
                    case "ft":
                        conversionFactor = 3280.8398950131;
                        break;
                    case "mile":
                        conversionFactor = .6213711922;
                        break;
                    case "meter":
                        conversionFactor = 1000;
                        break;
                    case "centimeter":
                        conversionFactor = 100000;
                }
            }else if (unitOne.equals("mile")){
                switch (unitTwo){
                    case "in":
                        conversionFactor = 63360;
                        break;
                    case "ft":
                        conversionFactor = 5280;
                        break;
                    case "kilometer":
                        conversionFactor = 1.609344;
                        break;
                    case "meter":
                        conversionFactor = 1609.344;
                        break;
                    case "centimeter":
                        conversionFactor = 160934.4;
                }
            }else if (unitOne.equals("meter")){
                switch (unitTwo){
                    case "in":
                        conversionFactor = 39.3700787402;
                        break;
                    case "ft":
                        conversionFactor = 3.280839895;
                        break;
                    case "kilometer":
                        conversionFactor = 0.001;
                        break;
                    case "mile":
                        conversionFactor = 0.0006213712;
                        break;
                    case "centimeter":
                        conversionFactor = 100;
                }
            }else if (unitOne.equals("centimeter")){
                switch (unitTwo){
                    case "in":
                        conversionFactor = 0.393700787402;
                        break;
                    case "ft":
                        conversionFactor = 0.03280839895;
                        break;
                    case "kilometer":
                        conversionFactor = 1 * (1 / Math.pow(10, 5));
                        break;
                    case "mile":
                        conversionFactor = 6.2137 * (1 /Math.pow(10, 6)) ;
                        break;
                    case "meter":
                        conversionFactor = 100;
                }
            }

            return valueOne * conversionFactor;
        }


        public double convertMass(double valueOne, String unitOne, String unitTwo){
            double conversionFactor = 1.0;
            if(unitOne.equals(unitTwo)){
                conversionFactor = 1.0;
            }else if (unitOne.equals("kg")){
                switch (unitTwo){
                    case "lb":
                        conversionFactor = 2.2046226218;
                        break;
                    case "g":
                        conversionFactor = 1000;
                        break;
                    case "ounce":
                        conversionFactor = 35.2739619496;
                }
            }else if (unitOne.equals("lb")){
                switch (unitTwo){
                    case "kg":
                        conversionFactor = 0.45359237;
                        break;
                    case "g":
                        conversionFactor = 453.59237;
                        break;
                    case "ounce":
                        conversionFactor = 16;
                }
            }else if (unitOne.equals("g")){
                switch (unitTwo){
                    case "kg":
                        conversionFactor = 0.001;
                        break;
                    case "lb":
                        conversionFactor = 0.0022046226;
                        break;
                    case "ounce":
                        conversionFactor = 0.0352739619;
                }
            }else if (unitOne.equals("ounce")){
                switch (unitTwo){
                    case "kg":
                        conversionFactor = 0.0283495231;
                        break;
                    case "lb":
                        conversionFactor = 0.0625;
                        break;
                    case "g":
                        conversionFactor = 28.3495231125;
                }
            }

            return valueOne * conversionFactor;
        }


        public double convertVolume(double valueOne, String unitOne, String unitTwo){double conversionFactor = 1.0;
            if(unitOne.equals(unitTwo)){
                conversionFactor = 1.0;
            }else if (unitOne.equals("liter")){
                switch (unitTwo){
                    case "gallon":
                        conversionFactor = 0.264172052358;
                        break;
                    case "milli":
                        conversionFactor = 1000;
                        break;
                    case "pint":
                        conversionFactor = 1.816165968538;
                        break;
                    case "cup":
                        conversionFactor = 4.22675283773;
                        break;
                }
            }else if (unitOne.equals("gallon")){
                switch (unitTwo){
                    case "liter":
                        conversionFactor = 3.785411784;
                        break;
                    case "milli":
                        conversionFactor = 3785.411784;
                        break;
                    case "pint":
                        conversionFactor = 8;
                        break;
                    case "cup":
                        conversionFactor = 16;
                        break;
                }
            }else if (unitOne.equals("milli")){
                switch (unitTwo){
                    case "liter":
                        conversionFactor = 0.001;
                        break;
                    case "gallon":
                        conversionFactor = 0.000264172052;
                        break;
                    case "pint":
                        conversionFactor = 0.001816165969;
                        break;
                    case "cup":
                        conversionFactor = 0.004226752838;
                        break;
                }
            }else if (unitOne.equals("pint")){
                switch (unitTwo){
                    case "liter":
                        conversionFactor = 0.5506104713575;
                        break;
                    case "gallon":
                        conversionFactor = 0.145455898268;
                        break;
                    case "milli":
                        conversionFactor = 550610.4713575;
                        break;
                    case "cup":
                        conversionFactor = 2.327294372294;
                        break;
                }
            }else if (unitOne.equals("cup")){
                switch (unitTwo){
                    case "liter":
                        conversionFactor = 0.2365882365;
                        break;
                    case "gallon":
                        conversionFactor = 0.0625;
                        break;
                    case "milli":
                        conversionFactor = 236.5882365;
                        break;
                    case "pint":
                        conversionFactor = 0.429683503688;
                        break;
                }
            }

            return valueOne * conversionFactor;
        }




        public double convertArea(double valueOne, String unitOne, String unitTwo){
            double conversionFactor = 1.0;
            if(unitOne.equals(unitTwo)){
                conversionFactor = 1.0;
            }else if(unitOne.equals("square centimeters")){
                switch (unitTwo){
                    case "square meters":
                        conversionFactor = 0.0001;
                        break;
                    case "square inches":
                        conversionFactor = 0.155000310001;
                        break;
                    case "square miles":
                        conversionFactor = 3.9 * (1/(Math.pow(10, 11)));
                        break;
                    case "square kilometers":
                        conversionFactor = 1 * (1/(Math.pow(10, 10)));
                        break;
                    case "square feet":
                        conversionFactor = 0.001076391042;
                        break;
                    case "square yards":
                        conversionFactor = 0.000119599005;
                        break;
                }
            }else if(unitOne.equals("square meters")){
                switch (unitTwo){
                    case "square centimeters":
                        conversionFactor = 10000;
                        break;
                    case "square inches":
                        conversionFactor = 1550.0031000062;
                        break;
                    case "square miles":
                        conversionFactor = 3.86102 * (1/(Math.pow(10, 7)));
                        break;
                    case "square kilometers":
                        conversionFactor = 1 * (1/(Math.pow(10, 6)));
                        break;
                    case "square feet":
                        conversionFactor = 10.76391041671;
                        break;
                    case "square yards":
                        conversionFactor = 1.195990046301;
                        break;
                }
            }else if(unitOne.equals("square inches")){
                switch (unitTwo){
                    case "square centimeters":
                        conversionFactor = 6.4516;
                        break;
                    case "square meters":
                        conversionFactor = 0.00064516;
                        break;
                    case "square miles":
                        conversionFactor = 2.49 * (1/(Math.pow(10, 10)));
                        break;
                    case "square kilometers":
                        conversionFactor = 6.45 * (1/(Math.pow(10, 10)));
                        break;
                    case "square feet":
                        conversionFactor = 0.006944444444;
                        break;
                    case "square yards":
                        conversionFactor = 0.000771604938;
                        break;
                }
            }else if(unitOne.equals("square miles")){
                switch (unitTwo){
                    case "square centimeters":
                        conversionFactor = 25899881103.36;
                        break;
                    case "square meters":
                        conversionFactor = 2589988.110336;
                        break;
                    case "square inches":
                        conversionFactor = 4014489600.0;
                        break;
                    case "square kilometers":
                        conversionFactor = 2.589988110336;
                        break;
                    case "square feet":
                        conversionFactor = 27878400;
                        break;
                    case "square yards":
                        conversionFactor = 3097600;
                        break;
                }
            }else if(unitOne.equals("square kilometers")){
                switch (unitTwo){
                    case "square centimeters":
                        conversionFactor = 10000000000.0;
                        break;
                    case "square meters":
                        conversionFactor = 1000000;
                        break;
                    case "square inches":
                        conversionFactor = 1550003100.0062;
                        break;
                    case "square miles":
                        conversionFactor = 0.386102158542;
                        break;
                    case "square feet":
                        conversionFactor = 10763910.41671;
                        break;
                    case "square yards":
                        conversionFactor = 1195990.0463011;
                        break;
                }
            }else if(unitOne.equals("square feet")){
                switch (unitTwo){
                    case "square centimeters":
                        conversionFactor = 929.0304;
                        break;
                    case "square meters":
                        conversionFactor = 0.09290304;
                        break;
                    case "square inches":
                        conversionFactor = 144;
                        break;
                    case "square miles":
                        conversionFactor = 3.587 * (1/(Math.pow(10, 8)));
                        break;
                    case "square kilometers":
                        conversionFactor = 9.2903 * (1/(Math.pow(10, 8)));
                        break;
                    case "square yards":
                        conversionFactor = 0.111111111111;
                        break;
                }
            }else if(unitOne.equals("square yards")){
                switch (unitTwo){
                    case "square centimeters":
                        conversionFactor = 8361.2736;
                        break;
                    case "square meters":
                        conversionFactor = 0.83612736;
                        break;
                    case "square inches":
                        conversionFactor = 1296;
                        break;
                    case "square miles":
                        conversionFactor = 3.22831 * (1/(Math.pow(10, 7)));
                        break;
                    case "square kilometers":
                        conversionFactor = 8.3612736 * (1/(Math.pow(10, 8)));
                        break;
                    case "square feet":
                        conversionFactor = 9;
                        break;
                }
            }

            return valueOne * conversionFactor;
        }


        public double convertTemperature(double valueOne, String unitOne, String unitTwo){
            double conversionFactor = 1.0;
            if(unitOne.equals(unitTwo)){
                conversionFactor = valueOne;
            }else if(unitOne.equals("Celsius")){
                switch (unitTwo){
                    case "Fahrenheit":
                        conversionFactor = valueOne * 1.8 + 32;
                        break;
                    case "Kelvin":
                        conversionFactor = valueOne + 273.15;
                        break;

                }
            }else if(unitOne.equals("Fahrenheit")){
                switch (unitTwo){
                    case "Celsius":
                        conversionFactor = (valueOne - 32)/1.8;
                        break;
                    case "Kelvin":
                        conversionFactor = (valueOne + 459.67)/1.8;
                        break;
                }
            }else if(unitOne.equals("Kelvin")){
                switch (unitTwo){
                    case "Celsius":
                        conversionFactor = valueOne - 273.15;
                        break;
                    case "Fahrenheit":
                        conversionFactor = valueOne * (9/5) - 459.67;
                        break;
                }
            }

            return conversionFactor;
        }


        public double convertCooking(double valueOne, String unitOne, String unitTwo){
            double conversionFactor = 1.0;
            if(unitOne.equals(unitTwo)){
                conversionFactor = 1.0;
            }else if(unitOne.equals("tablespoon")){
                switch (unitTwo){
                    case "teaspoon":
                        conversionFactor = 3;
                        break;
                }
            }else if(unitOne.equals("teaspoon")){
                switch (unitTwo){
                    case "tablespoon":
                        conversionFactor = 0.333333;
                        break;
                }
            }
            return valueOne * conversionFactor;
        }


    }
}
