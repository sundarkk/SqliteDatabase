package sundarchaupal.bonjaa.nescant.sqlitedatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase sd;
    EditText name;
    Button view, save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        view=findViewById(R.id.view);
        save=findViewById(R.id.save);
        //to create or open the sqlitedatabase
        sd=openOrCreateDatabase("dbname", 0, null);
        //to create new table if table not exists
        sd.execSQL("create table if not exists tname (name varchar(250))");
        //to insert the data into table while click on the save button
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, "Fill the value", Toast.LENGTH_SHORT).show();
                }
                else {
                    sd.execSQL("insert into tname values('"+name.getText().toString()+"')");
                    //to clear the form
                    name.setText("");
                }
            }
        });
        //to fetch the data from table using cursor class
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c=sd.rawQuery("select * from tname", null);
                c.moveToLast();
                Toast.makeText(MainActivity.this, c.getString(0), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
