/*
 * This file is generated by jOOQ.
 */
package model.generated.tables.records;


import model.generated.tables.PiattoTables;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PiattoRecord extends UpdatableRecordImpl<PiattoRecord> implements Record3<String, Double, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>PIATTO.NOME</code>.
     */
    public void setNome(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>PIATTO.NOME</code>.
     */
    public String getNome() {
        return (String) get(0);
    }

    /**
     * Setter for <code>PIATTO.PREZZO</code>.
     */
    public void setPrezzo(Double value) {
        set(1, value);
    }

    /**
     * Getter for <code>PIATTO.PREZZO</code>.
     */
    public Double getPrezzo() {
        return (Double) get(1);
    }

    /**
     * Setter for <code>PIATTO.COMPONENTE</code>.
     */
    public void setComponente(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>PIATTO.COMPONENTE</code>.
     */
    public String getComponente() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<String, String> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, Double, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<String, Double, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return PiattoTables.PIATTO.NOME;
    }

    @Override
    public Field<Double> field2() {
        return PiattoTables.PIATTO.PREZZO;
    }

    @Override
    public Field<String> field3() {
        return PiattoTables.PIATTO.COMPONENTE;
    }

    @Override
    public String component1() {
        return getNome();
    }

    @Override
    public Double component2() {
        return getPrezzo();
    }

    @Override
    public String component3() {
        return getComponente();
    }

    @Override
    public String value1() {
        return getNome();
    }

    @Override
    public Double value2() {
        return getPrezzo();
    }

    @Override
    public String value3() {
        return getComponente();
    }

    @Override
    public PiattoRecord value1(String value) {
        setNome(value);
        return this;
    }

    @Override
    public PiattoRecord value2(Double value) {
        setPrezzo(value);
        return this;
    }

    @Override
    public PiattoRecord value3(String value) {
        setComponente(value);
        return this;
    }

    @Override
    public PiattoRecord values(String value1, Double value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PiattoRecord
     */
    public PiattoRecord() {
        super(PiattoTables.PIATTO);
    }

    /**
     * Create a detached, initialised PiattoRecord
     */
    public PiattoRecord(String nome, Double prezzo, String componente) {
        super(PiattoTables.PIATTO);

        setNome(nome);
        setPrezzo(prezzo);
        setComponente(componente);
        resetChangedOnNotNull();
    }
}
