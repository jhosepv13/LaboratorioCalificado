package pe.bazan.jhosep.com.laboratoriocalificado;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    private Spinner spinner1;
    private RadioGroup radioGroup;
    private TextView displayText;
    private CheckBox checkBox;
    private CheckBox checkBox2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        radioGroup =  (RadioGroup) findViewById(R.id.radioGroup);
        displayText = (TextView) findViewById(R.id.displayText);


        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Toast.makeText(parent.getContext(),
                        "Has seleccionado una pizza : " + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg) {

            }

        });


        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);

        if(checkBox.isChecked())
            Toast.makeText(this, "Primer complemento seleccionado!", Toast.LENGTH_LONG).show();

    }

    public void showvalue(View view){
        String item = (String)spinner1.getSelectedItem();
        Toast.makeText(this, "Has seleccionado una pizza : " + item, Toast.LENGTH_SHORT).show();
    }

    public void radioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        // This check which radio button was clicked
        switch (view.getId()) {
            case R.id.radioButton1:
                if (checked)
                    //Do something when radio button is clicked
                    Toast.makeText(getApplicationContext(), "Has seleccionado una pizza con Masa Gruesa", Toast.LENGTH_SHORT).show();
                break;

            case R.id.radioButton2:
                //Do something when radio button is clicked
                Toast.makeText(getApplicationContext(), "Has seleccionado una pizza con Masa Delgada", Toast.LENGTH_SHORT).show();
                break;

            case R.id.radioButton3:
                //Do something when radio button is clicked
                Toast.makeText(getApplicationContext(), "Has seleccionado una pizza con Masa Artesanal", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void answer(View view){
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        if (radioButtonId == -1){
            Toast.makeText(this, "This field is required!", Toast.LENGTH_LONG).show();
        }
        else{
            // Individual selected
            if (radioButtonId == R.id.radioButton1){
                Toast.makeText(this, "You selected RelativeLayout", Toast.LENGTH_SHORT).show();
            }else if (radioButtonId == R.id.radioButton2){
                Toast.makeText(this, "You selected LinearLayout", Toast.LENGTH_SHORT).show();
            }//...

            // Dinamic selected
            RadioButton radioButton = (RadioButton) findViewById(radioButtonId);
            Toast.makeText(this, "This text is " + radioButton.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    public void androidCheckBoxClicked(View view) {
        // action for checkbox click
        switch (view.getId()) {
            case R.id.checkBox:
                CheckBox checkBox = (CheckBox) view;
                if(checkBox.isChecked())
                    Toast.makeText(this, checkBox.getText() + " seleccionado!", Toast.LENGTH_LONG).show();
                break;
            case R.id.checkBox2:
                if(checkBox2.isChecked())
                    Toast.makeText(this, checkBox2.getText() + " seleccionado!", Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void showDialog(View view){

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Confirmación de Pedido");
        alertDialog.setMessage("Su pedido esta siendo procesado");
        // Alert dialog button
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Alert dialog action goes here
                        dialog.dismiss();// use dismiss to cancel alert dialog
                    }
                });
        alertDialog.show();

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        // Notification
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Pizza Hut")
                .setContentText("Su pedido está en camino")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        // Notification manager
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);

         }

    }



