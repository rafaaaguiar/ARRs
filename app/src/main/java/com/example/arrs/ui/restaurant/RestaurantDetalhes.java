package com.example.arrs.ui.restaurant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.arrs.R;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

public class RestaurantDetalhes extends AppCompatActivity {

    private LatLng restaurantLocation;
    private String restaurantName;
    private HashMap<String, LatLng> restaurantLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhes_restaurante);


        restaurantLocations = new HashMap<>();
        restaurantLocations.put("Matsuya Aclimação",
                new LatLng(-23.585644365854087, -46.62646959600341));
        restaurantLocations.put("Pizzaria Tucuna",
                new LatLng(-23.500912684928668, -46.73355913023436));
        restaurantLocations.put("Gran Itália",
                new LatLng(-23.624678396893106, -46.60160856213655));
        restaurantLocations.put("Le Bife",
                new LatLng(-23.582133014564924, -46.68090433145299));
        restaurantLocations.put("Zio Vitto Pizza e Pasta",
                new LatLng(-23.58102031544115, -46.62524534425651));
        restaurantLocations.put("Matriz Bar e Chopperia",
                new LatLng(-23.57726247446499, -46.62884034765618));



        // Receber os dados do restaurante via Intent
        Intent intent = getIntent();
        restaurantName = intent.getStringExtra("restaurant_name");

        restaurantLocation = restaurantLocations.get(restaurantName);

        if (restaurantLocation == null){
            finish();
            return;
        }

        // Exibir o HTML local do restaurante
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        WebView webView = findViewById(R.id.restaurant_details_webview);
        webView.setWebViewClient(new WebViewClient());  // Permite carregar o conteúdo na WebView
        loadRestaurantDetails(webView, restaurantName); // Carregar o HTML de acordo com o restaurante

        // Configurar o botão para abrir o Google Maps
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button btnViewOnMap = findViewById(R.id.btn_view_on_map);


        btnViewOnMap.setOnClickListener(v -> {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + restaurantLocation.latitude + ","
                    + restaurantLocation.longitude +
                    "(" + Uri.encode(restaurantName) + ")");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            }
        });

        // Configurar o botão para fazer uma reserva
        Button btnMakeReservation = findViewById(R.id.btn_make_reservation);
        btnMakeReservation.setOnClickListener(v -> {
            Intent reservationIntent = new Intent(this, RestaurantReserva.class);
            reservationIntent.putExtra("restaurant_name", restaurantName);
            startActivity(reservationIntent);
        });
    }

    // Método para carregar o HTML de acordo com o restaurante selecionado
    private void loadRestaurantDetails(WebView webView, String restaurantName) {
        // Mapeamento do nome do restaurante para o arquivo HTML correspondente
        String fileName = "";
        if (restaurantName.equals("La Brasa Stakehouse")) {
            fileName = "HTML/teste.html";
        } else if (restaurantName.equals("Pizzaria Tucuna")) {
            fileName = "HTML/pizzaria_tucuna.html";
        } else if (restaurantName.equals("Gran Itália")) {
            fileName = "HTML/APS_DSW2.html";
        }
        // Adicione mais mapeamentos de restaurante -> arquivo HTML conforme necessário

        // Carregar o arquivo HTML da pasta assets
        if (!fileName.isEmpty()) {
            webView.loadUrl("file:///android_asset/restaurantes/" + fileName);
        }
    }
}
