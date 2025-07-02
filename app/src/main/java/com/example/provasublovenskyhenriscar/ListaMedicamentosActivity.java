package com.example.provasublovenskyhenriscar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaMedicamentosActivity extends AppCompatActivity {
    private ArrayList<Medicamento> medicamentos;
    private MedicamentoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicamento);

        RecyclerView rvMedicamentos = findViewById(R.id.rvMedicamentos);
        rvMedicamentos.setLayoutManager(new LinearLayoutManager(this));

        medicamentos = carregarMedicamentos();
        adapter = new MedicamentoAdapter(this, medicamentos);
        rvMedicamentos.setAdapter(adapter);

        Button btnVoltarCadastro = findViewById(R.id.btnVoltarCadastro);
        btnVoltarCadastro.setOnClickListener(v -> {
            startActivity(new Intent(this, CadastroActivity.class));
            finish();
        });
    }

    private ArrayList<Medicamento> carregarMedicamentos() {
        ArrayList<Medicamento> lista = new ArrayList<>();
        SharedPreferences prefs = getSharedPreferences("medicamentos", MODE_PRIVATE);
        String medicamentosStr = prefs.getString("lista", "[]");
        try {
            JSONArray arr = new JSONArray(medicamentosStr);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                String nome = obj.optString("nome", "");
                String dosagem = obj.optString("dosagem", "");
                String horario = obj.optString("horario", "");
                boolean tomado = obj.optBoolean("tomado", false);
                lista.add(new Medicamento(nome, dosagem, horario, tomado));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;
    }
}