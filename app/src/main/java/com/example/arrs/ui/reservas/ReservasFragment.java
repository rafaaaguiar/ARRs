package com.example.arrs.ui.reservas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arrs.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ReservasFragment extends Fragment {

    private static final String RESERVATIONS_PREF = "reservations_pref";
    private static final String RESERVATIONS_KEY = "reservations_key";

    private TextView textViewReservations;
    private RecyclerView recyclerView;
    private ReservationAdapter reservationAdapter;
    private TextView textViewNoReservations;
    private ArrayList<String> reservations;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_reservas, container, false);

        recyclerView = root.findViewById(R.id.recyclerViewReservations);
        textViewNoReservations = root.findViewById(R.id.textViewNoReservations);

        // Carregar os dados da reserva de SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(RESERVATIONS_PREF,
                getContext().MODE_PRIVATE);

        Set<String> reservationSet = sharedPreferences.getStringSet(RESERVATIONS_KEY, new HashSet<>());


        /**String reservationDetails = sharedPreferences.getString(RESERVATIONS_KEY,
                "Nenhuma reserva feita.");**/

        reservations = new ArrayList<>(reservationSet);
       /** if (reservationDetails != null) {
            reservations.add(reservationDetails);
        }**/

        // Configurar o RecyclerView para exibir as reservas

        reservationAdapter = new ReservationAdapter(reservations);
        recyclerView.setAdapter(reservationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Mostrar ou ocultar a mensagem de "Nenhuma reserva"
        if (reservations.isEmpty()) {
            textViewNoReservations.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            textViewNoReservations.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

        return root;
    }
}
