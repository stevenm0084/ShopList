package com.example.pc1.shoplist;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity{

    private Button addItem;
    private ListView mainList;
    private ArrayAdapter<Item> FoodItemsArrayAdapter;
    private ArrayAdapter<CharSequence> adapterItemLocations;
    private ArrayList<Item> foodItems;
    private Context context;
    private RadioButton radioButton;
    private Spinner spinnerItems;
    private LayoutInflater li;
    private View promptsView;
    private Items items;
    private Category category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup Varibles
        context = this;
        setUpSecondLayout();
        setVariables();
        addListenerOnSpinnerItemSelection();

    }

    public void addListenerOnSpinnerItemSelection(){
        spinnerItems.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void setUpSecondLayout(){
        li = LayoutInflater.from(context);
        promptsView = li.inflate(R.layout.layout_additem, null);

    }

    public void setVariables(){
        items = new Items();

        context = this;
        addItem = (Button) findViewById(R.id.btn_add_item);
        mainList = (ListView) findViewById(R.id.main_view);
        foodItems = new ArrayList<Item>();
        Log.i("Fooditems: ", foodItems.toString());

        spinnerItems = (Spinner) promptsView.findViewById(R.id.spinner_item_locations);

        //adapterItemLocations = ArrayAdapter.createFromResource(context, R.array.item_locations, R.layout.support_simple_spinner_dropdown_item);

        //adapterItemLocations.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinnerItems.setAdapter(adapterItemLocations);

        FoodItemsArrayAdapter =  new ArrayAdapter<Item>(context, android.R.layout.simple_list_item_checked, foodItems);    //no need to add because using android:entries in layout_additem*/


        mainList.setAdapter(FoodItemsArrayAdapter);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**/
                LayoutInflater li = LayoutInflater.from(context);
                promptsView = li.inflate(R.layout.layout_additem, null);

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                final int categoryPosition = spinnerItems.getSelectedItemPosition();

                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);


                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // PRODUCE, FROZEN, MEAT, CANNED, MEXICAN_SPICE, BREAKFAST, DRINKS, OTHER, COMPLETED

                                        switch (categoryPosition) {
                                            case 0:
                                                category = Category.PRODUCE;
                                                break;

                                            case 1:
                                                category = Category.FROZEN;
                                                break;

                                            case 2:
                                                category = Category.MEAT;
                                                break;

                                            case 3:
                                                category = Category.MEXICAN_SPICE;
                                                break;

                                            case 4:
                                                category = Category.BREAKFAST;
                                                break;

                                            case 5:
                                                category = Category.DRINKS;
                                                break;

                                            case 6:
                                                category = Category.OTHER;
                                                break;

                                            case 7:
                                                category = Category.COMPLETED;
                                                break;
                                        }

                                        Log.i("Food Items Array: ", foodItems.toString());

                                        foodItems.add(new Item(userInput.getText().toString(), Category.OTHER));

                                        FoodItemsArrayAdapter.notifyDataSetChanged();
                                        }
                                    }

                                    )
                                            .

                                    setNegativeButton("Cancel",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            }

                                    );

                                    // create alert dialog
                                    AlertDialog alertDialog = alertDialogBuilder.create();

                                    // show it
                                    alertDialog.show();

                                }
            });
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
