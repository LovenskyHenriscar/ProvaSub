package com.example.provasublovenskyhenriscar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MedicamentoAdapter extends RecyclerView.Adapter<MedicamentoAdapter.ViewHolder> {
    private ArrayList<Medicamento> medicamentos;
    private Context context;

    public MedicamentoAdapter(Context context, ArrayList<Medicamento> medicamentos) {
        this.context = context;
        this.medicamentos = medicamentos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_medicamento, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Medicamento medicamento = medicamentos.get(position);

        holder.tvNomeMedicamento.setText("Nome: " + medicamento.getNome());
        holder.tvDosagem.setText("Dosagem: " + medicamento.getDosagem());
        holder.tvHorario.setText("Horário: " + medicamento.getHorario());

        holder.cbTomado.setOnCheckedChangeListener(null);
        holder.cbTomado.setChecked(medicamento.isTomado());
        holder.cbTomado.setOnCheckedChangeListener((buttonView, isChecked) -> {
            medicamento.setTomado(isChecked);
            salvarMedicamentos();
        });

        holder.btnExcluir.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Excluir")
                    .setMessage("Deseja realmente excluir este medicamento?")
                    .setPositiveButton("Sim", (dialog, which) -> {
                        medicamentos.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, medicamentos.size());
                        salvarMedicamentos();
                        Toast.makeText(context, "Medicamento excluído!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Não", null)
                    .show();
        });

        holder.btnEditar.setOnClickListener(v -> {
            Intent intent = new Intent(context, CadastroActivity.class);
            intent.putExtra("editar_posicao", position);
            intent.putExtra("nome", medicamento.getNome());
            intent.putExtra("dosagem", medicamento.getDosagem());
            intent.putExtra("horario", medicamento.getHorario());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return medicamentos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeMedicamento, tvDosagem, tvHorario;
        CheckBox cbTomado;
        Button btnEditar, btnExcluir;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomeMedicamento = itemView.findViewById(R.id.tvNomeMedicamento);
            tvDosagem = itemView.findViewById(R.id.tvDosagem);
            tvHorario = itemView.findViewById(R.id.tvHorario);
            cbTomado = itemView.findViewById(R.id.cbTomado);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnExcluir = itemView.findViewById(R.id.btnExcluir);
        }
    }

    private void salvarMedicamentos() {
        SharedPreferences prefs = context.getSharedPreferences("medicamentos", Context.MODE_PRIVATE);
        JSONArray arr = new JSONArray();
        for (Medicamento m : medicamentos) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("nome", m.getNome());
                obj.put("dosagem", m.getDosagem());
                obj.put("horario", m.getHorario());
                obj.put("tomado", m.isTomado());
                arr.put(obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        prefs.edit().putString("lista", arr.toString()).apply();
    }
}