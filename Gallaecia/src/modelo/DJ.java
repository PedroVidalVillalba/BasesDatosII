package modelo;

import java.time.LocalDate;
import java.util.Objects;

public class DJ {
    private String dni;
    private String nome;
    private String rua;
    private Integer numero;
    private Integer cp;
    private String localidade;
    private Float salario;
    private String telefono;
    private LocalDate dataInicio;
    private LocalDate dataNacemento;
    private String formacion;
    private SistemaDeAudio sistemaDeAudio;

    public DJ(String dni, String nome, String rua, Integer numero, Integer cp, String localidade, Float salario, String telefono, LocalDate dataInicio, LocalDate dataNacemento, String formacion, SistemaDeAudio sistemaDeAudio) {
        if(dni!=null){
            this.dni = dni;
        }
        if(nome!=null){
            this.nome = nome;
        }
        if(rua!=null){
            this.rua = rua;
        }
        if(numero!=null){
            this.numero = numero;
        }
        if(cp!=null){
            this.cp = cp;
        }
        if(localidade!= null){
            this.localidade = localidade;
        }
        if(salario!=null){
            this.salario = salario;
        }
        if(telefono!=null){
            this.telefono = telefono;
        }
        if(dataInicio!=null){
            this.dataInicio = dataInicio;
        }
        if(dataNacemento!=null){
            this.dataNacemento = dataNacemento;
        }
        if(formacion!=null){
            this.formacion = formacion;
        }
        if(sistemaDeAudio!=null){
            this.sistemaDeAudio = sistemaDeAudio;
        }
    }

    public String getDni() {
        return dni;
    }

    public String getNome() {
        return nome;
    }

    public String getRua() {
        return rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public Integer getCp() {
        return cp;
    }

    public String getLocalidade() {
        return localidade;
    }

    public Float getSalario() {
        return salario;
    }

    public String getTelefono(){
        return telefono;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataNacemento() {
        return dataNacemento;
    }

    public String getFormacion() {
        return formacion;
    }

    public SistemaDeAudio getSistemaDeAudio() {
        return sistemaDeAudio;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataNacemento(LocalDate dataNacemento) {
        this.dataNacemento = dataNacemento;
    }

    public void setFormacion(String formacion) {
        this.formacion = formacion;
    }

    public void setSistemaDeAudio(SistemaDeAudio sistemaDeAudio) {
        this.sistemaDeAudio = sistemaDeAudio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DJ)) return false;
        DJ dj = (DJ) o;
        return dni.equals(dj.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}
