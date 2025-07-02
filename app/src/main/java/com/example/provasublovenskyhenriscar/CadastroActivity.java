package com.example.provasublovenskyhenriscar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CadastroActivity extends AppCompatActivity {

    private EditText etNome, etDosagem, etHorario;
    private Button btnSalvar, btnVerLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        setTitle("Cadastro de Medicamento");

        etNome = findViewById(R.id.etNome);
        etDosagem = findViewById(R.id.etDosagem);
        etHorario = findViewById(R.id.etHorario);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnVerLista = findViewById(R.id.btnVerLista);

        btnSalvar.setOnClickListener(v -> salvarMedicamento());

        btnVerLista.setOnClickListener(v -> {
            Intent intent = new Intent(CadastroActivity.this, ListaMedicamentosActivity.class);
            startActivity(intent);
        });
    }

    private void salvarMedicamento() {
        String nome = etNome.getText().toString().trim();
        String dosagem = etDosagem.getText().toString().trim();
        String horario = etHorario.getText().toString().trim();

        if (nome.isEmpty() || dosagem.isEmpty() || horario.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences prefs = getSharedPreferences("medicamentos", Context.MODE_PRIVATE);
        String medicamentosStr = prefs.getString("lista", "[]");

        try {
            JSONArray arr = new JSONArray(medicamentosStr);

            JSONObject obj = new JSONObject();
            obj.put("nome", nome);
            obj.put("dosagem", dosagem);
            obj.put("horario", horario);
            obj.put("tomado", false);

            arr.put(obj);

            prefs.edit().putString("lista", arr.toString()).apply();

            Toast.makeText(this, "Medicamento salvo com sucesso!", Toast.LENGTH_SHORT).show();

            // Limpa os campos após salvar
            etNome.setText("");
            etDosagem.setText("");
            etHorario.setText("");
            etNome.requestFocus();

        } catch (JSONException e) {
            Toast.makeText(this, "Erro ao salvar. Tente novamente!", Toast.LENGTH_SHORT).show();
        }
    }
}