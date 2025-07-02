package com.example.provasublovenskyhenriscar;

public class Medicamento {
    private String nome;
    private String dosagem;
    private String horario;
    private boolean tomado;

    public Medicamento(String nome, String dosagem, String horario, boolean tomado) {
        this.nome = nome;
        this.dosagem = dosagem;
        this.horario = horario;
        this.tomado = tomado;
    }

    // Novo construtor vazio (necessário para edição e para facilitar uso em listas)
    public Medicamento() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDosagem() { return dosagem; }
    public void setDosagem(String dosagem) { this.dosagem = dosagem; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public boolean isTomado() { return tomado; }
    public void setTomado(boolean tomado) { this.tomado = tomado; }

    // Método utilitário: converte para JSON
    public org.json.JSONObject toJson() {
        org.json.JSONObject obj = new org.json.JSONObject();
        try {
            obj.put("nome", nome);
            obj.put("dosagem", dosagem);
            obj.put("horario", horario);
            obj.put("tomado", tomado);
        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    // Método utilitário: cria Medicamento a partir de JSONObject
    public static Medicamento fromJson(org.json.JSONObject obj) {
        Medicamento m = new Medicamento();
        m.setNome(obj.optString("nome", ""));
        m.setDosagem(obj.optString("dosagem", ""));
        m.setHorario(obj.optString("horario", ""));
        m.setTomado(obj.optBoolean("tomado", false));
        return m;
    }
}