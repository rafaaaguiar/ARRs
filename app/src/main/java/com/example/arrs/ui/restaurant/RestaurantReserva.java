package com.example.arrs.ui.restaurant;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.arrs.R;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class RestaurantReserva extends AppCompatActivity {

    private EditText editTextCustomerName;
    private DatePicker datePickerReservation;
    private TimePicker timePickerReservation;
    private EditText editTextNumberOfGuests;
    private Button btnConfirmReservation;
    private static final String RESERVATIONS_PREF = "reservations_pref";
    private static final String RESERVATIONS_KEY = "reservations_key";

    private void saveReservation(String reservationDetails) {
        SharedPreferences sharedPreferences = getSharedPreferences(RESERVATIONS_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Set<String> reservationSet = sharedPreferences.getStringSet(RESERVATIONS_KEY, new HashSet<>());
        reservationSet.add(reservationDetails);

        editor.putStringSet(RESERVATIONS_KEY, reservationSet);
        editor.apply();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserva_restaurante);

        // Definindo o locale
        Locale locale = new Locale("pt", "BR");
        Locale.setDefault(locale);
        Configuration config = getResources().getConfiguration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Inicializar os componentes da interface
        editTextCustomerName = findViewById(R.id.editTextCustomerName);
        datePickerReservation = findViewById(R.id.datePickerReservation);
        timePickerReservation = findViewById(R.id.timePickerReservation);
        editTextNumberOfGuests = findViewById(R.id.editTextNumberOfGuests);
        btnConfirmReservation = findViewById(R.id.btnConfirmReservation);

        timePickerReservation.setIs24HourView(true);

        // Definir o listener do botão de confirmação
        btnConfirmReservation.setOnClickListener(v -> {
            try {
                String customerName = editTextCustomerName.getText().toString();
                int day = datePickerReservation.getDayOfMonth();
                int month = datePickerReservation.getMonth() + 1; // Meses começam do 0
                int year = datePickerReservation.getYear();
                int hour = timePickerReservation.getHour(); // use getHour() para API 23+
                int minute = timePickerReservation.getMinute(); // use getMinute() para API 23+
                String numberOfGuests = editTextNumberOfGuests.getText().toString();

                String confirmationMessage = "Reserva confirmada para " + customerName +
                        " no dia " + day + "/" + month + "/" + year +
                        " às " + hour + ":" + String.format("%02d", minute) +
                        " para " + numberOfGuests + " pessoas no restaurante." ;
                Toast.makeText(RestaurantReserva.this, confirmationMessage, Toast.LENGTH_LONG).show();

                saveReservation(confirmationMessage);
            } catch (Exception e) {
                Log.e("RestaurantReserva", "Erro ao confirmar reserva", e);
                Toast.makeText(RestaurantReserva.this, "Erro ao confirmar reserva: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}



/**package com.example.arrs.ui.restaurant;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.arrs.R;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class RestaurantReserva extends AppCompatActivity {

    private EditText editTextCustomerName;
    private DatePicker datePickerReservation;
    private TimePicker timePickerReservation;
    private EditText editTextNumberOfGuests;
    private Button btnConfirmReservation;
    private static final String RESERVATIONS_PREF = "reservations_pref";
    private static final String RESERVATIONS_KEY = "reservations_key";

    private void saveReservation(String reservationDetails) {
    SharedPreferences sharedPreferences = getSharedPreferences(RESERVATIONS_PREF, MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();

    // Adicione a reserva à lista de reservas
    Set<String> reservationSet = sharedPreferences.getStringSet(RESERVATIONS_KEY, new HashSet<>());

    reservationSet.add(reservationDetails);

    editor.putStringSet(RESERVATIONS_KEY,reservationSet);
    editor.apply();
}

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserva_restaurante);

        Locale locale = new Locale("pt", "BR");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Inicializar os componentes da interface
        editTextCustomerName = findViewById(R.id.editTextCustomerName);
        datePickerReservation = findViewById(R.id.datePickerReservation);
        timePickerReservation = findViewById(R.id.timePickerReservation);
        editTextNumberOfGuests = findViewById(R.id.editTextNumberOfGuests);
        btnConfirmReservation = findViewById(R.id.btnConfirmReservation);

        timePickerReservation.setIs24HourView(true);

        // Definir o listener do botão de confirmação
        btnConfirmReservation.setOnClickListener(v -> {
            // Obter os dados inseridos
            String customerName = editTextCustomerName.getText().toString();
            int day = datePickerReservation.getDayOfMonth();
            int month = datePickerReservation.getMonth() + 1; // Meses começam do 0
            int year = datePickerReservation.getYear();
            int hour = timePickerReservation.getCurrentHour();
            int minute = timePickerReservation.getCurrentMinute();
            String numberOfGuests = editTextNumberOfGuests.getText().toString();

            // Exibir uma mensagem de confirmação
            String confirmationMessage = "Reserva confirmada para " + customerName +
                    " no dia " + day + "/" + month + "/" + year +
                    " às " + hour + ":" + String.format("%02d", minute) +
                    " para " + numberOfGuests + " pessoas.";
            Toast.makeText(RestaurantReserva.this, confirmationMessage, Toast.LENGTH_LONG).show();

            // Aqui, você pode adicionar lógica para salvar os dados ou enviar para um servidor
            saveReservation(confirmationMessage);

            // Exibir mensagem de confirmação
            Toast.makeText(RestaurantReserva.this, confirmationMessage, Toast.LENGTH_LONG).show();
        });

    }
}**/

