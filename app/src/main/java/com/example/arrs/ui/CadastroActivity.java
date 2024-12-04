package com.example.arrs.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.arrs.R;

public class CadastroActivity extends AppCompatActivity {

    private EditText inputNovoEmail, inputNovaSenha;
    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        inputNovoEmail = findViewById(R.id.inputNovoEmail);
        inputNovaSenha = findViewById(R.id.inputNovaSenha);
        btnRegistrar = findViewById(R.id.btnRegistrar);


        btnRegistrar.setOnClickListener(v -> {
            String novoEmail = inputNovoEmail.getText().toString();
            String novaSenha = inputNovaSenha.getText().toString();

            if (!novoEmail.isEmpty() && !novaSenha.isEmpty()) {

                SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("email", novoEmail);
                editor.putString("senha", novaSenha);
                editor.apply();

                Toast.makeText(CadastroActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(CadastroActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
