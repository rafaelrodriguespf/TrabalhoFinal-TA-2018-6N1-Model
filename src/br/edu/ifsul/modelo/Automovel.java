/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Rafael
 */
@Entity
@Table(name = "automovel")
public class Automovel implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_automovel", sequenceName = "seq_automovel_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_automovel",strategy = GenerationType.SEQUENCE)
    private Integer id; 
     
    @NotNull(message = "A Kilometragem não pode ser nulo")
    @NotBlank(message = "A Kilometragem não pode ser em branco")
    @Length(max = 50, message = "A Kilometragem não pode ter mais que {max} caracteres")
    @Column(name = "quilometragem",length = 50, nullable = false) 
    private String quilometragem;
    
    @Temporal(TemporalType.DATE)
    @NotNull(message = "O Ano / Modelo não pode ser nulo")    
    @Column(name = "anoModelo",  nullable = false)    
    private Calendar anoModelo;
    
    @NotNull(message = "A Descrição não pode ser nulo")
    @NotBlank(message = "A Descrição não pode ser em branco")
    @Length(max = 50, message = "A Descrição não pode ter mais que {max} caracteres")
    @Column(name = "descricao",length = 50, nullable = false) 
    private String descricao;
    
    @NotNull(message = "A Cor não pode ser nulo")
    @NotBlank(message = "A Cor não pode ser em branco")
    @Length(max = 50, message = "A Cor não pode ter mais que {max} caracteres")
    @Column(name = "cor",length = 50, nullable = false) 
    private String cor;
    
    @NotNull(message = "O Número de portas não pode ser nulo")
    @Column(name = "portas", nullable = false) 
    private Integer portas;
    
    @NotNull(message = "O Motor não pode ser nulo")
    @Column(name = "motor", nullable = false, columnDefinition ="decimal(5,2)") 
    private Double motor;
    
    @NotNull(message = "O Valor não pode ser nulo")
    @Column(name = "valor", nullable = false, columnDefinition ="decimal(10,2)") 
    private Double valor;
    
    @NotNull(message = "A Placa não pode ser nulo")
    @NotBlank(message = "A Placa não pode ser em branco")
    @Length(max = 8, message = "A Placa não pode ter mais que {max} caracteres")
    @Column(name = "placa",length = 8, nullable = false) 
    private String placa;
    
    @NotNull(message = "O Funcionário não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "funcionario", referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_funcionario_id"))
    private Funcionario funcionario;
    
    @NotNull(message = "O Combustível não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "combustivel", referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_combustivel_id"))
    private Combustivel combustivel;
    
    
    @NotNull(message = "O Modelo não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "modelo", referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_modelo_id"))
    private Modelo modelo;
    
    
     @OneToMany(mappedBy = "automovel", cascade = CascadeType.ALL, 
            orphanRemoval = true, fetch=FetchType.EAGER)
     private List<Foto> fotos = new ArrayList<>();
     

    public Automovel() {
    }
    
    
     public void adicionarFoto(Foto obj){
        obj.setAutomovel(this);
        this.fotos.add(obj);
    }
    
    public void removerFoto(int index){
        this.fotos.remove(index);
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the kilometragem
     */
    public String getQuilometragem() {
        return quilometragem;
    }

    /**
     * @param kilometragem the kilometragem to set
     */
    public void setQuilometragem(String kilometragem) {
        this.quilometragem = kilometragem;
    }

    /**
     * @return the anoModelo
     */
    public Calendar getAnoModelo() {
        return anoModelo;
    }

    /**
     * @param anoModelo the anoModelo to set
     */
    public void setAnoModelo(Calendar anoModelo) {
        this.anoModelo = anoModelo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

    /**
     * @return the portas
     */
    public Integer getPortas() {
        return portas;
    }

    /**
     * @param portas the portas to set
     */
    public void setPortas(Integer portas) {
        this.portas = portas;
    }

    /**
     * @return the motor
     */
    public Double getMotor() {
        return motor;
    }

    /**
     * @param motor the motor to set
     */
    public void setMotor(Double motor) {
        this.motor = motor;
    }

    /**
     * @return the valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the funcionario
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * @param funcionario the funcionario to set
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * @return the combustivel
     */
    public Combustivel getCombustivel() {
        return combustivel;
    }

    /**
     * @param combustivel the combustivel to set
     */
    public void setCombustivel(Combustivel combustivel) {
        this.combustivel = combustivel;
    }

    /**
     * @return the modelo
     */
    public Modelo getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.getId());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Automovel other = (Automovel) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the fotos
     */
    public List<Foto> getFotos() {
        return fotos;
    }

    /**
     * @param fotos the fotos to set
     */
    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }
    
    
    
    
    
    
    
    
}
