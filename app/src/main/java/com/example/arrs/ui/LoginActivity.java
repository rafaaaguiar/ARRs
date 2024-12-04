package com.example.arrs.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.arrs.MainActivity;
import com.example.arrs.R;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputSenha;
    private Button btnLogin, btnCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Verifica se o usuário já está logado
        SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        boolean loggedIn = preferences.getBoolean("loggedIn", false);

        if (loggedIn) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Fecha a tela de login
        }


        inputEmail = findViewById(R.id.inputEmail);
        inputSenha = findViewById(R.id.inputSenha);
        btnLogin = findViewById(R.id.btnLogin);
        btnCadastro = findViewById(R.id.btnCadastro);


        //botão de login
        btnLogin.setOnClickListener(v -> {
            String email = inputEmail.getText().toString();
            String senha = inputSenha.getText().toString();


            if (email.equals("admin@admin.com") && senha.equals("1234")) {
                // Login bem-sucedido, vai para a MainActivity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Fecha a tela de login
            } else {

                Toast.makeText(LoginActivity.this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
            }
        });

        btnCadastro.setOnClickListener(v -> {

            Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
            startActivity(intent);
        });

    }
}
