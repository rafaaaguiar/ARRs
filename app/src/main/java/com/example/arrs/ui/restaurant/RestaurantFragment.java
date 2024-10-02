package com.example.arrs.ui.restaurant;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.arrs.databinding.FragmentRestaurantBinding;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RestaurantFragment extends Fragment {

    private FragmentRestaurantBinding binding;
    private ArrayAdapter<String> adapter;
    private HashMap<String, LatLng> restaurantLocations;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RestaurantViewModel restaurantViewModel =
                new ViewModelProvider(this).get(RestaurantViewModel.class);

        binding = FragmentRestaurantBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        /**final TextView textView = binding.textRestaurant;
        restaurantViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);**/

        List<String> restaurantes = new ArrayList<>(Arrays.asList("Matsuya Aclimação",
                "Pizzaria Tucuna", "Gran Itália","Le Bife","Zio Vitto Pizza e Pasta",
                "Matriz Bar e Chopperia"));

        restaurantLocations = new HashMap<>();
        restaurantLocations.put("Matsuya Aclimação", new LatLng(-23.585644365854087, -46.62646959600341));
        restaurantLocations.put("Pizzaria Tucuna", new LatLng(-23.500912684928668, -46.73355913023436));
        restaurantLocations.put("Gran Itália", new LatLng(-23.624678396893106, -46.60160856213655));
        restaurantLocations.put("Le Bife", new LatLng(-23.582133014564924, -46.68090433145299));
        restaurantLocations.put("Zio Vitto Pizza e Pasta", new LatLng(-23.58102031544115, -46.62524534425651));
        restaurantLocations.put("Matriz Bar e Chopperia", new LatLng(-23.57726247446499, -46.62884034765618));



        ListView listView = binding.listaRestaurantes;
        SearchView searchView = binding.searchRestaurant;

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                restaurantes
        );

        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

       listView.setOnItemClickListener((parent, view, position, id) -> {
                   String selectedRestaurant = adapter.getItem(position);
                   LatLng location = restaurantLocations.get(selectedRestaurant);
                   if (location != null) {
                       Intent intent = new Intent(getActivity(), RestaurantDetalhes.class);
                       intent.putExtra("restaurant_name", selectedRestaurant);
                       intent.putExtra("latitude", location.latitude);
                       intent.putExtra("longitude", location.longitude);
                       startActivity(intent);

                   }
               });

        /**listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedRestaurant = adapter.getItem(position);
            LatLng location = restaurantLocations.get(selectedRestaurant);

            if (location != null) {
                Uri gmmIntentUri = Uri.parse("geo:" + location.latitude + "," + location.longitude +
                        "?q=" + Uri.encode(selectedRestaurant));

                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });**/

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}