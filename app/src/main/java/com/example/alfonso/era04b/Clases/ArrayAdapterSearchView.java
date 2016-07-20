package com.example.alfonso.era04b.Clases;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

/**
 * Created by Alfonso on 19/07/2016.
 * Ultima modificación: 20/07/2016
 */

// Extension de la clase searchview para utilizar el autocompletado

public class ArrayAdapterSearchView extends SearchView {

    //Declaramos la searchview del tipo autocomplete.

    private SearchView.SearchAutoComplete mSearchAutoComplete;

    //constructores

    public ArrayAdapterSearchView(Context context) {
        super(context);
        initialize();

    }

    public ArrayAdapterSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }


    public void initialize() {
        mSearchAutoComplete = (SearchView.SearchAutoComplete) findViewById(android.support.v7.appcompat.R.id.search_src_text);
        this.setAdapter(null);
        this.setOnItemClickListener(null);
    }

    @Override
    public void setSuggestionsAdapter(CursorAdapter adapter) {
        // don't let anyone touch this

    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mSearchAutoComplete.setOnItemClickListener(listener);
    }

    //Funcion para pasar la lista de los parametros que seran mostrados

    public void setAdapter(ArrayAdapter<?> adapter) {
        mSearchAutoComplete.setAdapter(adapter);
    }

    //Funcion para pasar el texto buscado al searchview

    public void setText(String text) {
        mSearchAutoComplete.setText(text);
    }
}