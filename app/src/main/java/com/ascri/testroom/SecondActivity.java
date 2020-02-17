package com.ascri.testroom;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private int rowNum = 3;
    private int colNum = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        EditText[][] editTexts = new EditText[rowNum][colNum];
        GridLayout gridLayout = findViewById(R.id.grid);
        //define how many rows and columns to be used in the layout
        gridLayout.setRowCount(rowNum);
        gridLayout.setColumnCount(colNum);
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                editTexts[i][j] = new EditText(this);
                setPos(editTexts[i][j], i, j);
                gridLayout.addView(editTexts[i][j]);
            }
        }
    }

    //putting the edit text according to row and column index
    private void setPos(EditText editText, int row, int column) {
        GridLayout.LayoutParams param =new GridLayout.LayoutParams();
        param.width = 100;
        param.height = 100;
        param.setGravity(Gravity.CENTER);
        param.rowSpec = GridLayout.spec(row);
        param.columnSpec = GridLayout.spec(column);
        editText.setLayoutParams(param);
    }
}
