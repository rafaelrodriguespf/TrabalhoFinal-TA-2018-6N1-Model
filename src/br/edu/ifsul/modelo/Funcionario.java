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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author Rafael
 */
@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_funcionario", sequenceName = "seq_funcionario_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_funcionario",strategy = GenerationType.SEQUENCE)
    private Integer id;  
    
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser em branco")
    @Length(max = 40, message = "O nome não pode ter mais que {max} caracteres")
    @Column(name = "nome",length = 40, nullable = false)
    private String nome;
    
    @NotNull(message = "O email do funcionário não pode ser nulo")
    @NotBlank(message = "O email do funcionário não pode ser em branco")
    @Length(max = 80, message = "O email do funcionário não pode ter mais que {max} caracteres")        
    @Column(name = "email", length = 80, nullable = false, unique = true)
    private String email;
    
    @NotNull(message = "O Password não pode ser nulo")
    @NotBlank(message = "O Password não pode ser em branco")
    @Length(max = 20, message = "O Password não pode ter mais que {max} caracteres")    
    @Column(name = "password", length = 20, nullable = false) 
    private String password;
    
    @CPF
    @NotNull(message = "O CPF não pode ser nulo")
    @NotBlank(message = "O CPF não pode ser em branco")
    @Length(max = 11, message = "O CPF não pode ter mais que {max} caracteres")    
    @Column(name = "cpf", length = 11, nullable = false, unique =true) 
    private String cpf;
    
    @Temporal(TemporalType.DATE)
    @NotNull(message = "O nascimento não pode ser nulo")    
    @Column(name = "nascimento",  nullable = false)    
    private Calendar nascimento;
    
    @ManyToMany
    @JoinTable(name = "privilegios",
            joinColumns
            = @JoinColumn(name = "funcionario", referencedColumnName = "email", nullable = false),
            inverseJoinColumns
            = @JoinColumn(name = "privilegio", referencedColumnName = "tipo", nullable = false),
            uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_privilegios",
                        columnNames = {"funcionario", "privilegio"})})
    
    private List<Privilegio> privilegios = new ArrayList<>();

    public Funcionario() {
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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the nascimento
     */
    public Calendar getNascimento() {
        return nascimento;
    }

    /**
     * @param nascimento the nascimento to set
     */
    public void setNascimento(Calendar nascimento) {
        this.nascimento = nascimento;
    }

    /**
     * @return the privilegios
     */
    public List<Privilegio> getPrivilegios() {
        return privilegios;
    }

    /**
     * @param privilegios the privilegios to set
     */
    public void setPrivilegios(List<Privilegio> privilegios) {
        this.privilegios = privilegios;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Funcionario other = (Funcionario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
