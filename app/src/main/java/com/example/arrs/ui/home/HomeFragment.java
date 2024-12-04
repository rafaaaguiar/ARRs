/**package com.example.arrs.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.arrs.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}**/

package com.example.arrs.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.arrs.R;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    private MapView mapView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Inicializa o MapView
        mapView = root.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Define uma localização fictícia (latitude e longitude do restaurante)
        LatLng restaurantLocation = new LatLng(-23.585644365854087, -46.62646959600341);
        googleMap.addMarker(new MarkerOptions().position(restaurantLocation).title("Matsuya Aclimação"));
        LatLng restaurantLocation2 = new LatLng(-23.500912684928668, -46.73355913023436);
        googleMap.addMarker(new MarkerOptions().position(restaurantLocation2).title("Pizzaria Tucuna"));
        LatLng restaurantLocation3 = new LatLng(-23.624678396893106, -46.60160856213655);
        googleMap.addMarker(new MarkerOptions().position(restaurantLocation3).title("Gran Itália"));
        LatLng restaurantLocation4 = new LatLng(-23.582133014564924, -46.68090433145299);
        googleMap.addMarker(new MarkerOptions().position(restaurantLocation4).title("Le Bife"));
        LatLng restaurantLocation5 = new LatLng(-23.58102031544115, -46.62524534425651);
        googleMap.addMarker(new MarkerOptions().position(restaurantLocation5).title("Zio Vitto Pizza e Pasta"));
        LatLng restaurantLocation6 = new LatLng(-23.57726247446499, -46.62884034765618);
        googleMap.addMarker(new MarkerOptions().position(restaurantLocation6).title("Matriz Bar e Chopperia"));
        LatLng restaurantLocation7 = new LatLng(-23.51859415977322, -46.68218013090856);
        googleMap.addMarker(new MarkerOptions().position(restaurantLocation7).title("Reserva Rooftop"));
        LatLng restaurantLocation8 = new LatLng(-23.57726247446499, -46.62884034765618);
        googleMap.addMarker(new MarkerOptions().position(restaurantLocation8).title("Toro Negro Steakhouse"));
        LatLng restaurantLocation9 = new LatLng(-23.56022901728999, -46.6701349);
        googleMap.addMarker(new MarkerOptions().position(restaurantLocation9).title("Miró Gastronomia"));
        LatLng restaurantLocation10 = new LatLng(-23.59074990276226, -46.69000256213787);
        googleMap.addMarker(new MarkerOptions().position(restaurantLocation10).title("Spot Restaurante"));
        LatLng restaurantLocation11 = new LatLng(-23.563819363026266, -46.656031619810165);
        googleMap.addMarker(new MarkerOptions().position(restaurantLocation11).title("Seen - Restaurant & Bar"));
        LatLng restaurantLocation12 = new LatLng(-23.545325466957323, -46.64368526029008);
        googleMap.addMarker(new MarkerOptions().position(restaurantLocation12).title("Terraço Itália"));
        LatLng restaurantLocation13 = new LatLng(-23.586297058351676, -46.67640206213805);
        googleMap.addMarker(new MarkerOptions().position(restaurantLocation13).title("Imakay"));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(restaurantLocation, 15)); // Zoom na localização
    }

    // Ciclo de vida do MapView
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
